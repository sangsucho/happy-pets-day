let tmpEditBox = null;

// 메뉴버튼(수정,삭제) 누를 시 수정삭제창 나타나고 다른 곳 클릭하면 사라지기
$("body").on("click", function (event) {

    let target = $(event.target);
    let editBox = target.closest('.animal-swiper').find('.edit-box');

    if (target.hasClass("menu-dot-img")) {
        if (tmpEditBox) {
            tmpEditBox.css("display", "none");
        }
        editBox.css("display", "block");
        tmpEditBox = editBox;
    } else {
        tmpEditBox.css("display", "none");
    }
});

$('.menu-dot').on('click', function () {
    $('.edit-pet-number').val($(this).closest(".animal-swiper").find('.pet-number').val());
})

// 삭제버튼 누를 시 alert창
$(".delete1").on("click", function () {
    let petNumber = $(this).data("number");

    if (window.confirm("해당 반려동물을 삭제하시겠습니까?")) {
    } else {
    }
});

// 모달
let modal = $("#modal-container");
// '수정' 클릭 > 모달 띄워주기
$(".modify1").on("click", function () {
    modal.css('display', 'flex');
    let petNumber = $('.edit-pet-number').val();
    $.ajax({
        url: `/myPages/myPet/editPet/${petNumber}`,
        type: 'get',
        dataType: 'json',
        success: function (result) {
            $('#pet-number').val(petNumber);
            $('.pet-name').val(result.petName);
            $('.pet-detailed-type').val(result.petDetailedType);
            $('.pet-birth').val(result.petBirth);
            if (result.petKind != '강아지' && result.petKind != '고양이') {
                $('.etc-direct').click();
                // $('#etcDirect').val(result.petKind);
            } else {
                $('.pet-kind').text(result.petKind);
            }
            ;
            $('#etcDirect').val(result.petKind);

            if (result.petGender == 'M') {
                $('#male').prop('checked', true);
            } else if (result.petGender == 'F') {
                $('#female').prop('checked', true);
            }
            ;

            if (result.petSurgery == 'Y') {
                $('#yes').prop('checked', true);
            } else {
                $('#no').prop('checked', true);
            }
            ;

            if (result.petFileName) {
                $('#uploaded-image').attr('src', `/upload/pet/${result.petFileUploadPath}/${result.petFileUuid}_${result.petFileName}`);
                $('.isPetFile').val(1);
            } else {
                $('#uploaded-image').attr('src', 'https://lifet.co.kr/img/profile/default.png');
                $('.isPetFile').val(2);
            }
            ;

        }
    })
});

// 'X' 클릭 > 모달 끄기
$(".btn-close").on("click", function () {
    modal.css('display', 'none');
    $('#etcDirect').attr('type', 'hidden');
});

// '동물 종류' 드롭다운 메뉴 열기/닫기
$(".dropdown-btn").on("click", function () {
    if ($(".menu-list").css("display") == "none") {
        $(".menu-list").css("display", "block");
    } else {
        $(".menu-list").css("display", "none");
    }
});

$(".list button").on("click", function () {
    let selectValue = $(this).text();
    $(".dropdown-btn span").text(selectValue);
    $(".menu-list").css("display", "none");
    $("#etcDirect").val("");
});

$(".etc-direct").on("click", function () {
    $("#etcDirect").attr("type", "text");
    $("#etcDirect").focus();
});

$(document).ready(function () {
    $("#dog, #cat").on("click", function () {
        $("#etcDirect").attr("type", "hidden");
        $("#etcDirect").val($(this).val());
    });
});

// 선택한 이미지 원 안에 삽입
function updateImage(input) {
    let image = document.getElementById("uploaded-image");
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            image.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
    $('.isPetFile').val(0);
}

// '기본 이미지' 클릭 > 기본 프로필 이미지로 변경
function restoreDefaultImg() {
    let uploadedImage = document.getElementById("uploaded-image");
    uploadedImage.src = "https://lifet.co.kr/img/profile/default.png";
    $('#photo-file').val("");
    $('.isPetFile').val(0);
}

// 생년월일 입력 datepicker
$(function () {
    $('.datepicker').datepicker({});
});

$.datepicker.setDefaults({
    dateFormat: "yy-mm-dd",
    prevText: "이전 달",
    nextText: "다음 달",
    monthNames: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
    ],
    monthNamesShort: [
        "1월",
        "2월",
        "3월",
        "4월",
        "5월",
        "6월",
        "7월",
        "8월",
        "9월",
        "10월",
        "11월",
        "12월",
    ],
    dayNames: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesShort: ["일", "월", "화", "수", "목", "금", "토"],
    dayNamesMin: ["일", "월", "화", "수", "목", "금", "토"],
    showMonthAfterYear: true,
    yearSuffix: "년",
    changeMonth: true,
    changeYear: true,
    yearRange: "1930:2023",
});
