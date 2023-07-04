

// 댓글 작성
export function add(reply,callback,callbackPage,error){
    $.ajax({
        url : `/replies/reply`,
        type : 'post',
        data : JSON.stringify(reply),//우리가 보낼 데이터를 JSON형태로 보내준다. ->자바스크립트 객체를 JSON으로
        contentType: 'application/json; charset=utf-8', //보낼 데이터의 형식을 알려준다.
        success : function (){
            if(callback){
                callback(callbackPage);
            }
        },
        error : error
    });
}

// 댓글 리스트 가져오기
export function getListPage(pageInfo,callback, callbackPage, error){
    $.ajax({
        url : `/replies/list/${pageInfo.strollBoardNumber}/${pageInfo.page}`,
        type : 'get',
        dataType : 'json',
        success : function (result){
            if(callback){
                callback(result,callbackPage);
            }
        },
        error : error
    });
}

// 댓글 수정
export function modify(reply,pageInfo,callback,callbackPage,error) {
    $.ajax({
        url : `/replies/${reply.strollReplyNumber}`,
        type : 'patch',
        data : JSON.stringify(reply),
        contentType: 'application/json; charset=utf-8', //보낼 데이터의 형식을 알려준다.
        success : function (){
            if(callback){
                getListPage(pageInfo,callback,callbackPage,error);
            }
        },
        error : error
    });
}

// 댓글 삭제
export function remove(strollReplyNumber,pageInfo,callback,callbackPage,error){
    $.ajax({
        url : `/replies/${strollReplyNumber}`,
        type : 'delete',
        success : function (){
            if(callback){
                getListPage(pageInfo,callback,callbackPage,error);
            }
        },
        error : error
    });
}



//댓글 시간표시
export function timeForToday(value){
    // new Date() 현재 날짜와 시간
    const today = new Date();
    const timeValue = new Date(value);

    // Math.floor()는 소수점을 내림 처리 해준다.
    // getTime()은 1970년 01/01 을 기준으로 지금까지 몇 ms가 지났는지 알려준다.
    const betweenTime = Math.floor((today.getTime() - timeValue.getTime()) / 1000 / 60);

    if(betweenTime < 1) { return "방금 전"; }
    if(betweenTime < 60) {
        return `${betweenTime}분 전`;
    }

    const betweenTimeHour = Math.floor(betweenTime / 60);
    if(betweenTimeHour < 24){
        return `${betweenTimeHour}시간 전`;
    }

    const betweenTimeDay = Math.floor(betweenTime / 60 / 24);
    if(betweenTimeDay < 365){
        return `${betweenTimeDay}일 전`;
    }

    return `${Math.floor(betweenTimeDay / 365)}년 전`;
}






