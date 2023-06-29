// ajax를 실행시키는 함수가 있어야 한다.
// 스크롤해서 바닥에 닿으면 ajax 실행시켜야 한다.
// http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/38/ <- 38 부분을 6개 단위로 끊어야 한다.
// 그래서 1과 38 부분을 변수를 줘서 부딪힐때마다 변경되게 해야한다.

// a 태그 페이징 이동 막고 클릭 이벤트 발생 되면 자바스크립트 안에서 폼을 만든다.
// 히든으로 상세페이지에서 사용할 정보를 넣어놔야 한다
// 폼으로 보낸걸 컨트롤러로 받는데 그 때 VO를 사용해서 받는다.
// VO에 center의 정보도 넣어준다.
viewList();
function viewList() {
    $.ajax({
        url: "http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/100/",
        type: "get",
        dataType: "json",
        success: function (result) {
            if (result.TbAdpWaitAnimalView && result.TbAdpWaitAnimalView.row) {
                let list = result.TbAdpWaitAnimalView.row;
                makeList(list);
            }
        },
    });
}

function makeList(obj) {
    console.log(obj);
    let li = "";
    obj.forEach(b => {
        let fullName = `${b.NM}`;
        let centerName = fullName.split('(')[1].split('-')[0].split(')')[0];
        $('.board-title').text(centerName);

        let petName = fullName.split('(')[0];
        $('.board-middle').text(petName);

        let petSpcs = `${b.SPCS}`;
        if (petSpcs == 'CAT') {
            petSpcs = '고양이';
        } else if (petSpcs == 'DOG') {
            petSpcs = '강아지';
        }

        li += `
      <li class="board-data">
        <a href="javascript:void(0)" class="slide-card">
          <span class="board-img">
            <img
              src="https://animal.seoul.go.kr/comm/getImage?srvcId=MEDIA&upperNo=${b.ANIMAL_NO}&fileTy=ADOPTIMG&fileNo=1&thumb"
              alt=""
              class="card-img"
            />
          </span>
          <div class="board-text">
            <span class="board-title">${centerName}</span>       
            <h3 class="board-middle">${petName}</h3>          
            <p class="board-bottom">#${petSpcs}, #${b.BREEDS}</p>
          </div>
        </a>
      </li>
      `;
    });


    $(".content-list").html(li);
}

// a herf="javascript-void(0)"

// 검색 버튼 클릭 이벤트 처리
$(".search-form").on("submit", function (e) {
    e.preventDefault(); // 폼의 기본 동작인 페이지 새로고침을 막음
    const keyword = $(".search-input").val(); // 입력된 검색어 가져오기
    searchList(keyword); // 검색 함수 호출
});

// 검색 함수
function searchList(keyword) {
    const listItems = $(".board-data"); // 목록 아이템들을 선택
    listItems.hide(); // 모든 목록 아이템을 숨김

    listItems.each(function () {
        const petName = $(this).find(".board-middle").text(); // 각 목록 아이템의 동물 이름 가져오기
        if (petName.includes(keyword)) {
            // 검색어가 동물 이름에 포함되면 해당 목록 아이템을 표시
            $(this).show();
        }
    });
}


// 강아지와 고양이 선택 이벤트 처리
$(".nav-name").on("click", function (e) {
    e.preventDefault();
    const type = $(this).attr("id");

    if (type === "dog") {
        $(".board-data").hide();
        $(".board-data").each(function () {
            const petSpcs = $(this)
                .find(".board-bottom")
                .text()
                .toLowerCase()
                .includes("강아지");

            if (petSpcs) {
                $(this).show();
            }
        });
    } else if (type === "cat") {
        $(".board-data").hide();
        $(".board-data").each(function () {
            const petSpcs = $(this)
                .find(".board-bottom")
                .text()
                .toLowerCase()
                .includes("고양이");

            if (petSpcs) {
                $(this).show();
            }
        });
    } else {
        $(".board-data").show();
    }
});

// 검색 함수
function searchList(keyword) {
    $(".board-data").hide(); // 모든 목록 아이템을 숨김

    $(".board-data").each(function () {
        const petName = $(this).find(".board-middle").text(); // 각 목록 아이템의 동물 이름 가져오기
        if (petName.includes(keyword)) {
            // 검색어가 동물 이름에 포함되면 해당 목록 아이템을 표시
            $(this).show();
        }
    });
}

// 전체 게시글 수

let startIdx = 1;
let endIdx = 100;

function getTotalPostCount() {
    return $.ajax({
        url: `http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/${startIdx}/${endIdx}/`,
        type: "get",
        dataType: "json"
    });
}

function displayTotalPostCount() {
    getTotalPostCount()
        .done(function (result) {
            if (result.TbAdpWaitAnimalView && result.TbAdpWaitAnimalView.row) {
                const totalCount = result.TbAdpWaitAnimalView.row.length;
                $('.full-list').text(`전체(${totalCount})`);
            }
        })
        .fail(function (error) {
            console.error("Failed to fetch total post count:", error);
        });
}

$(document).ready(function () {
    displayTotalPostCount();
});



// 카테고리 분류
// 강아지와 고양이 선택 이벤트 처리

$(".category-img").on("click", function (e) {
    e.preventDefault();
    const category = $(this).data("category");

    if (category === "강아지") {
        $(".board-data").hide();
        $(".board-data").each(function () {
            const petSpcs = $(this)
                .find(".board-bottom")
                .text()
                .toLowerCase()
                .includes("강아지");

            if (petSpcs) {
                $(this).show();
            }
        });
    } else if (category === "고양이") {
        $(".board-data").hide();
        $(".board-data").each(function () {
            const petSpcs = $(this)
                .find(".board-bottom")
                .text()
                .toLowerCase()
                .includes("고양이");

            if (petSpcs) {
                $(this).show();
            }
        });
    } else {
        $(".board-data").show();
    }
});


