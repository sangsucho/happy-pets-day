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
}
