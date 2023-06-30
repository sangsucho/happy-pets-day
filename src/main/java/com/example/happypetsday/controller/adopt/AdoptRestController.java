package com.example.happypetsday.controller.adopt;

import com.example.happypetsday.dto.AdoptDto;
import com.example.happypetsday.service.adopt.AdoptService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/adopts/*")
public class AdoptRestController {
//    private final AdoptDto adoptDto;
    private final AdoptService adoptService;

    @GetMapping("/getCenterName")
    public AdoptDto getCenterByCenterName(@RequestParam("centerName") String centerName) {
        return adoptService.getCenterByCenterName(centerName);
    }
}
