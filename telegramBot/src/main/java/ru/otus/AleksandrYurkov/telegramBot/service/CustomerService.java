package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.otus.AleksandrYurkov.telegramBot.dto.CustomerDTO;
import ru.otus.AleksandrYurkov.telegramBot.entity.Customer;
import ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler.ValidationExceptionError;
import ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler.ValidationFieldError;
import ru.otus.AleksandrYurkov.telegramBot.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    Logger logger = LoggerFactory.getLogger(CustomerService.class);

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }
    public Optional<Customer> findByTelegramId(Customer customer) {
        return customerRepository.findByTelegramId(customer.getTelegramId(), customer.getTelephone());
    }
    public void createCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void validateCustomer(CustomerDTO customer) {
        List<ValidationFieldError> errors = new ArrayList<>();
        if (customer.firstname().matches("[\\u0400-\\u04FFa-zA-Z]+")) {
            errors.add(new ValidationFieldError("firstname:", "Содержит некорректные символы"));
        }
        if (customer.firstname().isEmpty() || customer.firstname().isBlank()) {
            errors.add(new ValidationFieldError("firstname:", "Не может быть пустым"));
        }
        if (customer.lastname().matches("[\\u0400-\\u04FFa-zA-Z]+")) {
            errors.add(new ValidationFieldError("lastname:", "Содержит некорректные символы"));
        }
        if (customer.lastname().isEmpty() || customer.lastname().isBlank()) {
            errors.add(new ValidationFieldError("lastname:", "Не может быть пустым"));
        }
        if (customer.telephone().matches("[\\d+]+")) {
            errors.add(new ValidationFieldError("telephone:", "Содержит некорректные символы"));
        }
        if (customer.telephone().isEmpty() || customer.telephone().isBlank()) {
            errors.add(new ValidationFieldError("telephone:", "Не может быть пустым"));
        }

        if (!errors.isEmpty()) {
            throw new ValidationExceptionError("Validation_Exception_Error", "Ошибка при заполнении полей", errors);
        }
    }
}
