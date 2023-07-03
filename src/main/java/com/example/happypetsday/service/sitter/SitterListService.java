package com.example.happypetsday.service.sitter;

import com.example.happypetsday.mapper.SitterListMapper;
import com.example.happypetsday.vo.SitterListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterListService {
    private final SitterListMapper sitterListMapper;

    @Transactional(readOnly = true)
    public List<SitterListVo> findAll(){
        return sitterListMapper.selectList();
    }

    public long findNumber(){
        return sitterListMapper.selectSitterNumber();
    }

    public int countSitter(Long userNumber){
        return sitterListMapper.countSitter(userNumber);
    }
}
