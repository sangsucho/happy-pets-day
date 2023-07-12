package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.*;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterReviewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterMapper {
//    시터
    void insert(SitterDto sitterDto);
    Long selectSitterNumber(Long userNumber);
    SitterDto selectSitterInfo(Long userNumber);

//    시터 지원
    void apply(SitterApplyDto sitterApplyDto);
    void updateSitter(SitterDto sitterDto);
    UserDto selectUserInfo(Long userNumber);

//    시터 필드
    void insertField(SitterFieldDto sitterFieldDto);
    void deleteField(Long userNumber);
    List<SitterFieldDto> selectField(Long userNumber);

//    시터 리스트
    List<SitterListVo> selectList();
    int countSitter(Long userNumber);

//    시터 프로필
//    List<SitterListVo> selectSitterProfile(Long sitterNumber);
    List<SitterFileDto> selectSitterFile(Long sitterNumber);
    SitterProfileFileDto selectSitterProfile(Long sitterNumber);
    List<SitterApplyLicenseFile> selectApplyFile(Long userNumber);
    Long userNumFromSitter(Long sitterNumber);
    UserDto selectUserName(Long sitterNumber);
    int selectSitterHeader(Long sitterNumber);

//    시터 예약
    void insertReserve(SitterReservationDto sitterReservationDto);
    void updateReserveStatus(Long userNumber);
    List<SitterReservationDto> selectReserveCantDate(Long sitterNumber);

//    시터 리뷰
    List<SitterReviewVo> selectSitterReview(Long sitterNumber);
    SitterReviewDto reviewCntAndScoreAvg(Long sitterNumber);

//    시터 수정
    SitterDto sitterUploadNumber(Long sitterNumber);
    void sitterUpdate(SitterDto sitterDto);
}
