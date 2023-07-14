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
    private Long ApplyNumber;
    private String userId;
    private String petFieldName;
    private String userPhoneNumber;
    private int reviewCount;
    private double reviewAvg;
    private String statusName;
    private String userName;
    private String userGender;
    private String userEmail;
    private String userBirth;
    private String userAddress;
    private String userAddressDetail;
    private String sitterStatus;
    private int userStatus;
}
