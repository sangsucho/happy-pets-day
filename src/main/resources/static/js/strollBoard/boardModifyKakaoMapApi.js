// 지도를 표시할 div와 초기 환경 설정
const mapContainer = document.getElementById('map');
const mapOption = {
    center: new kakao.maps.LatLng(boardLat, boardLng),
    level: 3
};

// 지도 생성
const map = new kakao.maps.Map(mapContainer, mapOption);

// 주소-좌표 변환 객체와 장소 검색 객체 생성
const geocoder = new kakao.maps.services.Geocoder();
const ps = new kakao.maps.services.Places();

// 마커와 인포윈도우 생성
let marker = new kakao.maps.Marker({
    map: map,
    position: new kakao.maps.LatLng(boardLat, boardLng)
});
const infowindow = new kakao.maps.InfoWindow({zindex:1});

// 현재 지도 중심좌표로 주소를 검색해서 지도 좌측 상단에 표시
searchAddrFromCoords(map.getCenter(), displayCenterInfo);

kakao.maps.event.addListener(map, 'click', function(mouseEvent) {
    searchDetailAddrFromCoords(mouseEvent.latLng, function(result, status) {
        let address = result[0].address.address_name;
        let addressTokens = address.split(" ");

        if (status !== kakao.maps.services.Status.OK) return;  // 조기 반환

        let detailAddr = !!result[0].road_address ? `<div>도로명주소 : ${result[0].road_address.address_name}</div>` : '';
        detailAddr += `<div>지번 주소 : ${result[0].address.address_name}</div>`;

        const content = `<div class="bAddr">
                            <span class="title">법정동 주소정보</span>
                            ${detailAddr}
                        </div>`;

        marker.setPosition(mouseEvent.latLng);
        marker.setMap(map);

        infowindow.setContent(content);
        infowindow.open(map, marker);

        let lat = mouseEvent.latLng.getLat();
        let lng = mouseEvent.latLng.getLng();

        console.log("위도 : "+lat);
        console.log("경도 : "+lng);

        $('.marker-lat').val(lat);
        $('.marker-lng').val(lng);


    });
});

kakao.maps.event.addListener(map, 'idle', function() {
    searchAddrFromCoords(map.getCenter(), displayCenterInfo);
});

function searchAddrFromCoords(coords, callback) {
    geocoder.coord2RegionCode(coords.getLng(), coords.getLat(), callback);
}

function searchDetailAddrFromCoords(coords, callback) {
    geocoder.coord2Address(coords.getLng(), coords.getLat(), callback);
}

function displayCenterInfo(result, status) {
    if (status !== kakao.maps.services.Status.OK) return;  // 조기 반환

    const infoDiv = document.getElementById('centerAddr');

    for(let i = 0; i < result.length; i++) {
        if (result[i].region_type === 'H') {
            let hjjs  = result[i].address_name.split(" ", 2);    // 행정주소

            console.log("행정주소 : "+hjjs[0]+" "+hjjs[1]);
            $('.administrative-district').val(hjjs[0]+" "+hjjs[1]);

            break;
        }
    }
}

function placesSearchCB(data, status, pagination) {
    if (status !== kakao.maps.services.Status.OK) return;  // 조기 반환

    const place = data[0];
    const latLng = new kakao.maps.LatLng(place.y, place.x);
    marker.setPosition(latLng);
    marker.setMap(map);

    $('.marker-lat').val(latLng.getLat());
    $('.marker-lng').val(latLng.getLng());

    searchDetailAddrFromCoords(latLng, function(result, status) {
        if (status !== kakao.maps.services.Status.OK) return;  // 조기 반환

        let detailAddr = !!result[0].road_address ? `<div class="detail-addr">도로명주소 : ${result[0].road_address.address_name}</div>` : '';
        detailAddr += `<div class="detail-addr">지번 주소 : ${result[0].address.address_name}</div>`;

        const content = `<div class="bAddr">
                            <span class="title">법정동 주소정보</span>
                            ${detailAddr}
                        </div>`;

        infowindow.setContent(content);
        infowindow.open(map, marker);


    });
    map.setCenter(latLng);
}

// 지도 검색
$('.search-btn').on('click',mapSearch );

// input칸 엔터 이벤트
$('.search-input').on('keypress', function (e) {
    if (e.code == 'Enter'&&$('.search-input').length>0) {
        mapSearch();
    }
});

function mapSearch() {
    let keyword = $('.search-input').val();
    ps.keywordSearch(keyword, placesSearchCB);
}

















