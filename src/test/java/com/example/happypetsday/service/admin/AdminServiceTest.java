package com.example.happypetsday.service.admin;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.mapper.AdminMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.UserVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @Mock
    private AdminMapper adminMapper;

    @InjectMocks
    private AdminService adminService;

    private UserDto userDto;
    private UserVo userVo ;
    private Criteria criteria;

    @BeforeEach
    void setUp() {
        criteria = new Criteria();

        userDto = new UserDto();
        userDto.setUserId("aaa");
        userDto.setUserName("김철수");
        userDto.setUserGender("M");
        userDto.setUserEmail("aa@naver.com");
        userDto.setUserBirth("1994-03-03");
        userDto.setUserPhoneNumber("010-1111-2222");
        userDto.setUserAddress("서울시 강남구");
        userDto.setUserStatus(1);

        userVo = new UserVo();
        userVo.setUserId("aaa");
        userVo.setUserName("김철수");
        userVo.setUserGender("M");
        userVo.setUserEmail("aa@naver.com");
        userVo.setUserBirth("1994-03-03");
        userVo.setUserPhoneNumber("010-1111-2222");
        userVo.setUserAddress("서울시 강남구");
        userVo.setUserStatus(1);
        userVo.setStatusName("일반회원");
    }

    @Test
    @DisplayName("회원 1명 상세정보 조회")
    void findUser() {

        doReturn(userVo).when(adminMapper).selectUserOne(any(Long.class));

        UserVo foundUser = adminService.findUser(45L);

        assertThat(foundUser.getUserId()).isEqualTo(userVo.getUserId());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void findAll() {
        doReturn(List.of(userVo)).when(adminMapper).selectAllUser(any(Criteria.class));

        List<UserVo> foundList = adminService.findAll(criteria);

        assertThat(foundList).contains(userVo);
    }

    @Test
    @DisplayName("전체 게시글 수 조회")
    void getTotal() {

        doReturn(45).when(adminMapper).selectTotal();

        int countlist = adminService.getTotal();

        assertThat(countlist).isNotNull();
    }

    @Test
    @DisplayName("회원 삭제(제명)")
    void modify() {
        doNothing().when(adminMapper).update(any(UserDto.class));

        adminService.modify(userDto);

        verify(adminMapper, times(1)).update(userDto);
    }

    @Test
    @DisplayName("검색")
    void searchUser() {

    }

//    @Test
//    @DisplayName("전체회원 수 조회")
//    void getTotalUser() {
//
//        doReturn(200L).when(adminMapper).selectTotalUser();
//
//        Long totalUserList = adminService.getTotalUser();
//
//        assertThat(totalUserList).isNotNull();
//    }
//
//    @Test
//    @DisplayName("일반회원 수 조회")
//    void getBasicUser() {
//
//        doReturn(30L).when(adminMapper).selectBasicUser();
//
//        Long BasicUserList = adminService.getBasicUser();
//
//        assertThat(BasicUserList).isNotNull();
//    }

}