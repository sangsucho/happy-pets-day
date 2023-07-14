
// 산책할 대표동물을 체크하면(radio)
// 해당 input의 css변경
$(document).ready(function() {
    $('.pet-radio-input').on('click', function() {
        // 모든 .my-pet 요소의 기본 배경 및 색상을 재설정
        $('.my-pet').css('background-color', '#FFF');
        $('.my-pet').css('color', '#68A5FE');

        if ($(this).is(':checked')) {
            // 선택한 .my-pet 요소의 배경 및 색상을 변경
            $(this)
                .closest('.my-pet')
                .css('background-color', '#68A5FE');
            $(this)
                .closest('.my-pet')
                .css('color', 'white');
            $(this)
                .closest('.my-pet')
                .css(' border', '0.2px solid #68A5FE');
        }
    });
});