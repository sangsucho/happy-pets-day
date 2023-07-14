package com.example.happypetsday.service.stroll;

import com.example.happypetsday.dto.StrollBoardDto;
import com.example.happypetsday.mapper.StrollBoardMapper;
import com.example.happypetsday.mapper.StrollReplyMapper;
import com.example.happypetsday.service.user.UserService;
import com.example.happypetsday.vo.Criteria;
import com.example.happypetsday.vo.MainStrollSearchVo;
import com.example.happypetsday.vo.StrollBoardVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StrollService {
    private final StrollBoardMapper strollBoardMapper;
    private final StrollReplyMapper strollReplyMapper;
    private final UserService userService;

    //    게시글 추가
    public void register(StrollBoardDto strollBoardDto) {
        if (strollBoardDto == null) {
            throw new IllegalArgumentException("산책 게시판 정보 누락");
        }
        strollBoardMapper.insert(strollBoardDto);
    }

    /**
     * 게시글 번호로 게시글 하나 조회(게시글 보기용)
     *
     * @param strollBoardNumber
     * @return StrollBoardVo
     */
    @Transactional(readOnly = true)
    public StrollBoardVo findBoard(Long strollBoardNumber) {
        if (strollBoardNumber == null) {
            throw new IllegalArgumentException("게시글 번호 누락");
        }
        return Optional.ofNullable(strollBoardMapper.selectOne(strollBoardNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("존재하지 않는 게시글 번호");
                });
    }

    //   게시글 조회 수 업데이트
    public void modifyViewCount(Long strollBoardNumber) {
        if (strollBoardNumber == null) {
            throw new IllegalArgumentException("게시글 번호 누락(조회수 없데이트)");
        }
        strollBoardMapper.updateViewCount(strollBoardNumber);
    }

    //    전체 게시글 조회(보기용)
    @Transactional(readOnly = true)
    public List<StrollBoardVo> findAll(Criteria criteria) {
        return strollBoardMapper.selectAll(criteria);
    }

    //    전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal() {
        return strollBoardMapper.selectTotal();
    }

    //    게시글 삭제
    public void remove(Long strollBoardNumber) {
        if (strollBoardNumber == null) {
            throw new IllegalArgumentException("게시글 번호 누락(게시글 삭제)");
        }
        strollReplyMapper.deleteAll(strollBoardNumber);
        strollBoardMapper.delete(strollBoardNumber);
    }

    //   게시글 수정
    public void modify(StrollBoardDto strollBoardDto) {
        if (strollBoardDto == null) {
            throw new IllegalArgumentException("게시글 정보 누락(게시글 수정)");
        }
        strollBoardMapper.update(strollBoardDto);
    }

    /**
     * 게시글 1개 조회(수정용)
     *
     * @param strollBoardNumber
     * @return StrollBoardDto
     */
    @Transactional(readOnly = true)
    public StrollBoardDto findBoardToModify(Long strollBoardNumber) {
        if (strollBoardNumber == null) {
            throw new IllegalArgumentException("게시글 번호 누락(게시글 수정)");
        }
        return Optional.ofNullable(strollBoardMapper.select(strollBoardNumber))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException();
                });
    }

    /**
     * 메인화면에 띄워줄 산책게시판 정보 가져오기
     * 1. 로그인이 안되어있으면 최신게시물 불러오기
     * 2. 로그인이 되어있으면 회원정보에 등록된 주소와 일치되는 게시물 가져오기
     * 3. 만약 가져온 리스트가 3개 이하면 등록된 주소에서 지역으로만 검색해서 가져와 리스트에 추가
     * 4. 그래도 리스트가 3개 이하면 전체 게시물에서 최신순으로 3개가져와서 리스트에 추가
     * 5. 등록된 게시물 자체가 3개 이하면 화면에서 처리
     * -> 게시물이 1 개라도 있으면 그냥 띄우고 하나도 없으면 게시물 등록해달라고 보여준다.
     *
     * @param userNumber
     * @return List<StrollBoardVo>
     */
    @Transactional(readOnly = true)
    public List<StrollBoardVo> findMainList(Long userNumber) {
        MainStrollSearchVo mainStrollSearchVo = new MainStrollSearchVo();
        List<StrollBoardVo> strollBoardList = new ArrayList<>();
        int totalCount = 0;
        long listSize = 0L;

        if(userNumber== null){
//            회원번호가 없으면(로그인이 되어있지 않으면)
//            조회 조건을 전체게시물로 하게끔 설정후 바로 반환
                mainStrollSearchVo.setFirstBoolean(false);
                mainStrollSearchVo.setSecondBoolean(false);
                return strollBoardMapper.selectMainList(mainStrollSearchVo);
            }

//       회원주소 가져와서 "서울", "강남구" 이런식으로 분리

        String[] splitAddress = userService.findUserInfoByUserNumber(userNumber)
                .getUserAddress().split(" ");
        mainStrollSearchVo.setAddressFirst(splitAddress[0]);
        mainStrollSearchVo.setAddressSecond(splitAddress[1]);


//        메인화면에 띄워줄 산책게시판 3개 목록 리스트에 조건에 맞게 추가
            for (int i = 0; i < 3; i++) {
                List<StrollBoardVo> tmpList = strollBoardMapper.selectMainList(mainStrollSearchVo);
                strollBoardList.addAll(tmpList);

                if (listSize != strollBoardList.size()) {
                    totalCount += tmpList.size();
                }

                listSize = strollBoardList.size();

                if (listSize >= 3) {
                    return strollBoardList;
                } else if (mainStrollSearchVo.getSecondBoolean()) {
                    mainStrollSearchVo.setSecondBoolean(false);
                } else if (mainStrollSearchVo.getFirstBoolean()) {
                    mainStrollSearchVo.setFirstBoolean(false);
                }

                mainStrollSearchVo.setSize((long) totalCount);
            }

            return strollBoardList;
        }


    }





















