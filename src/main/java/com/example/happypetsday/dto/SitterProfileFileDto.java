package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class SitterProfileFileDto {
    private Long sitterProfileNumber;
    private Long sitterNumber;
    private String sitterProfileFileName;
    private String sitterProfileUuid;
    private String sitterProfileUploadPath;
}
