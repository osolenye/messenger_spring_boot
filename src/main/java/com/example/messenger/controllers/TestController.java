package com.example.messenger.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }
}
