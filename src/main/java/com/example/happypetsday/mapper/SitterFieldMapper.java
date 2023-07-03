package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterFieldDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterFieldMapper {
    void insert(SitterFieldDto sitterFieldDto);
    void delete(long userNumber);
}
