package ru.otus.AleksandrYurkov.telegramBot.controllers;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.AleksandrYurkov.telegramBot.dto.CustomerDTO;
import ru.otus.AleksandrYurkov.telegramBot.entity.Customer;
import ru.otus.AleksandrYurkov.telegramBot.service.CustomerService;

import java.util.List;
import java.util.function.Function;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;
    public Logger log = LoggerFactory.getLogger(CustomerController.class);

    private static final Function<Customer, CustomerDTO> CUSTOMER_DTO = i -> new CustomerDTO(
            i.getLastname(), i.getFirstname(), i.getTelephone(), i.getTelegramId());

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        //TODO добавить проверку на токен (телеграм id) @RequestHeader(name = "telegram-id") или как то так..
        return customerService.getCustomerById(id).map(CUSTOMER_DTO).orElse(null);
    }

    @PostMapping("/register")
    public HttpStatus saveCustomer(@RequestBody CustomerDTO customerDTO) {
        customerService.validateCustomer(customerDTO);
        customerService.createCustomer(new Customer(customerDTO));
        return  HttpStatus.CREATED;
    }

    @GetMapping("/all")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
//дальше должен идти запрос услуг (as profession) либо register возвращать список услуг

}
