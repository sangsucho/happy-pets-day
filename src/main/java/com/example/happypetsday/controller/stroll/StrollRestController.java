package com.example.happypetsday.controller.stroll;

import com.example.happypetsday.service.stroll.StrollService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.PageVo;
import com.example.happypetsday.vo.StrollBoardSearchVo;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/strolls/v1/*")
@RequiredArgsConstructor
public class StrollRestController {
    private final StrollService strollService;

    @PostMapping("/list/{page}")
    public Map<String, Object> strollBoardList(@RequestBody StrollBoardSearchVo strollBoardSearchVo,
                                               @PathVariable("page") int page){
        Criteria criteria = new Criteria(page, 10);
        PageVo pageVo = new PageVo(criteria, strollService.findBoardSearchTotal(strollBoardSearchVo));
        strollService.findBoardSearch(strollBoardSearchVo,criteria);
        List<StrollBoardVo> boardList = strollService.findBoardSearch(strollBoardSearchVo,criteria);
        Map<String, Object> strollBoardMap = new HashMap<>();

        strollBoardMap.put("pageVo", pageVo);
        strollBoardMap.put("boardList", boardList);
        return strollBoardMap;
    }



}
