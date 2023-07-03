package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterFieldDto;
import com.example.happypetsday.mapper.SitterFieldMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterFieldService {
    private final SitterFieldMapper sitterFieldMapper;

    public void register(SitterFieldDto sitterFieldDto){
        if(sitterFieldDto == null){throw new IllegalArgumentException("회원 번호가 없습니다."); }
        sitterFieldMapper.insert(sitterFieldDto);
    }

    public void remove(long userNumber){
        if(userNumber <= 0){throw new IllegalArgumentException("회원 번호가 없습니다."); }
        sitterFieldMapper.delete(userNumber);
    }

}
