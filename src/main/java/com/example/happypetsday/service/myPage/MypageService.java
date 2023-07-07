package com.example.happypetsday.service.myPage;

import com.example.happypetsday.mapper.ResMapper;
import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.ResVo;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageService {
    private final StrollBoardMapper strollBoardMapper;
    private final ResMapper resMapper;

    // 마이페이지 내 산책모임 게시물 조회
    public List<StrollBoardVo> viewMypageBoard(Long userNumber, Criteria criteria) {
        return strollBoardMapper.selectMypageBoard(userNumber, criteria);
    }

    // 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotalMypageBoard() {
        return strollBoardMapper.selectTotal();
    }

    // 예약내역 조회
    public List<ResVo> findRes(Long userNumber, Criteria criteria) {
        return resMapper.selectRes(userNumber, criteria);
    }

    // 예약내역 게시물 수 조회
    @Transactional(readOnly = true)
    public int getTotalResList() {
        return resMapper.selectTotal();
    }

    // 예약상태 변경
    public void modify(ResVo resVo) {
        if (resVo == null) {
            throw new IllegalArgumentException("예약정보 누락");
        }
        resMapper.update(resVo);
    }
}
