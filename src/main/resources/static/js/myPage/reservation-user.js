
$(".cancel").on("click", function () {
  var $this = $(this); // 클릭된 요소를 변수에 저장

  if (confirm("예약을 취소하시겠습니까?")) {
    var reservationNumber = $('#reservationNumber').val();
    var reservationStatus = $('#reservationStatus').val();
    console.log(reservationNumber);

    $.ajax({
      url: `/myPages/reservationList`,
      type: 'get',
      data: {
        reservationNumber: reservationNumber,
        reservationStatus: reservationStatus
      },
      contentType: 'application/json; charset=utf-8',
      success: function (result) {
        console.log(result);
        $this.siblings('.reservation-status').text("취소");
        $this.siblings('.reservation-status').css("color", "red");
        $this.css("display", "none");
        $this.siblings('.complete').css("display", "none");
        $this.siblings('.r_res').css("display", "block");
      },
      error: function(xhr, status, error) {
        // 오류 응답 처리
        if (xhr.status === 400) {
          console.error('Error:', xhr.status);
        } else {
          // 기타 오류 발생
          console.error('Error:', xhr.status);
        }
      }
    });
  } else {
    // 취소 버튼을 누른 경우 아무 작업도 수행하지 않음
  }
});



// '이용 완료'
$(".complete").on("click", function () {
  if (confirm("이용 완료처리 하시겠습니까?")) {
    $(".reservation-status").text("이용 완료");
    $(".reservation-status").css("color", "#00bf00");

    $(".basic-btn").css("display", "none");
    $(".review-btn").css("display", "block");
  } else {
  }
});

// '후기 작성'

// '다시 예약하기' 클릭 > 해당 펫시터 예약 페이지로 이동

