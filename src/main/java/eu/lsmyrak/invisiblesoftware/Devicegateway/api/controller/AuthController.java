package eu.lsmyrak.invisiblesoftware.Devicegateway.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.LoginDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.RegisterDto;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return "tokken";
    }

    @PostMapping("/logout")
    public String logout() {
        return "logout";
    }

    @PostMapping("/register")
    public String register(@RequestBody  RegisterDto registerDto) {
        return "register";
    }

    @PostMapping("/unregister")
    public String unregister() {
        return "unregister";
    }
}
