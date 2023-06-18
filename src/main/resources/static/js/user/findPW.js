//아이디 유효성 검사 변수값
var idInput = document.getElementById("id");
var idConfirmMsg = document.querySelectorAll(".confirmMsg")[0];

//보안질문 유효성 검사
var questionInput = document.getElementById("question");
var questionConfirmMsg = document.querySelectorAll(".confirmMsg")[0];

//보안질문 유효성 검사
var answerInput = document.getElementById("answer");
var answerConfirmMsg = document.querySelectorAll(".confirmMsg")[0];

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

function toggleSelectList() {
  var selectList = document.getElementById("selectList");
  selectList.style.display =
    selectList.style.display === "none" ? "block" : "none";
}

function selectQuestion(questionIndex) {
  var questionText =
    document.getElementsByClassName("select-item")[questionIndex].innerText;
  var selectedQuestion = document.getElementById("selectedQuestion");
  selectedQuestion.innerText = questionText;
  selectedQuestion.style.color = "black"; // 폰트 색상을 블랙으로 변경

  toggleSelectList(); // selectList 숨기기
}

function selectList(select) {
  var selectBox = document.querySelector(".question-select-box");
  var selectedQuestion = document.getElementById("answer");

  selectBox.innerHTML = select;
  selectedQuestion.value = select;

  toggleSelectList(); // selectList 숨기기
}

//보안 질문 답변 유효성 검사
answerInput.addEventListener("input", function () {
  var answer = answerInput.value;

  if (
    answer.length < 2 ||
    answer.length >= 10 ||
    !/^[A-Za-z가-힣]+$/.test(answer)
  ) {
    answerConfirmMsg.innerHTML = "2~10자의 한글이나 영문으로 입력해주세요.";
    answerConfirmMsg.classList.add("error");
  } else {
    answerConfirmMsg.innerHTML = "";
    answerConfirmMsg.classList.remove("error");
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
