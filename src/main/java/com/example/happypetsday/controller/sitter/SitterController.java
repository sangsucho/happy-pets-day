package com.example.happypetsday.controller.sitter;

import com.example.happypetsday.dto.*;
import com.example.happypetsday.service.sitter.SitterApplyLicenseFileService;
import com.example.happypetsday.service.sitter.SitterFileService;
import com.example.happypetsday.service.sitter.SitterProfileFileService;
import com.example.happypetsday.service.sitter.SitterService;
import com.example.happypetsday.vo.SitterListVo;
import com.example.happypetsday.vo.SitterReviewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/sitter/*")
public class SitterController {
    private final SitterApplyLicenseFileService sitterApplyLicenseFileService;
    private final SitterProfileFileService sitterProfileFileService;
    private final SitterFileService sitterFileService;
    private final SitterService sitterService;

    @GetMapping("/apply")
    public String sitterApplyTo(HttpServletRequest req, Model model) {
        Long userNumber = (Long) req.getSession().getAttribute("userNumber");
        if (sitterService.findSitter(userNumber) != null || sitterService.findApplyNumber(userNumber) != null) {
            return "redirect:/sitter/list";
        }
        else {
            UserDto userDto = sitterService.findUserInfo(userNumber);
            model.addAttribute("info", userDto);
            return "sitter/applyTo";
        }
    }

    @PostMapping("/apply")
    public RedirectView sendApply( SitterFieldDto sitterFieldDto,
                                   SitterApplyDto sitterApplyDto, HttpServletRequest req, RedirectAttributes redirectAttributes,
                                   @RequestParam("applyFile") List<MultipartFile> files, @RequestParam(value = "applyFileTitle", required = false) List<String> applyFileTitle
    ) {
        sitterApplyDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
        sitterService.registerApply(sitterApplyDto);

        String[] fieldNames = req.getParameterValues("petFieldName");
        if(fieldNames != null){
        for(String fieldName : fieldNames){
            sitterFieldDto.setPetFieldName(fieldName);
            sitterFieldDto.setUserNumber((Long)req.getSession().getAttribute("userNumber"));
            sitterService.registerField(sitterFieldDto);
        }
        }


        redirectAttributes.addFlashAttribute("applyNumber", sitterApplyDto.getApplyNumber());

        if (files != null && !files.isEmpty() && applyFileTitle !=null) { // 파일 리스트가 null이 아니고 비어있지 않을 경우에만 처리
            try {
                sitterApplyLicenseFileService.registerAndSaveFiles(files, sitterApplyDto.getApplyNumber(), applyFileTitle, sitterApplyDto.getUserNumber());
            } catch (IOException e) {
                e.printStackTrace();
                return new RedirectView("/");
            }
        }
        return new RedirectView("/");
    }


    @GetMapping("/addList")
    public String sitterAddList() {
        return "sitter/sitterAddList";
    }

    @PostMapping("/addList")

    public RedirectView sendAddList(HttpServletRequest req, SitterDto sitterDto, RedirectAttributes redirectAttributes,
                                    @RequestParam("sitterProfileFile") List<MultipartFile> filess,
                                    @RequestParam("sitterFile") List<MultipartFile> files) {
        sitterDto.setUserNumber((Long) req.getSession().getAttribute("userNumber"));
        Long sitterNumber = sitterService.findSitter(sitterDto.getUserNumber());
        sitterDto.setSitterNumber(sitterNumber);

        if (files.isEmpty() || files.get(0).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "배경화면을 선택해주세요");
            return new RedirectView("/sitter/list");
        } else {
            try {
                sitterFileService.registerAndSaveFiles(files, sitterDto.getSitterNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sitterService.addList(sitterDto);
        }

        if (!filess.isEmpty() && !filess.get(0).isEmpty()) {
            try {
                sitterProfileFileService.registerAndSaveFiles(filess, sitterDto.getSitterNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new RedirectView("/sitter/list");
    }


    @GetMapping("/list")
    public String sitterList(@RequestParam(required = false) Long sitterNumber, Model model, HttpServletRequest req) {

        List<SitterListVo> sitterList = sitterService.findAll();

        for (int i = 0; i < sitterList.size(); i++) {
            SitterReviewDto sitterReviewDto = sitterService.reviewCntAndScoreAvg(sitterList.get(i).getSitterNumber());
            model.addAttribute("cntAndAvg", sitterReviewDto);
        }

        if (req.getSession().getAttribute("userNumber") != null) {
            Long userNum = (Long) req.getSession().getAttribute("userNumber");
            Long sitterNum = sitterService.findSitter(userNum);
            if (sitterNum == null) {
                model.addAttribute("sitterNumber", sitterNumber);
                model.addAttribute("sitterList", sitterList);
                return "sitter/sitterList";
            } else {
                int nullSitter = sitterService.findSitterHeaderCount(sitterNum);
                model.addAttribute("nullSitter", nullSitter);
            }
            model.addAttribute("showBtn", sitterService.countSitter((Long) req.getSession().getAttribute("userNumber")));
        }

        model.addAttribute("sitterNumber", sitterNumber);
        model.addAttribute("sitterList", sitterList);
        return "sitter/sitterList";
    }


    @GetMapping("/profile")
    public String sitterProfile(SitterDto sitterDto, HttpServletRequest req, Model model) {

        if (req.getSession().getAttribute("userNumber") != null) {
            Long userNumber = (Long) req.getSession().getAttribute("userNumber");
            Long sitterNumber = sitterDto.getSitterNumber();

            boolean isSitter = sitterService.findSitter(userNumber) == sitterNumber;
            model.addAttribute("isSitter", isSitter);
        }


        List<SitterFileDto> sitterFileDto = sitterService.findSitterFile(sitterDto.getSitterNumber());
        SitterProfileFileDto sitterProfileFileDto = sitterService.findSitterProfile(sitterDto.getSitterNumber());


        UserDto userDto = sitterService.findUserName(sitterDto.getSitterNumber());
        Long userNumFromSitterNum = sitterService.findUserNumFromSitter(sitterDto.getSitterNumber());
        List<SitterApplyLicenseFile> sitterApplyLicenseFile = sitterService.findLicenseFile(userNumFromSitterNum);
        List<SitterFieldDto> sitterFieldDto = sitterService.findField(userNumFromSitterNum);
        List<SitterReviewVo> sitterReviewVo = sitterService.findReview(sitterDto.getSitterNumber());
        SitterReviewDto sitterReviewDto = sitterService.reviewCntAndScoreAvg(sitterDto.getSitterNumber());

        model.addAttribute("profile", sitterProfileFileDto);
        model.addAttribute("sitter", sitterFileDto);
        model.addAttribute("license", sitterApplyLicenseFile);
        model.addAttribute("info", sitterService.findSitterInfo(userNumFromSitterNum));
        model.addAttribute("sitterName", userDto);
        model.addAttribute("field", sitterFieldDto);
        model.addAttribute("review", sitterReviewVo);
        model.addAttribute("cntAndAvg", sitterReviewDto);

        return "sitter/sitterProfile";
    }

    @GetMapping("/modifyInfo")
    public String sitterModify(Model model, HttpServletRequest req) {
        Long userNum = (Long) req.getSession().getAttribute("userNumber");
        Long sitterNum = sitterService.findSitter(userNum);
        SitterDto sitterDto = sitterService.sitterInfoUpload(sitterNum);

        SitterProfileFileDto sitterProfileFileDto = sitterService.findSitterProfile(sitterDto.getSitterNumber());
        List<SitterFileDto> sitterFileDto = sitterService.findSitterFile(sitterDto.getSitterNumber());


        model.addAttribute("info", sitterDto);
        model.addAttribute("profile", sitterProfileFileDto);
        model.addAttribute("file", sitterFileDto);
        return "sitter/sitterInfoModify";
    }

    @PostMapping("/sendModify")
    public RedirectView sendSitterModify(RedirectAttributes redirectAttributes, SitterDto sitterDto, @RequestParam("sitterProfileFile") List<MultipartFile> filess, @RequestParam("sitterFile") List<MultipartFile> files) {
        if (!filess.isEmpty() && !filess.get(0).isEmpty()) {
            try {
                sitterProfileFileService.registerAndSaveFiles(filess, sitterDto.getSitterNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (files.isEmpty() || files.get(0).isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "배경화면을 선택해주세요");
            return new RedirectView("/sitter/list");
        } else {
            try {
                sitterFileService.registerAndSaveFiles(files, sitterDto.getSitterNumber());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sitterService.sitterModify(sitterDto);
        }

        return new RedirectView("/sitter/profile?sitterNumber=" + sitterDto.getSitterNumber());
    }

}
