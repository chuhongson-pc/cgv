$(document).ready(function() {
			init_Room_Select();
			init_Film_Select();
			init_Table();
			//init_Status();
});

function init_Room_Select() {
	

	//loại tùy chọn trùng  
    var seen = {};
    $('.cinema-experience').find('.filter__list__item').each(function() {
        var txt = $(this).data('filter-value');
        if (seen[txt])
            $(this).remove();
        else
            seen[txt] = true;
    });
	
	

    $('.filter__by[data-default-value="Experience"]').click(function (e) {
        $('.filter.experience.multiSelect').toggleClass('opened');
        var dis = $('.filter.experience.multiSelect:first-child').find('.filter__list').css('display');

        if (dis == 'none') {
            $('.filter.experience.multiSelect:first-child').find('.filter__list').css('display', 'block').on("mouseleave", function () {
                $(this).css('display', 'none');
            });
            ;
        } else {
            $('.filter.experience.multiSelect:first-child').find('.filter__list').css('display', 'none');
        }
    });


    $('.cinema-experience').find('.filter__list__item').on("click", function () {
        console.log("click item");
        if ($(this).hasClass('selected')) { //click bỏ
            $('.movie__show.show.show-time').css('display', 'none');
            $(this).removeClass('selected'); //bỏ tùy chọn --> không hiển thị
        }
        else
        {
            $('.movie__show.show.show-time').css('display', 'none');
            $(this).addClass('selected'); //tùy chọn thêm
        }
        var selected_arr = $('.cinema-experience').find('.filter__list__item.selected').map(function () {
            return $(this).data('filter-value');
        });

        $('.cinema').filter(function (index) { //những phòng chưa được chọn
            var data = $(this).children('.filter-value').data('filter-value');
            if (selected_arr.length == 0)
                return true;
            var check = jQuery.inArray(data, selected_arr);
            if (check == '-1') {
                return false;
            }
            else {
                return true;
            }
        }).parent().parent().parent().css('display', 'block');
        //
        if (selected_arr.length === 0)
        {
            $('.filter.multiSelect').removeClass('applied');
            $('.movie__show.show.show-time').css('display', 'block');
        }
        else
        {
            $('.filter.experience.multiSelect').addClass('applied');
        }
    });
}

function init_Film_Select(){
	
	//loại tùy chọn trùng  
    var seen = {};
    $('.filter.movie').find('.filter__list__item').each(function() {
        var txt = $(this).data('filter-value');
        if (seen[txt])
            $(this).remove();
        else
            seen[txt] = true;
    });
	
	
     $('.filter__by[data-default-value="Movie"]').click(function (e) {
         console.log('clicked');
        $('.filter.movie').toggleClass('opened');
        var dis = $('.filter.movie:first-child').find('.filter__list').css('display');

        if (dis == 'none') {
            $('.filter.movie:first-child').find('.filter__list').css('display', 'block').on("mouseleave", function () {
                $(this).css('display', 'none');
            });
            ;
        } else {
            $('.filter.movie:first-child').find('.filter__list').css('display', 'none');
        }
    });
    //
    $('.filter.movie').find('.filter__list__item').on("click", function () {
        console.log("click item");
        if ($(this).hasClass('selected')) { //click bỏ
            $('.movie__show.show.show-time').css('display', 'none');
            $(this).removeClass('selected'); //bỏ tùy chọn --> không hiển thị
        }
        else
        {
            $('.movie__show.show.show-time').css('display', 'none');
            $(this).addClass('selected'); //tùy chọn thêm
        }
        //
        var selected_arr = $('.filter.movie').find('.filter__list__item.selected').map(function () {
            return $(this).data('filter-value');
        });

        $('.filter-value.movie__name').filter(function (index) { //những phòng chưa được chọn
            var data = $(this).data('filter-value');
            if (selected_arr.length == 0)
                return true;
            var check = jQuery.inArray(data, selected_arr);
            if (check == '-1') {
                return false;
            }
            else {
                return true;
            }
        }).parent().parent().parent().parent().css('display', 'block');
        //
        if (selected_arr.length === 0)
        {
            $('.filter.multiSelect').removeClass('applied');
            $('.movie__show.show.show-time').css('display', 'block');
        }
        else
        {
            $('.filter.movie.multiSelect').addClass('applied');
        }
    });
}

function init_Table(){
	$('.movie__show.show.show-time:even').addClass('alt');
	
	//$('.filter__list-inline').find('.filter__list__item').first().addClass('selected');
	var date_current = $('.filter__list-inline').find(':hidden').val();
	date_current = date_current.substring(8);
	console.log(date_current);
	
	$('.filter__list-inline').find("span").filter(function() 
	{ 
		return $(this).text() == date_current;
		
	}).parent().parent().addClass('selected');
	
	//kiểm tra -> thông báo nếu ko có lịch phim cho ngày được chọn.
	
	if ($('.movie__show.show.show-time').length == 0){
			$('<div class="movie__show show show-time  alt"><span class="error_empty_show_times">XIN LỖI, KHÔNG TÌM THẤY LỊCH CHIẾU PHIM.</span></div>').insertAfter('.filters.hide-for-small');
	}
	//kiểm tra giờ và ngày
	
	var dt = new Date();
	var date = dt.getDate();
	var hour = parseInt(dt.getHours());
	var min_current = parseInt(dt.getMinutes());
	
	var current_date = $('.filter__list__item.selected').children().children().next().next().text();
	var current_date = parseInt(current_date);
	console.log('ngay duoc chon:'+current_date);
	console.log('ngay hien tai:'+date);
	console.log('gio hien tai:'+hour);
	if(current_date == date){
		console.log("current_date == date");
		$('.session.available').each(function(){
			var full_time = $(this).find('.show_hour').text().trim();
			var time = full_time.substring(0,full_time.length - 3);
			var min = full_time.substring(full_time.length - 2,full_time.length);
			
			min = parseInt(min);
			time = parseInt(time);
			
			var timeOfSession = parseInt(time*60 + min);
			var timeOfLocal = parseInt(hour*60+min_current);
			
			console.log('session='+timeOfSession+'===local='+timeOfLocal);
			
			if((timeOfSession - timeOfLocal) < 60 ) {
				$(this).removeClass('available').addClass('late').addClass('late-out').parent().removeAttr('href');
			} 
		})
	}
	
}

	//function init_Status(){
	
	$(window).bind("load", function () {
	
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
	
	//vô hiệu hóa những ngày không có lịch
	$('.filter__list__item').each(function () {

	    var coPhim = $(this).find('input[name="coPhim"]').val();
	    console.log('coPhim=' + coPhim);
	    if (coPhim == '0') {
	        $(this).removeClass('available').addClass('not-available');
	        $(this).find('a').removeAttr('href');
	    }

	});
	});
	
//}
