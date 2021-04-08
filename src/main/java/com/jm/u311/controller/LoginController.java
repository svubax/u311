package com.jm.u311.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping({"/login", "/"})
    public String loginPage() {
        return "login";
    }
}
