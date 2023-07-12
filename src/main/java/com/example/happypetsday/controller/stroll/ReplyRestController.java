

package com.example.happypetsday.controller.stroll;

import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.service.stroll.ReplyService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import com.example.happypetsday.vo.StrollReplyVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/replies/*")
@RequiredArgsConstructor
public class ReplyRestController {
    private final ReplyService replyService;

    //    댓글 작성
    @PostMapping("/reply")
    public void replyRegister(@RequestBody StrollReplyDto strollReplyDto, HttpServletRequest req) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        strollReplyDto.setUserNumber(userNumber);
        replyService.register(strollReplyDto);
    }

    //    댓글 리스트 페이징 가져오기
    @GetMapping("/list/{strollBoardNumber}/{page}")
    public Map<String, Object> getReplyListPage(@PathVariable("strollBoardNumber") Long strollBoardNumber,
                                                @PathVariable("page") int page) {
        int totalReply = replyService.findTotal(strollBoardNumber);
        Criteria criteria = new Criteria(page, 3);
        PageVo pageVo = new PageVo(criteria, totalReply);
        List<StrollReplyVo> replyList = replyService.findReplyList(criteria, strollBoardNumber);
        Map<String, Object> replyMap = new HashMap<>();
        replyMap.put("pageVo", pageVo);
        replyMap.put("replyList", replyList);
        replyMap.put("totalReply", totalReply);

        return replyMap;
    }

    //    댓글 수정
    @PatchMapping("/{strollReplyNumber}")
    public void replyModify(@RequestBody StrollReplyDto strollReplyDto) {

        strollReplyDto.setStrollReplyNumber(strollReplyDto.getStrollReplyNumber());
        replyService.modify(strollReplyDto);
    }

    //    댓글 1개 삭제
    @DeleteMapping("/{strollReplyNumber}")
    public void replyRemove(@PathVariable("strollReplyNumber") Long strollReplyNumber) {
        replyService.remove(strollReplyNumber);
    }

    //    게시글 댓글 전체 삭제
    @DeleteMapping("/delete/{strollBoardNumber}")
    public void replyRemoveAll(@PathVariable("strollBoardNumber") Long strollBoardNumber) {
        replyService.removeAll(strollBoardNumber);
    }


}











