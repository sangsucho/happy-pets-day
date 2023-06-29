package com.example.happypetsday.service.myPage;

import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {
    private final StrollBoardMapper strollBoardMapper;


    public List<StrollBoardVo> viewMypageBoard(Long userNumber, Criteria criteria) {
        return strollBoardMapper.selectMypageBoard(userNumber, criteria);
    }

    @Transactional(readOnly = true)
    public int getTotalMypageBoard() {
        return strollBoardMapper.selectTotal();
    }

}
