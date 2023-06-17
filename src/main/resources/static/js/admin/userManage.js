// input칸 엔터 이벤트
$('.search-txt').on('keypress', function (e) {
    if (e.code == 'Enter') {
        let message = $(this).val();
        console.log(message);
        console.log('안녕'+message+'하세요');
        console.log(`안녕${message}하세요`);

        search(message);

        $(this).val('');
    }
});