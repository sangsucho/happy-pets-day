package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterFieldDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.function.DoubleToIntFunction;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Slf4j
class SitterFieldServiceTest {

    @Mock
    private SitterFieldService sitterFieldService;
    private SitterFieldDto sitterFieldDto;

    @BeforeEach
    void setUp(){
        sitterFieldDto = new SitterFieldDto();
        sitterFieldDto.setSitterNumber(3L);
        sitterFieldDto.setPetFieldName("asdasd");
    }

    @Test
    void register() {
        long userNumber = sitterFieldDto.getSitterNumber();
        sitterFieldService.register(userNumber);
    }

    @Test
    void modify() {
        long userNumber = sitterFieldDto.getSitterNumber();
        sitterFieldService.register(userNumber);

        sitterFieldDto.setPetFieldName("aaaaaa22");
        sitterFieldService.modify(userNumber);
    }
}