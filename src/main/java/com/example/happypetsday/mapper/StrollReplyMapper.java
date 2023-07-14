package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollReplyDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StrollReplyMapper {
    //    댓글 추가
    void insert(StrollReplyDto strollReplyDto);

    //    댓글 리스트 페이징
    List<StrollReplyVo> selectListPage(@Param("criteria") Criteria criteria, @Param("strollBoardNumber") Long strollBoardNumber);

    //    게시글 댓글 수
    int selectTotal(Long strollBoardNumber);

    //    댓글 한개 삭제
    void delete(Long strollReplyNumber);

    //    게시물에 있는 댓글 전체 삭제
    void deleteAll(Long strollBoardNumber);

    //    댓글 수정
    void update(StrollReplyDto strollReplyDto);


}














