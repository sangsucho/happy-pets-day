package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterApplyDto {
    private Long applyNumber;
    private String applyContent;
    private String applyDate;
    private String applyStatus;
    private Long userNumber;
}
