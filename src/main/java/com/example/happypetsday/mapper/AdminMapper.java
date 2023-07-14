package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.*;
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

    // 회원등급(UserStatus) 수정 - 회원삭제(제명)
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
    public List<SitterApplyVo> selectAllPost(Criteria criteria);

    // 전체 게시글 수 조회(펫시터신청관리)
    public int selectTotalPost();

    // 펫시터 지원서 조회
    public SitterApplyVo selectApplication(Long applyNumber);

    // 펫시터 자격증 파일 조회
    public List<SitterApplyVo> selectLicense(Long applyNumber);

    // 펫시터 전문분야 조회
    public List<SitterApplyVo> selectField(Long applyNumber);

    // applyStatus '승인 완료'로 변경
    public void changeStatus(SitterApplyVo sitterApplyVo);

    // 일반회원->펫시터로 변경(추가)
    public void addSitter(SitterApplyVo sitterApplyVo);

    // 회원등급(UserStatus) 수정 -일반회원->펫시터회원
    public void update(SitterApplyVo sitterApplyVo);

    // applyStatus '승인 거절'로 변경
    public void changeStatusRefuse(SitterApplyVo sitterApplyVo);

    // 시터신청 거절 시 apply테이블에서 삭제
    public void deleteSitterApply(Long userNumber);

    //펫시터회원 전체 조회(펫시터회원관리)
    public List<SitterVo> selectAllSitter(Criteria criteria);

    //전체 게시글 수 조회(펫시터회원관리)
    public int selectTotalSitter();

    // 회원id로 검색(펫시터회원관리)
    public List<SitterVo> searchId(@Param("criteria") Criteria criteria, @Param("keyword") String keyword);

    // 회원id로 검색조회결과 게시글 수
    public int searchIdCount(@Param("keyword") String keyword);

    // 펫시터회원(1명) 상세 조회(펫시터회원관리)
    public SitterVo selectSitterOne(Long sitterNumber);

    // 회원등급(SitterStatus) 수정 - 시터->일반회원 강등
    public void updateDemotion(SitterVo sitterVo);

    // 강등-시터 테이블에서 삭제
    public void deleteSitterTable(Long sitterNumber);

    // 펫시터 전문분야 삭제(강등 시 사용)
    public void deleteSitterField(Long userNumber);

    // 펫시터 자격증 삭제(강등 시 사용)
    public void deleteLicense(Long userNumber);

    // 펫시터 리뷰 삭제(강등 시 사용)
    public void deleteSitterReview(Long sitterNumber);

    // 펫시터 예약 삭제(강등 시 사용)
    public void deleteSitterReservation(Long sitterNumber);

    // 펫시터 프로필파일 삭제(강등 시 사용)
    public void deleteProfileFile(Long sitterNumber);

    // 펫시터 시터파일 삭제(강등 시 사용)
    public void deleteSitterFile(Long sitterNumber);

}
