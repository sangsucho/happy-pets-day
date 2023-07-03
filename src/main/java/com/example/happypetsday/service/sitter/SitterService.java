package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.mapper.SitterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterService {
    private final SitterMapper sitterMapper;


    public void register(SitterDto sitterDto){
        if(sitterDto == null){throw new IllegalArgumentException("정보 누락");}
        sitterMapper.insert(sitterDto);
    }

    public long findSitter(long userNumber){
        if(userNumber <= 0){
            throw new IllegalArgumentException("회원번호가 없습니다.");
        }
        return sitterMapper.selectSitterNumber(userNumber);
    }

    public void addList(SitterDto sitterDto){
        if(sitterDto == null){
            throw  new IllegalArgumentException("시터 권한 없음");
        }
        sitterMapper.updateSitter(sitterDto);
    }

    public void registerApply(SitterApplyDto sitterApplyDto) {
        if(sitterApplyDto==null){throw new IllegalArgumentException("회원정보 누락");}
        sitterMapper.apply(sitterApplyDto);

    }
}
