package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usersManage/*")
public class AdminRestController {
    private final AdminService adminService;

    //    회원 삭제
    @PatchMapping("/delete")
    public void userModify(@RequestBody UserDto userDto){
        userDto.setUserNumber(userDto.getUserNumber());
        adminService.modify(userDto);
    }

    //  userId로 검색(전체회원관리)
//    @PostMapping("/board")
//    public String boardWrite(@RequestBody BoardDto boardDto){
//        System.out.println(boardDto);
//        return "success";
//    }

    @GetMapping("/searchUser")
    public UserDto userList(){
        UserDto userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setUserId("aaaa");

        return userDto;
    }

    @GetMapping("/{userNumber}")
    public UserDto userDetail(@PathVariable("userNumber") Long userNumber){
//        adminService.findUser(userNumber);

        System.out.println(userNumber + "=============================");

        UserDto userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setUserId("aaaa");

        return userDto;
    }



}
