package ru.otus.AleksandrYurkov.telegramBot.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/")
public class Demo {
    @GetMapping("/hello")
    public String greetings() {
        return "GET: Hello World!";
    }

    @PostMapping("/hello")
    public String postGreetings() {
        return "POST: Hello World!";
    }

    // GET /add?a1=10&b=10
    @GetMapping("/add")
    public int addOperation(
            @RequestParam(name = "a1", defaultValue = "0") Integer a,
            @RequestParam(defaultValue = "0") Integer b
    ) {
        return a + b;
    }

    @GetMapping("/users/{user_id}/documents/{document_id}")
    public String getDocInfo(
            @PathVariable(name = "user_id") Long userId,
            @PathVariable(name = "document_id") Long documentId
    ) {
        return "USER: " + userId + " - DOC: " + documentId + " - VALUE: 1";
    }

    @GetMapping("/injdemo")
    public String injectArgsExample(
            HttpServletRequest request,
            @RequestHeader(name = "Accept") String contentType
    ) {
        return contentType;
    }
}