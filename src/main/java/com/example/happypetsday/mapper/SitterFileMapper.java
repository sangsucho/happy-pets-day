package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterFileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterFileMapper {
    List<SitterFileDto> selectFile(long sitterNumber);

    void insert(SitterFileDto sitterFileDto);

    void delete(long sitterNumber);
}
