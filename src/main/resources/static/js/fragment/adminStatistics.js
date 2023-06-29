// 전체회원 수 ajax로 띄우기
function getTotalCount(error){
    $.ajax({
        url : `/usersManage/usersNumberCount`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            // console.log(result);
            $(".whole-user-number").text(result.totalUserNumber);
            $(".basic-user-number").text(result.basicUserNumber);
            $(".whole-petsitter-number").text(result.sitterUserNumber);
            $(".unapproved-petsitter-number").text(result.unapprovedSitterNumber);
        },
        error : error
    });
}

// 페이지 로드 시 전체회원 수를 설정하는 함수 호출
$(document).ready(function() {
    getTotalCount(errorMsg);
});
function errorMsg (a,b,c){
    console.log(c);
};