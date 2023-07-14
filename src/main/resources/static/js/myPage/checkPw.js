// 회원번호로 이름 가져오기
$(document).ready(function () {
    getUserName();

    function getUserName() {
        $.ajax({
            url: "/myPages/userName",
            type: "GET",
            dataType: "text",
            success: function (userName) {
                updateUserName(userName);
            },
            error: function (xhr, status, error) {
                console.error("Failed to fetch userName:", error);
            }
        });
    }

    function updateUserName(userName) {
        $('.font-bold.user-name').text(userName);
    }
});