package com.example.happypetsday.service.adopt;

import com.example.happypetsday.dto.AdoptDto;
import com.example.happypetsday.mapper.AdoptMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor

public class AdoptService {

    private final AdoptMapper adoptMapper;

    public AdoptDto getCenterByCenterName(String centerName) {
        return adoptMapper.getCenterByCenterName(centerName);
    }
}
