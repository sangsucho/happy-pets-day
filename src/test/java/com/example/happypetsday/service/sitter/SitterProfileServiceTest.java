package com.example.happypetsday.service.sitter;

import com.example.happypetsday.mapper.SitterProfileMapper;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
@Slf4j
@Transactional
class SitterProfileServiceTest {

    @Mock
    private SitterProfileMapper sitterProfileMapper;

    @InjectMocks
    private SitterProfileService sitterProfileService;
    private SitterVo sitterVo;

    @BeforeEach
    void setUp(){
        sitterVo = new SitterVo();

        sitterVo.setUserNumber(3L);
        sitterVo.setSitterHeader("test header");
        sitterVo.setSitterIntroduction("test intro");
        sitterVo.setApplyFileName("file title");
        sitterVo.setApplyFileTitle("제목");
        sitterVo.setApplyFileUuid("test UUID");
        sitterVo.setSitterNumber(3L);
    }

    @Test
    void find() {
        doReturn(sitterVo).when(sitterProfileMapper).select(anyLong());
        List<SitterListVo> found = sitterProfileService.find(3L);
        assertThat(found).isEqualTo(sitterVo);
    }
}