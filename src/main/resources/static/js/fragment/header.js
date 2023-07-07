$('.header--sitter-btn').hover(function(){
    $('.header--sitter-container').css('display', 'flex');
},function(){
    $('.header--sitter-container').css("display", "none");
});

//로그아웃하면 세션스토리지 초기화
$('.header--logout-btn').on('click',function (e){
    e.preventDefault();
    sessionStorage.clear();
    window.location.href = '/user/logout';
});

