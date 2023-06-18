package com.example.happypetsday.controller.recommend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/recommend/*")
public class RecommendController {

    @GetMapping("/main")
    public String recommend(){
        return "recommend/petRecommend";
    }

    @GetMapping("/detail")
    public String recommendDetail(){
        return "recommend/petRecommendDetail";
    }

    @GetMapping("/list")
    public String recommendList(){
        return "recommend/petRecommendList";
    }



}
