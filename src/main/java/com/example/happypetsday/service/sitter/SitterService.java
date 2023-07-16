package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.*;
import com.example.happypetsday.mapper.SitterMapper;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterService {
    private final SitterMapper sitterMapper;


    //    시터 테이블
    public void register(SitterDto sitterDto) {
        if (sitterDto == null) {
            throw new IllegalArgumentException("정보 누락");
        }
        sitterMapper.insert(sitterDto);
    }

    public Long findSitter(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return sitterMapper.selectSitterNumber(userNumber);
    }

    public SitterDto findSitterInfo(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return sitterMapper.selectSitterInfo(userNumber);
    }

    public UserDto findUserInfo(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return sitterMapper.selectUserInfo(userNumber);
    }

    public Long findApplyNumber(Long userNumber){
        return sitterMapper.selectApplyNumber(userNumber);
    }

    //    시터 게시글
    public void addList(SitterDto sitterDto) {
        if (sitterDto == null) {
            throw new IllegalArgumentException("시터 권한 없음");
        }
        sitterMapper.updateSitter(sitterDto);
    }

    public void registerApply(SitterApplyDto sitterApplyDto) {
        if (sitterApplyDto == null) {
            throw new IllegalArgumentException("회원정보 누락");
        }
        sitterMapper.apply(sitterApplyDto);
    }

    //    시터 필드
    public void registerField(SitterFieldDto sitterFieldDto) {
        if (sitterFieldDto == null) {
            throw new IllegalArgumentException("회원 번호가 없습니다.");
        }
        sitterMapper.insertField(sitterFieldDto);
    }

    public void removeField(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 번호가 없습니다.");
        }
        sitterMapper.deleteField(userNumber);
    }

    public List<SitterFieldDto> findField(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("시터 필드가 없습니다.");
        }
        return sitterMapper.selectField(userNumber);
    }


    //    시터 리스트
    @Transactional(readOnly = true)
    public List<SitterListVo> findAll() {
        return sitterMapper.selectList();
    }

    public int findSitterHeaderCount(Long sitterNumber) {
        return sitterMapper.selectSitterHeader(sitterNumber);
    }


//    public long findNumber(){
//        return sitterMapper.selectSitterNumber();
//    }

    public int countSitter(Long userNumber) {
        return sitterMapper.countSitter(userNumber);
    }


    //    시터 프로필
    @Transactional(readOnly = true)
    public List<SitterFileDto> findSitterFile(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("펫시터 번호가 없습니다");
        }
        return Optional.ofNullable(sitterMapper.selectSitterFile(sitterNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("시터파일이 없습니다.");
                });
    }

    @Transactional(readOnly = true)
    public SitterProfileFileDto findSitterProfile(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("펫시터 번호가 없습니다");
        }
        return sitterMapper.selectSitterProfile(sitterNumber);
    }


    @Transactional(readOnly = true)
    public List<SitterApplyLicenseFile> findLicenseFile(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("유저 번호가 없습니다");
        }
        return Optional.ofNullable(sitterMapper.selectApplyFile(userNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("시터자격증이 없습니다.");
                });
    }

    //    시터넘버로 유저넘버 뽑아오기
    public Long findUserNumFromSitter(Long sitterNumber) {
        return sitterMapper.userNumFromSitter(sitterNumber);
    }

    //    시터 > 유저 > 네임
    public UserDto findUserName(Long sitterNumber) {
        return sitterMapper.selectUserName(sitterNumber);
    }


    //    시터 예약
    public void registerReserve(SitterReservationDto sitterReservationDto) {

        sitterMapper.insertReserve(sitterReservationDto);
    }

    public void modifyReserveStatus(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("로그인 후 이용하세요.");
        }
        sitterMapper.updateReserveStatus(userNumber);
    }

    public List<SitterReservationDto> findCantDate(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("시터번호 누락");
        }
        return sitterMapper.selectReserveCantDate(sitterNumber);
    }

    //    시터 리뷰
    public List<SitterReviewVo> findReview(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("시터번호 누락");
        }
        return sitterMapper.selectSitterReview(sitterNumber);
    }

    public SitterReviewDto reviewCntAndScoreAvg(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("시터번호 누락");
        }
        return sitterMapper.reviewCntAndScoreAvg(sitterNumber);
    }

    public void modifyMyReview(SitterReviewDto sitterReviewDto){
        sitterMapper.updateMyReview(sitterReviewDto);
    }

    public void removeReview(Long reviewNumber){
        sitterMapper.deleteReview(reviewNumber);
    }

    //    시터 수정
    public SitterDto sitterInfoUpload(Long sitterNumber) {
        if (sitterNumber == null) {
            throw new IllegalArgumentException("시터번호 누락");
        }
        return sitterMapper.sitterUploadNumber(sitterNumber);
    }

    public void sitterModify(SitterDto sitterDto) {
        sitterMapper.sitterUpdate(sitterDto);
    }

    public List<SitterListVo> findBestSitter(){
        return sitterMapper.findSitterMain();
    }

    // 마이페이지 '예약 확인' 펫시터 숫자 가져오기
//    public Long findSitterCnt(Long userNumber) {
//        if (userNumber == null) {
//            throw new IllegalArgumentException("회원번호가 없습니다.");
//        }
//        return sitterMapper.selectSitterCnt(userNumber);
//    }
}
