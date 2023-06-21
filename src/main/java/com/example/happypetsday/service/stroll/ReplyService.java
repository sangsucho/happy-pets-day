package com.example.happypetsday.service.stroll;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.mapper.StrollReplyMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {
    private final StrollReplyMapper strollReplyMapper;

//    댓글 추가
    public void register(StrollReplyDto strollReplyDto){
        if(strollReplyDto == null){
            throw new IllegalArgumentException("댓글 등록 정보 누락");
        }
        strollReplyMapper.insert(strollReplyDto);
    }


}





















