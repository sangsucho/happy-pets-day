package com.example.happypetsday.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

    @GetMapping("/applicationManage")
    public String applicationManage() {
        return "admin/applicationManage";
    }

    @GetMapping("/petsitterDetailView")
    public String petsitterDetailView(){
        return "admin/petsitterDetailView";
    }

    @GetMapping("/petsitterManage")
    public String petsitterManage(){
        return "admin/petsitterManage";
    }

    @GetMapping("/strollBoardManage")
    public String strollBoardManage(){
        return "admin/strollBoardManage";
    }

    @GetMapping("/userDetailManage")
    public String userDetailManage(){
        return "admin/userDetailManage";
    }

    @GetMapping("/userManage")
    public String userManage(){
        return "admin/userManage";
    }

    @GetMapping("/viewApplication")
    public String viewApplication(){
        return "admin/viewApplication";
    }
}
