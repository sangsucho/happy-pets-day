// ajax를 실행시키는 함수가 있어야 한다.
// 스크롤해서 바닥에 닿으면 ajax 실행시켜야 한다.
// http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/38/ <- 38 부분을 6개 단위로 끊어야 한다.
// 그래서 1과 38 부분을 변수를 줘서 부딪힐때마다 변경되게 해야한다.

// a 태그 페이징 이동 막고 클릭 이벤트 발생 되면 자바스크립트 안에서 폼을 만든다.
// 히든으로 상세페이지에서 사용할 정보를 넣어놔야 한다
// 폼으로 보낸걸 컨트롤러로 받는데 그 때 VO를 사용해서 받는다.
// VO에 center의 정보도 넣어준다.
$.ajax({
    url: "http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/38/",
    type: "get",
    dataType: "json",
    success: function(result) {
        if (result.TbAdpWaitAnimalView && result.TbAdpWaitAnimalView.row) {
            let list = result.TbAdpWaitAnimalView.row;
            makeList(list);
        }
    },
});

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
        <a href="" class="slide-card">
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
