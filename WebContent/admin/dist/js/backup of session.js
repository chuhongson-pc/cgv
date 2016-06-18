$(function() {

//load movie list
$(".session_date_picker").datepicker({
	format : "dd-mm-yyyy",
	autoclose : true,
	todayHighlight: true
});

$('.session_date_picker').datepicker().on('changeDate', function(e){
	ajax_LoadMovieList();
});

$('select[name="currentSession.movieOfSession.maPhim"]').change(function(){
	ajax_LoadSessionAvailable();
	
});

$('#session-type-select').on('change',function(){
	
	//remove selected session time -reload
	
	$('.row.panel.my-panel-default').find('.btn.btn-session-time').each(function(){
		var value = $(this).val();
		$(this).removeClass('btn-primary').removeClass('btn-warning').removeClass('btn-hidden').addClass('btn-default').text(value);
	});
	
	updateNumberOfSessionAvailable();
	
	init_Select_Session_Time();
	
});

//call diaglog select session type

$('.row.panel.my-panel-default').on('click','.btn-session-time', function(){
	var typeOfSession = $('#session-type-select').val();
	if(typeOfSession == '-1') dialog_Must_Select_Type_Of_Session();
});


//step next
$('a[href="#next"], li[role="tab"]').click(function(){
	
	console.log('next clicked');
	
	var step_current = $('.steps.clearfix').find('.current').find('span[class="number"]').text();
	
	console.log('current='+step_current);
	
	if(step_current == '2.') {
		init_Step2();
	}
	else if(step_current == '3.'){
		init_Step3();
	}
	
});

});

function init_Step3(){
	
	console.log('step3');
	
	$( ".panel-setting-ticket-price" ).empty();
	
	$( ".table-type-ticket" ).clone().prependTo('.panel-setting-ticket-price');
	
	//replace
	$('.panel-setting-ticket-price').find('.checkbox').hide().parent().append('<input class="form-control new-price" placeholder="">');
	
	//remove cac row không được chọn
	$('.panel-setting-ticket-price').find('input[type="checkbox"]').not('.selected').each(function(){
		
		var value  = $(this).val();
		console.log('hide='+value);
		$('tr[id='+value+']').hide();
		
	});
	
}

function init_Step2(){
	console.log('init step2');
	
	//var typeOfMovie = $('#timeOfMovie').text();
	
	$.ajax({  
        url:"../session.html?action=tickettype",  
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
                +'<td>'+data[i].giaTien+' VNĐ</td>'
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
	$('.row.panel.my-panel-default').on('click','.btn-session-time', function(){
		
		var typeOfSession = $('#session-type-select').val();
		var button = $(this);
		
		if(typeOfSession == '-1') dialog_Must_Select_Type_Of_Session();
		
		if($(this).hasClass('btn-default')){
			//add
			
			if(typeOfSession == '0'){
				addSessionButton(button,'btn-primary', typeOfSession);
			}
			else  if (typeOfSession == '1'){ //3D
				console.log('chọn suất 3d');
				addSessionButton(button,'btn-warning', typeOfSession);
			} else dialog_Must_Select_Type_Of_Session();
			
		}
		
		else 
		{
			if(typeOfSession == '0'){
				console.log('chọn suất 2d');
				removeSessionButton(button, 'btn-primary');
			}
			else if (typeOfSession == '1'){ //3D
				console.log('chọn suất 3d');
				removeSessionButton(button, 'btn-warning');
			} else dialog_Must_Select_Type_Of_Session();
			
		}	
			
//			var button = $(this);
//			
//			var typeOfSession = $('#session-type-select').val();
//			if(typeOfMovie == '2D')
//			{
//				$('#dialog3d').dialog({ 
//		               autoOpen: false, 
//		               buttons: {
//		                  XÓA: function() {
//		                      	$(this).dialog("close");
//		          		   		//remove
//		                      	console.log('remove from dialog');
//		          		   		removeSessionButton(button);
//		                  },
//		                  SESSION3D: function(){
//		                      	$(this).dialog("close");
//		                      	button.attr('data-type','1');
//		                      	var last_data = button.attr('data-session');
//		                      	console.log(last_data);
//		                      	last_data = last_data.toString();
//		                      	last_data = last_data.substring(0,last_data.length-1);
//		                      	last_data += '1';
//		                      	button.attr('data-session',last_data);
//		                      	button.removeClass('btn-primary').addClass('btn-warning');
//		                  }
//		               },
//		               title: "Thao tác"
//		            });
//				 $( "#dialog3d" ).dialog( "open" );
//			}
//			else //3d
//			{
//				console.log('remove from 2D type');
//		          removeSessionButton(button);
//			}
//			       
//		}
		
		updateNumberOfSessionAvailable();
		
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
			if(!$(this).hasClass('btn-hidden') && $(this).hasClass('btn-default')) i++;
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
	button.attr('data-session',movieId+'-'+roomId+'-'+button.val()+'-'+typeOfSession);
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
				 +'<a data-toggle="collapse" data-parent="#accordion" href="#'+data[i].room.maPhong+'" class="" aria-expanded="true"> Suất khả dụng cho '
				 +'phim trong ngày '+date+' tại '+data[i].room.tenPhong+' '
				 +'<span class="badge" style="float: right;"></span>'
				 +'</a>'
				 +'</h5>'
				 +'</div>'
				 +'<div id="'+data[i].room.maPhong+'" class="panel-collapse collapse in" aria-expanded="true">'
				 +'<div class="panel-body">'
				 +'</div></div></div></div>';
        		 
        		 //console.log(append);
        		 
        		 $('.session_panel').append(append);
        		 
        		 
        		 
        		 var sessionTimeList = data[i].sessionTimeList;

        		 for(var j in sessionTimeList){
        			 var tenPhim = sessionTimeList[j].tenPhim.toString();
        			 if(tenPhim != ''){
        				 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-type="0" data-session="" class="btn btn-success disabled btn-session-time">'+sessionTimeList[j].gioChieu+' ['+sessionTimeList[j].tenPhim+']</button>');
        			 }
        			 else {
        				 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-type="0" data-session="" class="btn btn-default btn-session-time">'+sessionTimeList[j].gioChieu+'</button>'); 
        			 }
        			 
        		 }
        		 
        		 $('a[href=#'+data[i].room.maPhong+']').find('span').text(sessionTimeList.length);
	 
        	 }
        	 
        	 
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
        // Allways allow previous action even if the current form is not valid!
        if (currentIndex > newIndex)
        {
            return true;
        }
        // Forbid next action on "Warning" step if the user is to young
        if (newIndex === 3 && Number($("#age-2").val()) < 18)
        {
            return false;
        }
        // Needed in some cases if the user went back (clean up)
        if (currentIndex < newIndex)
        {
            // To remove error styles
            form.find(".body:eq(" + newIndex + ") label.error").remove();
            form.find(".body:eq(" + newIndex + ") .error").removeClass("error");
        }
        form.validate().settings.ignore = ":disabled,:hidden";
        return form.valid();
    },
    onStepChanged: function (event, currentIndex, priorIndex)
    {
        // Used to skip the "Warning" step if the user is old enough.
        if (currentIndex === 2 && Number($("#age-2").val()) >= 18)
        {
            form.steps("next");
        }
        // Used to skip the "Warning" step if the user is old enough and wants to the previous step.
        if (currentIndex === 2 && priorIndex === 3)
        {
            form.steps("previous");
        }
    },
    onFinishing: function (event, currentIndex)
    {
        form.validate().settings.ignore = ":disabled";
        return form.valid();
    },
    onFinished: function (event, currentIndex)
    {
        alert("Submitted!");
    },
    labels: {
        cancel: "Cancel",
        current: "",
        pagination: "Pagination",
        finish: "Finish",
        next: "Tiếp tục",
        previous: "Quay lại",
        loading: "Loading ..."
    }
});