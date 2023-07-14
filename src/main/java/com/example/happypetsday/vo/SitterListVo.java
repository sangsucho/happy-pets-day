package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterListVo {
    private String userName;
    private String sitterHeader;
    private Long sitterNumber;
    private String sitterFileFileName;
    private String sitterFileUploadPath;
    private String sitterFileUuid;
    private String sitterProfileFileName;
    private String sitterProfileUploadPath;
    private String sitterProfileUuid;
    private Long applyNumber;
    private String applyFileName;
    private String applyFileUploadPath;
    private String applyFileUuid;
    private String applyTitle;
    private Long userNumber;
    private int reviewCnt;
    private double scoreAvg;
    private String sitterIntroduction;
}
