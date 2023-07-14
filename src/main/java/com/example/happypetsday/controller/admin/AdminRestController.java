package com.example.happypetsday.controller.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.service.sitter.SitterApplyLicenseFileService;
import com.example.happypetsday.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usersManage/*")
public class AdminRestController {
    private final AdminService adminService;

    @Value("${sitterFile.dir}")
    private String fileDir;

    //    회원 삭제
    @PatchMapping("/delete")
    public void userModify(@RequestBody UserDto userDto) {
        userDto.setUserNumber(userDto.getUserNumber());
        adminService.modify(userDto);
    }

    //    회원 복구
    @PatchMapping("/restore")
    public void userRestore(@RequestBody UserDto userDto) {
        userDto.setUserNumber(userDto.getUserNumber());
        adminService.restoreUser(userDto);
    }

    // 사용자 검색 기능(전체회원관리)
    @GetMapping("/search/{page}")
    public Map<String, Object> searchUser(@RequestParam("keyword") String keyword, @PathVariable("page") int page) {


        // 검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        int totalSearch = adminService.getSearchTotal(keyword);
        Criteria criteria = new Criteria(page, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<UserVo> userList = adminService.searchUser(criteria, keyword);
        PageVo pageVo = new PageVo(criteria, totalSearch);
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("pageVo", pageVo);
        searchMap.put("userList", userList);
        searchMap.put("totalSearch", totalSearch);

        return searchMap;
    }


    // 전체회원수, 일반회원수 조회
    @GetMapping("/usersNumberCount")
    public UserVo usersNumberCount() {
        return adminService.getUsersCount();
    }


    // 산책게시판관리 검색 기능
    @GetMapping("/postSearch/{page}")
    public Map<String, Object> searchPost(@RequestParam("keyword") String keyword, @PathVariable("page") int page) {


        // 검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        int searchTotal = adminService.searchNumTitleCount(keyword);
        Criteria criteria = new Criteria(page, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<StrollBoardVo> postList = adminService.searchNumTitle(criteria, keyword);
        PageVo pageVo = new PageVo(criteria, searchTotal);
        Map<String, Object> searchPostMap = new HashMap<>();
        searchPostMap.put("pageVo", pageVo);
        searchPostMap.put("postList", postList);
        searchPostMap.put("searchTotal", searchTotal);

        return searchPostMap;
    }

    // 펫시터신청서 자격증 파일 가져오기
    private final SitterApplyLicenseFileService sitterApplyLicenseFileService;

    @GetMapping("/view")
    public byte[] display(String fileName) throws IOException {
        return FileCopyUtils.copyToByteArray(new File(fileDir, fileName));
    }

    // applyStatus '승인 완료'로 변경,일반회원->펫시터로 변경(추가)
    @PatchMapping("/add")
    public void statusModify(@RequestBody SitterApplyVo sitterApplyVo) {
        sitterApplyVo.setApplyNumber(sitterApplyVo.getApplyNumber());
        adminService.modifyStatus(sitterApplyVo);
        sitterApplyVo.setUserNumber(sitterApplyVo.getUserNumber());
        adminService.registerSitter(sitterApplyVo);
        sitterApplyVo.setUserNumber(sitterApplyVo.getUserNumber());
        adminService.modifyUserToSitter(sitterApplyVo);
    }

    // applyStatus '승인 거절'로 변경
    @PatchMapping("/refuse")
    public void statusModifyRefuse(@RequestBody SitterApplyVo sitterApplyVo) {
        sitterApplyVo.setUserNumber(sitterApplyVo.getUserNumber());
        adminService.modifyStatusRefuse(sitterApplyVo);
        adminService.removeSitterApply(sitterApplyVo.getApplyNumber());
    }

    // 펫시터회원관리 검색 기능
    @GetMapping("/sitterSearch/{page}")
    public Map<String,Object> searchSitter(@RequestParam("keyword") String keyword,@PathVariable("page")int page) {

        // 검색어와 기본 페이지 정보를 설정하여 검색 결과를 가져옴
        int searchTotalSitter = adminService.getSearchTotalSitter(keyword);
        Criteria criteria = new Criteria(page, 10);  // 예시로 페이지 1, 10개씩 가져오도록 설정
        List<SitterVo> sitterList = adminService.searchSitter(criteria, keyword);
        PageVo pageVo = new PageVo(criteria,searchTotalSitter);
        Map<String,Object> searchSitterMap = new HashMap<>();
        searchSitterMap.put("pageVo",pageVo);
        searchSitterMap.put("sitterList",sitterList);
        searchSitterMap.put("searchTotalSitter", searchTotalSitter);

        return searchSitterMap;
    }

    // sitterStatus '일반회원'으로 변경(강등)
    @PatchMapping("/demotion")
    public void statusModifyDemotion(@RequestBody SitterVo sitterVo){
        UserDto userDto = new UserDto();

        userDto.setUserNumber(sitterVo.getUserNumber());
        userDto.setUserStatus(sitterVo.getUserStatus());

//        sitterVo.setUserNumber(sitterVo.getUserNumber());
        adminService.modifyDemotion(sitterVo);
        adminService.restoreUser(userDto);
        adminService.removeLicense(sitterVo.getUserNumber());
        adminService.removeSitterApply(sitterVo.getUserNumber());
        adminService.removeReview(sitterVo.getSitterNumber());
        adminService.removeReservation(sitterVo.getSitterNumber());
        adminService.removeProfileFile(sitterVo.getSitterNumber());
        adminService.removeSitterFile(sitterVo.getSitterNumber());
        adminService.removeSitterField(sitterVo.getUserNumber());
        adminService.removeSitterUser(sitterVo.getSitterNumber());
    }
}
