$('.material-symbols-outlined').on('click', function (){
    window.location.href='/sitter/addList';
})

// $('.profile-box').on('click', function (){
//     let sitterNumber = $('').val();
//     window.location.href='/sitter/list?sitterNumber=' + sitterNumber;
//     // console.log(sitterNumber);
// })

$('.profile-box').on('click', function() {
    let sitterNumber = $(this).data('sitter-number');
    window.location.href='/sitter/profile?sitterNumber=' + sitterNumber;
    console.log(sitterNumber);
});

document.addEventListener("DOMContentLoaded", function () {

    function handleRating(rating) {
        var fillStars = document.querySelector(".star-ratings-fill");
        var fillWidth = (rating / 5) * 100 + "%";
        fillStars.style.width = fillWidth;
    }

    handleRating();
});

