$(function() {

//load movie list
$(".session_date_picker").datepicker({
	format : "dd-mm-yyyy",
	autoclose : true,
	todayHighlight: true
});

$('.session_date_picker').datepicker().on('changeDate', function(e){
	
	var current_date = new Date();
	var selected_date = $(this).datepicker('getDate');
	selected_date.setHours(23);
	selected_date.setMinutes(59);
	selected_date.setSeconds(59);
	
	console.log('current-date='+current_date.getTime());
	console.log('selected-date='+selected_date.getTime());
	
	
	if(current_date <= selected_date){
		ajax_LoadMovieList();
	}
	else {
		$('#date_not_valid').dialog({ 
	        autoOpen: false, 
	        buttons: {
	           OK: function() {
	               	$(this).dialog("close");
	           }
	   
	        },
	        title: "Thao tác"
	     });
		 $( "#date_not_valid" ).dialog( "open" );
	}
	
});

$('select[name="currentSession.movieOfSession.maPhim"]').change(function(){
	
	ajax_LoadSessionAvailable();
});

$('#session-type-select').on('change',function(){
	
	console.log('change');
	
	var typeOfSession = $('#session-type-select').val();
	if(typeOfSession == '-1') dialog_Must_Select_Type_Of_Session();
	else
	{
		refresh_Session_Time_Button();
		
		updateNumberOfSessionAvailable();
		
		init_Select_Session_Time();
		
	}
});


//call diaglog select session type

$('.row.panel.my-panel-default').on('click','.btn-session-time', function(){
	var typeOfSession = $('#session-type-select').val();
	if(typeOfSession == '-1') dialog_Must_Select_Type_Of_Session();
});


//step next
$('a[href="#next"], li[role="tab"]').click(function(){
	
	//console.log('next clicked');
	
//	var step_current = $('.steps.clearfix').find('.current').find('span[class="number"]').text();
//	
//	console.log('current='+step_current);
//	
//	if(step_current == '2.') {
//		
//		console.log($('.btn.btn-session-time.btn-warning, .btn.btn-session-time.btn-primary').length);
//		
//		if($('.btn.btn-session-time.btn-warning, .btn.btn-session-time.btn-primary').length > 0) { 
//			
//			console.log('to Step2');
//			
//			init_Step2();
//
//		} else {
//			
//			console.log('call Dialog empty session');
//			
//			$('#empty_session').dialog({ 
//		        autoOpen: false, 
//		        buttons: {
//		           OK: function() {
//		               	$(this).dialog("close");
//		           }
//		   
//		        },
//		        title: "Thao tác"
//		     });
//			 $( "#empty_session" ).dialog( "open" );		
//			
//		}
//	
//	}
//	else if(step_current == '3.'){
//		init_Step3();
//	}
	
});


});

/************* FUNCTION ***************/
function refresh_Session_Time_Button(){
	
	console.log('clear session time button');
	
	$('.row.panel.my-panel-default').find('.btn.btn-session-time').not('.disabled').each(function(){
		var value = $(this).val();
		$(this).removeClass('btn-primary').removeClass('btn-warning').removeClass('btn-hidden').addClass('btn-default').text(value);
	});
}

function init_Step3(){
	
	console.log('step3');
	
	$( ".panel-setting-ticket-price" ).empty();
	
	$( ".table-type-ticket" ).clone().prependTo('.panel-setting-ticket-price');
	
	//replace
	$('.panel-setting-ticket-price').find('.checkbox').each(function(){
		
		var ticketTypeCode = $(this).find('input[type="checkbox"]').val();
		var ticketTypePrice = $('td[id='+ticketTypeCode+']').data('price-default');
		
		$(this).hide().parent().append('<input class="form-control new-price" id="'+ticketTypeCode+'" value="'+ticketTypePrice+'">');
		
	});
	
	//remove cac row không được chọn
	$('.panel-setting-ticket-price').find('input[type="checkbox"]').not('.selected').each(function(){
		
		var value  = $(this).val();
		console.log('hide='+value);
		$('tr[id='+value+']').hide();
		$('tr[id='+value+']').find('.form-control.new-price').remove();
		
		
	});
	
	//ajax load date setting
	ajax_LoadDateSetting();
	
	//event change checkbox
	$('.row.panel.my-panel-default').on('click','input[type="checkbox"]', function() {
		reload_NewPrice();
	});
	
	
//	$('.row.panel.my-panel-default').on('click','#thu4vuive', function() {
//		
//		console.log('change');
//		
//		var ischecked= $(this).is(':checked');
//		var giaMoi = $(this).val();
//		
//		if(!ischecked) //uncheck
//		 {
//			 $('.form-control.new-price').each(function(){
//				 
//					var maLV = $(this).attr('id');
//	
//			        var giaGoc = $('td[id='+maLV+']').data('price-default');
//			        $(this).val(giaGoc);
//			       
//			});
//			 
//		 } else 
//		 {
//			 $('.form-control.new-price').each(function(){
//				 $(this).val(giaMoi);
//			 });
//		 }
//		
//
//    }); 
//	
//$('.row.panel.my-panel-default').on('click','#suatcuoituan', function() {
//		
//		console.log('change');
//		
//		var ischecked= $(this).is(':checked');
//		var giaMoi = $(this).val();
//		
//		
//		
//		if(!ischecked) //uncheck
//		 {
//			 $('.form-control.new-price').each(function(){
//				 	var maLV = $(this).attr('id');	
//			        var giaGoc = $('td[id='+maLV+']').data('price-default');
//			        $(this).val(giaGoc);   
//			});
//			 
//		 } else 
//		 {
//			 $('.form-control.new-price').each(function(){
//				 var maLV = $(this).attr('id');	
//			     var giaGoc = $('td[id='+maLV+']').data('price-default');
//				 $(this).val(parseInt(giaGoc) + parseInt(giaMoi));
//			 });
//		 }
//		
//
//    }); 
//	
//$('.row.panel.my-panel-default').on('click','#ngayle', function() {
//	
//	console.log('change');
//	
//	var ischecked= $(this).is(':checked');
//	var giaMoi = $(this).val();
//	
//	if(!ischecked) //uncheck
//	 {
//		 $('.form-control.new-price').each(function(){
//			 	var maLV = $(this).attr('id');	
//		        var giaGoc = $('td[id='+maLV+']').data('price-default');
//		        $(this).val(giaGoc);   
//		});
//		 
//	 } else 
//	 {
//		 $('.form-control.new-price').each(function(){
//			 var maLV = $(this).attr('id');	
//		     var giaGoc = $('td[id='+maLV+']').data('price-default');
//			 $(this).val(parseInt(giaGoc) + parseInt(giaMoi));
//		 });
//	 }
//	
//
//}); 
//
//$('.row.panel.my-panel-default').on('click','#suatchieusom', function() {
//	
//	console.log('change');
//	
//	var ischecked= $(this).is(':checked');
//	var giaMoi = $(this).val();
//	
//	if(!ischecked) //uncheck
//	 {
//		 $('.form-control.new-price').each(function(){
//			 	var maLV = $(this).attr('id');	
//		        var giaGoc = $('td[id='+maLV+']').data('price-default');
//		        $(this).val(giaGoc);   
//		});
//		 
//	 } else 
//	 {
//		 $('.form-control.new-price').each(function(){
//			 var maLV = $(this).attr('id');	
//		     var giaGoc = $('td[id='+maLV+']').data('price-default');
//			 $(this).val(parseInt(giaGoc) - parseInt(giaMoi));
//		 });
//	 }
//	
//
//}); 
	
}

function reload_NewPrice(){
		
		console.log('change');
		
		
		$('.form-control.new-price').each(function(){
			
			var maLV = $(this).attr('id');	
			var giaTriGoc = parseInt($('td[id='+maLV+']').data('price-default'));
			
			console.log('giaTriGoc='+giaTriGoc);

			$('.panel-body.date-setting-price').find('input[type="checkbox"]').each(function(){
				
				var ischecked= $(this).is(':checked');
				var element_id = $(this).attr('id');
				var giaTriThayDoi = parseInt($(this).val());
				
				console.log('giaTriThayDoi='+giaTriThayDoi);
				
				if(ischecked)
				{ //uncheck
					if(element_id == 'suatchieusom') {
						//add
						giaTriGoc -= giaTriThayDoi;
					}
					else if(element_id == 'thu4vuive') {
						giaTriGoc = giaTriThayDoi;
					}
					else {
						//sub
						giaTriGoc += giaTriThayDoi;
					}
						
				}
				
			});
			//set value
			$(this).val(giaTriGoc); //giaTriGoc updated
				
		});
}

function ajax_LoadDateSetting(){
	
	console.log('ajax_LoadDateSetting');
	
	$.ajax({  
        url:"../session.html?action=datesetting",  
        type:'post',  
        dataType:'json',  
        success:function(data){
        	
        		var suatChieuSom = data.suatChieuSom;
        		var suatCuoiTuan = data.suatCuoiTuan;
        		var thu4VuiVe = data.thu4VuiVe;
        		var ngayLe = data.ngayLe;
        		var giaNgayLe = data.giaNgayLe;
        		
        		var currentDate = $('input[name="currentSession.ngayChieu"]').val();
        		
        		//clear
        		$('.date-setting-price').empty();
        		
        		thu4VuiVe_Func(currentDate, thu4VuiVe);
        		suatCuoiTuan_Func(currentDate, suatCuoiTuan);
        		ngayLe_Func(currentDate, ngayLe, giaNgayLe);
        		
        		suatChieuSom_Func(suatChieuSom);

        	
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	}) ; 
}

function createDate(str)
{
	var arr = str.split('-');
	
	return new Date(arr[2]+','+arr[1]+','+arr[0]);
}

function thu4VuiVe_Func(currentDate, thu4VuiVe){
	var date = createDate(currentDate);
	var dow = date.getDay();
		
	if(dow == 3){
		//append
		var append  = '<div class="checkbox">'
			            +'<label>'
			            +'<input type="checkbox" id="thu4vuive" value="'+thu4VuiVe+'" checked="true">Ngày thứ 4 Vui Vẻ.'
			            +'</label>'
		                +'</div>';
		
		$('.date-setting-price').append(append);
		
		//add price	
		$('.form-control.new-price').val(thu4VuiVe);
		
	}
	
}

function suatCuoiTuan_Func(currentDate, suatCuoiTuan){
	var date = createDate(currentDate);
	var dow = date.getDay();
	
	if(dow == 6 || dow == 7){
		//append
		var append  = '<div class="checkbox">'
			            +'<label>'
			            +'<input type="checkbox" id="suatcuoituan" value="'+suatCuoiTuan+'" checked="true">Suất cuối tuần.'
			            +'</label>'
		                +'</div>';
		
		$('.date-setting-price').append(append);
		
		//add price	
		$('.form-control.new-price').each(function(){
			var old_price = parseInt($(this).val());
			$(this).val(old_price+parseInt(suatCuoiTuan));
		});
		
		
	}
}

function ngayLe_Func(currentDate, ngayLe, giaNgayLe){
	var ngayChon = currentDate.substring(0, 5);
	console.log('ngayChon='+ngayChon);
	
	var ngayLe_arr = ngayLe.split(", ");
	
	if(jQuery.inArray(ngayChon, ngayLe_arr) != -1){
		//append
		var append  = '<div class="checkbox">'
			            +'<label>'
			            +'<input type="checkbox" id="ngayle" value="'+giaNgayLe+'" checked="true">Ngày lễ.'
			            +'</label>'
		                +'</div>';
		
		$('.date-setting-price').append(append);
		
		//add price	
		$('.form-control.new-price').each(function(){
			var old_price = parseInt($(this).val());
			$(this).val(old_price+parseInt(giaNgayLe));
		});
		
		
	}
	
}

function suatChieuSom_Func(suatChieuSom){
	var result = true;
	$('.row.panel.my-panel-default').find('.btn.btn-session-time.btn-primary:not(".disabled"), .btn.btn-session-time.btn-warning:not(".disabled")').each(function(){
		var data = $(this).val();
		console.log(data);
		var gio = parseInt(data.substring(0,2));

		if(data != null && gio >= 12){
			result = false;
		}
		console.log('time_suatchieusom='+gio);
	});
	
	if(result) {
			var append  = '<div class="checkbox">'
	            +'<label>'
	            +'<input type="checkbox" id="suatchieusom" value="'+suatChieuSom+'" checked="true">Suất chiếu sớm (trước 12h).'
	            +'</label>'
                +'</div>';

			$('.date-setting-price').append(append);
			
			//add price	
			$('.form-control.new-price').each(function(){
				var old_price = parseInt($(this).val());
				$(this).val(old_price-parseInt(suatChieuSom));
			});
	}
		
}




function init_Step2(){
	console.log('init step2');
	
	var typeOfSession = $('#session-type-select').val();
	
	$.ajax({  
        url:"../session.html?action=tickettype&type="+typeOfSession,  
        type:'post',  
        dataType:'json',  
        success:function(data){
        	
        	
        	//clear
        	$('.ticket-type-panel').find('.tbody_ticket_type').empty();
        	
        	//apend
        	for(var i in data){
        		
        		var append ='<tr id="'+data[i].maLoaiVe+'">'
                +'<td>'+data[i].maLoaiVe+'</td>'
                +'<td>'+data[i].tenLoaiVe+'</td>'
                +'<td>'+data[i].moTa+'</td>'
                +'<td>'+data[i].loai+'</td>'
                +'<td id="'+data[i].maLoaiVe+'" data-price-default="'+data[i].giaTien+'">'+data[i].giaTien+' VNĐ</td>'
                +'<td>'
                +'<div class="checkbox">'
                +'<label>'
                +'<input type="checkbox" value="'+data[i].maLoaiVe+'"> Áp dụng'
                +'</label>'
                +'</div>'
                +'</td>'
                +'</tr>';
        		
        		
        		//add
        		$('.ticket-type-panel').find('.tbody_ticket_type').append(append);
        		
        	}
        	
        	//chờ click áp dụng
        	
        	$('.ticket-type-panel').find('.tbody_ticket_type').on('click','input[type="checkbox"]', function(){
        		$(this).addClass('selected');
        	});
        			

            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	}) ; 
	
	
	
}

function init_Select_Session_Time(){
	console.log('init');
	
	
	//chọn button 
	$('.row.panel.my-panel-default').on('click','.btn-session-time', function(event){
		
		event.stopImmediatePropagation();
		
		console.log('click button session');
		
		
		var button = $(this);
		
		
		//add session đúng kiểu phòng
		var typeOfSession = $('#session-type-select').val();
		var roomId = button.data('room');
		
		var typeOfRoom = $('.row.panel.my-panel-default').find('a[href="#'+roomId+'"]').data('room-type');
		
		console.log('roomId='+roomId);
		
		console.log("typeOfSession="+typeOfSession);
		
		console.log('typeOfRoom='+typeOfRoom);
		
		if(typeOfSession == '1' && typeOfRoom == '2D'){
			$('#typeRoomNotVaild').dialog({ 
		        autoOpen: false, 
		        buttons: {
		           OK: function() { $(this).dialog("close"); }
		        }
		     });
			 $( "#typeRoomNotVaild" ).dialog( "open" );		
		}
		else
		{
			if(button.hasClass('btn-default')){
				//add
				console.log('click add');
				
				if(typeOfSession == '0'){
					addSessionButton(button,'btn-primary', typeOfSession);
				}
				else  if (typeOfSession == '1'){ //3D
					console.log('chọn suất 3d');
					addSessionButton(button,'btn-warning', typeOfSession);
				} 
				
			}
			
			else if(!button.hasClass('btn-default'))
			{
				
				if(typeOfSession == '0'){
					console.log('remove suất 2d');
					removeSessionButton(button, 'btn-primary');
				}
				else if (typeOfSession == '1'){ //3D
					console.log('remove suất 3d');
					removeSessionButton(button, 'btn-warning');
				} else dialog_Must_Select_Type_Of_Session();
				
			}	
				
			updateNumberOfSessionAvailable();
		}
					
	});
}

function dialog_Must_Select_Type_Of_Session(){
	$('#type_session').dialog({ 
        autoOpen: false, 
        buttons: {
           OK: function() {
               	$(this).dialog("close");
           }
   
        },
        title: "Thao tác"
     });
	 $( "#type_session" ).dialog( "open" );
}

function updateNumberOfSessionAvailable(){
	
	$('.row.panel.my-panel-default').find('.panel-session-in-room').each(function(){	
		var i=0;
		$(this).find('.btn-session-time').each(function(){
			if(!$(this).hasClass('btn-hidden') && $(this).hasClass('btn-default') && !$(this).hasClass('disabled')) i++;
		});
		
		$(this).find('.badge').text(i);
		
	});
}

function addSessionButton(button, class_add, typeOfSession){
	
	console.log('add Session');
	
	button.removeClass('btn-default').addClass(class_add);
	
	console.log('gioChieu='+button.val());
	
	var movieId = $('select[name="currentSession.movieOfSession.maPhim"]').val();
	var movieName = $('#select_movie option[value='+movieId+']').text();
	var roomId = button.data('room');
	var date_current = $('input[name="currentSession.ngayChieu"]').val();
	var timeOfMovie = button.data('minute');
	
	button.text(button.text()+' ['+movieName+']');
	
	//remove button not available
	var hour = button.val();
	var timeOfMovie = button.data('minute');
	//convert timeOfMovie to %5=0
	if(timeOfMovie%5 != 0){
		timeOfMovie += 5 - (timeOfMovie%5);
	}
	
	
	//convert hour to minute
	
	var endPoint = convertToMinute(hour) + (timeOfMovie + 15);
	var element = button;
	
	while(true){
		element = element.next();
		if(element.hasClass('btn-session-time')){
			if(convertToMinute(element.val()) < endPoint) {
				element.addClass('btn-hidden');
			}
			else break;
		} else break;
	}
	
	endPoint = convertToMinute(hour) - (timeOfMovie + 15);
	element = button;
	
	while(true){
		element = element.prev();
		if(element.hasClass('btn-session-time')){
			if(convertToMinute(element.val()) > endPoint) {
				element.addClass('btn-hidden');
			}
			else break;
		} else break;

	}

	//add data session
	button.attr('data-session',movieId+','+roomId+','+date_current+','+button.val()+','+timeOfMovie+','+typeOfSession);
}

function removeSessionButton(button, class_remove){
	
	console.log('remove Session');
		
	button.removeClass(class_remove).addClass('btn-default');
	
	//remove button not available
	var hour = button.val();
	button.text(hour);
	var timeOfMovie = button.data('minute');
	//convert timeOfMovie to %5=0
	
	if(timeOfMovie%5 != 0){
		timeOfMovie += 5 - (timeOfMovie%5);
	}
	
	//console.log('time='+timeOfMovie);
	
	//convert hour to minute
	
	var endPoint = convertToMinute(hour) + (timeOfMovie + 15);
	var element = button;
	
	while(true){
		element = element.next();
		if(element.hasClass('btn-session-time') && element.hasClass('btn-hidden')){
			if(convertToMinute(element.val()) < endPoint) {
				element.removeClass('btn-hidden');
			}
			else break;
		} else break;
	}
	
	endPoint = convertToMinute(hour) - (timeOfMovie + 15);
	element = button;
	
	while(true){
		element = element.prev();
		if(element.hasClass('btn-session-time') && element.hasClass('btn-hidden')){
			if(convertToMinute(element.val()) > endPoint) {
				element.removeClass('btn-hidden');
			}
			else break;
		} else break;

	}
		
	//remove data session
	button.attr('data-session','');
}


function convertToMinute(hour){
	
	//console.log('str_hour='+hour);
	var hour_arr = hour.split(":");
	
	//console.log(hour_arr[0]+':'+hour_arr[1]);
	
	return parseInt(hour_arr[0])*60 + parseInt(hour_arr[1]);
}


function ajax_LoadMovieList(){
	console.log('get danhSachPhim');
	$('select[name="currentSession.movieOfSession.maPhim"]').find('option').remove();	
    
    var choose = $('.session_date_picker').val();
    console.log('chon:'+choose);
      
    $.ajax({  
                url:"../session.html?action=movielist&date="+choose,  
                type:'post',  
                dataType:'json',  
                success:function(data){
                	
                			//clear
                			$('.session_panel').children(0).remove();
                			
                	

                	
                			$('select[name="currentSession.movieOfSession.maPhim"]').find('option').remove();
                			$('select[name="currentSession.movieOfSession.maPhim"]').append('<option value="0">Chọn phim</option>'); 
                            $.each(data,function(i){                           	
                                    $('select[name="currentSession.movieOfSession.maPhim"]').append('<option value='+data[i].maPhim+'>'+data[i].tenPhim+'</option>'); 
                            });  
                            

                    },  
                error:  function(){  
                        alert("LỖI !");  
                    }                     
        }) ; 
    


}

function ajax_LoadSessionAvailable(){
	
	console.log('ajax_LoadSessionAvailable');
	
	var movieId = $('select[name="currentSession.movieOfSession.maPhim"]').val();
	var current_date = $('input[name="currentSession.ngayChieu"]').val();
	
	console.log('call:../session.html?action=sessiontime&date='+current_date+'&movieId='+movieId);
	
	 $.ajax({  
         url:"../session.html?action=sessiontime&date="+current_date+"&movieId="+movieId,  
         type:'post',  
         dataType:'json',  
         success:function(data){
        	 
        	 //clear select type
        	 $('#session-type-select').find('option').remove();
        	 
        	 //set menu chọn
        	 var append_select_typeNull = '<option value="-1">--Chọn loại suất--</option>'
        	 var append_select_type2D = '<option value="0">2D</option>';
          	 var append_select_type3D = '<option value="1">3D</option>';
        	 
        	 //remove div
             $('.info_movie_current').children(0).remove();
             
             	var type= '2D';
             	if(data[0].movie_select.type3d == '1') { 
             		type = '2D/3D';  
             		$('#session-type-select').append(append_select_typeNull).append(append_select_type2D).append(append_select_type3D);
             	}
             	else $('#session-type-select').append(append_select_typeNull).append(append_select_type2D);
             	
             	

	           var append_info = '<div class="form-group">'
	           +'<strong>'+data[0].movie_select.tenPhim+'</strong>' 
	           +'<span id="timeOfMovie">'+type+'</span>'
	           +'<span>Thời lượng: '+data[0].movie_select.thoiLuong+' phút</span>'
	           +'<span>Công chiếu: '+data[0].movie_select.thoiGianCongChieu+'</span>'
	           +'</div>'
	           
	           $('.info_movie_current').append(append_info);
             
        	 //remove
        	 $('.panel-group.panel-session-in-room').each(function(){
        		 $(this).remove();
        	 });
        	 
        	 
        	 var date = $('.session_date_picker').val();
        	 
        	 
        	 for (var i in data) {
        		 //console.log(data[i].room.tenPhong);

        		 //tạo ra các collapse
        		 var append ='<div class="panel-group panel-session-in-room" id="accordion"> '
				 +'<div class="panel panel-success">'
				 +'<div class="panel-heading">'
				 +'<h5>'
				 +'<a data-toggle="collapse" data-parent="#accordion" href="#'+data[i].room.maPhong+'" data-room-type="'+data[i].room.loaiPhong+'" class="collapsed" aria-expanded="false"> Suất khả dụng cho '
				 +'phim trong ngày '+date+' tại '+data[i].room.tenPhong+' ['+data[i].room.loaiPhong+'] '
				 +'<span class="badge" style="float: right;"></span>'
				 +'</a>'
				 +'</h5>'
				 +'</div>'
				 +'<div id="'+data[i].room.maPhong+'" class="panel-collapse collapse" aria-expanded="false" style="height: 0px;">'
				 +'<div class="panel-body">'
				 +'</div></div></div></div>';
        		 
        		 //console.log(append);
        		 
        		 $('.session_panel').append(append);
        		 
        		 
        		 
        		 var sessionTimeList = data[i].sessionTimeList;

        		 for(var j in sessionTimeList){
        			 var tenPhim = sessionTimeList[j].tenPhim.toString();
        			 if(tenPhim != ''){
        				 var type3d = sessionTimeList[j].type3d.toString();
        				 if(type3d == 'true'){
        					 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-type="0" data-session="" class="btn btn-success btn-warning disabled btn-session-time">'+sessionTimeList[j].gioChieu+' ['+sessionTimeList[j].tenPhim+']</button>'); 
        				 }
        				 else {
        					 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-type="0" data-session="" class="btn btn-success btn-primary disabled btn-session-time">'+sessionTimeList[j].gioChieu+' ['+sessionTimeList[j].tenPhim+']</button>'); 
            				 
        				 }
        				 
        			 }
        			 else {
        				 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-type="0" data-session="" class="btn btn-default btn-session-time">'+sessionTimeList[j].gioChieu+'</button>'); 
        			 }
        			 
        		 }
        		 
        		 $('a[href=#'+data[i].room.maPhong+']').find('span').text(sessionTimeList.length);
	 
        	 }
        	 
        	 
        		console.log('call update Number');
        		updateNumberOfSessionAvailable();
        	 
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
 }) ; 
}




var form = $("#add_session").show();
 
form.steps({
    headerTag: "h3",
    bodyTag: "section",
    transitionEffect: $.fn.steps.transitionEffect.none,
    transitionEffectSpeed: 200,
    titleTemplate: '<span class="number">#index#.</span> #title#',
    loadingTemplate: '<span class="spinner"></span> #text#',
    onStepChanging: function (event, currentIndex, newIndex)
    {
    	//Allways allow previous action even if the current form is not valid!
	    if (currentIndex > newIndex)
	    {
	            return true;
	    }

    	
    	console.log('currentIndex='+currentIndex);
        
        if (currentIndex === 1){
        	if($('input[class="selected"]').length === 0) {
 
        		console.log('call Dialog ticket type');
    			
    			$('#empty_ticket_type').dialog({ 
    		        autoOpen: false, 
    		        buttons: {
    		           OK: function() {
    		               	$(this).dialog("close");
    		           }
    		   
    		        },
    		        title: "Thao tác"
    		     });
    			 $( "#empty_ticket_type" ).dialog( "open" );
    			 
    			 return false;
            	
            }
        }
        
        if (currentIndex === 0){
        	if($('.btn.btn-session-time.btn-warning, .btn.btn-session-time.btn-primary').not('.disabled').length === 0) {
        		
            	
        		console.log('call Dialog session empty');
    			
    			$('#empty_session').dialog({ 
    		        autoOpen: false, 
    		        buttons: {
    		           OK: function() {
    		               	$(this).dialog("close");
    		           }
    		   
    		        },
    		        title: "Thao tác"
    		     });
    			 $( "#empty_session" ).dialog( "open" );
    			 
    			 return false;
        	}
        }
        
        return form.valid();
    },
    onStepChanged: function (event, currentIndex, priorIndex)
    {
//        // Used to skip the "Warning" step if the user is old enough.
//        if (currentIndex === 2 && Number($("#age-2").val()) >= 18)
//        {
//            form.steps("next");
//        }
//        // Used to skip the "Warning" step if the user is old enough and wants to the previous step.
//        if (currentIndex === 2 && priorIndex === 3)
//        {
//            form.steps("previous");
//        }
    	
    	if (currentIndex === 1 )
			if($('.btn.btn-session-time.btn-warning, .btn.btn-session-time.btn-primary').length > 0) { 
			
			console.log('to Step2');
			
			init_Step2();

		} else {
		
			console.log('call Dialog empty session');
			
			$('#empty_session').dialog({ 
		        autoOpen: false, 
		        buttons: {
		           OK: function() {
		               	$(this).dialog("close");
		           }
		   
		        },
		        title: "Thao tác"
		     });
			 $( "#empty_session" ).dialog( "open" );		
		
		}
    	else if(currentIndex === 2){
    		init_Step3();
    	}
        
    },
    onFinishing: function (event, currentIndex)
    {
        form.validate().settings.ignore = ":disabled";
        return form.valid();
    },
    onFinished: function (event, currentIndex)
    {
        //add movie session
    	
    	
    	var session_time_str="";
    	var ticket_type_str="";
    	$('.row.panel.my-panel-default').find('.btn.btn-session-time.btn-primary:not("disabled"), .btn.btn-session-time.btn-warning:not("disabled")').each(function(){
    		var data = $(this).data('session');
    		if(data != null){
    			session_time_str += ' ' + data;
    		}
    	});
    	
    	$('.row.panel.my-panel-default').find('.tbody_ticket_type').find('tr:visible').find('.form-control.new-price').each(function(){
    		
    		var data = $(this).attr('id')+"-"+$(this).val();
    		if(data != null){
    			ticket_type_str += ' ' + data;
    		}
    		
    		
    		
    	});
    	
    	console.log(session_time_str);
    	console.log(ticket_type_str);
    	
    	
    	//call ajax add session
    	$.ajax({  
            url:"../session.html?action=addsession",  
            type:'post',
            data:{
                time: session_time_str,
                ticket: ticket_type_str
             },
            dataType:'json',  
            success:function(data){
            	
            	alert('THÊM THÀNH CÔNG !');
            	

                },  
            error:  function(){  
                    alert("LỖI !");  
                }                     
    	}) ; 
    	
    	
    },
    labels: {
        cancel: "Cancel",
        current: "",
        pagination: "Pagination",
        finish: "Thêm suất chiếu",
        next: "Tiếp tục",
        previous: "Quay lại",
        loading: "Loading ..."
    }
});