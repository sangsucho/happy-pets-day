package com.example.happypetsday.mapper;


import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.vo.SitterVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterProfileMapper {
    SitterVo select(Long sitterNumber);

}
