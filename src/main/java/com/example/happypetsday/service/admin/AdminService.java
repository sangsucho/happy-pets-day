package com.example.happypetsday.service.admin;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.AdminMapper;
import com.example.happypetsday.vo.*;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService {
    private final AdminMapper adminMapper;

    /**
     * 회원 1명 상세정보 조회(전체회원-회원상세정보)
     * @param userNumber
     * @throws IllegalArgumentException 존재하지 않는 회원 userNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public UserVo findUser(Long userNumber){
        if(userNumber==null){
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectUserOne(userNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }

    //    전체 조회(전체회원관리)
    @Transactional(readOnly = true)
    public List<UserVo> findAll(Criteria criteria){
        return adminMapper.selectAllUser(criteria);
    }

    //    전체 게시글 수 조회(전체회원관리)
    @Transactional(readOnly = true)
    public int getTotal(){
        return adminMapper.selectTotal();
    }


    // 수정(회원등급 변경)-제명
    public void modify(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.update(userDto);
    }

    // 회원 복구
    public void restoreUser(UserDto userDto) {
        // 회원 복구를 위한 로직 구현
        // userNumber를 사용하여 탈퇴 회원을 찾고, 상태를 변경하여 회원 복구 수행

        if (userDto == null) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }

        // 회원 상태를 변경하여 복구
        userDto.setUserStatus(1); // 복구할 상태 값(일반회원)으로 변경

        adminMapper.update(userDto);
    }

    // userId와 Name으로 검색(전체회원관리)
    @Transactional(readOnly = true)
    public List<UserVo> searchUser(Criteria criteria, String keyword){
        List<UserVo> resultList = adminMapper.searchIdName(criteria, keyword);
        return resultList;
    }

    // userId와 Name으로 검색결과 게시글 수
    @Transactional(readOnly = true)
    public int getSearchTotal(String keyword){
        return adminMapper.searchIdNameCount(keyword);
    }

    // 전체회원수, 일반회원수 조회
    @Transactional(readOnly = true)
    public UserVo getUsersCount(){
        UserVo userVo = new UserVo();

        userVo.setTotalUserNumber(adminMapper.selectTotalUser());
        userVo.setBasicUserNumber(adminMapper.selectBasicUser());
        userVo.setSitterUserNumber(adminMapper.selectSitterUser());
        userVo.setUnapprovedSitterNumber(adminMapper.selectUnapprovedSitter());

        return userVo;
    }

    // userId와 게시물제목으로 검색(산책게시판관리)
    @Transactional(readOnly = true)
    public List<StrollBoardVo> searchNumTitle(Criteria criteria, String keyword){
        List<StrollBoardVo> resultList = adminMapper.searchNumTitle(criteria, keyword);
        return resultList;
    }

    // userId와 게시물제목으로 검색결과 게시글 수(산책게시판관리)
    @Transactional(readOnly = true)
    public int searchNumTitleCount(String keyword){
        return adminMapper.searchNumTitleCount(keyword);
    }

    // 전체 조회(펫시터신청관리)
    @Transactional(readOnly = true)
    public List<SitterApplyVo> findAllPost(Criteria criteria){
        return adminMapper.selectAllPost(criteria);
    }

    // 전체 게시글 수 조회(펫시터신청관리)
    @Transactional(readOnly = true)
    public int getTotalPost(){
        return adminMapper.selectTotalPost();
    }

    /**
     * 펫시터신청서 조회(펫시터신청관리-펫시터신청서열람)
     * @param applyNumber
     * @throws IllegalArgumentException 존재하지 않는 applyNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public SitterApplyVo findApplicaton(Long applyNumber){
        if(applyNumber==null){
            throw new IllegalArgumentException("신청번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectApplication(applyNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 신청입니다.");});
    }

    /**
     * 펫시터 자격증파일 조회(펫시터신청관리-펫시터신청서열람)
     * @param applyNumber
     * @throws IllegalArgumentException 존재하지 않는 applyNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public List<SitterApplyVo> findLicense(Long applyNumber){
        if(applyNumber==null){
            throw new IllegalArgumentException("신청번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectLicense(applyNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 신청입니다.");});
    }

    /**
     * 펫시터 전문분야 조회(펫시터신청관리-펫시터신청서열람)
     * @param applyNumber
     * @throws IllegalArgumentException 존재하지 않는 applyNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public List<SitterApplyVo> findField(Long applyNumber){
        if(applyNumber==null){
            throw new IllegalArgumentException("신청번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectField(applyNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 신청입니다.");});
    }

    // applyStatus '승인 완료'로 변경
    public void modifyStatus(SitterApplyVo sitterApplyVo){
        if(sitterApplyVo == null){
            throw new IllegalArgumentException("신청 정보가 없습니다.");
        }
        adminMapper.changeStatus(sitterApplyVo);
    }

    // 일반회원->펫시터로 변경(추가)
    public void registerSitter(SitterApplyVo sitterApplyVo){
        if(sitterApplyVo==null){
            throw new IllegalArgumentException("회원정보 누락");
        }
        adminMapper.addSitter(sitterApplyVo);
    }

    // 수정(회원등급 변경)-일반회원->펫시터회원
    public void modifyUserToSitter(SitterApplyVo sitterApplyVo){
        if(sitterApplyVo == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.update(sitterApplyVo);
    }

    // applyStatus '승인 거절'로 변경
    public void modifyStatusRefuse(SitterApplyVo sitterApplyVo){
        if(sitterApplyVo == null){
            throw new IllegalArgumentException("신청 정보가 없습니다.");
        }
        adminMapper.changeStatusRefuse(sitterApplyVo);
    }

    // 전체 조회(펫시터회원관리)
    @Transactional(readOnly = true)
    public List<SitterVo> findAllSitter(Criteria criteria){
        return adminMapper.selectAllSitter(criteria);
    }

    // 전체 게시글 수 조회(펫시터회원관리)
    @Transactional(readOnly = true)
    public int getTotalSitter(){
        return adminMapper.selectTotalSitter();
    }

    // userId로 검색(펫시터회원관리)
    @Transactional(readOnly = true)
    public List<SitterVo> searchSitter(Criteria criteria, String keyword){
        List<SitterVo> resultList = adminMapper.searchId(criteria, keyword);
        return resultList;
    }

    // userId로 검색결과 게시글 수(펫시터회원관리)
    @Transactional(readOnly = true)
    public int getSearchTotalSitter(String keyword){
        return adminMapper.searchIdCount(keyword);
    }

    /**
     * 펫시터회원 1명 상세정보 조회(펫시터회원-회원상세정보)
     * @param sitterNumber
     * @throws IllegalArgumentException 존재하지 않는 회원 sitterNumber로 조회하는 경우
     */
    @Transactional(readOnly = true)
    public SitterVo findSitterUser(Long sitterNumber){
        if(sitterNumber==null){
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return Optional.ofNullable(adminMapper.selectSitterOne(sitterNumber))
                .orElseThrow(()->{ throw new IllegalArgumentException("존재하지 않는 회원입니다.");});
    }

    // 수정(회원등급 변경)-펫시터->일반회원 강등
    public void modifyDemotion(SitterVo sitterVo){
        if(sitterVo == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.updateDemotion(sitterVo);
    }

    // 펫시터 전문분야 삭제(강등 시 사용)
    public void removeSitterField(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }

        adminMapper.deleteSitterField(userNumber);
    }

    // 펫시터 자격증 삭제(강등 시 사용)
    public void removeLicense(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }

        adminMapper.deleteLicense(userNumber);
    }
}








