let petNumber = $('.pet-number').val();

$.ajax({
    // 클릭한 게시물의 정보를 가져와야 하니까 petNumber를 가져올 게시물 번호에 넣어준다.
    url: `http://openapi.seoul.go.kr:8088/786e44785864656334354e61496a76/json/TbAdpWaitAnimalView/${petNumber}/${petNumber}/`,
    type: "get",
    dataType: "json",
    success: function (result) {
        let b = result.TbAdpWaitAnimalView.row[0];
        let fullName = `${b.NM}`;
        let centerName = fullName.split('(')[1].split('-')[0].split(')')[0];
        let petName = fullName.split('(')[0];
        let petSpcs = `${b.SPCS}`;
        if (petSpcs == 'CAT') {
            petSpcs = '고양이';
        } else if (petSpcs == 'DOG') {
            petSpcs = '강아지';
        }

        getCenter(centerName);

        // 성별 'M', 'W' > '남아', '여아'로 변경
        let petGender = `${b.SEXDSTN}`;
        if (petGender == 'M') {
            petGender = '남아';
        } else if (petGender == 'W') {
            petGender = '여아';
        }

        let petWeight = `${b.BDWGH}` + 'kg';
        let regiDate = `${b.ENTRNC_DATE}`;
        let introVideo = `${b.INTRCN_MVP_URL}`;
        let content = `<div>${b.INTRCN_CN}</div>`;

        // 입양상태 : N(입양대기), P(입양진행중), C(입양완료)
        let adoptStatus = `${b.ADP_STTUS}`;
        switch (adoptStatus) {
            case 'N':
                adoptStatus = '입양대기';
                break;
            case 'P':
                adoptStatus = '입양진행중';
                break;
            case 'C':
                adoptStatus = '입양완료';
                break;
            default:
        }

        $(".gender").text(petGender);
        $(".name").text(petName);
        $(".register-date").text(regiDate);
        $(".weight").text(petWeight);
        $(".adopt-status").text(adoptStatus);
        $(".adopt-status").text(adoptStatus);
        $(".introduce-video").prop("href", introVideo);
        $(".introduce-video").on("click", function(event) {
            if (introVideo == "") {
                event.preventDefault();
                alert("영상 URL이 존재하지 않습니다.")
            } else {

            }
        })
        $(".content").append(content);
        $(".pet-image").attr('src', `https://animal.seoul.go.kr/comm/getImage?srvcId=MEDIA&upperNo=${b.ANIMAL_NO}&fileTy=ADOPTIMG&fileNo=1&thumb`)
        $(".title-section1").text(centerName);
        $(".title-section2").text(petName);
        $(".title-section3").text(regiDate);
    }
});
function getCenter(centerName, callback) {
    $.ajax({
        url: `/adopts/getCenterName`,
        type: "get",
        data: { centerName: centerName },
        dataType: "json",
        success: function(result) {
            console.log(result);
            showCenter(centerName, result);
            callback(result);
        },
        error: function(xhr, status, error) {
            console.log(error);
        }
    });
}

function showCenter(centerName, center) {
    let text = `
    <table class="center-infomation">
      <tr>
        <th class="center-name">센터 이름</th>
        <th class="center-address">센터 주소</th>
        <th class="center-call-number">센터 연락처</th>
      </tr>
      <tr class="center-detail">
        <td class="center-name-detail">${center.centerName}</td>
        <td class="center-address-detail">${center.centerAddress}</td>
        <td class="center-call-number-detail">${center.centerCallNumber}</td>
      </tr>
    </table>
  `;

    $(".center-info").html(text);
}