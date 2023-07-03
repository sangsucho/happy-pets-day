package com.example.happypetsday.mapper;


import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterProfileMapper {
    List<SitterListVo> select(Long sitterNumber);

}
