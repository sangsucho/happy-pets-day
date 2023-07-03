package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterAddListVo {
    private String sitterHeader;
    private String sitterIntroduction;
    private Long sitterNumber;
    private String sitterFileFileName;
    private String sitterFileUploadPath;
    private String sitterFileUuid;
    private String sitterProfileFileName;
    private String sitterProfileUploadPath;
    private String sitterProfileUuid;
}
