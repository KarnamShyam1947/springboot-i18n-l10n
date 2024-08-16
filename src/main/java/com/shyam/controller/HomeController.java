package com.shyam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shyam.services.LocaleService;

@Controller
public class HomeController {

    private final LocaleService localeService;
    
    public HomeController(LocaleService localeService) {
        this.localeService = localeService;
    }

    @GetMapping("/")
    String home() {
        return "index";
    }
    
    @GetMapping("/current")
    @ResponseBody
    String current() {
        return localeService.getLocale();
    }
    
}
