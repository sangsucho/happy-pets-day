package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterFileMapper {
    List<SitterFileDto> selectFile(Long sitterNumber);
    void insert(SitterFileDto sitterFileDto);
    void update(SitterFileDto sitterFileDto);
    void delete(Long sitterNumber);
//    시터넘버로 해당 시터의 모든 파일정보 가져오기
    List<SitterFileDto> select(Long sitterNumber);

}
