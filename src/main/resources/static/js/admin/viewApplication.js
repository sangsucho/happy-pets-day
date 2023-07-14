//목록으로 가기
$('.list').on('click', function () {
    window.location.href = '/admin/applicationManage';
});

// applyStatus '승인 완료'로 변경,일반회원->펫시터로 변경(추가)
$('.approve').on('click', function () {
    let confirmMessage = $(this).data('confirm');
    if (confirm(confirmMessage)) {
        modify();
        alert('신청이 승인 됐습니다.');
        window.location.href = "/admin/applicationManage"
    }
})

function modify() {
    let applyStatus = $('.apply-status').val();
    let userNumber = $('.user-number').val();
    let applyNumber = $('.apply-number').val();
    let sitterStatus = $('.sitter-status').val();
    let userStatus = $('.approve').val();
    let apply = {
        applyNumber: applyNumber,
        applyStatus: applyStatus,
        userNumber: userNumber,
        sitterStatus: sitterStatus,
        userStatus: userStatus
    }


    $.ajax({
        url: `/usersManage/add`,
        type: 'patch',
        data: JSON.stringify(apply),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            // console.log("ajax연결 완료")
        },
        error: function (a, b, c) {
            console.log(c);
        }
    });
}


// applyStatus '승인 거절'로 변경
$('.refuse').on('click', function () {
    let confirmMessage = $(this).data('confirm');
    if (confirm(confirmMessage)) {
        modifyRefuse();
        alert('신청이 거절 됐습니다.');
        window.location.href = "/admin/applicationManage";
    }
})

function modifyRefuse() {
    let applyStatus = $('.apply-status').val();
    let userNumber = $('.user-number').val();
    let applyNumber = $('.apply-number').val();
    let applyRefuse = {
        applyNumber: applyNumber,
        applyStatus: applyStatus,
        userNumber: userNumber
    }


    $.ajax({
        url: `/usersManage/refuse`,
        type: 'patch',
        data: JSON.stringify(applyRefuse),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            console.log("ajax연결 완료")
        },
        error: function (a, b, c) {
            console.log(c);
        }
    });
}

