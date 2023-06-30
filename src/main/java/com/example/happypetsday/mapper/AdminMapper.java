package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import com.example.happypetsday.vo.StrollReplyVo;
import com.example.happypetsday.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    //회원(1명) 상세 조회(전체회원관리)
    public UserVo selectUserOne(Long userNumber);

    //회원 전체 조회(전체회원관리)
    public List<UserVo> selectAllUser(Criteria criteria);

    //전체 게시글 수 조회(전체회원관리)
    public int selectTotal();

    // 회원 삭제(제명)
    public void update(UserDto userDto);

    // 회원id,name으로 검색(전체회원관리)
    public List<UserVo> searchIdName(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    // 회원id,name으로 검색조회결과 게시글 수
    public int searchIdNameCount(@Param("keyword") String keyword);

    // 전체회원 수 조회
    public Long selectTotalUser();

    // 일반회원 수 조회
    public Long selectBasicUser();

    // 펫시터회원 수 조회
    public Long selectSitterUser();

    // 펫시터 미승인 수 조회
    public Long selectUnapprovedSitter();

    // 회원id, 게시물제목으로 검색(산책게시판관리)
    public List<StrollBoardVo> searchNumTitle(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    // 회원id,게시물제목으로 검색조회결과 게시글 수
    public int searchNumTitleCount(@Param("keyword") String keyword);

    // 전체게시물 조회(펫시터신청관리)
    public List<SitterApplyDto> selectAllPost(Criteria criteria);

    // 전체 게시글 수 조회(펫시터신청관리)
    public int selectTotalPost();
}
