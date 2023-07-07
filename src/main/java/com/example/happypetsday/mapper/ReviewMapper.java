package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterReviewDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReviewMapper {
    // 리뷰 작성
    void insert(SitterReviewDto sitterReviewDto);
}
