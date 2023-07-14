
$(".cancel").on("click", function () {
    let $this = $(this);
    let $resBox = $this.closest('#contents-box');
    let btnBox = $this.closest('.button-wrap');
    let reservationNumber = $resBox.find('#resNumber').val();
    let reservationStatus = '취소';
    let sitterNumber = $resBox.find('#sitterNumber').val();
    let resInfo = {
        reservationNumber : reservationNumber,
        reservationStatus : reservationStatus,
        sitterNumber : sitterNumber
    }

    if (confirm("예약을 취소하시겠습니까?")) {
    // 확인 버튼을 누른 경우 예약 테이블의 '예약상태'를 '취소'로 변경
        $.ajax({
        url : `/myPages/reservationList/cancel`,
        type : 'patch',
        data : JSON.stringify(resInfo),
        contentType : 'application/json; charset=utf-8',
        success : function() {
            $resBox.find(".reservation-status").css("color", "red").text("취소");
            btnBox.find('.basic-btn').css('display', 'none');
            btnBox.find('.r-res').css('display', 'block');
        }
      });
    } else {
        // 취소 버튼을 누른 경우 아무 작업도 수행하지 않음
  }
});


// '이용 완료'
$(".complete").on("click", function () {
    let $this = $(this);
    let $resBox = $this.closest('#contents-box');
    let btnBox = $this.closest('.button-wrap');
    let reservationNumber = $resBox.find('#resNumber').val();
    let reservationStatus = '이용 완료';
    let sitterNumber = $resBox.find('#sitterNumber').val();
    let resInfo = {
        reservationNumber : reservationNumber,
        reservationStatus : reservationStatus,
        sitterNumber : sitterNumber
    }

  if (confirm("이용 완료처리 하시겠습니까?")) {
      $.ajax({
          url : `/myPages/reservationList/completed`,
          type : 'patch',
          data : JSON.stringify(resInfo),
          contentType : 'application/json; charset=utf-8',
          success : function() {
              $resBox.find(".reservation-status").css("color", "#00bf00").text("이용 완료");
              btnBox.find('.review').css('display', 'block');
              btnBox.find('.basic-btn').css('display', 'none');
          }
      });
  } else {
  }
});


let reviewBtnTarget = null;

// '후기 작성'
$('.review').on('click', function() {
    $('#modal-container').css('display', 'flex');
    reviewBtnTarget = this;
    let resNumber = $(this).closest('#contents-box').find('.res-number').val();
    let sitterNumber = $(this).closest("#contents-box").find(".sitter-number").val();
    $('.resNumberForReview').val(resNumber);
    $('.sitterNumberForReview').val(sitterNumber);

    // 별점 선택하기
    $(".star-rating .fa-regular").click(function () {
        $(this).addClass("active");
        $(this).nextAll().addClass("active");
        $(this).prevAll().removeClass("active");
        let rating = $(this).prev("input.rating").val();
        $('#rate').val(rating);
    });
});


// 리뷰 저장하기 클릭
$('.update-btn').on('click', function() {
    let updateBtn = $(this);
    let reservationNumber = $('.resNumberForReview').val();
    let sitterNumber = $('.sitterNumberForReview').val();
    let reviewScore = $('#rate').val();
    let reviewContent = $('.reply-section').val();

    // 평점, 리뷰 유효성 검사
    let ratingValue = $(".rate").val(); // 별점 값 가져오기
    let comment = $(".reply-section").val(); // 댓글 내용 가져오기

    if (ratingValue === "0" || comment.trim() === "") {
        // 별점 또는 댓글이 비어있는지 확인
        alert("별점과 댓글을 모두 입력하세요.");
        return false; // 제출 방지
    }

    $.ajax({
       url : `/review/register`,
       type : 'post',
       data : JSON.stringify({
          reservationNumber : reservationNumber,
          sitterNumber : sitterNumber,
          reviewScore : reviewScore,
          reviewContent : reviewContent
       }),
       contentType : 'application/json; charset=utf-8',
       success : function () {
           console.log("리뷰 등록 완료");
           updateBtn.closest('#profile-contents').find('.reply-section').val('');
           updateBtn.closest('#profile-contents').find('.fa-regular').removeClass('active');
           updateBtn.closest('#modal-container').css('display', 'none');
           $(reviewBtnTarget).closest('.button-wrap').find('.review').css('display', 'none');
           $(reviewBtnTarget).closest('.button-wrap').find('.r-res').css('display', 'block');
           $(reviewBtnTarget).closest('.button-wrap').find('.my-review').css('display', 'block');
       },
        error : function (a, b, c) {
           console.log(c);
        }
    });
});

// '다시 예약하기' 클릭 > 해당 펫시터 예약 페이지로 이동
$('.r-res').on('click', function() {
    let sitterNumber = $(this).closest('#contents-box').find('#sitterNumber').val();
    console.log(sitterNumber);
    window.location.href = '/sitter/profile?sitterNumber=' + sitterNumber + '#moveScroll';
});

// '리뷰 보기' 클릭 > 해당 펫시터의 예약 페이지로 이동
$('.my-review').on('click', function() {
    let sitterNumber = $(this).closest('#contents-box').find('#sitterNumber').val();
    console.log(sitterNumber);
    window.location.href = '/sitter/profile?sitterNumber=' + sitterNumber + '#moveScroll';
});


