package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.dto.SitterFieldDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterMapper {
    void insert(SitterDto sitterDto);
    long selectSitterNumber(long userNumber);

//    시터 지원
    void apply(SitterApplyDto sitterApplyDto);
    void updateSitter(SitterDto sitterDto);

//    시터 필드
    void insertField(SitterFieldDto sitterFieldDto);
    void deleteField(long userNumber);
}
