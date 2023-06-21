package com.example.happypetsday.controller.stroll;

import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.service.stroll.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/replies/*")
@RequiredArgsConstructor
public class ReplyRestController {
    private final ReplyService replyService;

    @PostMapping("/reply")
    public void replyRegister(@RequestBody StrollReplyDto strollReplyDto, HttpServletRequest req){
        Long userNumber = (Long)req.getSession().getAttribute("userNumber");
        strollReplyDto.setUserNumber(userNumber);

        replyService.register(strollReplyDto);
    }



}











