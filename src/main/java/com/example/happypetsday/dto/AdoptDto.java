package com.example.happypetsday.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class AdoptDto {
    int ANIMAL_NO; // 동물 번호
    String petName;  // 이름
    String centerName; // 센터명
    String regiDate; // 입소날짜
    String SPCS; // 종
    String BREEDS; // 품종
    String petGender; // 성별
    String AGE; // 나이
    double petWeight;  // 체중
    String adoptStatus;  // 입양상태
    String TMPR_PRTC_STTUS; // 임시보호 상태
    String introVideo;  // 소개영상 URL
    String content;  // 소개내용
    String TMPR_PRTC_CN;  // 임시보호내용
}