package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PetDto {
    private Long petNumber;
    private Long userNumber;
    private String petName;
    private String petBirth;
    private String petKind;
    private String petDetailedType;
    private String petGender;
    private String petSurgery;
}
