package com.example.happypetsday.controller.adopt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/adopt/*")
public class AdoptController {
    @GetMapping("/list")
    public String AdoptionInformation() {
        return "AdoptionInformation/AdoptionInformation";
    }

    @GetMapping("/detail")
    public String detail() {
        return "AdoptionInformation/adoptDetail";
    }

    @GetMapping("/api")
    public String apiTest() {
        return "AdoptionInformation/adoptDetail";
    }

    @GetMapping("/detailByMain")
    public String detailByMain(@RequestParam("petNumber") int petNumber, Model model) {
        model.addAttribute("petNumber", petNumber);
        return "AdoptionInformation/adoptDetail";
    }
}