package com.example.happypetsday.service.sitter;

import com.example.happypetsday.mapper.SitterProfileMapper;
import com.example.happypetsday.vo.SitterVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterProfileService {
    private final SitterProfileMapper sitterProfileMapper;

    @Transactional(readOnly = true)
    public SitterVo find(Long sitterNumber){
        if(sitterNumber == null){
            throw new IllegalArgumentException("펫시터 번호가 없습니다");
        }
        return Optional.ofNullable(sitterProfileMapper.select(sitterNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 펫시터 번호");});
    }


}
