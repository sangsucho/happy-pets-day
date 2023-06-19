package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollBoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StrollBoardMapper {
//    게시글 등록
    void insert(StrollBoardDto strollBoardDto);
//    게시글 1개 조회
    StrollBoardDto selectOnd(Long strollBoardNumber);



}
