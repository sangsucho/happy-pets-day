package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PetFileDto {
    private Long petNumber;
    private Long petFileNumber;
    private String petFileName;
    private String petFileUploadPath;
    private String petFileUuid;
}
