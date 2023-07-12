package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterProfileFileDto;
import com.example.happypetsday.vo.SitterListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterProfileFileMapper {
    List<SitterProfileFileDto> selectFile(long sitterNumber);

    void insert(SitterProfileFileDto sitterProfileFileDto);

    void delete(long sitterNumber);
}
