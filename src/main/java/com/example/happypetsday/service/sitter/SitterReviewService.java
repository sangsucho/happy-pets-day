package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterReviewDto;
import com.example.happypetsday.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterReviewService {
    private final ReviewMapper reviewMapper;

    public void register(SitterReviewDto sitterReviewDto) {
        if (sitterReviewDto == null) {
            throw new IllegalArgumentException("리뷰 정보 누락");
        }
        reviewMapper.insert(sitterReviewDto);
    }

//    @Transactional(readOnly = true)
//    public ResVo showModalByResNumber(Long resNumber) {
//        if(resNumber == null) {
//            throw new IllegalArgumentException("예약 번호 누락");
//        }
//
//
//    }
}
