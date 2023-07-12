package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterFileDto;
import com.example.happypetsday.vo.SitterListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterFileMapper {
    List<SitterFileDto> selectFile(Long sitterNumber);
    void insert(SitterFileDto sitterFileDto);
    void update(SitterFileDto sitterFileDto);
    void delete(Long sitterNumber);

}
