package com.example.happypetsday.mapper;

import com.example.happypetsday.vo.SitterVo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SitterProfileMapperTest {

    SitterVo sitterVo = new SitterVo();

    @BeforeEach
    void setUp(){
        sitterVo.setUserNumber(3L);
    }

    @Test
    void select() {
    }
}