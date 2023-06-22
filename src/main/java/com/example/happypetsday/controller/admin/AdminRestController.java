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




}
