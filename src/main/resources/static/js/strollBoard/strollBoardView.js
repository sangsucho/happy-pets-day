import * as reply from '../module/strollReply.js';

const strollBoardNumber = $('.boardNum').val();
let page = 1;

$(document).ready(function () {
    // reply-btns가 클릭되었을 때
    $('.reply-list-wrap').on('click', '.reply-btns', function (e) {
        e.stopPropagation(); // 이벤트 버블링을 막는다.

        var currentEditBox = $(this).closest('.reply').find('.edit-box');
        // 현재 클릭한 요소를 제외한 다른 모든 edit-box 요소에 'none' 클래스를 부여합니다.
        $('.edit-box').not(currentEditBox).addClass('none');

        // 현재 클릭한 요소에 'none' 클래스를 추가/제거합니다.
        currentEditBox.toggleClass('none');
    });

    // 문서의 다른 부분이 클릭되었을 때
    $(document).click(function () {
        $('.edit-box').addClass('none');
    });
});


// 댓글 수정 버튼 처리
$('.reply-list-wrap').on('click', '.modify1', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');

    let a = $(this).closest('.reply').find('.reply-number');
    let b = this.closest('.reply').querySelector('.reply-number');
    console.log(b);

    console.log(b.value);
    console.log(b.name);

    $content.replaceWith(`
    <div class='modify-box'>
      <textarea class='modify-content'>${$content.text()}</textarea>
      <button type='button' class='modify-content-btn'>수정 완료</button>
    </div>
    `);
    $('.reply-btns__box').addClass('none');
});

// 목록 버튼
$('.list-btn').on('click', function () {
    window.location.href = '/stroll/list';
});

// 게시글 삭제
$('.board-delete-btn').on('click', function () {
    if (window.confirm('해당 게시글을 삭제하시겠습니까?')) {
        let boardNumber = $('.boardNum').val();
        window.location.href = '/stroll/remove?strollBoardNumber=' + boardNumber;
    } else {
    }
});

// 수정 버튼 화면 이동
$('.board-modify-btn').on('click', function () {
    let boardNumber = $('.boardNum').val();
    window.location.href = '/stroll/modify?strollBoardNumber=' + boardNumber;
});

// 에러메세지 출력
function showError(a, b, c) {
    console.error(c);
}

// 댓글 작성 버튼
$('.reply-write-btn').on('click', replyWrite);

// 댓글 작성 엔터키
$('#reply-content').on('keypress', function (e) {
    if (e.code == 'Enter') {
        e.preventDefault(); // 줄바꿈 방지
        if ($('#reply-content').val().length > 0) {
            replyWrite();
        } else {
            $('#reply-content').val(''); // 내용이 없을 경우 textarea를 비웁니다.
        }
    }
});

// 댓글 작성
function replyWrite() {
    if (loginNumber == -1) {
        alert('로그인을 해주세요');
        window.location.href = '/user/login';
        return;
    }

    let strollReplyContent = $('#reply-content').val();

    let replyObj = {
        strollReplyContent: strollReplyContent,
        strollBoardNumber: strollBoardNumber
    }

    page = 1;

    reply.add(replyObj,
        function () {
            reply.getListPage({strollBoardNumber: strollBoardNumber, page: page},
                showReply, showReplyPage, showError)
        }
        , showError)

    $('#reply-content').val(''); // 댓글 작성 후 textarea를 비웁니다.
}


reply.getListPage({strollBoardNumber: strollBoardNumber, page: page}, showReply, showReplyPage, showError);


// 댓글 리스트 보여주기
function showReply(map, showReplyPage) {

    let text = '';

    map.replyList.forEach(r => {
        text += `
               <div class="reply">
                <div class="reply-box" data-num="${r.strollReplyNumber}">
                    <div class="reply-info-box">
                        <div class="reply-box__writer">
                            ${r.userId}
                        </div>
                        <div class="reply-date">
                            ${reply.timeForToday(r.strollReplyUpdateDate)}
                        </div>
                    </div>
                    <!-- 댓글 번호 -->
                    <input type="hidden" class="reply-number" name="strollReplyNumber" value="${r.strollReplyNumber}">

                    <div class="reply-box__content">${r.strollReplyContent}</div>
                </div>
                <div class="reply-btn-box">
                    `;

        if (r.userNumber == loginNumber) {
            text += `     
                    <span class="reply-btns"></span>
                    <article class="edit-box none">
                        <div class="select-box">
                            <div class="btn-list">
                                <button type="button" class="modify1 reply-control-btn">수정
                                    <img src="https://lifet.co.kr/img/icon/icon_modify.svg" alt=""/>
                                </button>
                            </div>
                            <div class="btn-list">
                                <button type="button" class="delete1 reply-control-btn">삭제
                                    <img src="https://lifet.co.kr/img/icon/icon_del.svg" alt=""/>
                                </button>
                            </div>
                        </div>
                    </article>`;
        }

        text += `
                </div>
            </div>
        `;
    });

    $('.reply-list-wrap').html(text);

    showReplyPage(map.pageVo);

    $('.reply-count').text(`${map.totalReply}`)

}

//페이징 버튼 처리
$('.page-ul').on('click', 'a', function (e) {
    e.preventDefault();
    page = $(this).data('page');
    reply.getListPage({strollBoardNumber: strollBoardNumber, page: page}, showReply, showReplyPage, showError);
});


//  댓글 페이징 리스트
function showReplyPage(pageVo) {
    let pageText = '';

    if (pageVo.prev) {
        pageText +=
            `
            <li><a href="javascript:void(0)" data-page="${pageVo.startPage - 1}" class="prev">&lt;</a></li>
            `;
    }

    for (let i = pageVo.startPage; i <= pageVo.endPage; i++) {
        pageText += `
           <li if="${i == pageVo.criteria.page}"><a href="javascript:void(0)" data-page="${i}"
              ${i == pageVo.criteria.page ? `class="active"` : ""}>${i}</a></li>
           `;
    }
    if (pageVo.next) {
        pageText += `
                <li><a href="javascript:void(0)" data-page="${pageVo.endPage + 1}" class="next">&gt;</a></li>
            `;
    }

    $('.page-ul').html(pageText);
}

//  클릭해서 댓글 수정
$('.reply-list-wrap').on('click', '.modify-content-btn', modifyStrollReply);

// 댓글 수정
function modifyStrollReply() {
    let strollReplyContent = $(this).siblings('.modify-content').val();
    let strollReplyNumber = $(this).closest('.reply-box').data('num');
    let thisPage = $('.active').data('page');

    let modifyReply = {
        strollReplyContent: strollReplyContent,
        strollReplyNumber: strollReplyNumber
    }
    let pageInfo = {
        strollBoardNumber: strollBoardNumber,
        page: thisPage
    }

    reply.modify(modifyReply, pageInfo, showReply, showReplyPage, showError);
}


//댓글 삭제
$('.reply-list-wrap').on('click', '.delete1', function () {

    if (confirm("해당 댓글을 삭제하시겠습니까?")) {
        let thisPage = $('.active').data('page');
        let rNum = $(this).closest('.reply-btn-box').siblings('.reply-box').data('num');
        let pageInfo = {
            strollBoardNumber: strollBoardNumber,
            page: thisPage
        }
        reply.remove(rNum, pageInfo, showReply, showReplyPage, showError)

    } else {
    }

});





































