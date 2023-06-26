package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.UserDto;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.SearchVo;
import com.example.happypetsday.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdminMapper {
    //회원(1명) 상세 조회
    public UserVo selectUserOne(Long userNumber);

    //회원 전체 조회
    public List<UserVo> selectAllUser(Criteria criteria);

    //전체 게시글 수 조회
    public int selectTotal();

    // 회원 삭제(제명)
    public void update(UserDto userDto);

    // 검색
    public List<UserDto> select(SearchVo searchVo);

    // 전체회원 수 조회
    public Long selectTotalUser();

    // 일반회원 수 조회
    public Long selectBasicUser();

    // 펫시터회원 수 조회
    public Long selectSitterUser();

    // 펫시터 미승인 수 조회
    public Long selectUnapprovedSitter();
}
