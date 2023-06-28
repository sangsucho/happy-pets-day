// 아이디 유효성 검사 변수값
var idInput = document.getElementById("id");
var idConfirmMsg = document.getElementById("idConfirmMsg");

// 비밀번호 유효성 검사 변수값
var pwInput = document.getElementById("password");
var pwConfirmMsg = document.getElementById("pwConfirmMsg");

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

// 아이디 저장
$(document).ready(function() {
  var idInput = $("#id");
  var saveIdCheckbox = $("#saveId");

  // 아이디 저장 여부 확인
  var savedId = getSavedId();
  if (savedId) {
    idInput.val(savedId);
    saveIdCheckbox.prop("checked", true);
  }

  // 아이디 저장 버튼 클릭 이벤트 처리
  $(document).on("click", "#saveId", function(event) {
    var userId = idInput.val();

    // 아이디 저장 여부 확인
    if (saveIdCheckbox.is(":checked")) {
      saveId(userId);
    } else {
      removeSavedId();
    }
  });

  // 아이디 저장 및 삭제 함수
  function saveId(userId) {
    // 로컬 스토리지에 아이디 저장
    localStorage.setItem("savedId", userId);
  }

  function getSavedId() {
    // 로컬 스토리지에서 저장된 아이디 가져오기
    var savedId = localStorage.getItem("savedId");
    return savedId;
  }

  function removeSavedId() {
    // 로컬 스토리지에서 저장된 아이디 삭제
    localStorage.removeItem("savedId");
    idInput.val(""); // 아이디 입력란 비우기
  }

  // 로그아웃 클릭 이벤트 처리
  $(document).on("click", "#logoutButton", function(event) {
    removeSavedId(); // 저장된 아이디 삭제
  });

  // 로그인 페이지로 이동 시 아이디 입력란 초기화
  $(document).on("click", "#loginLink", function(event) {
    idInput.val(""); // 아이디 입력란 비우기
  });
});
