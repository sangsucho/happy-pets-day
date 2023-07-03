package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.dto.SitterFieldDto;
import com.example.happypetsday.vo.SitterListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterMapper {
//    시터
    void insert(SitterDto sitterDto);
    long selectSitterNumber(long userNumber);

//    시터 지원
    void apply(SitterApplyDto sitterApplyDto);
    void updateSitter(SitterDto sitterDto);

//    시터 필드
    void insertField(SitterFieldDto sitterFieldDto);
    void deleteField(long userNumber);

//    시터 리스트
    List<SitterListVo> selectList();
    int countSitter(Long userNumber);

//    시터 프로필
    List<SitterListVo> selectSitterProfile(Long sitterNumber);

}
