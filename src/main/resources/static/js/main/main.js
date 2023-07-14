$('.sitter-info').mouseover(function () {
    $(this).children().eq(0).css('opacity', '0.3');
    $(this).children().eq(1).css('display', 'flex');
});

$('.sitter-info').mouseout(function () {
    $(this).children().eq(0).css('opacity', '1');
    $(this).children().eq(1).css('display', 'none');
});

$('.header--sitter-btn').hover(function () {
    $('.header--sitter-container').css('display', 'flex');
}, function () {
    $('.header--sitter-container').css("display", "none");
});

// 입양 리스트 보여주기
