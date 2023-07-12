package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.vo.PetVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PetMapper {

    //    회원번호로 펫 정보 불러오기
    List<PetDto> selectUsersPet(Long userNumber);

    //    펫 등록하기
    void insertPet(PetDto petDto);

    //    회원번호로 펫 정보 불러오기(펫 이미지 포함)
    List<PetVo> selectMyPetByUserNumber(Long userNumber);

    //    펫 번호로 펫정보 불러오기
    PetVo selectByPetNumber(Long petNumber);

    //    펫 정보 수정하기
    void updateMyPet(PetDto petDto);

    //    펫 삭제하기
    void delete(Long petNumber);
}
