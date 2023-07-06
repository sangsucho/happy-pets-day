package com.example.happypetsday.mapper;

import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.ResVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResMapper {
    // 예약내역 조회
    List<ResVo> selectRes(@Param("userNumber") Long userNumber, @Param("criteria") Criteria criteria);

    // 예약내역 게시물 수 조회
    int selectTotal();

    // 예약상태 변경
    void update(ResVo resVo);
}
