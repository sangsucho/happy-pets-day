$('.material-symbols-outlined').on('click', function () {
    window.location.href = '/sitter/addList';
})

// $('.profile-box').on('click', function (){
//     let sitterNumber = $('').val();
//     window.location.href='/sitter/list?sitterNumber=' + sitterNumber;
//     // console.log(sitterNumber);
// })

$('.profile-box').on('click', function () {
    let sitterNumber = $(this).data('sitter-number');
    window.location.href = '/sitter/profile?sitterNumber=' + sitterNumber;
    console.log(sitterNumber);
});



