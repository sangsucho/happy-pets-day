package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class SitterReviewVo {
    private Long userNumber;
    private Long sitterNumber;
    private String userName;
    private double reviewScore;
    private String reviewContent;
    private String reviewDate;
    private Long reviewNumber;
    private Long reservationNumber;
}
