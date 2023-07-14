
// 회원번호로 시터번호 가져오기
$.ajax({
   url : `/myPages/sitter/profile/view`,
   type : 'get',
   dataType : 'json',
   success : function(result) {
      console.log(result);
      if (result != null) {
      $('.viewProfile').attr("href", `/sitter/profile?sitterNumber=${result}#moveScroll`);
      } else if (result == null) {
         alert('펫시터 신청을 해주세요 !');
      }
   },
   error : function(a, b, c) {
      console.log(c);
   }
});