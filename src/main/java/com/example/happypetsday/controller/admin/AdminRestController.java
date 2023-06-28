package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
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

//    @GetMapping("/searchUser")
//    public UserDto userList(){
//        UserDto userDto = new UserDto();
//        userDto.setUserNumber(1L);
//        userDto.setUserId("aaaa");
//
//        return userDto;
//    }
//
//    @GetMapping("/{userNumber}")
//    public UserDto userDetail(@PathVariable("userNumber") Long userNumber){
////        adminService.findUser(userNumber);
//
//        System.out.println(userNumber + "=============================");
//
//        UserDto userDto = new UserDto();
//        userDto.setUserNumber(1L);
//        userDto.setUserId("aaaa");
//
//        return userDto;
//    }

    // 사용자 검색 기능
    @GetMapping("/search")
    public List<UserVo> searchUser(@RequestParam("keyword") String keyword) {

        System.out.println(keyword);

//        검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        Criteria criteria = new Criteria(1, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<UserVo> userList = adminService.searchUser(criteria, keyword);


        return userList;
    }

    // 전체회원수, 일반회원수 조회
    @GetMapping("/usersNumberCount")
    public UserVo usersNumberCount(){
        return adminService.getUsersCount();
    }

}
