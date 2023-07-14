//펫시터->일반회원 강등
$('.btn-degrade').on('click',function (){
    let confirmMessage = $(this).data('confirm');
    let sitterNumber = $('.sitter-number').val();
    if (confirm(confirmMessage)) {
        sitterModify(sitterNumber);
        alert('회원이 강등되었습니다');
    }
    window.location.href=`/admin/petsitterManage`;
});


function sitterModify(sitterNumber) {
    let sitterStatus = $('.btn-degrade').val();
    let userStatus = $('.user-status').val();
    let userNumber = $('.user-number').val();
    let petFieldName = $('.pet-FieldName').val();
    let applyFileTitle = $('.apply-FileTitle').val();

    let sitter = {
        sitterNumber:sitterNumber,
        sitterStatus:sitterStatus,
        userNumber:userNumber,
        userStatus:userStatus,
        petFieldName:petFieldName,
        applyFileTitle:applyFileTitle
    }
    console.log(sitter);

    $.ajax({
        url : `/usersManage/demotion`,
        type : 'patch',
        data : JSON.stringify(sitter),
        contentType : 'application/json; charset=utf-8',
        success : function(){
            console.log("ajax연결 완료")

        },
        error : function (a,b,c){
            console.log(c);
        }
    });
}

//회원 삭제
$('.btn-delete').on('click',function (){
    let confirmMessage = $(this).data('confirm');
    let userNumber = $('.user-number').val();
    if (confirm(confirmMessage)) {
        modify(userNumber);
        alert('회원이 삭제되었습니다');
    }
    window.location.href=`/admin/petsitterManage`
})


function modify(userNumber) {
    let userStatus = $('.btn-delete').val();

    let user = {
        userNumber:userNumber,
        userStatus:userStatus
    }


    $.ajax({
        url : `/usersManage/delete`,
        type : 'patch',
        data : JSON.stringify(user),
        contentType : 'application/json; charset=utf-8',
        success : function(){
            // console.log("ajax연결 완료")

        },
        error : function (a,b,c){
            console.log(c);
        }
    });
}