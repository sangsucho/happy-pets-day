$('.select-btn').on('click', function (){
    $(this).siblings().removeClass('click');
    $(this).addClass('click');
    $(this).parent().parent().removeClass('show');
    if(!$(this).parent().parent().hasClass('section5')){
        $('html, body').animate({scrollTop: $(this).parent().parent().next().offset().top-500}, 400);
        $(this).parent().parent().next().addClass('show');
    }else {
        $('html, body').animate({scrollTop: $('.next').offset().top-500},400);
    }
});
