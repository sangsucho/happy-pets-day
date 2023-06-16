package com.example.happypetsday.controller.stroll;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stroll/*")
public class StrollController {

    @GetMapping("/view")
    public String boardView(){
        return "strollBoard/strollBoardView";
    }

    @GetMapping("/write")
    public String boardWrite(){
        return "strollBoard/strollBoardWrite";
    }

}
