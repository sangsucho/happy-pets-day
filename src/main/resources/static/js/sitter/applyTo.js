let $input = $("#post-image");
let $imgList = $(".img-list");
console.log($input);

// file change이벤트로 미리보기 갱신하기
$input.on("change", function () {
    let dt = new DataTransfer();
    let files = this.files;
    let newFiles = checkLength(files, 3);

    $('.file-wrap .img-list').detach();
    $('.file-wrap .input-hidden').detach();

    if(files.length > 3){
        return;
    }

    for (let i = 0; i < files.length; i++) {
        $(".file-wrap").prepend(`<li class="img-list"></li>
                    <div class="input-hidden">
                      <div class="license-name">
                        <p>자격증 이름 :</p>
                      </div>
                      <input type="text" name="applyFileTitle"/>
                    </div>`);

        let src = URL.createObjectURL(files[i]);
        let $img = $("<img>").attr("src", src);
        $img.css({
            width: "100%",
            height: "100%",
        });
        $(".img-list").eq(0).append($img);


    }
});

// //자격증 파일갯수 3개로 제한
// $input.on("change", function () {
//     let files = this.files;
//     //   console.log(files);
//
//     // 길이 체크함수 (files, 원하는 길이)
//     let newFiles = checkLength(files, 3);
//     if (newFiles.length < files.length) {
//         this.value = ""; // 파일 입력 비우기
//     }
//
// });

//파일 길이 체크 함수(체크할 files객체, 제한할 길이)
function checkLength(files, num) {
    if (files.length > num) {
        alert(`파일은 최대 ${num}개까지만 첨부 가능합니다.`);
        // 최대 수를 넘으면 비어있는 files객체 반환
        return new DataTransfer().files;
    }
    return files;
}
