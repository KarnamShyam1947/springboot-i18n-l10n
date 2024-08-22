package com.shyam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shyam.dto.ReviewDTO;
import com.shyam.services.LocaleService;
import com.shyam.services.ReviewService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final LocaleService localeService;
    private final ReviewService reviewService;

    @GetMapping("/")
    String home() {
        ReviewDTO review = new ReviewDTO();
        review.setTitle("Hello");
        review.setUsername("shyam");
        review.setDescription("this is some description");

        reviewService.addReview(review);
        
        return "index";
    }
    
    @GetMapping("/current")
    @ResponseBody
    String current() {
        return localeService.getLocale();
    }
    
}
