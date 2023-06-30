package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdoptDto {
    private Long centerNumber;
    private String centerName;
    private String centerAddress;
    private String centerCallNumber;
}
