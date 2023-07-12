package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.SitterReviewDto;
import com.example.happypetsday.service.sitter.SitterReviewService;
import com.example.happypetsday.vo.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/review/*")
public class ReviewRestController {
    private final SitterReviewService sitterReviewService;

    // 리뷰 작성 모달 띄우기
//    @GetMapping("/showModal")
//    public ResVo getResInfo(@PathVariable("resNumber") Long resNumber) {
//        return sitterReviewService.showModalByResNumber(resNumber);
//    }

    // 리뷰 작성
    @PostMapping("/register")
    public void reviewRegister(@RequestBody SitterReviewDto sitterReviewDto, HttpServletRequest req) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        sitterReviewDto.setUserNumber(userNumber);

        sitterReviewService.register(sitterReviewDto);
    }
}
