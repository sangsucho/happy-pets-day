package com.example.happypetsday.service.admin;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.AdminMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.SitterApplyVo;
import com.example.happypetsday.vo.StrollBoardVo;
import com.example.happypetsday.vo.UserVo;
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


    // 수정(회원 제명-회원등급 변경)
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

    // userId와 게시물제목으로 검색결과 게시글 수
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
}








