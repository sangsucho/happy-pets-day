updatePetState();

// 이벤트 핸들러 및 초기 상태 설정을 위한 함수를 정의
function updatePetState() {
    // 모든 .my-pet 요소의 기본 배경 및 색상을 재설정
    $('.my-pet').css('background-color', '#FFF');
    $('.my-pet').css('color', '#68A5FE');

    // 현재 체크된 라디오 버튼이 있는 .my-pet 요소의 배경 및 색상을 변경
    $('.pet-radio-input:checked')
        .closest('.my-pet')
        .css('background-color', '#68A5FE');
    $('.pet-radio-input:checked')
        .closest('.my-pet')
        .css('color', 'white');
    $('.pet-radio-input:checked')
        .closest('.my-pet')
        .css('border', '0.2px solid #68A5FE');
}

// 라디오 버튼을 클릭할 때마다 상태를 업데이트
$('.pet-radio-input').on('click', updatePetState);


$('.board-write-btn').on('click', function (e) {
    if (!($('.marker-lat').val() || $('.marker-lng').val())) {
        e.preventDefault();
        alert("지도에 모일 장소를 클릭해 주세요!!")
    }
});









