package com.example.happypetsday.controller.myPage;

import com.example.happypetsday.service.pet.PetService;
import com.example.happypetsday.vo.PetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/myPage/*")
public class MyPageRestController {
    private final PetService petService;
//    마이펫 수정 창 띄우기
    @GetMapping("/myPet/editPet/{petNumber}")
    public PetVo getMyPetInfo(@PathVariable("petNumber") Long petNumber){
        return petService.findPetByPetNumber(petNumber);
    }

}
