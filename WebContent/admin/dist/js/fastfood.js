$(document).ready(function(){

	var validator = validate_ff();
	
	load_DataTable();
	
	
	//info fastfood
	
	$('.row.panel.my-panel-default ').on('click', 'button[data-target="#infoFFModal"]', function(){
		
		//clear validator
		validator.resetForm();
		
		//ajax load info ff
		ajax_LoadFFInfo($(this));
		
		
	});
	
	$('.row.panel.my-panel-default ').on('click', 'button[data-target="#addFFModal"]', function(){
		
		//clear modal
		$('#addFFModal').find('input[name="tenFF"]').val("");
		$('#addFFModal').find('textarea[name="moTa"]').val("");
		$('#addFFModal').find('input[name="giaTien"]').val("");
		
	});
	
	
	

	$('#add_save_button').on('click', function(){		
		//ajax add ff
		var form = $("#addFFModal_Form");
		
        if (form.valid()) 
        {
        	ajax_addFF();
        }
	});
	//update
	
	$('#info_save_button').on('click', function(){	
		
        
         var form = $("#infoFFModal_Form");

         if (form.valid()) 
         {
        	 ajax_UpdateFFInfo($(this));
         }
        
		
	});
	
	//close modal ->reload
	$('#infoFFModal, #addFFModal').find('.close').on('click', function(){
		
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
                    ajax_DeleteFF(button);
                },
                CANCEL: function(){
                	$(this).dialog("close");
                }
             }
          });
          
          $('#confirm').dialog( "open" );
			
	});

});

function ajax_DeleteFF(button){
	
	var id = button.data('ff-id');
	
	 $.ajax({  
         url:"../fastfood.html?action=delete&id="+id,  
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
	
	$('#fastfood_Table_wrapper').remove();

	var append = '<table class="table table-striped table-bordered table-hover" id="fastfood_Table">'
					+'<thead>'
						+'<tr>'
							+'<th>Tên</th>'
							+'<th>Loại</th>'
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
	
	console.log('load table');
	
	$('#fastfood_Table').dataTable({
		//"sScrollX": "100%", //This is what made my columns increase in size.
        "bScrollCollapse": true,
        //"sScrollY": "400px",
        "bAutoWidth": false,
        "aoColumns": [
            { "sWidth": "null" }, // 1st column width 
            { "sWidth": "null" }, // 2nd column width 
            { "sWidth": "null" }, // 3rd column width
            { "sWidth": "null" }, // 4rd column width
            { "sWidth": "14%" }, // 5th column width

            ],
		  	"bPaginate": true,
		  	"order": [ 0, 'asc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,		  	
		 	"sAjaxSource" : "../fastfood.html?action=list"
	});
	
//	setTimeout(insertComma, 1000);
//	insertComma();
}

function insertComma(){
	
	console.log('insert comma');
	
	$('.row.panel.my-panel-default').find('.money').each(function (){
		
		console.log('item');
		var text = $(this).text();
		text = text.replace(",","");
		console.log(text);
		$(this).text(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}

function ajax_UpdateFFInfo(button){
	
	//get Data
	var modal = $('#infoFFModal');
	
	var maFF = modal.find('#info_save_button').attr('data-ff-id');
	console.log('maFF='+maFF);
	
	//var maFF = button.data('ff-id');
	
	var tenFF =  modal.find('input[name="tenFF"]').val();	
	var loaiFF = modal.find('select[name="loaiFF"]').val();	
	var moTa = modal.find('textarea[name="moTa"]').val();	
	var giaTien = modal.find('input[name="giaTien"]').val();
   	var trangThai = modal.find('select[name="trangThai"]').val();
	
	var send = {
			maFF: maFF,
			tenFF: tenFF,
			loaiFF : loaiFF,
			moTa: moTa,
			giaTien: giaTien,
			trangThai: trangThai
	}
	
	 $.ajax({  
         url:"../fastfood.html?action=edit",  
         data: send,
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var result = data.result;
        	 
        	 if(result == 1) {
        		var append =  '<div class="alert alert-success">'
        						+'Thông tin loại đồ ăn đã được cập nhật.'
        						+'</div>';
        		$('#infoFFModal').find('.row.panel-modal').append(append);
        		
        		$('.alert.alert-success').fadeOut(3000);
        		
        		
        		
        	 }
        	 else {
        		 var append = '<div class="alert alert-danger">'
                                +'Lỗi cập nhật, bạn vui lòng thử lại.'
                                +'</div>';	
        		 
        		 $('#infoFFModal').find('.row.panel-modal').append(append);
        		 
        		 $('.alert.alert-danger').fadeOut(1000);
        	 }
        	 
        	 	
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }) ; 
}

function ajax_addFF(){
	//get Data
	var modal = $('#addFFModal');
	
	var tenFF =  modal.find('input[name="tenFF"]').val();
	var loaiFF = modal.find('select[name="loaiFF"]').val();
	var moTa = modal.find('textarea[name="moTa"]').val();
	var giaTien = modal.find('input[name="giaTien"]').val();
	var trangThai = modal.find('select[name="trangThai"]').val();
	
	var send = {			
			tenFF: tenFF,
			loaiFF : loaiFF,
			moTa: moTa,
			giaTien: giaTien,
			trangThai: trangThai
	}
	
	 $.ajax({  
         url:"../fastfood.html?action=add",  
         data: send,
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var result = data.result;
        	 
        	 if(result == 1) {
        		var append =  '<div class="alert alert-success">'
        						+'Thông tin loại đồ ăn đã được thêm vào.'
        						+'</div>';
        		$('#addFFModal').find('.row.panel-modal').append(append);
        		
        		$('.alert.alert-success').fadeOut(3000);
        		
        	 }
        	 else {
        		 var append = '<div class="alert alert-danger">'
                                +'Lỗi, bạn vui lòng thử lại.'
                                +'</div>';
        		 
        		 $('#addFFModal').find('.row.panel-modal').append(append);
        		 $('.alert.alert-danger').fadeOut(1000);
        	 }
        	 
        	 	
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }) ; 
}

function ajax_LoadFFInfo(button){
	
	var id = button.data('ff-id');	
	
	
	 $.ajax({  
         url:"../fastfood.html?action=info&id="+id,  
         type:'post',  
         dataType:'json',  
         success:function(data){  			

        	 var modal = $('#infoFFModal');
        	 
        	 //set data
        	 modal.find('input[name="tenFF"]').val(data.tenFF);
        	 modal.find('textarea[name="moTa"]').val(data.moTa);
        	 modal.find('input[name="giaTien"]').val(data.giaTien);
        	 modal.find('select[name="trangThai"]').val(data.trangThai);
        	 modal.find('select[name="loaiFF"]').val(data.loaiFF);
        	 modal.find('select[name="fastFood"]').val(data.maFF);
        	 
        	 $('#info_save_button').attr('data-ff-id', data.maFF);
        	 
             },  
         error:  function(){  
                 alert("LỖI !");  
             }                     
	 }); 
	 
	
	
}


function validate_ff()
{
	console.log('validate');
	var validator = $('#page-wrapper').find("#infoFFModal_Form, #addFFModal_Form").validate({
   	 rules: {
       	tenFF:{
               required: true,
               minlength: 5,
               maxlength: 50
           },
       	moTa:{
               required: true,
               minlength: 10,
               maxlength: 50
           },
       giaTien:{
           	required: true,
           	number: true
           }
      
       },
       messages: {
       	tenFF: {
           	required: "Bạn vui lòng nhập tên đồ ăn.",
           	minlength: jQuery.validator.format("Tên phim phải có ít nhất {0} ký tự"),
           	maxlength: jQuery.validator.format("Tên phim nhiều nhất {0} ký tự")
           },
           moTa: {
           	required: "Bạn vui lòng nhập mô tả.",
           	minlength: jQuery.validator.format("Tên phim phải có ít nhất {0} ký tự"),
           	maxlength: jQuery.validator.format("Tên phim nhiều nhất {0} ký tự")
           },          
           giaTien:{
           	required: "Bạn vui lòng nhập dữ liệu.",
           	number: "Bạn phải nhập số."	
           }
       }
   });  
	
	
	return validator;
}