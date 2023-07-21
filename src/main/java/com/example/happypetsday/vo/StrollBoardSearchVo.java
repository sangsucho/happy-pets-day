package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class StrollBoardSearchVo {
    private String searchType;
    private String keyword;
}
