package com.example.happypetsday.controller.main;

import com.example.happypetsday.service.stroll.StrollService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StrollService strollService;

    @GetMapping("/")
    public String main(Model model, HttpServletRequest req) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        model.addAttribute("boardList", strollService.findMainList(userNumber));


        return "main/main";
    }
}
