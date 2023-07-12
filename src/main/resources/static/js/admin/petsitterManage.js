// input칸 엔터 이벤트
$('.search-txt').on('keypress', function (e) {
    if (e.code == 'Enter') {
        let message = $(this).val();
        searchAjax(message,1,searchList,showSearchPage);
    }
});

// 검색 버튼 클릭 이벤트 처리
$(".search-button").click(function () {
    let keyword = $(".search-txt").val();  // 입력된 검색어 가져오기
    // Ajax를 통해 검색 결과 요청
    searchAjax(keyword,1,searchList,showSearchPage);
});

searchAjax('',1,searchList,showSearchPage);

function searchAjax(keyword,page,searchList,showSearchPage) {
    $.ajax({
        url: `/usersManage/sitterSearch/${page}`,
        type: "get",
        data:{keyword: keyword},
        dataType: "json",
        success: function (data) {
            console.log(data);
            searchList(data.sitterList);
            showSearchPage(data.pageVo);
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}


function searchList(sitterList) {
    // 검색 결과가 없는 경우 처리
    if (sitterList.length === 0) {
        $(".board-table tbody").html('<tr><td colspan="6" align="center">등록된 회원이 없습니다.</td></tr>');
        return;
    }

    // 검색 결과를 템플릿에 바인딩하여 업데이트
    let sitterListHtml = "";
    sitterList.forEach(function (sitter) {

        sitterListHtml += `
                    <tr>
                        <td class="no">${sitter.sitterNumber}</td>

                        <td class="user-id">
                            <a href="/admin/petsitterDetailView?sitterNumber=${sitter.sitterNumber}" >${sitter.userId}</a>
                         </td>
                        <td class="specialty" >${sitter.petFieldName}</td>
                        <td class="phone-num" >${sitter.userPhoneNumber}</td>
                        <td class="review" >${sitter.reviewCount}</td>
                        <td class="average" >${sitter.reviewAvg}</td>
                    </tr>
                `;

    });

    $(".board-table tbody").html(sitterListHtml);

}

//  검색조회결과 페이징 처리
function showSearchPage(pageVo) {
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

$('.page-ul').on('click','a', function (){
    let keyword = $(".search-txt").val();
    let page = $(this).data('page');

    searchAjax(keyword, page, searchList, showSearchPage);
})
