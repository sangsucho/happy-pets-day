package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterApplyDto;
import com.example.happypetsday.dto.SitterDto;
import com.example.happypetsday.dto.SitterFieldDto;
import com.example.happypetsday.mapper.SitterMapper;
import com.example.happypetsday.vo.SitterListVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

//    시터 필드
    public void registerField(SitterFieldDto sitterFieldDto){
        if(sitterFieldDto == null){throw new IllegalArgumentException("회원 번호가 없습니다."); }
        sitterMapper.insertField(sitterFieldDto);
    }

    public void removeField(long userNumber){
        if(userNumber <= 0){throw new IllegalArgumentException("회원 번호가 없습니다."); }
        sitterMapper.deleteField(userNumber);
    }

//    시터 리스트
@Transactional(readOnly = true)
public List<SitterListVo> findAll(){
    return sitterMapper.selectList();
}

//    public long findNumber(){
//        return sitterMapper.selectSitterNumber();
//    }

    public int countSitter(Long userNumber){
        return sitterMapper.countSitter(userNumber);
    }

//    시터 프로필
    @Transactional(readOnly = true)
    public List<SitterListVo> findSitterProfile(Long sitterNumber){
        if(sitterNumber == null){
            throw new IllegalArgumentException("펫시터 번호가 없습니다");
        }
        return Optional.ofNullable(sitterMapper.selectSitterProfile(sitterNumber))
                .orElseThrow(() -> {throw new IllegalArgumentException("존재하지 않는 펫시터 번호");});
    }


}
