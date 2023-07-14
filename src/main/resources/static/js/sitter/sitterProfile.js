// 초기에 보여지는 댓글 개수 설정
var visibleReviews = 3;

// 모든 리뷰 요소들을 선택합니다.
var reviewElements = document.getElementsByClassName("review-review");

if (reviewElements.length <= visibleReviews) {
    document.getElementsByClassName("review-more-btn")[0].style.display =
        "none";
}

// 보여지는 리뷰 개수를 제어하는 함수입니다.
function controlVisibleReviews() {
    // 추가로 보여질 리뷰 개수를 설정합니다.
    var additionalReviews = 3;


    // 추가로 보여질 리뷰 개수만큼 리뷰 요소들을 반복하여 보이도록 설정합니다.
    for (var i = visibleReviews; i < visibleReviews + additionalReviews; i++) {
        if (i < reviewElements.length) {
            reviewElements[i].style.display = "block";
        }
    }

    // 보여지는 리뷰 개수를 업데이트합니다.
    visibleReviews += additionalReviews;

    // 더 이상 보여질 리뷰가 없을 경우, "더 보기" 버튼을 숨깁니다.
    if (visibleReviews >= reviewElements.length) {
        document.getElementsByClassName("review-more-btn")[0].style.display =
            "none";
    }
}

// 초기에는 보여지는 리뷰 개수를 설정한 만큼만 보이도록 합니다.
for (var i = 0; i < visibleReviews; i++) {
    if (i < reviewElements.length) {
        reviewElements[i].style.display = "block";
    }
}

// "더 보기" 버튼을 클릭했을 때, 댓글을 추가로 보여주도록 합니다.
document
    .getElementsByClassName("review-more-btn")[0]
    .addEventListener("click", controlVisibleReviews);

// 초기에는 추가로 보여질 리뷰를 숨깁니다.
for (var i = visibleReviews; i < reviewElements.length; i++) {
    reviewElements[i].style.display = "none";
}

// ========================================

$(document).ready(function () {
    calendarInit();
});

/*
  달력 렌더링 할 때 필요한 정보 목록 

  현재 월(초기값 : 현재 시간)
  금월 마지막일 날짜와 요일
  전월 마지막일 날짜와 요일
*/

function calendarInit() {
    // 날짜 정보 가져오기
    var date = new Date(); // 현재 날짜(로컬 기준) 가져오기
    var utc = date.getTime() + date.getTimezoneOffset() * 60 * 1000; // uct 표준시 도출
    var kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
    var today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)

    var thisMonth = new Date(
        today.getFullYear(),
        today.getMonth(),
        today.getDate()
    );

    // 달력에서 표기하는 날짜 객체

    var currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
    var currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
    var currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

    // var resultDate = currentYear + "-" + (currentMonth + 1) + "-" + currentDate;

    // console.log(resultDate);

    // kst 기준 현재시간
    // console.log(thisMonth);

    // 캘린더 렌더링
    renderCalender(thisMonth);

    function renderCalender(thisMonth) {
        // 렌더링을 위한 데이터 정리
        currentYear = thisMonth.getFullYear();
        currentMonth = thisMonth.getMonth();
        currentDate = thisMonth.getDate();


        // 이전 달의 마지막 날 날짜와 요일 구하기
        var startDay = new Date(currentYear, currentMonth, 0);
        var prevDate = startDay.getDate();
        var prevDay = startDay.getDay();

        // 이번 달의 마지막날 날짜와 요일 구하기
        var endDay = new Date(currentYear, currentMonth + 1, 0);
        var nextDate = endDay.getDate();
        var nextDay = endDay.getDay();

        // console.log(prevDate, prevDay, nextDate, nextDay);

        // 현재 월 표기
        $(".year-month").text(currentYear + "." + (currentMonth + 1));

        // 렌더링 html 요소 생성
        calendar = document.querySelector(".dates");
        calendar.innerHTML = "";

        // 오늘 날짜
        var todayDate = today.getDate();

        // 지난달
        for (var i = prevDate - prevDay; i <= prevDate; i++) {
            if (currentMonth === 0) {
                // 이전 달이 1월인 경우, 이전 해의 12월로 설정
                calendar.innerHTML =
                    calendar.innerHTML + '<div class="day prev disable">' + i + "</div>";
            } else {
                calendar.innerHTML =
                    calendar.innerHTML + '<div class="day prev disable">' + i + "</div>";
            }
        }

        // 이번 달
        for (var i = 1; i <= nextDate; i++) {
            if (todayDate > i && currentMonth === today.getMonth()) {
                // 오늘 날짜 이전의 날짜는 disable 클래스를 추가하여 스타일링
                calendar.innerHTML =
                    calendar.innerHTML +
                    '<div class="day current disable">' +
                    i +
                    "</div>";
            } else {
                calendar.innerHTML =
                    calendar.innerHTML + '<div class="day current">' + i + "</div>";
            }
        }

        let sitterNumber = $('.sitterNumber').val();
        console.log(sitterNumber);

        $.ajax({
            url: "/sitters/reservations", //url
            method: "GET",
            data: {sitterNumber: sitterNumber},
            success: function (response) {
                var soldDates = response.map(date => date.substr(8, 2));
                var soldMonth = response.map(date => date.substr(6, 1));

                $(".dates .day").each(function () {
                    var dateText = $(this).text();
                    var monthText = $(".year-month").text().substr(5, 1);

                    for (var i = 0; i < soldDates.length; i++) {
                        if (dateText === soldDates[i] && monthText === soldMonth[i]) {
                            $(this).addClass("disable");
                        }
                    }

                });
            },
            error: function (xhr, status, error) {
                console.error("데이터를 가져오는 데 실패했습니다.", error);
            },
        });


        // 다음달
        for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
            if (currentMonth === 11) {
                // 다음 달이 12월인 경우, 다음 해의 1월로 설정
                calendar.innerHTML =
                    calendar.innerHTML + '<div class="day next disable">' + i + "</div>";
            } else {
                calendar.innerHTML =
                    calendar.innerHTML + '<div class="day next disable">' + i + "</div>";
            }
        }
    }

    // 이전달로 이동
    $(".go-prev").on("click", function () {
        thisMonth = new Date(currentYear, currentMonth - 1, 1);
        renderCalender(thisMonth);
    });

    // 다음달로 이동
    $(".go-next").on("click", function () {
        thisMonth = new Date(currentYear, currentMonth + 1, 1);
        renderCalender(thisMonth);
    });
}

// ========================================
$(document).ready(function () {
    calendarInit();


    // 날짜 클릭 이벤트 처리
    $(".dates").on("click", ".day.current", function () {
        // 기존에 선택된 날짜의 클래스 제거
        $(".dates .day.current.selected").removeClass("selected");

        // 선택된 날짜에 클래스 추가
        $(this).addClass("selected");
        // 선택된 날짜의 정보 가져오기
        var selectedDate = $(this).text(); // 선택된 날짜의 텍스트 가져오기
        var selectedMonth = $(".year-month").text().split(".")[1] - 1; // 선택된 월 가져오기 (0부터 시작하므로 1을 빼줌)
        var selectedYear = $(".year-month").text().split(".")[0]; // 선택된 연도 가져오기

        // Date 객체 생성하여 선택된 날짜의 정보 설정
        var dateObject = new Date(selectedYear, selectedMonth, selectedDate);

        // 선택된 날짜의 정보 출력
        // console.log(dateObject);
        // console.log(selectedYear + "-" + selectedMonth + "-" + selectedDate);
        // console.log(resultDate);
        // 예약 버튼의 데이터 속성에 선택된 날짜 설정
        $(".reser-btn button").data("selectedDate", dateObject);
    });
});

$(document).ready(function () {
    calendarInit();

    // 예약 버튼 클릭 이벤트 처리
    $(".reser-btn button").on("click", function () {
        let sitterNum = $('.sitterNumber').val();
        // 선택된 날짜 가져오기
        var selectedDate = $(this).data("selectedDate");

        let dateInfo = new Date(selectedDate);
        let dateResult = dateInfo.getFullYear() + "-" + (dateInfo.getMonth() + 1) + "-" + dateInfo.getDate();


        if (selectedDate) {
            // 서버에 전송할 데이터 객체 생성
            var data = {
                reservationDate: dateResult, // 선택된 날짜를 ISO 8601 형식의 문자열로 변환하여 전송
                sitterNumber: sitterNum
            };

        // 선택된 날짜를 서버에 저장하기 위해 AJAX 요청 보내기
        $.ajax({
            url: '/sitters/reserve', // 서버에서 날짜 저장을 처리하는 URL
            method: 'POST',
            data: data,
            success: function () {
                // 요청이 성공적으로 처리되었을 때 실행되는 콜백 함수
                console.log('날짜가 성공적으로 저장되었습니다.');
                window.location.href = '/sitter/list';
            },
            error: function (xhr, status, error) {
                // 요청이 실패했을 때 실행되는 콜백 함수
                console.error('날짜 저장에 실패했습니다.', error);
                alert("로그인 후 이용하세요.")
                window.location.href = '/user/login';
            }
        });
        } else {
            // 날짜가 선택되지 않았을 때 처리
            console.log('날짜를 선택해주세요.');
        }
    });
});


//=====================================================================
let $reserveBtn = $(".reser-btn > button");


$reserveBtn.on("click", function () {

})

$('.modify-btn').on('click', function(){
  let sitterNumber = $('.sitterNumber').val();
  window.location.href = '/sitter/modifyInfo?sitterNumber=' + sitterNumber;
});


document.addEventListener("DOMContentLoaded", function () {

    function handleRating(rating) {
        var fillStars = document.querySelector(".star-ratings-fill");
        var fillWidth = (rating / 5) * 100 + "%";
        fillStars.style.width = fillWidth;
    }

  handleRating();
});

