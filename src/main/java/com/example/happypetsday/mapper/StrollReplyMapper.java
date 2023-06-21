package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StrollReplyMapper {
//    댓글 추가
    void insert(StrollReplyDto strollReplyDto);

}














