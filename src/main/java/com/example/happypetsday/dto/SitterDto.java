package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterDto {
    private Long sitterNumber;
    private Long userNumber;
    private String sitterHeader;
    private String sitterIntroduction;
    private String sitterStatus;
    private int sitterPrice;
}
