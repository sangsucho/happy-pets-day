package com.example.happypetsday.service.myPage;

import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final StrollBoardMapper strollBoardMapper;


    public List<StrollBoardVo> viewMypageBoard(Long userNumber) {
        return strollBoardMapper.selectMypageBoard(userNumber);
    }
}
