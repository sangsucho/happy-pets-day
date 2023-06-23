package com.example.happypetsday.service.stroll;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.mapper.StrollReplyMapper;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StrollService {
    private final StrollBoardMapper strollBoardMapper;
    private final StrollReplyMapper strollReplyMapper;

//    게시글 추가
    public void register(StrollBoardDto strollBoardDto){
        if(strollBoardDto==null){
            throw new IllegalArgumentException("산책 게시판 정보 누락");
        }
        strollBoardMapper.insert(strollBoardDto);
    }

    /**
     * 게시글 번호로 게시글 하나 조회(게시글 보기용)
     * @param strollBoardNumber
     * @return StrollBoardVo
     */
    @Transactional(readOnly = true)
    public StrollBoardVo findBoard(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return Optional.ofNullable(strollBoardMapper.selectOne(strollBoardNumber))
                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 게시글 번호");});
    }

//   게시글 조회 수 업데이트
    public void modifyViewCount(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락(조회수 없데이트)");
        }
        strollBoardMapper.updateViewCount(strollBoardNumber);
    }

//    전체 게시글 조회(보기용)
    @Transactional(readOnly = true)
    public List<StrollBoardVo> findAll(Criteria criteria){
        return strollBoardMapper.selectAll(criteria);
    }

//    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(){
        return strollBoardMapper.selectTotal();
    }

//    게시글 삭제
    public void remove(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락(게시글 삭제)");
        }
        strollReplyMapper.deleteAll(strollBoardNumber);
        strollBoardMapper.delete(strollBoardNumber);
    }
//   게시글 수정
    public void modify(StrollBoardDto strollBoardDto){
        if(strollBoardDto==null){
            throw new IllegalArgumentException("게시글 정보 누락(게시글 수정)");
        }
        strollBoardMapper.update(strollBoardDto);
    }

    /**
     * 게시글 1개 조회(수정용)
     * @param strollBoardNumber
     * @return StrollBoardDto
     */
    @Transactional(readOnly = true)
    public StrollBoardDto findBoardToModify(Long strollBoardNumber){
        if(strollBoardNumber==null){
            throw new IllegalArgumentException("게시글 번호 누락(게시글 수정)");
        }
        return Optional.ofNullable(strollBoardMapper.select(strollBoardNumber))
                .orElseThrow(()->{throw new IllegalArgumentException();});
    }



}





















