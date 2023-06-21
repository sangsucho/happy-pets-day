$(document).ready(function() {
    // reply-btns가 클릭되었을 때
    $('.reply-list-wrap').on('click', '.reply-btns', function(e) {
        e.stopPropagation(); // 이벤트 버블링을 막는다.

        var currentEditBox = $(this).closest('.reply').find('.edit-box');
        // 현재 클릭한 요소를 제외한 다른 모든 edit-box 요소에 'none' 클래스를 부여합니다.
        $('.edit-box').not(currentEditBox).addClass('none');

        // 현재 클릭한 요소에 'none' 클래스를 추가/제거합니다.
        currentEditBox.toggleClass('none');
    });

    // 문서의 다른 부분이 클릭되었을 때
    $(document).click(function() {
        $('.edit-box').addClass('none');
    });
});


// 댓글 수정 버튼 처리
$('.reply-list-wrap').on('click', '.modify1', function () {
    let $content = $(this).closest('.reply').find('.reply-box__content');
    $content.replaceWith(`
<div class='modify-box'>
  <textarea class='modify-content'>${$content.text()}</textarea>
  <button type='button' class='modify-content-btn'>수정 완료</button>
</div>
`);
    $('.reply-btns__box').addClass('none');
});

// 목록 버튼
$('.list-btn').on('click',function(){
    window.location.href = '/stroll/list';
});

// 게시글 삭제
$('.board-delete-btn').on('click',function (){
    if (window.confirm('해당 게시글을 삭제하시겠습니까?')){
       let boardNumber = $('.boardNum').val();
        window.location.href = '/stroll/remove?strollBoardNumber=' + boardNumber;
    }else{}
});

// 수정 버튼 화면 이동
$('.board-modify-btn').on('click',function (){
    let boardNumber = $('.boardNum').val();
    window.location.href = '/stroll/modify?strollBoardNumber=' + boardNumber;
});











