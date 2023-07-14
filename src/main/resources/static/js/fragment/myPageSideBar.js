// 회원번호로 시터번호 가져오기
$.ajax({
    url: `/myPages/sitter/profile/view`,
    type: 'get',
    dataType: 'json',
    success: function (result) {
        console.log(result);
        $('.viewProfile').attr("href", `/sitter/profile?sitterNumber=${result}`);
    },
    error: function (a, b, c) {
        console.log(c);
    }
});