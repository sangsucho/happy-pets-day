package com.example.happypetsday.service.pet;

import com.example.happypetsday.dto.PetDto;
import com.example.happypetsday.mapper.PetFileMapper;
import com.example.happypetsday.mapper.PetMapper;
import com.example.happypetsday.vo.PetVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PetService {
    private final PetMapper petMapper;

    /**
     * 회원번호로 펫 리스트 가져오기
     *
     * @param userNumber
     * @return
     * @throws IllegalArgumentException
     */
    @Transactional
    public List<PetDto> findPet(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("펫 조회 회원 번호 누락");
        }
        return petMapper.selectUsersPet(userNumber);
    }

    /**
     * 펫 정보 등록하기
     *
     * @param petDto
     * @throws IllegalArgumentException 펫 정보, 회원번호 누락시 발생
     */
    public void addPet(PetDto petDto) {
        if (petDto == null) {
            throw new IllegalArgumentException("펫, 회원정보 누락");
        }
        petMapper.insertPet(petDto);
    }

    /**
     * 마이페이지 펫정보 불러오기
     *
     * @param userNumber
     * @return petVo list로 반환
     * @throws IllegalArgumentException 로그인 정보 누락시 발생
     */
    @Transactional(readOnly = true)
    public List<PetVo> findPetInfo(Long userNumber) {
        if (userNumber == null) {
            throw new IllegalArgumentException("회원 정보 누락");
        }
        List<PetVo> petVoList = petMapper.selectMyPetByUserNumber(userNumber);
        for (int i = 0; i < petVoList.size(); i++) {
            PetVo petVo = petVoList.get(i);
            String path = "";
            if (petVo.getPetSurgery().equals("Y")) {
                petVo.setPetSurgery("완료");
            }

            if (petVo.getPetSurgery().equals("N")) {
                petVo.setPetSurgery("미완료");
            }

            if (petVo.getPetFileUploadPath() == null || petVo.getPetFileUuid() == null | petVo.getPetFileName() == null) {
                path = "no";
            } else {
                path = petVo.getPetFileUploadPath() + "/" + petVo.getPetFileUuid() + "_" + petVo.getPetFileName();
            }
            petVo.setFileUploadPath(path);
        }
        return petVoList;
    }

    @Transactional(readOnly = true)
    public PetVo findPetByPetNumber(Long petNumber) {
        if (petNumber == null) {
            throw new IllegalArgumentException("펫 번호 누락");
        }

        return Optional.ofNullable(petMapper.selectByPetNumber(petNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 반려동물");
                });
    }

    public void editMyPet(PetDto petDto) {
        if (petDto == null) {
            throw new IllegalArgumentException("펫 정보 누락");
        }
        petMapper.updateMyPet(petDto);
    }

    public void removeMyPet(Long petNumber) {
        if (petNumber == null) {
            throw new IllegalArgumentException("펫 번호 누락");
        }
        petMapper.delete(petNumber);
    }
}














