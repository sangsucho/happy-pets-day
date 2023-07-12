package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class ResVo {
    private Long reservationNumber;
    private String sitterName;
    private Long sitterNumber;
    private Long userNumber;
    private String userName;
    private String reservationDate;
    private String reservationStatus;
    private String petFieldName;
    private String petKind;
    private int reviewCnt;
    private String userPhoneNumber;
    private Long petNumber;
    private String resUserName;
}
