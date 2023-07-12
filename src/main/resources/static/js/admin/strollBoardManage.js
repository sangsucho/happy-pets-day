// input칸 엔터 이벤트
$('.search-txt').on('keypress', function (e) {
    if (e.code == 'Enter') {
        let message = $(this).val();
        searchAjax(message, 1, searchList, showSearchPage);
    }
});

// 검색 버튼 클릭 이벤트 처리
$(".search-button").click(function () {
    let keyword = $(".search-txt").val();  // 입력된 검색어 가져오기
    // Ajax를 통해 검색 결과 요청
    searchAjax(keyword, 1, searchList, showSearchPage);
});

searchAjax('', 1, searchList, showSearchPage);

function searchAjax(keyword, page, searchList, showSearchPage) {
    $.ajax({
        url: `/usersManage/postSearch/${page}`,
        type: "get",
        data: {keyword: keyword},
        dataType: "json",
        success: function (data) {
            console.log(data);
            searchList(data.postList);
            showSearchPage(data.pageVo);
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}


function searchList(postList) {
    // 검색 결과가 없는 경우 처리
    if (postList.length === 0) {
        $(".board-table tbody").html('<tr><td colspan="4" align="center">등록된 회원이 없습니다.</td></tr>');
        return;
    }

    // 검색 결과를 템플릿에 바인딩하여 업데이트
    let postListHtml = "";
    postList.forEach(function (board) {
        postListHtml += `
                    <tr>
                        <td class="no">${board.strollBoardNumber}</td>

                        <td class="title">
                            <a href="/stroll/view?strollBoardNumber=${board.strollBoardNumber}" >${board.strollBoardTitle}</a>
                         </td>
                        <td class="author">${board.userId}</td>
                        <td class="date">${board.strollBoardMeetingDate}</td>
                        <td class="location">${board.strollBoardAdminDistrict}</td>
                    </tr>
                `;

    });

    $(".board-table tbody").html(postListHtml);

}

//  검색조회결과 페이징 처리
function showSearchPage(pageVo) {
    console.log(pageVo);
    let pageText = '';

    if (pageVo.prev) {
        pageText +=
            `
            <li><a href="javascript:void(0)" data-page="${pageVo.startPage - 1}" class="prev">&lt;</a></li>
            `;
    }

    for (let i = pageVo.startPage; i <= pageVo.endPage; i++) {
        pageText += `
           <li if="${i == pageVo.criteria.page}"><a href="javascript:void(0)" data-page="${i}"
              ${i == pageVo.criteria.page ? `class="active"` : ""}>${i}</a></li>
           `;
    }
    if (pageVo.next) {
        pageText += `
                <li><a href="javascript:void(0)" data-page="${pageVo.endPage + 1}" class="next">&gt;</a></li>
            `;
    }

    $('.page-ul').html(pageText);
}

$('.page-ul').on('click', 'a', function () {
    let keyword = $(".search-txt").val();
    let page = $(this).data('page');

    searchAjax(keyword, page, searchList, showSearchPage);
})