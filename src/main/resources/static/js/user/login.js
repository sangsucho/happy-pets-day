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

  // 로그인 버튼 클릭 이벤트 처리
  $(document).on("click", "#submit", function(event) {
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

});