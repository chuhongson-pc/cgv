$(document).ready(function(){

	var validator = validate_ticket();
	
	load_DataTable();
	
	//info ticket
	
	$('.row.panel.my-panel-default ').on('click', 'button[data-target="#infoTicketModal"]', function(){
		
		//clear validator
		validator.resetForm();
		
		//call ajax
		ajax_LoadFFList();
		
		//ajax load info ticket
		ajax_LoadTicketInfo($(this));
	
	});
	
	//add
	$('.row.panel.my-panel-default ').on('click', 'button[data-target="#addTicketModal"]', function(){
		
		//clear input last
		$('#addTicketModal').find('input[name="tenLoaiVe"]').val("");
		$('#addTicketModal').find('textarea[name="moTa"]').val("");
		$('#addTicketModal').find('input[name="giaTien"]').val("");
		$('#addTicketModal').find('select[name="soGheThuong"]').val(0);
		$('#addTicketModal').find('select[name="soGheVip"]').val(0);
		
		//call ajax
		ajax_LoadFFList();
	});
	
	$('#add_save_button').on('click', function(){		
		//ajax add ticket
		ajax_addTicket();
	});
	
	//update
	$('#info_save_button').on('click', function(){
		
	     var maLoaiVe = $(this).data('ticket-id');
		
         var form = $("#infoTicketModal_Form");

         if (form.valid()) 
         {
        	 ajax_UpdateTicketInfo(maLoaiVe);
         }			
	});
	
	//close modal ->reload
	$('#infoTicketModal, #addTicketModal').find('.close').on('click', function(){
		
		reaload_DataTable();
		
	});
	
	$('#info_close_button, #add_close_button').on('click', function(){
		reaload_DataTable();
	});
	
	//delete
	$('.row.panel.my-panel-default ').on('click', '.btn.btn-danger', function(){
		
		
		var button = $(this);
		$('#confirm').dialog({ 
             autoOpen: false, 
             buttons: {
                OK: function(){
                    $(this).dialog("close");
                    ajax_DeleteTicket(button);
                },
                CANCEL: function(){
                	$(this).dialog("close");
                }
             }
          });
          
          $('#confirm').dialog( "open" );
		
	});

});

function ajax_DeleteTicket(button){
	
	var id = button.data('ticket-id');
	
	 $.ajax({  
         url:"../ticket.html?action=delete&id="+id,  
         type:'post',  
         dataType:'json',  
         success:function(data){  			
        	 var result = data.result;
        	 
	        	 if(result == 1) {
	        		 reaload_DataTable();
	        	 } else {
	        		 alert("Lỗi, vui lòng thử lại !");
	        	 }
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }); 
}

function reaload_DataTable(){
	
	$('#ticket_Table_wrapper').remove();

	var append = '<table class="table table-striped table-bordered table-hover" id="ticket_Table">'
					+'<thead>'
						+'<tr>'
							+'<th>Tên</th>'
							+'<th>Loại</th>'
							+'<th>Số ghế</th>'
							+'<th>Đồ ăn</th>'
							+'<th>Giá tiền</th>'
							+'<th>Status</th>'
							+'<th>Thao tác</th>'
						+'</tr>'
					+'</thead>'
				+'</table>';
	
	$(append).insertBefore('.text-center');
	
	load_DataTable();
}

function load_DataTable(){
	$('#ticket_Table').dataTable({
		//"sScrollX": "100%", //This is what made my columns increase in size.
        "bScrollCollapse": true,
        //"sScrollY": "400px",
        "bAutoWidth": false,
        "aoColumns": [
            { "sWidth": "null" }, // 1st column width 
            { "sWidth": "null" }, // 2nd column width 
            { "sWidth": "null" }, // 3rd column width
            { "sWidth": "null" }, // 4th column width 
            { "sWidth": "null" }, // 5th column width 
            { "sWidth": "null" }, // 5th column width 
            { "sWidth": "14%" }, // 6th column width

            ],
		  	"bPaginate": true,
		  	"order": [ 0, 'asc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,		  	
		 	"sAjaxSource" : "../ticket.html?action=list"
	      });
	
}

function insertComma(){
	
	console.log('insert comma');
	
	$('#infoTicketModal').find('.money, input[name="giaTien"]').each(function () {
		
		var text = $(this).val();
		text = text.replace(",","");
		$(this).text(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}


function ajax_UpdateTicketInfo(){
	
	//get Data
	var modal = $('#infoTicketModal');

	var maLoaiVe = modal.find('#info_save_button').attr('data-ticket-id');
	console.log('maLoaiVe='+maLoaiVe);
	
	var tenLoaiVe =  modal.find('input[name="tenLoaiVe"]').val();
	var loaiSuatApDung = modal.find('select[name="loaiSuatApDung"]').val();
	var moTa = modal.find('textarea[name="moTa"]').val();
	var soGheThuong = modal.find('select[name="soGheThuong"]').val();
	var soGheVip = modal.find('select[name="soGheVip"]').val();
	
	var giaTien_str = modal.find('input[name="giaTien"]').val();
	giaTien_str = giaTien_str.replace(",","");
	
	var giaTien = giaTien_str;
   	var trangThai = modal.find('select[name="trangThai"]').val();
	var maFF =  modal.find('select[name="fastFood"]').val();
	
	var send = {
			maLoaiVe: maLoaiVe,
			tenLoaiVe: tenLoaiVe,
			loaiSuatApDung : loaiSuatApDung,
			moTa: moTa,
			soGheThuong: soGheThuong,
			soGheVip: soGheVip,
			maFF: maFF,
			giaTien: giaTien,
			trangThai: trangThai
	}
	
	 $.ajax({  
         url:"../ticket.html?action=edit",  
         data: send,
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var result = data.result;
        	 
        	 if(result == 1) {
        		var append =  '<div class="alert alert-success">'
        						+'Thông tin loại vé đã được cập nhật.'
        						+'</div>';
        		$('#infoTicketModal').find('.row.panel-modal').append(append);
        		
        		$('.alert.alert-success').fadeOut(3000);
        		
        		
        		
        	 }
        	 else {
        		 var append = '<div class="alert alert-danger">'
                                +'Lỗi cập nhật, bạn vui lòng thử lại.'
                                +'</div>';	
        		 $('.alert.alert-danger').fadeOut(1000);
        	 }
        	 
        	 	
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }) ; 
}

function ajax_addTicket(){
	//get Data
	var modal = $('#addTicketModal');
	
	var tenLoaiVe =  modal.find('input[name="tenLoaiVe"]').val();
	var loaiSuatApDung = modal.find('select[name="loaiSuatApDung"]').val();
	var moTa = modal.find('textarea[name="moTa"]').val();
	var soGheThuong = modal.find('select[name="soGheThuong"]').val();
	var soGheVip = modal.find('select[name="soGheVip"]').val();
	
	var giaTien_str = modal.find('input[name="giaTien"]').val();
	giaTien_str = giaTien_str.replace(",","");
	var giaTien = giaTien_str;
	
	var maFF =  modal.find('select[name="fastFood"]').val();
	
	var send = {
			
			tenLoaiVe: tenLoaiVe,
			loaiSuatApDung : loaiSuatApDung,
			moTa: moTa,
			soGheThuong: soGheThuong,
			soGheVip: soGheVip,
			maFF: maFF,
			giaTien: giaTien
	}
	
	 $.ajax({  
         url:"../ticket.html?action=add",  
         data: send,
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var result = data.result;
        	 
        	 if(result == 1) {
        		var append =  '<div class="alert alert-success">'
        						+'Thông tin loại vé đã được thêm vào.'
        						+'</div>';
        		$('#addTicketModal').find('.row.panel-modal').append(append);
        		
        		$('.alert.alert-success').fadeOut(3000);
        		
        	 }
        	 else {
        		 var append = '<div class="alert alert-danger">'
                                +'Lỗi, bạn vui lòng thử lại.'
                                +'</div>';	
        		 $('.alert.alert-danger').fadeOut(1000);
        	 }
        	 
        	 	
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }) ; 
}

function ajax_LoadTicketInfo(button){
	
	var id = button.data('ticket-id');	
	
	//console.log('ticket_id='+id);
	
	 $.ajax({  
         url:"../ticket.html?action=info&id="+id,  
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var modal = $('#infoTicketModal');
        	 
        	 //set data
        	 modal.find('input[name="tenLoaiVe"]').val(data.tenLoaiVe);
        	 modal.find('textarea[name="moTa"]').val(data.moTa);
        	 modal.find('input[name="giaTien"]').val(data.giaTien);
        	 modal.find('select[name="loaiSuatApDung"]').val(data.loaiSuatApDung);
        	 modal.find('select[name="soGheThuong"]').val(data.soGheThuong);
        	 modal.find('select[name="soGheVip"]').val(data.soGheVip);
        	 modal.find('select[name="trangThai"]').val(data.trangThai);
        	 modal.find('select[name="fastFood"]').val(data.maFF);
        	 
        	 $('#info_save_button').attr('data-ticket-id', data.maLoaiVe);
        	 
        	 
        	 //load done -> insert comma
        	 //setTimeout(insertComma,1000);
        	 insertComma();
        	 
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }); 
	 
	
	
}

function ajax_LoadFFList(){
	
	console.log('get fflist');
	
	$('select[name="fastFood"]').find('option').remove();
	
	$('#infoTicketModal').find('input').val("");	
    
    $.ajax({  
                url:"../ticket.html?action=fflist",  
                type:'post',  
                dataType:'json',  
                success:function(data){
                	
                			$('select[name="fastFood"]').find('option').remove();
                			
                			$('select[name="fastFood"]').append('<option value="0">Chọn Fastfood</option>');
                			
                            $.each(data,function(i){                           	
                                    $('select[name="fastFood"]').append('<option value='+data[i].maFF+'>'+data[i].tenFF+'</option>'); 
                            });  
                            

                    },  
                error:  function(){  
                        alert("LỖI !");  
                    }                     
        }) ; 
    


}

function validate_ticket()
{
	console.log('validate');
	var validator = $('#page-wrapper').find("#infoTicketModal_Form").validate({
   	 rules: {
       	tenLoaiVe:{
               required: true,
               minlength: 5,
               maxlength: 100
           },
       	moTa:{
               required: true,
               minlength: 10,
               maxlength: 300
           },
           giaTien:{
           	required: true,
           	number: true
           }
      
       },
       messages: {
       	tenLoaiVe: {
           	required: "Bạn vui lòng nhập tên loại vé.",
           	minlength: jQuery.validator.format("Tên loại vé phải có ít nhất {0} ký tự"),
           	maxlength: jQuery.validator.format("Tên loại vé nhiều nhất {0} ký tự")
           },
           moTa: {
           	required: "Bạn vui lòng nhập mô tả.",
           	minlength: jQuery.validator.format("Mô tả loại vé phải có ít nhất {0} ký tự"),
           	maxlength: jQuery.validator.format("Mô tả loại vé nhiều nhất {0} ký tự")
           },          
           giaTien:{
           	required: "Bạn vui lòng nhập dữ liệu.",
           	number: "Bạn phải nhập số."	
           }
       }
   });  
	return validator;
}