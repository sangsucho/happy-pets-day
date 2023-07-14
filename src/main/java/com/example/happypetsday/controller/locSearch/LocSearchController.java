package com.example.happypetsday.controller.locSearch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/loc/*")
public class LocSearchController {
    @GetMapping("/list")
    public String list() {
        return "locSearch/medicine";
    }

    @GetMapping("/pharmacy")
    public String pharmacy() {
        return "locSearch/pharmacy";
    }
}
