package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import com.example.happypetsday.vo.StrollBoardVo;
import com.example.happypetsday.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //    회원 복구
    @PatchMapping("/restore")
    public void userRestore(@RequestBody UserDto userDto){
        userDto.setUserNumber(userDto.getUserNumber());
        adminService.restoreUser(userDto);
    }

    // 사용자 검색 기능(전체회원관리)
    @GetMapping("/search/{page}")
    public Map<String,Object> searchUser(@RequestParam("keyword") String keyword,@PathVariable("page")int page) {


    // 검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        int totalSearch = adminService.getSearchTotal(keyword);
        Criteria criteria = new Criteria(page, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<UserVo> userList = adminService.searchUser(criteria, keyword);
        PageVo pageVo = new PageVo(criteria,totalSearch);
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("pageVo",pageVo);
        searchMap.put("userList",userList);
        searchMap.put("totalSearch", totalSearch);

        return searchMap;
    }


    // 전체회원수, 일반회원수 조회
    @GetMapping("/usersNumberCount")
    public UserVo usersNumberCount(){
        return adminService.getUsersCount();
    }


    // 산책게시판관리 검색 기능
    @GetMapping("/postSearch/{page}")
    public Map<String,Object> searchPost(@RequestParam("keyword") String keyword,@PathVariable("page")int page) {


        // 검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        int searchTotal = adminService.searchNumTitleCount(keyword);
        Criteria criteria = new Criteria(page, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<StrollBoardVo> postList = adminService.searchNumTitle(criteria, keyword);
        PageVo pageVo = new PageVo(criteria,searchTotal);
        Map<String,Object> searchPostMap = new HashMap<>();
        searchPostMap.put("pageVo",pageVo);
        searchPostMap.put("postList",postList);
        searchPostMap.put("searchTotal", searchTotal);

        return searchPostMap;
    }
}
