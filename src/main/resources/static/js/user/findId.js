//아이디 유효성 검사 변수값
var nameInput = document.getElementById("name");
var nameConfirmMsg = document.querySelector(".confirmMsg");

//핸드폰번호 유효성 검사 변수값
var phoneInput = document.getElementById("phone");
var phoneConfirmMsg = document.querySelectorAll(".confirmMsg")[0];

// 아이디 유효성 검사
nameInput.addEventListener("input", function () {
  var name = nameInput.value;
  if (name.length < 2 || name.length >= 10 || !/^[A-Za-z가-힣]+$/.test(name)) {
    nameConfirmMsg.innerHTML = "2~10자의 한글이나 영문으로 입력해주세요.";
    nameConfirmMsg.classList.add("error");
  } else {
    nameConfirmMsg.innerHTML = "";
    nameConfirmMsg.classList.remove("error");
  }
});

// 핸드폰번호 유효성 검사
phoneInput.addEventListener("input", function () {
  if (phoneInput.validity.valid) {
    phoneConfirmMsg.innerHTML = "";
    phoneConfirmMsg.classList.remove("error");
  } else {
    phoneConfirmMsg.innerHTML = "올바른 핸드폰 번호를 입력해주세요.";
    phoneConfirmMsg.classList.add("error");
  }
});

// 페이지 탭 링크 요소들을 가져옵니다.
var pageTabLinks = document.querySelectorAll(".page-tab li a");

// 각 페이지 탭 링크에 클릭 이벤트를 추가합니다.
pageTabLinks.forEach(function (link) {
  link.addEventListener("click", function (event) {
    // 모든 페이지 탭 링크에서 색상 초기화
    pageTabLinks.forEach(function (link) {
      link.style.color = "";
    });

    // 클릭한 링크의 글씨 색상을 검은색으로 변경
    this.style.color = "black";
  });
});
