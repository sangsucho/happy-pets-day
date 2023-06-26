package com.example.happypetsday.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class PetVo {
    private Long petNumber;
    private String petName;
    private String petBirth;
    private String petKind;
    private String petDetailedType;
    private String petGender;
    private String petSurgery;
    private String petFileName;
    private String petFileUploadPath;
    private String petFileUuid;
    private String fileUploadPath;
}
