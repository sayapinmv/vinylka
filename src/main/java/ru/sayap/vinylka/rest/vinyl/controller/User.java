package ru.sayap.vinylka.rest.vinyl.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class User {

    final String USERID = "777";

    @GetMapping(USERID)
    public String user() {
        return "./"+USERID;
    }
}
