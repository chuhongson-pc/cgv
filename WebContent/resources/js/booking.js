

//init booking step1

function init_Step1(){
	
	
	restore_TicketSelected();
	
	//hiển thị icon
	show_icon();
	
	//tính giá tiền và tổng tiền
	$('select').on("change", function(){
		
		var maLoaiVe = $(this).attr('id');
		var giaVe = $(this).data('price');
		
		var soVe = $(this).val();
		
		console.log('giaVe='+giaVe);
		console.log('soVe='+soVe);
		
		var giaTien = soVe * giaVe;
		
		//kiểm tra số vé tối đa được chọn
		if(getNumberOfTicket() <= 10)
		{

			updateGiaTriVe(maLoaiVe, giaTien);
			
		}
		else
		{
			//dialog
            $('#overNumberSeatDialog').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#overNumberSeatDialog").dialog("open");
            		
			//trả lại giá trị trước
			$(this).val(0);

			//cập nhật lại tổng khi trả select về 0
			$('span[id='+maLoaiVe+']').text(0);
				
			//hiển thị tổng tiền			
			$('span[id="tongCong"]').text(tinhTongTienVe());
		
		}
		
		//update danh sách chọn
		update_ticketTypeSelected();
		
        //update số lượng được chọn
        update_Ticket_Allow();
        
        //update tổng tiền
        $('input[name="tongCong"]').val(tinhTongTienVe());
		
	});
	
	insertComma();
	
	//chưa chọn
	$('.chooseat_Button').click(function(){
		
		var soDuTK  = parseInt($('input[name="soDuTK"]').val());
		var tongTien = tinhTongTienVe();
		
		
		if(getNumberOfTicket() == 0) {
			event.preventDefault();
			//show dialog
			$('#notTicketDialog').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#notTicketDialog").dialog("open");
			
		}
		
		if(soDuTK < tongTien) {
			event.preventDefault();
			//show dialog
			$('#notEnoughMoney').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#notEnoughMoney").dialog("open");
		}
		
	});
	
	//focus vào phần chọn loại vé
	
	$('html, body').animate({ scrollTop: $(".movie-title").offset().top }, 0);
	
}

function tinhTongTienVe(){
	var sum=0;
	$('select').each(function(){
		var soVeDaChon = $(this).val();
		var giaVe = $(this).data('price');
		sum += soVeDaChon * giaVe;
	});
	return sum;
}

function update_ticketTypeSelected(){
	var str='';
	$('select').each(function(){
		var soVeDaChon = $(this).val();
		var maLoaiVe = $(this).attr('id');
		if(soVeDaChon != 0) str += maLoaiVe+'-'+soVeDaChon+' ';
	});
	$('input[name="ticketTypeSelected"]').val(str);
}

function getNumberOfTicket() {
	var count = 0;
	$('select').each(function(){
		var soLuongChon = $(this).val();
		
		var vip = parseInt($(this).prev().val());
		var normal = parseInt($(this).prev().prev().val());

		count = count + parseInt((vip+normal)*soLuongChon);
	
	});
	
	return count;
}

function update_Ticket_Allow(){
	var vip_ticket = 0;
	var normal_ticket = 0;
	$('select').each(function(){
		var soLuongChon = $(this).val();
		var vip = parseInt($(this).prev().val());
		var normal = parseInt($(this).prev().prev().val());
		
		vip_ticket = vip_ticket + (vip*soLuongChon);
		normal_ticket = normal_ticket + (normal*soLuongChon);
		
		
	});
	//update hidden 
	$('input[name="soGheVipDuocChon"]').val(vip_ticket);
	$('input[name="soGheThuongDuocChon"]').val(normal_ticket);
}

function insertComma(){
	
	$('.account_balance').formatNumber({format:"#,###", locale:"us"});
	$('.price_ticket_str').each(function(){
		$(this).formatNumber({format:"#,###", locale:"us"});
	});
	
}

function show_icon (){
	$(window).bind("load", function () {
	    // thêm các span thuộc tính
		var element = $('input[name="doTuoiChoPhep"]').parent();
	    var old = $('input[name="doTuoiChoPhep"]').val();
	    var type3d = $('input[name="type3d"]').val();

	    if (old == 'G')
	        $(element).append('<span class="movie-rating movie-rating-g"></span>');
	    if (old == '16')
	        $(element).append('<span class="movie-rating movie-rating-nc16"></span>');

	    if (type3d == 'true')
	        $(element).append('<span class="movie-rating movie-rating-3d"></span>');
	    
	    if (type3d == 'false' )  $(element).append('<a href="javascript: void(0);" class="icon-2d icon"></a>');
	    	
	});
}

function updateGiaTriVe(maLoaiVe, giaTien){
	
	//hiển thị giá tiền
	$('span[id='+maLoaiVe+']').text(giaTien);
	
	//hiển thị tổng tiền
	var tongTien = tinhTongTienVe();
	$('span[id="tongCong"]').text(tongTien);
	
	//phẩy cho giá trị mới
	if(giaTien > 0) $('span[id='+maLoaiVe+']').formatNumber({format:"#,###", locale:"us"});
	
	if(tongTien > 0) $('span[id="tongCong"]').formatNumber({format:"#,###", locale:"us"});
}

function restore_TicketSelected() {	
    var ticket_array = $('input[name="ticketTypeSelected"]').val().trim().split(" ");
    
    if(ticket_array.length > 0)
    {
    	jQuery.each(ticket_array, function (i, val) {

    		var array_info = val.split("-");

    		var maFF, soLuong, giaVe;
    		
    		jQuery.each(array_info, function (j, val) {
    			if (j == 0)
    			{    
    				maVe = val;
    				giaVe = $('select[id='+maVe+']').data('price');
    			}
    			else if (j == 1)
    			{    
    				soLuong = parseInt(val);
    				$('select[id='+maVe+']').val(soLuong);
    			}
	        });
	        console.log(maVe+", "+giaVe+", "+soLuong);
	        //hiển thị lại
	        updateGiaTriVe(maVe, giaVe*soLuong);
	
	    });
    }
}


/* ---------------STEP 2--------------------- */

function init_Step2() {
    "use strict";
    
	//hiển thị icon
	show_icon();

    //hien thi bang thong tin loai ve da chon
    show_Table_Detail();

    //hien thi tinh trang phong
    show_Room_Status();
    
    //restore lại ghế đã chọn trước
    show_SeatsYour();


    //xử lý việc kích chọn ghế
    $('.sits__place.sits-state-room').click(function (e) {
        e.preventDefault();

        var soGheThuongDuocChon = parseInt($('input[name="soGheThuongDuocChon"]').val());
        var soGheVipDuocChon = parseInt($('input[name="soGheVipDuocChon"]').val());
        
        console.log('THUONG='+soGheThuongDuocChon+" | VIP="+soGheVipDuocChon);

        var data_type = $(this).attr('data-type');
        var place = $(this).attr('data-place');
        //type = 2 : vip

        //console.log('data-type=' + data_type);

        //chưa chọn
        if (!$(e.target).hasClass('sits-state--your')) {

            if (data_type == 2 && soGheVipDuocChon > 0) {
                //chọn ghế vip
                add_Seat_Selected($(this), soGheThuongDuocChon, soGheVipDuocChon, place, data_type);

            } else if (data_type == 2 && soGheVipDuocChon == 0) {
                //thông báo không thể chọn ghế vip
                get_Dialog(2);
            }

            else if (data_type == 1 && soGheThuongDuocChon > 0) {
                //chọn ghế thường
                add_Seat_Selected($(this), soGheThuongDuocChon, soGheVipDuocChon, place, data_type);

            } else if (data_type == 1 && soGheThuongDuocChon == 0) {
                //thông báo không thể chọn thêm ghế thường
                get_Dialog(1);
            }
        }
        else //đã chọn
        {
            //xóa ghế vừa chọn
            remove_Seat_Selected($(this),soGheThuongDuocChon, soGheVipDuocChon, place, data_type);
        }
    });
    
    //click button
    $('.fastfood_Button, .checkout_Button').click(function(){
    	 var soGheThuongDuocChon = parseInt($('input[name="soGheThuongDuocChon"]').val());
         var soGheVipDuocChon = parseInt($('input[name="soGheVipDuocChon"]').val());
         if(soGheThuongDuocChon != 0 || soGheVipDuocChon != 0){
 			event.preventDefault();
			//show dialog
			$('#notSeatDialog').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#notSeatDialog").dialog("open");

         }
    });

}

function show_SeatsYour(){
	var seatsYour_arr = $('input[name="seatsYour"]').val().split(" ");
	
	jQuery.each(seatsYour_arr, function(i, val){
		
		$('.sits__place.sits-state-room[data-place='+val+']').addClass('sits-state--your');
		
	});
}

function get_Dialog(type) {
	
	var dialog_element;
	if(type == 1) dialog_element = $("#fullNormal");
	else dialog_element = $("#fullVip");
	
    var current_seat = this;
    dialog_element.dialog({
        autoOpen: false,
        buttons: {
            OK: function () {
                $(this).dialog("close");
            }
        },
        title: "Thông báo"
    });

    dialog_element.dialog("open");
}

function add_Seat_Selected(element, soGheThuongDuocChon, soGheVipDuocChon, place, data_type) {

    if (!element.hasClass('sits-state--not')) {
        element.addClass('sits-state--your');

        //cập nhật danh sách chọn
        var list = $(':hidden[name="seatsYour"]').val();
        $('.seat_label').html(list + " " + place);
        $(':hidden[name="seatsYour"]').val(list + " " + place);

        //cập nhật số ghế đã chọn (vip-normal)
        if (data_type == 1) {
            soGheThuongDuocChon = soGheThuongDuocChon - 1;
            $(':hidden[name="soGheThuongDuocChon"]').val(soGheThuongDuocChon);
        }
        else
        {
            soGheVipDuocChon = soGheVipDuocChon - 1;
            $(':hidden[name="soGheVipDuocChon"]').val(soGheVipDuocChon);
        }

    }
}

function remove_Seat_Selected(element, soGheThuongDuocChon, soGheVipDuocChon, place, data_type) {
    element.removeClass('sits-state--your');

    //cập nhật lại 2 danh sách
    var list = $(':hidden[name="seatsYour"]').val();

    list = list.replace(' ' + place, '');

    //cập nhật ds hidden
    $(':hidden[name="seatsYour"]').val(list);

    //cập nhật ds hiện
    console.log(list.toString());
    $('.seat_label').html(list.toString());

    //cập nhật số ghế đã chọn (vip-normal)
    if (data_type == 1) {
        soGheThuongDuocChon = soGheThuongDuocChon + 1;
        $(':hidden[name="soGheThuongDuocChon"]').val(soGheThuongDuocChon);
    }
    else
    {
        soGheVipDuocChon = soGheVipDuocChon + 1;
        $(':hidden[name="soGheVipDuocChon"]').val(soGheVipDuocChon);
    }
}


function reload_Seats(element) {
    var row_current = $(element).parent();
    var row_letter = row_current.attr("data-row");
    //vòng for từ phải qua
    var i = 0;
    row_current.children('.sits__place.sits-state--your').each(function () {
        i++;
        $(this).text(i).attr("data-place", row_letter + i);

    });
}

function show_Room_Status() {
    //hiển thị trạng thái từ hidden 
    var str_seats = $('input[name="soDoGhe"]').val().trim();
    //console.log(str_seats);
    var array_seats = str_seats.split(" ");

    jQuery.each(array_seats, function (i, val) {
        //console.log(val);
        var array_info = val.split("-");
        var data_x;
        var data_y;
        var data_place;
        var data_type;
        jQuery.each(array_info, function (j, val) {
            //console.log('i='+j+", val="+val);
            if (j == 0)
                data_x = val;
            else if (j == 1)
                data_y = val;
            else if (j == 2)
                data_place = val;
            else if (j == 3)
                data_type = val;
        });
        //có thông số -> set 

        var cell = $('.sits__row[data-y="' + data_y + '"]').find('.sits__place[data-x="' + data_x + '"]');
        cell.addClass('sits-state-room').attr('data-place', data_place).attr('data-type', data_type);
        if (data_type == 2) {
            cell.addClass('sits-price--expensive');
        }

        cell.text(data_place.substring(1, data_place.length));
    });


    //.Disable seat booked
    var booked_array = $(':hidden[name="seatsBooked"]').val();
    booked_array = booked_array.split(" ");
    jQuery.each(booked_array, function (i, val) {
        //console.log(val);
        $('.sits__place.sits-state-room').each(function () {
            //console.log(val + '=' + $(this).attr('data-place'));
            if (val === $(this).attr('data-place')) {
                //console.log('addClass');
                $(this).addClass('sits-state--not');
            }
        });
    });
}

function show_Table_Detail() {

    var ticketTypeSelected = $('input[name="ticketTypeSelected"]').val().trim();

    var ticketTypeSelectedArray = ticketTypeSelected.split(" ");

    var sum = 0;

    jQuery.each(ticketTypeSelectedArray, function (i, val) {

        var array_info = val.split("-");

        var maLoaiVe, soLuongChon;


        jQuery.each(array_info, function (j, val) {
            if (j == 0)
                maLoaiVe = val;
            else if (j == 1)
                soLuongChon = parseInt(val);
        });
        //console.log('maLoaiVe=' + maLoaiVe);

        var row = $('tr[id=' + maLoaiVe + ']');
        var giaTien = parseInt(row.find('input[name="giaTien"]').val());

        //chèn vào số lượng được chọn
        row.find('.number_selected').text(soLuongChon);
        //chèn vào giá tiền
        row.find('.price_ticket_sum').text(soLuongChon * giaTien);
        
       
        sum = sum +  (soLuongChon * giaTien);
        
        console.log('='+sum);

    });

    //loại bỏ những row không được chọn
    remove_Row();

    //hiển thị tổng tiền
    console.log('tongTien='+sum);
    $('#tongTienVe').text(sum);

    //điền dấu phẩy
    insertCommaStep2();
}

function remove_Row() {
    $('.row_detail_selected').each(function () {
        var soLuongChon = $(this).find('.number_selected').text();

        if (soLuongChon == 0)
            $(this).remove();

    });
}

function insertCommaStep2() {
    $('.price_ticket_sum').each(function () {
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}


//--------------STEP 3-------------//
function init_Step3() {
	
	//hiển thị icon
	show_icon();

    //hien thi bang thong tin loai ve da chon
    show_Table_Detail();


    //khôi phục các giá trị đã chọn trước
    restore_FFSelected();

    //hiển thị ghế đã chọn
    $('.seat_label').html($('input[name="seatsYour"').val());

    //init table chọn fast food
    $('select').change(function () {

        var giaFF = $(this).data('price');
        var soLuong = $(this).val();
        var maFF = $(this).attr('id');

        if (getSoLuongFFDaChon() <= 10) {
        	
            updateGiaTriFF(maFF, giaFF, soLuong);
            update_FFTypeSelected();
            
        }
        else {
            //dialog
            $('#fullFF').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#fullFF").dialog("open");
            //trả về value 0
            $(this).val(0);
            $('span[id=' + maFF + ']').text(0);
            $('span[id="tongCong"]').text(tongTienFF()).formatNumber({format: "#,###", locale: "us"});
        }
        
    });

    insertCommaFF();
    
    
    $('.checkout_Button').click(function(){
		
		var soDuTK  = parseInt($('input[name="soDuTK"]').val());
		var tongTien = tongTienFF() + parseInt($('#tongTienVe').text().replace(",",""));
		
		if(soDuTK < tongTien) {
			event.preventDefault();
			//show dialog
			$('#notEnoughMoney').dialog({
                autoOpen: false,
                buttons: {
                    OK: function () {
                        $(this).dialog("close");
                    }
                },
                title: "Thông báo"
            });

            $("#notEnoughMoney").dialog("open");
		}
		
	});

}

function updateGiaTriFF(maFF, giaFF, soLuong) {
    $('span[id=' + maFF + ']').text(giaFF * soLuong);
    //cập nhật lại tổng cộng
    $('span[id="tongCong"]').text(tongTienFF());

    //cập nhật dấu phẩy
    if(soLuong != 0) $('span[id=' + maFF + ']').formatNumber({format: "#,###", locale: "us"});           
    if(tongTienFF() != 0) $('span[id="tongCong"]').formatNumber({format: "#,###", locale: "us"});
}

function getSoLuongFFDaChon() {
    var count = 0;
    $('select').each(function () {
        var soLuong = $(this).val();
        count += parseInt(soLuong);
    });
    console.log('dachon=' + count);
    return count;
}

function tongTienFF() {
    var sum = 0;
    $('select').each(function () {
        var giaFF = $(this).data('price');
        var soLuong = $(this).val();
        sum += giaFF * soLuong;
    });
    return sum;
}
function insertCommaFF() {
	
	$('.account_balance').formatNumber({format:"#,###", locale:"us"});
	
    $('.price_ff_str').each(function () {
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}

function restore_FFSelected() {
	
    var ff_array = $('input[name="fastFoodTypeSelected"]').val().trim().split(" ");
    if(ff_array.length > 0)
    {
    	jQuery.each(ff_array, function (i, val) {

    		var array_info = val.split("-");

    		var maFF, soLuong, giaFF;
    		console.log('giaFF='+giaFF);
    		jQuery.each(array_info, function (j, val) {
    			if (j == 0)
    			{    
    				maFF = val;
    				giaFF = $('select[id='+maFF+']').data('price');
    			}
    			else if (j == 1)
    			{    
    				soLuong = parseInt(val);
    				$('select[id='+maFF+']').val(soLuong);
    			}
	        });
	        console.log(maFF+", "+giaFF+", "+soLuong);
	        //hiển thị lại
	        updateGiaTriFF(maFF, giaFF, soLuong);
	
	    });
    }
}

function update_FFTypeSelected(){
	var str='';
	$('select').each(function(){
		var soLuongDaChon = $(this).val();
		var maFF= $(this).attr('id');
		if(soLuongDaChon != 0) str += maFF+'-'+soLuongDaChon+' ';
	});
	$('input[name="fastFoodTypeSelected"]').val(str);
}

//--------------STEP 4-------------//
function init_Step4(){
	//hiển thị icon
	show_icon();
	
    //hiển thị ghế đã chọn
    $('.seat_label').html($('input[name="seatsYour"').val());

	show_Table_Detail_Step4();
	
	$('.account_balance').formatNumber({format:"#,###", locale:"us"});
    
}

function show_Table_Detail_Step4()
{

    var ticketTypeSelected = $('input[name="ticketTypeSelected"]').val().trim();
    var fastFoodTypeSelected =	$('input[name="fastFoodTypeSelected"]').val().trim();

    var ticketTypeSelectedArray = (ticketTypeSelected+' '+fastFoodTypeSelected).trim().split(" ");
    
    //console.log('array='+ticketTypeSelectedArray);
    console.log('ff='+$('input[name="fastFoodTypeSelected"]').val());
    console.log('ticket='+$('input[name="ticketTypeSelected"]').val());
    var sum = 0;

    jQuery.each(ticketTypeSelectedArray, function (i, val) {

    	console.log('val='+val);
        var array_info = val.split("-");

        var maLoaiVe, soLuongChon;


        jQuery.each(array_info, function (j, value) {
            if (j == 0){	
            	maLoaiVe = value;
            	console.log('maLoaiVe='+maLoaiVe);
            }
                
            else if (j == 1){
            	
            	soLuongChon = parseInt(value);
            	console.log('soLuongChon='+soLuongChon);
            }
                
        });
        //console.log('maLoaiVe=' + maLoaiVe);

        var row = $('tr[id=' + maLoaiVe + ']');
        var giaTien = parseInt(row.find('input[name="giaTien"]').val());

        //chèn vào số lượng được chọn
        row.find('.number_selected').text(soLuongChon);
        //chèn vào giá tiền
        row.find('.price_ticket_sum').text(soLuongChon * giaTien);
        
       
        sum = sum +  (soLuongChon * giaTien);
        
        console.log('='+sum);

    });

    //loại bỏ những row không được chọn
    remove_Row();

    //hiển thị tổng tiền
    console.log('tongTien='+sum);
    $('input[name="tongCong"]').val(sum);
    $('#tongTienVe').text(sum);

    //điền dấu phẩy
    insertCommaStep2();
}

