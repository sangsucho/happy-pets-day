package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterProfileFileDto;
import com.example.happypetsday.vo.SitterListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterProfileFileMapper {
    List<SitterProfileFileDto> selectFile(Long sitterNumber);
    void insert(SitterProfileFileDto sitterProfileFileDto);
    void update(SitterProfileFileDto sitterProfileFileDto);
    void delete(Long sitterNumber);
    List<SitterProfileFileDto> select(Long sitterNumber);

}
