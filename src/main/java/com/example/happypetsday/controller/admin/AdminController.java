package com.example.happypetsday.controller.admin;

import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.service.stroll.StrollService;
import com.example.happypetsday.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;
    private final StrollService strollService;

    @GetMapping("/applicationManage")
    public String applicationManage(Criteria criteria, Model model) {
        List<SitterApplyVo> postList = adminService.findAllPost(criteria);

        model.addAttribute("postList", postList);
        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotalPost()));

        return "admin/applicationManage";
    }

    @GetMapping("/petsitterDetailView")
    public String petsitterDetailView(Long sitterNumber, Model model){
        SitterVo sitterVo = adminService.findSitterUser(sitterNumber);

        model.addAttribute("sitter", sitterVo);

        return "admin/petsitterDetailView";
    }

    @GetMapping("/petsitterManage")
    public String petsitterManage(Model model){
//        Criteria criteria = new Criteria();
//        List<SitterVo> sitterList = adminService.findAllSitter(criteria);
//
//        model.addAttribute("sitterList", sitterList);
//        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotalSitter()));
        return "admin/petsitterManage";
    }

    @GetMapping("/strollBoardManage")
    public String strollBoardManage(Criteria criteria, Model model) {
        List<StrollBoardVo> boardList = strollService.findAll(criteria);

        model.addAttribute("boardList", boardList);
        model.addAttribute("pageInfo", new PageVo(criteria, strollService.getTotal()));

        return "admin/strollBoardManage";
    }

    @GetMapping("/userDetailManage")
    public String userDetailManage(Long userNumber, Model model) {
        UserVo userVo = adminService.findUser(userNumber);

        model.addAttribute("user", userVo);
        return "admin/userDetailManage";
    }

    @GetMapping("/userManage")
    public String userManage(Criteria criteria, Model model) {
        List<UserVo> userList = adminService.findAll(criteria);

//        List<UserVo> resultList = userList.stream()
//                .map(user -> {
//                    user.setUserStatusResult(adminService.viewStatus(user.getUserStatus()));
//                    return user;
//                })
//                .collect(Collectors.toList());


//        List<UserVo> resultList = new ArrayList<>();
//
//        for(UserVo user: userList){
//            user.setUserStatusResult(adminService.viewStatus(user.getUserStatus()));
//            resultList.add(user);
//        }

        model.addAttribute("userList", userList);
        model.addAttribute("pageInfo", new PageVo(criteria, adminService.getTotal()));

        return "admin/userManage";
    }


    @GetMapping("/viewApplication")
    public String viewApplication(Long applyNumber, Model model) {
        SitterApplyVo sitterApplyVo = adminService.findApplicaton(applyNumber);
        List<SitterApplyVo> licenseList = adminService.findLicense(applyNumber);
        List<SitterApplyVo> fieldList = adminService.findField(applyNumber);

        model.addAttribute("view", sitterApplyVo);
        model.addAttribute("license", licenseList);
        model.addAttribute("field", fieldList);
        return "admin/viewApplication";
    }


}
