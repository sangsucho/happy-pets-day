package com.example.happypetsday.controller.adopt;

import com.example.happypetsday.dto.AdoptDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adopt/*")
public class AdoptController {
    @GetMapping("/list")
    public String AdoptionInformation(){
        return "AdoptionInformation/AdoptionInformation";
    }
    @GetMapping("/detail")
    public String detail(){return "AdoptionInformation/adoptDetail";}

    @GetMapping("/api")
    public String apiTest() { return "AdoptionInformation/adoptDetail"; }

    @PostMapping("/detailByMain")
    public String detailByMain(AdoptDto adoptDto, Model model) {
        model.addAttribute("petInfo", adoptDto);
        return "AdoptionInformation/adoptDetail"; }
}

// String return = 해당 html파일로 바로 이동 (model에 데이터를 담는다.)
// RedirectView = 컨트롤러를 타서 가져온 데이터로 다른 작업을 해야하면 이걸 써야한다. (RedirectAttribute에 데이터 담는다)
