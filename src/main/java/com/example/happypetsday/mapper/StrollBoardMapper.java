package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.MainStrollSearchVo;
import com.example.happypetsday.vo.StrollBoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StrollBoardMapper {
    //    게시글 등록
    void insert(StrollBoardDto strollBoardDto);

    //    게시글 1개 조회 (게시글 보기용)
    StrollBoardVo selectOne(Long strollBoardNumber);

    //    게시글 조회수 업데이트
    void updateViewCount(Long strollBoardNumber);

    //    전체 게시글 수 조회
    int selectTotal();

    //    전체 게시글 조회
    List<StrollBoardVo> selectAll(Criteria criteria);

    //    게시글 삭제
    void delete(Long strollBoardNumber);

    //    게시글 수정
    void update(StrollBoardDto strollBoardDto);

    //    게시글 1개 조회 (게시글 수정용)
    StrollBoardDto select(Long strollBoardNumber);

    //    마이페이지 내 산책모임 게시물 조회
    List<StrollBoardVo> selectMypageBoard(@Param("userNumber") Long userNumber, @Param("criteria") Criteria criteria);

    //    메인화면에서 산책 게시판 조회
    List<StrollBoardVo> selectMainView(MainStrollSearchVo mainStrollSearchVo);

}

