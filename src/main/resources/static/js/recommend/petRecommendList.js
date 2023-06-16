$('.recommend-item').mouseover(function (){
    $(this).children().eq(0).css('opacity', '0.3');
    $(this).children().eq(1).css('display', 'flex');
});

$('.recommend-item').mouseout(function (){
    $(this).children().eq(0).css('opacity', '1');
    $(this).children().eq(1).css('display', 'none');
});