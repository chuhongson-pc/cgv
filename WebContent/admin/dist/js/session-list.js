$(document).ready(function(){
	
	//load movie list
	$(".session_date_picker").datepicker({
		format : "dd-mm-yyyy",
		autoclose : true,
		todayHighlight: true
	});
	
	$('#ngayChieu').datepicker().on('changeDate', function(e){
		
		ajax_LoadSessionList();
	});
	
	
	$('.row.panel.my-panel-default ').on('click', '.btn-session-time', function(){
		console.log('click');
		ajax_LoadSessionInfo($(this));
		
	});
	
	//confirm session
	$('.row.panel.my-panel-default ').on('click', '#confirm_button', function(){
				
		ajax_ConfirmSession();
	});
	
	//delete session
	$('.row.panel.my-panel-default ').on('click', '#delete_button', function(){
		
		$('#confirm_Delete').dialog({ 
            autoOpen: false, 
            buttons: {
               XÓA: function() {
            	   $(this).dialog("close");
            	   ajax_DeleteSession();
               },
               CANCEL: function(){
            	   $(this).dialog("close");
               }
            },
            title: "Thao tác"
         });
         
         $("#confirm_Delete").dialog("open");
         
         $('.ui-dialog').css('z-index', 9999);
		
	});
	
	
	//close modal button
	$('.row.panel.my-panel-default ').on('click', '#info_close_button', function(){
		
		//reload
		ajax_LoadSessionList();
		
	});
	
	
	
});

function ajax_DeleteSession(){
	var sessionId = $('#maSuat').text();
	$.ajax({  
	       url:"../session.html?action=deletesession&id="+sessionId,  
	       type:'post',  
	       dataType:'json',  
	       success:function(data){
	      	
	    	   var result = data.result;
	        	 
	        	 if(result == 1) {
	        		 //close modal
	        		 //$('#infoSessionModal').modal('hide');
	        		 $('#infoSessionModal').modal('toggle');
	        		 
	        		 //reload
	        		 ajax_LoadSessionList();
	        	 } 
	        	 else 
	        	 {
	        		 alert("Lỗi, vui lòng thử lại !");
	        	 }
	      	
	           },  
	       error:  function(){  
	               alert("LỖI !");  
	           }                     
		 }) ; 
}

function ajax_ConfirmSession(){
	var sessionId = $('#maSuat').text();
	
	$.ajax({  
	       url:"../session.html?action=confirmsession&id="+sessionId,  
	       type:'post',  
	       dataType:'json',  
	       success:function(data){
	      	
	    	   var result = data.result;
	        	 
	        	 if(result == 1) {
	        		 var append =  '<div class="alert alert-success">'
 						+'Suất chiếu đã được duyệt.'
 						+'</div>';
	        		 
	        		 $('#infoSessionModal').find('.row.panel-modal').append(append);
 		
	        		 //$('.alert.alert-success').fadeOut(3000);
	        		 
	        		 //hide button
	        		 $('#confirm_button').hide();
	        	 } 
	        	 else 
	        	 {
	        		 alert("Lỗi, vui lòng thử lại !");
	        	 }
	      	
	           },  
	       error:  function(){  
	               alert("LỖI !");  
	           }                     
		 }) ; 
	
}

function ajax_LoadSessionInfo(button){
	
	var sessionId = button.data('sessionid');
	

	 $.ajax({  
       url:"../session.html?action=sessioninfo&id="+sessionId,  
       type:'post',  
       dataType:'json',  
       success:function(data){
      	
    	 console.log(data);
    	 var dt = data[0];
    	 //clear modal
      	 $('#infoSessionModal').find('h3').empty();
      	 $('#infoSessionModal').find('.lead').empty();
      	 $('#detailTable').find('tbody').find('tr').remove();
      	 $('.alert.alert-success').remove();
      	 $('#confirm_button').show();
      	
      	 
      	 //tieu de
      	 var tenPhim = dt.movieOfSession.tenPhim;
      
      	 var thoiGian = dt.ngayChieu + " | " +dt.gioChieu + " đến " + dt.gioKetThuc +" | " + dt.roomOfSession.tenPhong;
      	 
      	 console.log('tenPhim='+tenPhim);
      	 console.log('time='+thoiGian);
      	 console.log('maSuat='+dt.maSuat);
      	 
      	 $('#infoSessionModal').find('h3').append(tenPhim);
      	 $('#infoSessionModal').find('.lead').append(thoiGian);
      	 $('#maSuat').text(dt.maSuat);
      	 
      	 var ticket = dt.ticketTypeSettingList;
      	 
      	 for(var i in ticket){
      		 
      		 	console.log('i='+i);
      		 
	      		 var item = ticket[i];
	      		 
	      		 var append = '<tr id="'+item.maLoaiVe+'">'
	      			 		+'<td>'
	      			 		+item.tenLoaiVe
	      			 		+'</td>'
	      			 		+'<td>'
	      			 		+'<span class="money">'+item.giaTien +'</span> VNĐ'
	      			 		+'</td>'
	      			 		+'</tr>';
	      		
	      		$('#detailTable').find('tbody').append(append);	 
      	 }
      	 
      	 //
      	 var trangThai = dt.trangThai;
      	 
      	 if(trangThai == '1') $('#confirm_button').hide();
      	 
      	insertComma();
      	
           },  
       error:  function(){  
               alert("LỖI !");  
           }                     
	 }) ; 
	
}

function insertComma(){
	
	console.log('insert comma');
	
	$('.row.panel.my-panel-default ').find('.money').each(function () {
		
		var text = $(this).text();

		text = text.replace(",","");
		$(this).text(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
        
    });
}

function ajax_LoadSessionList(){
	
	var current_date = $('#ngayChieu').val();
	

	 $.ajax({  
        url:"../session.html?action=sessionlist&date="+current_date,  
        type:'post',  
        dataType:'json',  
        success:function(data){
       	 

       	 //remove
       	 $('.panel-group.panel-session-in-room').each(function(){
       		 $(this).remove();
       	 });
       	 
       	 
       	 var date = $('.session_date_picker').val();
       	 
       	 for (var i in data) {
       		 
       		 //tạo ra các collapse
       		 var append ='<div class="panel-group panel-session-in-room" id="accordion"> '
				 +'<div class="panel panel-success">'
				 +'<div class="panel-heading">'
				 +'<h5>'
				 +'<a data-toggle="collapse" data-parent="#accordion" href="#'+data[i].room.maPhong+'" data-room-type="'+data[i].room.loaiPhong+'" aria-expanded="true"> Các suất '
				 +'chiếu phim trong ngày '+date+' tại '+data[i].room.tenPhong+' ['+data[i].room.loaiPhong+'] '
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
       			 
       				 var type3d = sessionTimeList[j].type3d.toString();
       				 var trangThai = sessionTimeList[j].trangThai;
       				 
       				 console.log('Trangthai='+trangThai);
       				 
       				 var disabled_btn = '';
       				 if(trangThai == '1') disabled_btn = '';
       				 else if (trangThai == '2') disabled_btn = 'disabled';
       				 
       				 if(type3d == 'true'){
       					 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" data-toggle="modal" data-target="#infoSessionModal" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-sessionid="'+sessionTimeList[j].maSuat+'" class="btn btn-warning btn-session-time '+disabled_btn.toString()+'">'+sessionTimeList[j].gioChieu+' ['+sessionTimeList[j].tenPhim+']</button>'); 
       				 }
       				 else {
       					 $('div[id='+data[i].room.maPhong+']').find('.panel-body').append('<button type="button" data-toggle="modal" data-target="#infoSessionModal" value="'+sessionTimeList[j].gioChieu+'" data-minute="'+sessionTimeList[j].thoiLuong+'" data-room="'+sessionTimeList[j].maPhong+'" data-sessionid="'+sessionTimeList[j].maSuat+'" class="btn btn-primary btn-session-time '+disabled_btn.toString()+'">'+sessionTimeList[j].gioChieu+' ['+sessionTimeList[j].tenPhim+']</button>'); 
           				 
       				 }
       				 
       		 }
       		 
       		 $('a[href=#'+data[i].room.maPhong+']').find('span').text(sessionTimeList.length);
	 
       	 }
       	 
       	 
       		console.log('call update Number');
       		updateNumberOfSession();
       	 
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	 }) ; 
}

function updateNumberOfSession(){
	
	$('.row.panel.my-panel-default').find('.panel-session-in-room').each(function(){	
		var i=0;
		$(this).find('.btn-session-time').each(function(){
			i++;
		});
		
		$(this).find('.badge').text(i);
		
	});
}