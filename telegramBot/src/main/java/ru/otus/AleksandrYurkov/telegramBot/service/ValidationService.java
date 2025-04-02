package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Data
public class ValidationService {
    String errorMessage = "Содержит некорректные символы. Повторите ввод!!!";;

    public boolean validateString(String string) {
//        if (!) {
//          errorMessage = "Содержит некорректные символы. Повторите ввод!!!";
//            return true;
//        } else {
            return !string.matches("^[A-Za-zА-Яа-я]+$");
//        }
    }

    public boolean validateTelephone(String string) {
        if (!string.matches("[\\d+]+")) {
            errorMessage = "Содержит некорректные символы. Повторите ввод!!!";
            return true;
        } else {
            return false;
        }
    }


//    public void validateCustomer(CustomerDTO customer) {
//
//        if (customer.firstname().matches("[\\u0400-\\u04FFa-zA-Z]+")) {
//            errors.add(new ValidationFieldError("firstname:", "Содержит некорректные символы"));
//        }
//        if (customer.firstname().isEmpty() || customer.firstname().isBlank()) {
//            errors.add(new ValidationFieldError("firstname:", "Не может быть пустым"));
//        }
//        if (customer.lastname().matches("[\\u0400-\\u04FFa-zA-Z]+")) {
//            errors.add(new ValidationFieldError("lastname:", "Содержит некорректные символы"));
//        }
//        if (customer.lastname().isEmpty() || customer.lastname().isBlank()) {
//            errors.add(new ValidationFieldError("lastname:", "Не может быть пустым"));
//        }
//        if (customer.telephone().matches("[\\d+]+")) {
//            errors.add(new ValidationFieldError("telephone:", "Содержит некорректные символы"));
//        }
//        if (customer.telephone().isEmpty() || customer.telephone().isBlank()) {
//            errors.add(new ValidationFieldError("telephone:", "Не может быть пустым"));
//        }
//
//        if (!errors.isEmpty()) {
//            throw new ValidationExceptionError("Validation_Exception_Error", "Ошибка при заполнении полей", errors);
//        }
//    }
}
