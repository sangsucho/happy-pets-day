// 산책게시판 검색버튼 클릭
$('.search-button').on('click', function () {
    boardSearchAjax(1);
});

// 산책게시판 검색 엔터키
$('.search-txt').on('keypress', function (e) {
    if (e.code == 'Enter') {
        e.preventDefault();
        boardSearchAjax(1);
    }
});

boardSearchAjax(1);

//산책게시판 검색 Ajax
function boardSearchAjax(page) {
    let keyword = $('.search-txt').val();
    let searchType = $('.search-select').val();

    let strollSearch = {
        keyword: keyword,
        searchType: searchType
    }

    $.ajax({
        url: `/strolls/v1/list/${page}`,
        type: 'post',
        data: JSON.stringify(strollSearch),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function (result) {
            showBardList(result,showBardListPage);
        },
        error: function (a, b, c) {
            console.error(c);
        }
    });
}

//산책게시글 리스트
function showBardList(map,showBardListPage) {
    let text = '';

    if (map.boardList.length == 0) {
        text += `
                <tr>
                    <td colspan="5" align="center">해당 조건으로 등록된 게시물이 없습니다.</td>
                </tr>
            `;
        console.log(text);
        $('.strollBoardList').html(text);
        showBardListPage(map.pageVo);
        return;
    }
    map.boardList.forEach(board => {
            text += `
                 <tr>
                    <td class="no">${board.strollBoardNumber}</td>
                    <td class="title">
                        <a href="/stroll/view?strollBoardNumber=${board.strollBoardNumber}">
                        ${board.strollBoardTitle}
                        </a>
                    </td>
                    <td class="author">${board.userId}</td>
                    <td class="date">${board.strollBoardMeetingDate}</td>
                    <td class="location">${board.strollBoardAdminDistrict}</td>
                </tr>
            `;
    });

    $('.strollBoardList').html(text);
    showBardListPage(map.pageVo);
}

function showBardListPage(pageVo) {
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

//검색결과 페이징
$('.page-ul').on('click','a', function (e) {
    e.preventDefault();
    let page = $(this).data('page');
    boardSearchAjax(page);
});