package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.service.admin.AdminService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Transactional
@SpringBootTest
@Slf4j
class AdminMapperTest {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;

    private UserVo userVo;
    private UserDto userDto;
    private Criteria criteria;
    private SitterApplyDto sitterApplyDto;

    @BeforeEach
    void setUp() {
        criteria = new Criteria();
        userVo = new UserVo();

        userVo.setUserNumber(1L);

        userDto = new UserDto();
        userDto.setUserNumber(1L);
        userDto.setUserStatus(1);

        sitterApplyDto = new SitterApplyDto();
        sitterApplyDto.setApplyStatus("승인 대기");
        sitterApplyDto.setUserNumber(1L);
        sitterApplyDto.setApplyNumber(1L);
    }

    @Test
    @DisplayName("회원 1명 상세정보 조회")
    void selectUserOne() {
    UserVo finduser =adminMapper.selectUserOne(userVo.getUserNumber());
    log.info(finduser.toString());

        assertThat(finduser.getUserNumber())
                .isEqualTo(userVo.getUserNumber());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void selectAllUser() {
        int beforeSize = adminMapper.selectAllUser(criteria).size();

        assertThat(adminMapper.selectAllUser(criteria).size()).isGreaterThan(0);
        assertThat(adminMapper.selectTotal()).isGreaterThan(0);
    }

    @Test
    @DisplayName("회원 삭제(제명)")
    void update() {
        userDto.setUserStatus(-1);

        adminMapper.update(userDto);

        assertThat(adminMapper.selectUserOne(userDto.getUserNumber()).getUserStatus())
                .isEqualTo(userDto.getUserStatus());
    }

    @Test
    @DisplayName("회원 ID, 이름으로 검색")
    void searchIdNameTest() {
//        criteria.setPage(1); // 페이지 번호 설정
//        criteria.setAmount(10); // 페이지 당 데이터 수 설정
        String keyword = "12"; // 검색 키워드 설정

        List<UserVo> userList = adminMapper.searchIdName(criteria, keyword);

        assertThat(userList).isNotNull();

    }

    @Test
    @DisplayName("전체회원 수 조회")
    void selectTotalUser(){
        Long totalUserCount = adminMapper.selectTotalUser();
        UserVo userVo = new UserVo();
        userVo.setTotalUserNumber(totalUserCount);

        // Assertion
        assertEquals(totalUserCount, userVo.getTotalUserNumber());
    }

    @Test
    @DisplayName("일반회원 수 조회")
    void selectBasicUser() {
        Long basicUserCount = adminMapper.selectBasicUser();
        UserVo userVo = new UserVo();
        userVo.setBasicUserNumber(basicUserCount);

        // Assertion
        assertEquals(basicUserCount, userVo.getBasicUserNumber());
    }

//    @Test
//    @DisplayName("applyStatus '승인완료'로 변경")
//    void changeStatus(){
//        sitterApplyDto.setApplyStatus("승인 완료");
//
//        adminMapper.changeStatus(sitterApplyDto);
//
//        assertThat(adminMapper.selectApplication(sitterApplyDto.getApplyNumber()).getApplyStatus())
//                .isEqualTo(sitterApplyDto.getApplyStatus());
//    }

}