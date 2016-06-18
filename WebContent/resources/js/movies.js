

$(document).ready(function () {
	
	$(window).bind("load", function() {
	// thêm các span thuộc tính
		$('.movie__grid__item').each(function () {
			var old = $(this).find('input[name="doTuoiChoPhep"]').val();
			var language = $(this).find('input[name="ngonNgu"]').val();
			var type3d = $(this).find('input[name="type3d"]').val();
			
			if(old=='G') $(this).find('.bottom-thumbnail').append('<span class="movie-g" data-filter-value="g">G</span>');
			if(old=='16') $(this).find('.bottom-thumbnail').append('<span class="movie-3d" data-filter-value="16">16+</span>');
			if(language == 'sub') $(this).find('.bottom-thumbnail').append('<span class="movie-sub" data-filter-value="sub">PHỤ ĐỀ</span>'); 
			if(language == 'voice') $(this).find('.bottom-thumbnail').append('<span class="movie-voice" data-filter-value="voice">LỒNG TIẾNG</span>'); 
			if(type3d == 1) $(this).find('.bottom-thumbnail').append('<span class="movie-3d" data-filter-value="3d">3D</span>');
			
		});
			
			
	});
	
	
    var selected_arr = [];
    //do something
    var type = $('input[name="listType"]').val();

    if (type == 'showing') {
        $('a[href="/CGV/movies.html?action=showing"]').parent().addClass('active');
    }
    else {
        $('a[href="/CGV/movies.html?action=coming_soon"]').parent().addClass('active');
    }



    //lọc
    //bật tắt lọc
    $('.filter.multiSelect.selected').click(function () {

        console.log('clicked filter');
        var dis = $('.filter__list').css('display');
        if (dis == 'none') {
            $('.filter__list').css('display', 'block').on("mouseleave", function () {
                $(this).css('display', 'none');
            });
        }
        else {
            $('.filter__list').css('display', 'none');
        }

    });

    //click vào tùy chọn
    $('.filter__list__item').on("click", function () {
        event.stopPropagation();
        var option = $(this).data('filter-value');
        if ($(this).hasClass('selected')) { //click bỏ
            //$('.filter__list').css('display', 'block');
            $(this).removeClass('selected'); //bỏ tùy chọn --> không hiển thị
        }
        else
        {
            //$('.filter__list').css('display', 'block');
            $(this).addClass('selected'); //tùy chọn thêm
        }
        //update selected items
        //danh sách tùy chọn
        selected_arr = $('.filter__list__item.selected').map(function () {
            return $(this).data('filter-value');
        });
        console.log('size=' + selected_arr.length);

        //refresh
        refresh_Moive_Grid();
    });


    function refresh_Moive_Grid() {
        //hiển thị những tùy chọn phù hợp xuất hiện
        $('.movie__grid__item').each(function () {
            console.log('each movie');
            //các thuộc tính của phim là 1 mảng
            var data_arr = $(this).find('span').map(function () {
                return $(this).data('filter-value');
            });

            console.log('size data=' + data_arr.length);
            jQuery.each(data_arr, function (i, item) {
                console.log(i + ":" + item);
            });

            if (selected_arr.length > 0)
            {
                var i, check = '-1';
                for (i = 0; i < data_arr.length; i++) {
                    console.log(data_arr[i]);
                    check = jQuery.inArray(data_arr[i], selected_arr);
                    
                    if(check != '-1') break;
                }

                console.log('check=' + check);

                //remove Class or add Class
                if (check == '-1')
                    $(this).removeClass('show').addClass('hide');
                else
                    $(this).removeClass('hide').addClass('show');
            }
            else {
                $('.movie__grid__item').removeClass('hide').addClass('show');
            }
        });
    }
    ;

});
