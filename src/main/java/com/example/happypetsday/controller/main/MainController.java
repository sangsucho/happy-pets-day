package com.example.happypetsday.controller.main;

import com.example.happypetsday.service.sitter.SitterService;
import com.example.happypetsday.service.stroll.StrollService;
import com.example.happypetsday.vo.SitterListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StrollService strollService;

    private final SitterService sitterService;

    @GetMapping("/")
    public String main(Model model, HttpServletRequest req) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        model.addAttribute("boardList", strollService.findMainList(userNumber));
        List<SitterListVo> sitterList = sitterService.findBestSitter();
        for(SitterListVo sitter : sitterList){
            String fileName = "/upload/sitter/profileFile/" + sitter.getSitterProfileUploadPath() + "/" + sitter.getSitterProfileUuid() + "_" + sitter.getSitterProfileFileName();
            sitter.setSitterFileFileName(fileName);
        }
        model.addAttribute("sitterList", sitterList);


        return "main/main";
    }
}
