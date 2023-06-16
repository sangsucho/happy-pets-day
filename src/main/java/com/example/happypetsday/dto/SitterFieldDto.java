package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterFieldDto {
    private Long sitterFieldNumber;
    private String petFieldName;
    private Long sitterNumber;
}
