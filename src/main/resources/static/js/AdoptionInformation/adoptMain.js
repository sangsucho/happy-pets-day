$.ajax({
    url: `http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/1/3/`,
    type: "get",
    dataType: "json",
    success: function (result) {
        if (result.TbAdpWaitAnimalView && result.TbAdpWaitAnimalView.row) {
            let list = result.TbAdpWaitAnimalView.row;
            makeList(list);
        }
    },
});

function makeList(obj) {
    let li = "";
    let i = 1;  // 각 게시물에 번호를 준다.
    obj.forEach(b => {
        let fullName = `${b.NM}`;
        let centerName = fullName.split('(')[1].split('-')[0].split(')')[0];
        let petName = fullName.split('(')[0];
        let petSpcs = `${b.SPCS}`;
        if (petSpcs == 'CAT') {
            petSpcs = '고양이';
        } else if (petSpcs == 'DOG') {
            petSpcs = '강아지';
        }

        // 쿼리스트링을 사용해서 클릭한 게시물 번호의 정보를 상세페이지로 넘긴다.
        li += `
           <a href="http://localhost:10000/adopt/detailByMain?petNumber=${i++}" class="adopt-section-content">
            <img
              src="https://animal.seoul.go.kr/comm/getImage?srvcId=MEDIA&upperNo=${b.ANIMAL_NO}&fileTy=ADOPTIMG&fileNo=1&thumb"
              alt=""
              class="card-img"
            />
                <div>
                    <span class="board-title">${centerName}</span>       
                    <h3 class="board-middle">${petName}</h3>          
                    <p class="board-bottom">#${petSpcs}, #${b.BREEDS}</p>
                </div>
            </a>
        `;
    });

    $(".adopt-section-content-container").html(li);
}
