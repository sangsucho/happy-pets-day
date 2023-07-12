package com.example.happypetsday.controller.user;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.UserMapper;
import com.example.happypetsday.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/*")
public class UserRestController {
    private final UserService userService;
    private final UserMapper userMapper;

    //    아이디 중복확인
    @GetMapping("/checkDuplicate")
    public boolean checkDuplicate(@RequestParam String userId) {
        return userService.isUserIdAvailable(userId);
    }

    //  아이디 찾기
    @GetMapping("/users/findId")
    public ResponseEntity<String> findUserIdByNameAndPhone(@RequestParam String userName, @RequestParam String userPhoneNumber) {
        try {
            int phoneNumber = Integer.parseInt(userPhoneNumber);
            String userId = userService.findUserIdByNameAndPhone(userName, phoneNumber);
            return ResponseEntity.ok(userId);
        } catch (NumberFormatException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("유효하지 않은 핸드폰 번호입니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이름 또는 핸드폰 번호 누락");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류가 발생했습니다.");
        }
    }


    @GetMapping("/findPW")
    public ResponseEntity<String> findUserPassword(@RequestParam("userId") String userId,
                                                   @RequestParam("questionNumberInput") int questionNumberInput,
                                                   @RequestParam("answer") String answer) {
        boolean isVerified = userService.verifyUserSecurityAnswer(userId, questionNumberInput, answer);
        if (isVerified) {
            String password = userService.getUserPassword(userId);
            return ResponseEntity.ok().body(password);
        } else {
            return ResponseEntity.badRequest().body("일치하는 정보가 없습니다.");
        }
    }


}
