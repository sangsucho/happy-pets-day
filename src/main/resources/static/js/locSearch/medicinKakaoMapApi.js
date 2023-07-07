// var map;
// var geocoder;
// var wtmX, wtmY; // 변수 선언
//
// viewList();
//
// function viewList() {
//   $.ajax({
//     url: "http://openapi.seoul.go.kr:8088/564967784a77687435354174525951/json/LOCALDATA_020301/1/10000/",
//     type: "get",
//     dataType: "json",
//     success: function (result) {
//       let list = result.LOCALDATA_020301.row;
//       processCoordinates(list);
//     },
//     error: function (xhr, status, error) {
//       console.log(error);
//     }
//   });
// }
//
// function processCoordinates(list) {
//   map = new kakao.maps.Map(document.getElementById('map'), {
//     center: new kakao.maps.LatLng(37.553836, 126.969652),
//     level: 3
//   });
//
//   geocoder = new kakao.maps.services.Geocoder();
//
//   for (var i = 0; i < list.length; i++) {
//     var item = list[i];
//
//     // X와 Y 값을 가져와서 wtmX와 wtmY에 할당
//     var x = parseFloat(item.X);
//     var y = parseFloat(item.Y);
//     wtmX = x;
//     wtmY = y;
//
//     geocoder.transCoord(x, y, function (result, status) {
//       if (status === kakao.maps.services.Status.OK) {
//         var lat = result[0].y;
//         var lng = result[0].x;
//         var title = item.BPLCNM;
//         var latlng = new kakao.maps.LatLng(lat, lng);
//
//         var marker = new kakao.maps.Marker({
//           map: map,
//           position: latlng,
//           title: title
//         });
//       }
//     }, {
//       input_coord: kakao.maps.services.Coords.WTM,
//       output_coord: kakao.maps.services.Coords.WGS84
//     });
//   }
// }

var map;
var geocoder;
var wtmX, wtmY;
var infowindows = [];

viewList();

function viewList() {
  $.ajax({
    url: "http://openapi.seoul.go.kr:8088/564967784a77687435354174525951/json/LOCALDATA_020301/1/100/",
    type: "get",
    dataType: "json",
    success: function (result) {
      let list = result.LOCALDATA_020301.row;
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

    geocoder.transCoord(x, y, createMarker.bind(null, item), {
      input_coord: kakao.maps.services.Coords.WTM,
      output_coord: kakao.maps.services.Coords.WGS84
    });
  }
}

// let $marker = $('.customoverlay').closest('div');


$('#map').on('click', 'area', function (){

  let title = $(this).attr('title');

  let $span = $(`span.title:contains(${title})`);

  $span.closest('.customoverlay').show();

  // $(this).find('.customoverlay').show();

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

//     kakao.maps.event.addListener(marker, 'click', function () {
//       closeAllInfowindows();
//       infowindow.open(map, marker);
//     });
//   }
// }




// var map;
// var geocoder;
// var wtmX, wtmY;
// // var currentInfowindow = null;
//
// viewList();
//
// function viewList() {
//   $.ajax({
//     url: "http://openapi.seoul.go.kr:8088/564967784a77687435354174525951/json/LOCALDATA_020301/1/1000/",
//     type: "get",
//     dataType: "json",
//     success: function (result) {
//       let list = result.LOCALDATA_020301.row;
//       processCoordinates(list);
//     },
//     error: function (xhr, status, error) {
//       console.log(error);
//     }
//   });
// }
//
// function processCoordinates(list) {
//   map = new kakao.maps.Map(document.getElementById('map'), {
//     center: new kakao.maps.LatLng(37.553836, 126.969652),
//     level: 3
//   });
//
//   geocoder = new kakao.maps.services.Geocoder();
//
//   for (var i = 0; i < list.length; i++) {
//     var item = list[i];
//     var x = parseFloat(item.X);
//     var y = parseFloat(item.Y);
//     wtmX = x;
//     wtmY = y;
//
//     geocoder.transCoord(x, y, createMarker.bind(null, item), {
//       input_coord: kakao.maps.services.Coords.WTM,
//       output_coord: kakao.maps.services.Coords.WGS84
//     });
//   }
// }
//
// function createMarker(item, result, status) {
//   if (status === kakao.maps.services.Status.OK) {
//     var lat = result[0].y;
//     var lng = result[0].x;
//     var latlng = new kakao.maps.LatLng(lat, lng);
//     var title = item.BPLCNM;
//
//     let content = '<div class="customoverlay">' +
//     // `  <a href="https://map.kakao.com/link/map/${place},${lat}, ${lng}" target="_blank">`+
//     // `    <span class="title">산책 날짜 : ${date}  <br/> 모임장소 : ${place}</span>` +
//     // '  </a>' +
//     '</div>';
//
//     let position = new kakao.maps.LatLng(lat, lng);
//     var marker = new kakao.maps.Marker({
//       map: map,
//       position: latlng,
//       title: title
//     });
//     // 커스텀 오버레이를 생성합니다
//     let customOverlay = new kakao.maps.CustomOverlay({
//       map: map,
//       position: position,
//       content: content,
//       yAnchor: 1
//     });
//     kakao.maps.event.addListener(marker, 'click', function () {
//       if (customOverlay) {
//         customOverlay.close();
//       }
//
//
//     });
//   }
// }

// // 커스텀 오버레이가 표시될 위치입니다
// let position = new kakao.maps.LatLng(boardLat, boardLng);
//
// // 커스텀 오버레이를 생성합니다
// let customOverlay = new kakao.maps.CustomOverlay({
//   map: map,
//   position: position,
//   content: content,
//   yAnchor: 1
// });


// function adjustInfoWindowWidth() {
//   var infowindowContent = document.getElementById('infowindow-content');
//   if (infowindowContent) {
//     var contentWidth = infowindowContent.clientWidth;
//     var maxWidth = 200; // 최대 너비를 지정하고 싶다면 원하는 값을 사용하세요
//     var adjustedWidth = Math.min(contentWidth, maxWidth);
//
//     infowindowContent.style.width = adjustedWidth + 'px';
//     infowindowContent.style.border = '1px solid rgb(118, 129, 168)';
//     infowindowContent.style.textAlign = 'center';
//   }
// }
