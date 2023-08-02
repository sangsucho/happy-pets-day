package com.example.happypetsday.controller.myPage;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.myPage.MypageService;
import com.example.happypetsday.service.pet.PetFileService;
import com.example.happypetsday.service.pet.PetService;
import com.example.happypetsday.service.user.UserService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/myPage/*")
public class MyPageController {
    private final UserService userService;
    private final PetService petService;
    private final PetFileService petFileService;
    private final MypageService mypageService;

    @GetMapping("/addPet")
    public String addPet() {
        return "myPage/addPet";
    }

    @PostMapping("/addPet")
    public RedirectView addPet(PetDto petDto, HttpServletRequest req, @RequestParam("petFile") MultipartFile petFile, @Param("direct") String direct) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        petDto.setUserNumber(userNumber);
        if (petDto.getPetKind().equals("direct")) {
            petDto.setPetKind(direct);
        }
        petService.addPet(petDto);
        if (!petFile.isEmpty()) {
            try {
                petFileService.registerAndSaveFile(petFile, petDto.getPetNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new RedirectView("/myPage/myPet");
    }

    @GetMapping("/main")
    public String myPage() {
        return "myPage/myPage";
    }

    @GetMapping("/myPage")
    public String myPage(HttpServletRequest req, Model model) {
        model.addAttribute("userInfo", userService.findUserInfoByUserNumber((Long) req.getSession().getAttribute("userNumber")));
        return "myPage/myPage";
    }

    @PostMapping("/myPage")
    public RedirectView goMypage(String userPassword, HttpServletRequest req, RedirectAttributes redirectAttributes) {
        try {
            if (userPassword.equals(userService.findUserPasswordByUserNumber((Long) req.getSession().getAttribute("userNumber")))) {
                req.getSession().setAttribute("checkPw", "Y");
                return new RedirectView("/myPage/myPage");
            }
        } catch (IllegalArgumentException e) {
            return new RedirectView("/myPage/checkPw");
        }
        return new RedirectView("/myPage/checkPw");
    }

    @PostMapping("/edit")
    public RedirectView editUserInfo(UserDto userDto, HttpServletRequest req) {
        userDto.setUserNumber((Long) req.getSession().getAttribute("userNumber"));
        userService.editUserInfo(userDto);
        return new RedirectView("/myPage/myPage");
    }

    @GetMapping("/myPet")
    public String myPet(HttpServletRequest req, Model model) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        model.addAttribute("petList", petService.findPetInfo(userNumber));
        return "myPage/myPet";
    }

    @PostMapping("/myPet/editPet")
    public RedirectView editMyPet(PetDto petDto, @RequestParam("petFile") MultipartFile petFile, @Param("isPetFile") int isPetFile) {
        if (petFileService.findFile(petDto.getPetNumber()) != null) {
            if(isPetFile == 0){
                if (!petFile.isEmpty()) {
                    try {
                        petFileService.modifyAndSaveFile(petFile, petDto.getPetNumber());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    petFileService.remove(petDto.getPetNumber());
                }
            }
        } else {
            if (!petFile.isEmpty()) {
                try {
                    petFileService.registerAndSaveFile(petFile, petDto.getPetNumber());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        petService.editMyPet(petDto);
        return new RedirectView("/myPage/myPet");
    }

    @GetMapping("/myPet/delete")
    public RedirectView deleteMyPet(@RequestParam("petNumber") Long petNumber) {
        if (petFileService.findFile(petNumber) != null) {
            petFileService.remove(petNumber);
        }
        petService.removeMyPet(petNumber);
        return new RedirectView("/myPage/myPet");
    }

    @GetMapping("/goMyPage")
    public RedirectView goMyPage(HttpServletRequest req) {
        if (req.getSession().getAttribute("checkPw") != null) {
            return new RedirectView("/myPage/myPage");
        }
        return new RedirectView("/myPage/checkPw");
    }

    @GetMapping("/checkPw")
    public String checkPw() {
        return "myPage/checkPw";
    }

    @GetMapping("/checkReservation")
    public String checkReservation(Model model, HttpServletRequest req, Criteria criteria) {
        model.addAttribute("checkRes", mypageService.findResForSitter((Long) req.getSession().getAttribute("userNumber"), criteria));
        model.addAttribute("pageInfo", new PageVo(criteria, mypageService.getTotalResList((Long)req.getSession().getAttribute("userNumber"))));
        return "myPage/reservation-sitter";
    }

    @GetMapping("/reservationList")
    public String reservationList(Model model, HttpServletRequest req, Criteria criteria) {
        model.addAttribute("resList", mypageService.findRes((Long) req.getSession().getAttribute("userNumber"), criteria));
        model.addAttribute("pageInfo", new PageVo(criteria, mypageService.getTotalResList((Long)req.getSession().getAttribute("userNumber"))));
        return "myPage/reservation-user";
    }

    @GetMapping("/strollList")
    public String strollList(Model model, HttpServletRequest req, Criteria criteria) {
        model.addAttribute("boardList", mypageService.viewMypageBoard((Long)req.getSession().getAttribute("userNumber"), criteria));
        model.addAttribute("pageInfo", new PageVo(criteria, mypageService.getTotalList((Long)req.getSession().getAttribute("userNumber"))));
        return "myPage/strollList"; }

}

