package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SitterMapper {
    void insert(SitterDto sitterDto);
    long selectSitterNumber(long userNumber);
    void update(SitterDto sitterDto);
}
