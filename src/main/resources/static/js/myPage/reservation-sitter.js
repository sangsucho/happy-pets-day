// 예약 거절
$('.reject-btn').on('click', function() {
    let resBox = $(this).closest('#contents-box');
    let btnBox = $(this).closest('.button-wrap');
    let reservationNumber = resBox.find('#reservationNumber').val();
    let reservationStatus = '취소';
    let sitterNumber = resBox.find('#sitterNumber').val();
    let userPhoneNumber = resBox.find('#userPhoneNumber').val();
    let checkResInfo = {
        reservationNumber : reservationNumber,
        reservationStatus : reservationStatus,
        sitterNumber : sitterNumber,
        userPhoneNumber : userPhoneNumber
    }
    console.log(reservationNumber);
    console.log(reservationStatus);
    console.log(sitterNumber);
    console.log(userPhoneNumber);

    if(confirm("예약을 거절하시겠습니까?")) {
        $.ajax({
           url : `/myPages/checkReservation/reject`,
           type : 'patch',
           data : JSON.stringify(checkResInfo),
           contentType : 'application/json; charset=utf-8',
           success : function() {
               resBox.find('.reservation-status').css("color", "red").text('취소');
               btnBox.find('.reject-btn').css('display', 'none');
               btnBox.find('.reject-ok-btn').css('display', 'block');
               btnBox.find('.reject-ok-btn').css('pointer-events', 'none');
           },
           error : function (a, b, c) {
               console.log(c);
           }
        });
    } else {

    }
});
