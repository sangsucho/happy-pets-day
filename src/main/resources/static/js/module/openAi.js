// gpt 통신
export function sendMessage(aiChatArr, callback, defaultChatMsg) {
    $.ajax({
        url: '/chatBot/question',
        type: 'post',
        data : sessionStorage.getItem('aiChatData'),
        contentType:'application/json; charset=utf-8',
        success: function (result){

            let message = result.choices[0].message.content;

            // 챗봇으로 받은 메세지를 배열에 저장
            aiChatArr.push({role: 'assistant', content: message});
            // //저장한 배열을 세션스토리지에 저장
            sessionStorage.setItem('aiChatData', JSON.stringify(aiChatArr));
            callback(message);
            defaultChatMsg();
        },
        error: function (a, b, c) {
            console.error(c);
            let message = '챗봇과 연결이 되지 않았습니다. 나중에 다시 시도해 주세요';
            aiChatArr.push( {role:'assistant',content:message}  );
            //저장한 배열을 세션스토리지에 저장

            sessionStorage.setItem('aiChatData', JSON.stringify(aiChatArr));
            callback(message);
            defaultChatMsg();
        }
    });


}