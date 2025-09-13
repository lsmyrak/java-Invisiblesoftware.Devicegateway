package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login() {
        return "tokken";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/unregister")
    public String unregister() {
        return "unregister";
    }
}
