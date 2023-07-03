package com.example.happypetsday.mapper;

import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterListMapper {
    List<SitterListVo> selectList();
    long selectSitterNumber();
    int countSitter(Long userNumber);
}
