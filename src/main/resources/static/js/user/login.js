//아이디 유효성 검사 변수값
var idInput = document.getElementById("id");
var idConfirmMsg = document.querySelectorAll(".confirmMsg")[0];

//비밀번호 유효성 검사 변수값
var pwInput = document.getElementById("password");
var pwConfirmMsg = document.querySelectorAll(".confirmMsg")[1];

// 아이디 유효성 검사
idInput.addEventListener("input", function () {
  var id = idInput.value;
  if (!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,12}$/.test(id)) {
    idConfirmMsg.innerHTML =
      "영어와 숫자를 조합한 6~12글자의 아이디를 입력해주세요.";
    idConfirmMsg.classList.add("error");
  } else {
    idConfirmMsg.innerHTML = "";
    idConfirmMsg.classList.remove("error");
  }
});

// 비밀번호 유효성 검사
pwInput.addEventListener("input", function () {
  var password = pwInput.value;
  if (
    !/^(?=.*[a-zA-Z])(?=.*\d)(?=.*[!@#$%^&*])[a-zA-Z\d!@#$%^&*]{8,15}$/.test(
      password
    )
  ) {
    pwConfirmMsg.innerHTML =
      "8~15 글자의 문자/숫자/특수문자 필수 조합이어야 합니다.";
    pwConfirmMsg.classList.add("error");
  } else {
    pwConfirmMsg.innerHTML = "";
    pwConfirmMsg.classList.remove("error");
  }
});
