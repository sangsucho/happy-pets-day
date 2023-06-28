
$('.btn-delete').on('click',modify)


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