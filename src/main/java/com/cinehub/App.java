package com.cinehub;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class App {

    @GetMapping("/")
    public String home() {
        return "Bienvenue sur l'API CineHub!";
    }

}
