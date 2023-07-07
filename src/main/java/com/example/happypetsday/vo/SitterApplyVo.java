package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterApplyVo {
    private Long applyNumber;
    private String applyContent;
    private String applyDate;
    private String applyStatus;
    private Long userNumber;
    private String userName;
    private String userPhoneNumber;
    private String userAddress;
    private Long applyFileNumber;
    private String applyFileTitle;
    private String applyFileName;
    private String applyFileUuid;
    private String applyFileUploadPath;
    private Long sitterFieldNumber;
    private String petFieldName;
    private String sitterStatus;
    private Long sitterNumber;
    private int userStatus;
}
