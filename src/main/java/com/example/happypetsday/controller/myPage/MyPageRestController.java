package com.example.happypetsday.controller.myPage;

import com.example.happypetsday.service.myPage.MypageService;
import com.example.happypetsday.service.pet.PetService;
import com.example.happypetsday.vo.PetVo;
import com.example.happypetsday.vo.ResVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPages/*")
@Slf4j
public class MyPageRestController {
    private final PetService petService;
    private final MypageService mypageService;
    private final ResVo resVo;
//    마이펫 수정 창 띄우기
    @GetMapping("/myPet/editPet/{petNumber}")
    public PetVo getMyPetInfo(@PathVariable("petNumber") Long petNumber){
        return petService.findPetByPetNumber(petNumber);
    }

//    예약 취소 처리
    @GetMapping("/reservationList")
    public void updateResStatus(@RequestParam("reservationStatus") String reservationStatus, @RequestParam("reservationNumber") Long reservationNumber) {
        resVo.setReservationStatus(reservationStatus);
        resVo.setReservationNumber(reservationNumber);
        resVo.setReservationStatus("취소");
        mypageService.modify(resVo);
    }


}
