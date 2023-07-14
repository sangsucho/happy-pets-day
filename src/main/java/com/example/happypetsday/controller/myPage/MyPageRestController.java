package com.example.happypetsday.controller.myPage;

import com.example.happypetsday.service.myPage.MypageService;
import com.example.happypetsday.service.pet.PetService;
import com.example.happypetsday.service.sitter.SitterService;
import com.example.happypetsday.vo.PetVo;
import com.example.happypetsday.vo.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPages/*")
@Slf4j
public class MyPageRestController {
    private final PetService petService;
    private final MypageService mypageService;
    private final SitterService sitterService;

    //    마이펫 수정 창 띄우기
    @GetMapping("/myPet/editPet/{petNumber}")
    public PetVo getMyPetInfo(@PathVariable("petNumber") Long petNumber) {
        return petService.findPetByPetNumber(petNumber);
    }

    // 예약 상태 '취소'
    @PatchMapping("/reservationList/cancel")
    public void resCancel(@RequestBody ResVo resVo) {
        mypageService.modify(resVo);
    }

    // 예약 상태 '이용 완료'
    @PatchMapping("/reservationList/completed")
    public void resComplete(@RequestBody ResVo resVo) {
        mypageService.modify(resVo);
    }

    // 펫시터 예약 확인 '거절'
    @PatchMapping("/checkReservation/reject")
    public void reject(@RequestBody ResVo resVo) {
        mypageService.updateForSitter(resVo);
    }

    // 회원번호로 시터번호 가져오기
    @GetMapping("/sitter/profile/view")
    public Long viewSitterProfile(HttpServletRequest req) {
        return sitterService.findSitter((Long) req.getSession().getAttribute("userNumber"));
    }
}
