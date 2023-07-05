$(document).ready(function () {
    // 채팅창 열기
    $('#chatbot-open').click(function () {
        $('#chatbot').show();
        $('#chatbot-open').hide();
        $('.chat-room-detail-wrap').hide();
        $('.chat-room-wrap').show();
    });

    $('.chat-screen-close').click(function () {
        $('#chatbot').hide();
        $('#chatbot-open').show();
    });

    // 채팅방 목록, 채팅 방 이동
    $('.show-chat').on('click',function(){
        $('.chat-room-detail-wrap').show();
        $('.chat-room-wrap').hide();

        $('.chat-prev').on('click',function(){
            $('.chat-room-detail-wrap').hide();
            $('.chat-room-wrap').show();

        })
    });

});

$(document).ready(function () {
});


// 보내기 버튼 클릭 이벤트
$('#chatbot-send').on('click', function () {
    let message = $('#chatbot-input').val();
    if(message.length==0){
        return;
    }
    addUserMessage(message);
    $('#chatbot-input').val('');
    // chatBot.sendMessage(message, addBotMessage); // 챗봇 비동기 통신
});

// input칸 엔터 이벤트
$('#chatbot-input').on('keypress', function (e) {

    if (e.code == 'Enter') {
        let message = $(this).val();
        if(message.length==0){
            return;
        }
        addUserMessage(message);
        $(this).val('');
        //  chatBot.sendMessage(message, addBotMessage); // 챗봇 비동기 통신
    }
});


// 유저 메세지 추가
function addUserMessage(message) {
    let htmlCode = `<div class="user-message message">
         <div class="message-text">${message}</div>
       </div>`;

    $('.chatbot-body').append(htmlCode);
    $('.chatbot-body')[0].scrollTop = $('.chatbot-body')[0].scrollHeight;// 스크롤 하단으로 이동
}
// 챗봇 메세지 추가
function addBotMessage(message) {
    let htmlCode = `<div class="bot-message message">
         <div class="message-text">${message}</div>
       </div>`;

    $('.chatbot-body').append(htmlCode);
    $('.chatbot-body')[0].scrollTop = $('.chatbot-body')[0].scrollHeight;// 스크롤 하단으로 이동
}



// 삭제버튼 띄우기
$('.chat--more-dot').on('click',function(e){
    let exitBtn = $(this).siblings('.chat-room-exit-btn');
    let x = e.pageX;
    let y = e.pageY;

    if (exitBtn.is(':visible')) {
        exitBtn.hide();
    } else {
        exitBtn.show();
    }
});

// 채팅방 나가기 버튼 클릭시
$('.chat-room-exit-btn').on('click',function(){
    $(this).hide();

});








