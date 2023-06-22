package com.example.happypetsday.service.stroll;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.mapper.StrollReplyMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import com.example.happypetsday.vo.StrollReplyVo;
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

//    댓글 리스트 가져오기
    public List<StrollReplyVo> findReplyList(Criteria criteria, Long strollBoardNumber){
        if(strollBoardNumber==null||criteria==null){
            throw new IllegalArgumentException("댓글 페이징 정보 누락");
        }
        return strollReplyMapper.selectListPage(criteria,strollBoardNumber);
    }

//    게시글 댓글 수
    public int findTotal(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return strollReplyMapper.selectTotal(strollBoardNumber);
    }

//    댓글 1개 삭제
    public void remove(Long strollReplyNumber){
        if(strollReplyNumber==null){
            throw new IllegalArgumentException("댓글 번호 누락");
        }
        strollReplyMapper.delete(strollReplyNumber);
    }

//    게시글에 있는 전체 댓글 삭제
    public void removeAll(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락(댓글삭제)");
        }
        strollReplyMapper.deleteAll(strollBoardNumber);
    }

//    댓글 수정
    public void modify(StrollReplyDto strollReplyDto){
        if(strollReplyDto==null){
            throw new IllegalArgumentException("댓글 수정 정보 누락");
        }
        strollReplyMapper.update(strollReplyDto);
    }







}





















