//alt table
$('.movie__show.show-time.show.movie-detail:even').addClass('alt');


//full
$('input[name="statusFull"]').each(function(){
	var full = $(this).val();
	if(full=='true') $(this).parent().removeClass('available').addClass('full').addClass('sold-out').parent().removeAttr('href');
});
//3d
$('input[name="type3d"]').each(function(){
	var type3d = $(this).val();
	if(type3d=='true') $(this).parent().append('<span class="type3d"></span>');
});


//chọn ngày
var date_current = $('.synopis-schedule').find(':hidden').val();
date_current = date_current.substring(8);
console.log(date_current);

$('.synopis-schedule').find("span").filter(function () {
    return $(this).text() == date_current;

}).parent().parent().addClass('selected');

// kiểm tra -> thông báo nếu ko có lịch phim cho ngày được chọn.

//vô hiệu hóa những ngày không có lịch
$('.filter__list__item').each(function () {

    var coPhim = $(this).find('input[name="coPhim"]').val();
    console.log('coPhim=' + coPhim);
    if (coPhim == '0') {
        $(this).removeClass('available').addClass('not-available');
        $(this).find('a').removeAttr('href');
    }

});

//hiển thị các span thuộc tính
$(window).bind("load", function () {
    // thêm các span thuộc tính
    var element = $('input[name="doTuoiChoPhep"]').parent();
    var old = $('input[name="doTuoiChoPhep"]').val();
    var language = $('input[name="ngonNgu"]').val();
    var type3d = $('input[name="type3d"]').val();

    if (old == 'G')
        $(element).append('<span class="movie-g-big" data-filter-value="g">G</span>');
    if (old == '16')
        $(element).append('<span class="movie-16-big" data-filter-value="16">16+</span>');
    if (language == 'sub')
        $(element).append('<span class="movie-sub-big" data-filter-value="sub">PHỤ ĐỀ</span>');
    if (language == 'voice')
        $(element).append('<span class="movie-voice-big" data-filter-value="voice">LỒNG TIẾNG</span>');
    if (type3d == 1)
        $(element).append('<span class="movie-3d-big" data-filter-value="3d">3D</span>');
});


//if ($('.movie__show.show-time.show.movie-detail').length == 0) {
//	$('<div class="movie__show show show-time  alt"><span class="error_empty_times_info">XIN LỖI, KHÔNG TÌM THẤY LỊCH CHIẾU PHIM.</span></div>').insertAfter('.filters.hide-for-small');
//}


//kiểm tra giờ và ngày

var dt = new Date();
var date = dt.getDate();
var hour = parseInt(dt.getHours());
var min_current = parseInt(dt.getMinutes());

var current_date = $('.filter__list__item.selected').children().children().next().next().text();
var current_date = parseInt(current_date);

if (current_date == date) {
    console.log("current_date == date");
    $('.session.available').each(function () {
        var full_time = $(this).find('.show_hour').text();
        var time = full_time.substring(0, full_time.length - 3);
        var min = full_time.substring(full_time.length - 2, full_time.length);

        min = parseInt(min);
        time = parseInt(time);

		var timeOfSession = parseInt(time*60 + min);
		var timeOfLocal = parseInt(hour*60+min_current);

		if((timeOfSession - timeOfLocal) < 60 ) {
			$(this).removeClass('available').addClass('late').addClass('late-out').parent().removeAttr('href');
		} 
    })
}
