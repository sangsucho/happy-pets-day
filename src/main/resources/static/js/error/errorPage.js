//에러페이지

//이전화면으로 가기
$('.prev-btn').on('click', function () {
    window.history.back();
});

//메인 화면으로 가기
$('.main-btn').on('click', function () {
    window.location.href = '/';
});