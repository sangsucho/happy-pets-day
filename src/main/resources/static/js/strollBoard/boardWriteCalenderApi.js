$.datepicker.setDefaults({
    dateFormat: 'yy-mm-dd',
    prevText: '이전 달',
    nextText: '다음 달',
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
});

$(function () {
    var currentDate = new Date();
    $("#datepicker").datepicker({
        minDate: currentDate,
        onSelect: function (dateText) {
            $("#selectedDate").val(dateText);
        },
        beforeShowDay: function (date) {
            var selectedDate = $("#selectedDate").datepicker('getDate');
            if (selectedDate !== null) {
                if (date.getTime() === selectedDate.getTime()) {
                    return [true, 'selectedDate'];
                }
            }
            return [true, ''];
        }
    });
});


let today = new Date();

let year = today.getFullYear();
let month = ('0' + (today.getMonth() + 1)).slice(-2);
let day = ('0' + today.getDate()).slice(-2);

let dateString = year + '-' + month + '-' + day;

$("#selectedDate").val(dateString);

