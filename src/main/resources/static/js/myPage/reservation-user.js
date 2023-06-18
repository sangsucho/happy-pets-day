// '다시 예약하기' 버튼 숨김
$(".r_res").hide();
$(".review").hide();

// '예약 취소'
$(".cancel").on("click", function () {
  if (confirm("예약을 취소하시겠습니까?")) {
    // 확인 버튼을 누른 경우

    // reservation-status의 텍스트를 '예약취소'로 변경
    $(".reservation-status").text("예약 취소");
    $(".reservation-status").css("color", "red");
    // '다시 예약하기' 버튼으로 변경
    $(".cancel").hide();
    $(".complete").hide();
    $(".r_res").show();
  } else {
    // 취소 버튼을 누른 경우 아무 작업도 수행하지 않음
  }
});

// '이용 완료'
$(".complete").on("click", function () {
  if (confirm("이용 완료처리 하시겠습니까?")) {
    $(".reservation-status").text("이용 완료");
    $(".reservation-status").css("color", "#00bf00");

    $(".cancel").hide();
    $(".complete").hide();
    $(".r_res").show();
    $(".review").show();
  } else {
  }
});

// '다시 예약하기' 클릭 > 해당 예약 펫시터 예약 페이지로 이동
