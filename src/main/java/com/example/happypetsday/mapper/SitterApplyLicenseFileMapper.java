package com.example.happypetsday.mapper;

import com.example.happypetsday.dto.SitterApplyLicenseFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SitterApplyLicenseFileMapper {
    void insert(SitterApplyLicenseFile sitterApplyLicenseFile);

    void delete(Long applyNumber);

    Long selectApplyNumber(Long userNumber);

    List<SitterApplyLicenseFile> select(Long userNumber);

    List<SitterApplyLicenseFile> selectOldList();

    List<SitterApplyLicenseFile> selectByUserNumber(Long userNumber);
}
