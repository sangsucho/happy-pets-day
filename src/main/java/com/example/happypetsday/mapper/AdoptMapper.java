package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.AdoptDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdoptMapper {
    AdoptDto getCenterByCenterName(@Param("centerName") String centerName);
}
