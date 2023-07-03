package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class SitterVo {
    private Long SitterNumber;
    private Long UserNumber;
    private String SitterHeader;
    private String SitterIntroduction;
//  private   Long ApplyFileNumber;
    private String ApplyFileTitle;
    private String ApplyFileName;
    private String ApplyFileUuid;
//    Long ApplyNumber;
}
