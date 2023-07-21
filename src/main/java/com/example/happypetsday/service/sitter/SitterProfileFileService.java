package com.example.happypetsday.service.sitter;

import com.example.happypetsday.dto.SitterProfileFileDto;
import com.example.happypetsday.mapper.SitterProfileFileMapper;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class SitterProfileFileService {
    private final SitterProfileFileMapper sitterProfileFileMapper;

    @Value("${sitterProfile.dir}")
    private String fileDir;

    public void register(SitterProfileFileDto sitterProfileFileDto){
//        if(sitterProfileFileDto == null) { throw new IllegalArgumentException("파일 정보 누락"); }

        sitterProfileFileMapper.insert(sitterProfileFileDto);
    }

    public List<SitterProfileFileDto> findList(Long sitterNumber) {
        return sitterProfileFileMapper.selectFile(sitterNumber);
    }

    public SitterProfileFileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
        // 파일 이름에 공백이 들어오면 처리해준다.
        originName = originName.replaceAll("\\s+", "");

//        파일 이름에 붙여줄 uuid 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면(폴더가 없다면)
        if (!uploadPath.exists()) {
//            경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath, sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
//        transferTo(경로)
//        MultipartFile객체를 실제로 저장시킨다.
//        저장시킬 경로와 이름을 매개변수로 넘겨주면 된다.
        file.transferTo(uploadFile);

//        썸네일 저장처리
//        이미지 파일인 경우에만 처리하는 조건식
        if (Files.probeContentType(uploadFile.toPath()).startsWith("image")) {
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_" + sysName));
            Thumbnailator.createThumbnail(file.getInputStream(), out, 300, 200);
            out.close();
        }

//        boardNumber를 제외한 모든 정보를 가진 FileDto를 반환한다.
        SitterProfileFileDto fileDto = new SitterProfileFileDto();
        fileDto.setSitterProfileUuid(uuid.toString());
        fileDto.setSitterProfileFileName(originName);
        fileDto.setSitterProfileUploadPath(getUploadPath());

        return fileDto;
    }

    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param filess       여러 파일을 담은 리스트
     * @param sitterNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void registerAndSaveFiles(List<MultipartFile> filess, Long sitterNumber) throws IOException {
        for (MultipartFile file : filess) {
            SitterProfileFileDto fileDto = saveFile(file);
            fileDto.setSitterNumber(sitterNumber);
            if (fileDto.getSitterNumber() != null) {
                sitterProfileFileMapper.delete(sitterNumber);
            }
            register(fileDto);
        }
    }

    private String getUploadPath() {
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }

    //    시터 번호로 실제 파일에 있는 시터 프로필사진 삭제
    public void removeSitterProfileFile(Long sitterNumber){
        if (sitterNumber == null) {
            throw new IllegalArgumentException("시터 번호 누락(file)");
        }
        List<SitterProfileFileDto> fileList = sitterProfileFileMapper.select(sitterNumber);

        for(SitterProfileFileDto file : fileList){
            File target = new File(fileDir,file.getSitterProfileUploadPath()+"/"+file.getSitterProfileUuid()+"_"+file.getSitterProfileFileName());
            File thumbnail = new File(fileDir,file.getSitterProfileUploadPath()+"/th_"+ file.getSitterProfileUuid()+"_"+file.getSitterProfileFileName());

            if(target.exists()){
                target.delete();
            }
            if(thumbnail.exists()){
                thumbnail.delete();
            }
        }

    }





}
