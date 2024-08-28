package org.springboot.restapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

@GetMapping("/auth/oauth2-login")
public String login() {
    return "login";
}

}

