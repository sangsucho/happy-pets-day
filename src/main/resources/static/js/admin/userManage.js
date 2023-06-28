// input칸 엔터 이벤트
$('.search-txt').on('keypress', function (e) {
    if (e.code == 'Enter') {
        let message = $(this).val();
        searchAjax(message,searchList);
        $(this).val('');
    }
});

// 검색 버튼 클릭 이벤트 처리
$(".search-button").click(function () {
    var keyword = $(".search-txt").val();  // 입력된 검색어 가져오기

    // Ajax를 통해 검색 결과 요청
    searchAjax(keyword,searchList);
    $(".search-txt").val('');
});


function searchAjax(keyword,searchList) {
    $.ajax({
        url: "/usersManage/search",
        type: "get",
        data: {keyword: keyword},
        dataType: "json",
        success: function (data) {
            searchList(data);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}


function searchList(userList){
    // 검색 결과가 없는 경우 처리
    if (userList.length === 0) {
        $(".board-table tbody").html('<tr><td colspan="4" align="center">등록된 회원이 없습니다.</td></tr>');
        return;
    }

    // 검색 결과를 템플릿에 바인딩하여 업데이트
    var userListHtml = "";
    userList.forEach(function (user) {
        // userListHtml += '<tr>';
        // userListHtml += '<td class="no">' + user.userNumber + '</td>';
        // userListHtml += '<td class="user-id"><a href="/admin/userDetailManage?userNumber=' + user.userNumber + '">' + user.userId + '</a></td>';
        // userListHtml += '<td class="user-name">' + user.userName + '</td>';
        // userListHtml += '<td class="user-level">' + user.statusName + '</td>';
        // userListHtml += '</tr>';

        userListHtml += `
                    <tr>
                        <td class="no">${user.userNumber}</td>

                        <td class="user-id">
                            <a href="/admin/userDetailManage?userNumber=${user.userNumber}" >${user.userId}</a>
                         </td>
                        <td class="user-name" >${user.userName}</td>
                        <td class="user-level" >${user.statusName}</td>
                    </tr>
                `;

    });

    $(".board-table tbody").html(userListHtml);







}













