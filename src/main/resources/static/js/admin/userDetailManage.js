$('.btn-delete').on('click',function (){
    let confirmMessage = $(this).data('confirm');
    if (confirm(confirmMessage)) {
        modify();
        alert('회원이 삭제되었습니다');
    }
    let userNumber = $('.user-number').val();
    window.location.href=`/admin/userDetailManage?userNumber=${userNumber}`
})


function modify() {
    let userStatus = $('.btn-delete').val();
    let userNumber = $('.user-number').val();
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