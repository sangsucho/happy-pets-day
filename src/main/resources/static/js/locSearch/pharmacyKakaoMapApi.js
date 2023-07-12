var map;
var geocoder;
var wtmX, wtmY;
var infowindows = [];

viewList();

function viewList() {
    $.ajax({
        url:"http://openapi.seoul.go.kr:8088/564967784a77687435354174525951/json/LOCALDATA_020302/1/100/ ",
        type: "get",
        dataType: "json",
        success: function (result) {
            let list = result.LOCALDATA_020302.row;
            processCoordinates(list);
        },
        error: function (xhr, status, error) {
            console.log(error);
        }
    });
}

function processCoordinates(list) {
    map = new kakao.maps.Map(document.getElementById('map'), {
        center: new kakao.maps.LatLng(37.553836, 126.969652),
        level: 3
    });

    geocoder = new kakao.maps.services.Geocoder();

    for (var i = 0; i < list.length; i++) {
        var item = list[i];
        var x = parseFloat(item.X);
        var y = parseFloat(item.Y);
        wtmX = x;
        wtmY = y;

        if (item.DTLSTATENM === "정상") {
            geocoder.transCoord(x, y, createMarker.bind(null, item), {
                input_coord: kakao.maps.services.Coords.WTM,
                output_coord: kakao.maps.services.Coords.WGS84
            });
        }
    }
}


$('#map').on('click', 'area', function (){

    let title = $(this).attr('title');

    let $span = $(`span.title:contains(${title})`);

    $span.closest('.customoverlay').show();


});


function createMarker(item, result, status) {
    if (status === kakao.maps.services.Status.OK) {
        var lat = result[0].y;
        var lng = result[0].x;
        var latlng = new kakao.maps.LatLng(lat, lng);
        var title = item.BPLCNM;

        var marker = new kakao.maps.Marker({
            map: map,
            position: latlng,
            title: title
        });

        var infowindowContent = '<div class="customoverlay" >' +
            '<a href="https://map.kakao.com/link/map/' + title + ',' + lat + ',' + lng + '" target="_blank">' +
            '<span class="title">' + title + '</span>' +
            '</a>' +
            '</div>';

        let position = new kakao.maps.LatLng(lat, lng);

// 커스텀 오버레이를 생성합니다
        let customOverlay = new kakao.maps.CustomOverlay({
            map: map,
            position: position,
            content: infowindowContent,
            yAnchor: 1
        });

        infowindows.push(customOverlay);
    }

}