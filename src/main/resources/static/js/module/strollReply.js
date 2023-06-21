

// 댓글 작성
export function add(reply,error){
    $.ajax({
        url : `/replies/reply`,
        type : 'post',
        data : JSON.stringify(reply),//우리가 보낼 데이터를 JSON형태로 보내준다. ->자바스크립트 객체를 JSON으로
        contentType: 'application/json; charset=utf-8', //보낼 데이터의 형식을 알려준다.
        success : function (){
            // if(callback){
            //     callback();
            // }
        },
        error : error
    });
}