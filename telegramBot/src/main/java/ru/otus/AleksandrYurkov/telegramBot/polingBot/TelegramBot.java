package ru.otus.AleksandrYurkov.telegramBot.polingBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.otus.AleksandrYurkov.telegramBot.config.BotConfig;
import ru.otus.AleksandrYurkov.telegramBot.dto.OrderDTO;
import ru.otus.AleksandrYurkov.telegramBot.entity.*;
import ru.otus.AleksandrYurkov.telegramBot.service.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
@AllArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    private final BotConfig botConfig;
    private final CustomerService customerService;
    private final MasterService masterService;
    private final MasterProfessionService masterProfessionService;
    private final DateTimeService dateTimeService;
    private final OrdersService ordersService;
    private ValidationService validationService;



    // Хранилище состояний пользователей
    private Map<Long, UserState> userStates = new HashMap<>(); // хз как его сделать бином

    @Override
    public String getBotUsername() {
        return botConfig.getName();
    }

    @Override
    public String getBotToken() {
        return botConfig.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String message = update.getMessage().getText();

            // Получаем или создаем состояние для пользователя
            UserState userState = userStates.getOrDefault(chatId, new UserState());

            switch (userState.getCurrentStep()) {
                case START:
                    if (message.equalsIgnoreCase("/start")) {
                        sendMsg(chatId, "Привет! Давай познакомимся.");
                        sendMsg(chatId, "Пожалуйста, напиши своё имя");
                        userState.customer.setTelegramId(update.getMessage().getChat().getFirstName());
                        userState.setCurrentStep(UserState.Step.NAME);
                        userStates.put(chatId, userState);
                    }
                    break;
                case NAME:
                    if (validationService.validateString(message)) {
                        sendMsg(chatId, validationService.getErrorMessage()); // Ë не работает
                        break;
                    }
                    userState.customer.setFirstname(message);
                    sendMsg(chatId, "Отлично! Теперь напиши свою фамилию");
                    userState.setCurrentStep(UserState.Step.SURNAME);
                    userStates.put(chatId, userState);
                    break;
                case SURNAME:
                    if (validationService.validateString(message)) {
                        sendMsg(chatId, validationService.getErrorMessage());
                        break;
                    }
                    userState.customer.setLastname(message);
                    sendMsg(chatId, "Хорошо, осталось указать телефон");
                    userState.setCurrentStep(UserState.Step.TELEPHONE);
                    userStates.put(chatId, userState);
                    break;
                case TELEPHONE:
                    if (validationService.validateTelephone(message)) {
                        sendMsg(chatId, validationService.getErrorMessage());
                        break;
                    }
                    userState.customer.setTelephone(message);
                    sendMsg(chatId, "Спасибо за информацию!\n" +
                            "Telegram: " + userState.customer.getTelegramId() + "\n" +
                            "Имя: " + userState.customer.getFirstname() + "\n" +
                            "Фамилия: " + userState.customer.getLastname() + "\n" +
                            "Телефон: " + userState.customer.getTelephone());
                    customerService.createCustomer(userState.customer);
                    List<Profession> professions = masterService.getAllProfessions();
                    sendMsg(chatId, "Выберите необходимую услугу по номеру:\n");
                    for (int i = 0; i < professions.size(); i++) {
                        sendMsg(chatId, (i + 1) + " : " + professions.get(i).getName() + "\n");
                    }
                    userState.setCurrentStep(UserState.Step.PROFESSION_NUMBER);
//                    Optional<Customer> tmp = customerService.findByTelegramId(userState.customer);
                    log.info("\n\n\n\n\n\n\n яТуттьь \n");
//                    log.info(tmp.get().toString());
//                    userState.getNewOrders().setCustomer(tmp.get());
                    userState.getNewOrders().setCustomer(userState.customer);
                    // записать в order customer_id
                    break;
                case PROFESSION_NUMBER:
                    int professionSize = masterService.getAllProfessions().size();
                    try {
                        int inputNumber = Integer.parseInt(message);
                        if (inputNumber > 0 && inputNumber <= professionSize) {
                            sendMsg(chatId, "Выберите мастера по номеру:\n");

                            List<Long> mastersId = masterProfessionService.getAllMastersById((long) inputNumber);
                            for (int i = 0; i < mastersId.size(); i++) {
                                Optional<Master> master = masterService.getMasterById(mastersId.get(i));
                                sendMsg(chatId, master.get().getId() + " : " +master.get().getLastname() + " " +
                                        master.get().getFirstname() + "\n"); // выводим фактический id мастера
                            }
                            userState.setCurrentStep(UserState.Step.MASTER_NUMBER);
                            break;
                        } else {
                            sendMsg(chatId, "Некорректный ввод. Введите число из списка профессий\n");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        sendMsg(chatId, "Некорректный ввод. Введите число \n");
                    }
                case MASTER_NUMBER:
//                    получаем айди профессии
                    // можно записать в order customer_id
//                    по нему находим в табл дата свободное время, возвращаем листом
//                    лист выводим и даем выбор клиенту
//                    меняем userState.setCurrentStep.DATE
                    try {
                        int inputNumber = Integer.parseInt(message);
                        Master master = masterService.getMasterById((long) inputNumber).orElse(null);
                        if (master != null) {
                            userState.getNewOrders().setMaster(master);
                        }

                        List<DateTime> dateList = dateTimeService.getAllMastersById((long) inputNumber);
                        for(int i = 0; i < dateList.size(); i++) {
                            sendMsg(chatId, dateList.get(i).getId() + " : " + dateList.get(i).getDate() + "\n");
                        }
                        userState.setCurrentStep(UserState.Step.DATE);
                        break;

                    } catch (NumberFormatException e) {
                        sendMsg(chatId, "Некорректный ввод. Введите число \n");
                    }

                case DATE:
                    int inputNumber = Integer.parseInt(message);
                    DateTime time = dateTimeService.getDateTime((long) inputNumber).orElse(null);
                    if(time != null) {
                        dateTimeService.delete((long) inputNumber);
                        userState.getNewOrders().setDateTime(time.getDate());

                        sendMsg(chatId, userState.getNewOrders().getCustomer().getFirstname()
                                + ", ваша запись подтверждена! \n"
                                + userState.getNewOrders().getDateTime() + "\n" + "К мастеру : "
                                + userState.getNewOrders().getMaster().lastname + " "
                                + userState.getNewOrders().getMaster().firstname);
                        log.info(userState.getNewOrders().toString());
                        ordersService.creatOrders(userState.getNewOrders());
                        userStates.remove(chatId);
                        break;
                    } else {
                        sendMsg(chatId,"Что то пошло не так \n По пробуйте еще раз. Введите команду /start");
                        userState.setCurrentStep(UserState.Step.START);
                        userStates.remove(chatId);
                        break;
                    }

            }
        }
    }

    private void sendMsg(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Data
    // Класс для хранения состояния пользователя
    private static class UserState {
        enum Step {
            START, NAME, SURNAME, TELEPHONE, PROFESSION_NUMBER, MASTER_NUMBER, DATE
        }

        private Customer customer = new Customer();
        private Orders newOrders = new Orders();// мб можно убрать и вынести
        private Step currentStep = Step.START;

    }
}
