// 모달
let modal = $("#modal-container");

// '수정' 클릭 > 모달 띄워주기
$(".modify1").on("click", function () {
  modal.show();
});

// 'X' 클릭 > 모달 끄기
$(".btn-close").on("click", function () {
  modal.hide();
});

// '동물 종류' 드롭다운 메뉴 열기/닫기
$(".dropdown-btn").on("click", function () {
  if ($(".menu-list").css("display") == "none") {
    $(".menu-list").css("display", "block");
  } else {
    $(".menu-list").css("display", "none");
  }
});

$(".list button").on("click", function () {
  let selectValue = $(this).text();
  $(".dropdown-btn span").text(selectValue);
  $(".menu-list").css("display", "none");
  $("#etcDirect").val("");
});

$(".etc-direct").on("click", function () {
  $("#etcDirect").attr("type", "text");
  $("#etcDirect").focus();
});

$(document).ready(function () {
  $("#dog, #cat").on("click", function () {
    $("#etcDirect").attr("type", "hidden");
    $("#etcDirect").val($(this).val());
  });
});

// 선택한 이미지 원 안에 삽입
function updateImage(input) {
  let image = document.getElementById("uploaded-image");
  if (input.files && input.files[0]) {
    let reader = new FileReader();
    reader.onload = function (e) {
      image.src = e.target.result;
    };
    reader.readAsDataURL(input.files[0]);
  }
}

// 별점
$(".star-rating .fa-regular").click(function () {
  $(this).addClass("active");
  $(this).nextAll().addClass("active");
  $(this).prevAll().removeClass("active");
  var ratingValue = $(this).prevAll().length + 1;
  $("#rate").val(ratingValue);
});

// 댓글 유효성

$(document).ready(function () {
  $("#updateBtn").click(function () {
    var ratingValue = $(".rate").val(); // 별점 값 가져오기
    var comment = $(".reply-section").val(); // 댓글 내용 가져오기

    if (ratingValue === "0" || comment.trim() === "") {
      // 별점 또는 댓글이 비어있는지 확인
      alert("별점과 댓글을 모두 입력하세요.");
      return false; // 제출 방지
    }
  });
});
