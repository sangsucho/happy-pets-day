package com.example.happypetsday.controller.user;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserRestController {
    private final UserService userService;

    @GetMapping("/checkDuplicate")
    public boolean checkDuplicate(@RequestParam String userId) {
        return userService.isUserIdAvailable(userId);
    }

    private String savedId;

    @PostMapping("/saveId")
    public void saveId(@RequestParam String userId) {
        savedId = userId;
    }

    @GetMapping("/getSavedId")
    public String getSavedId() {
        return savedId;
    }

    @PostMapping("/removeSavedId")
    public void removeSavedId() {
        savedId = null;
    }
}
