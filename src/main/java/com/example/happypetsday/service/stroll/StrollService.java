package com.example.happypetsday.service.stroll;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.mapper.StrollBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StrollService {
    private final StrollBoardMapper strollBoardMapper;

//    게시글 추가
    public void register(StrollBoardDto strollBoardDto){
        if(strollBoardDto==null){
            throw new IllegalArgumentException("산책 게시판 정보 누락");
        }
        strollBoardMapper.insert(strollBoardDto);
    }

//   게시글 하나 조회
    @Transactional(readOnly = true)
    public StrollBoardDto findBoard(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return Optional.ofNullable(strollBoardMapper.selectOnd(strollBoardNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 게시글 번호");});
    }


}
