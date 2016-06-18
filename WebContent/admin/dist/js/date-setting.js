$(document).ready(function () {
	console.log('Run');
	
    //comma
    insertComma(); 
    
    $('.row.panel.my-panel-default ').find('.money').on('change', function(){
    	 insertComma(); 
    });

    var validator = $("#date_setting_form").validate({
        
        rules: {
        	'suatChieuSom':{
                required: true,
                number: true
                
            },
        	'suatChieuCuoiTuan':{
        		 required: true,
                 number: true
            },
            'thuTuVuiVe': {
            	required: true,
                number: true
            },
            'ngayLe': {
                required: true
             
            },
            'giaNgayLe': {
            	required: true,
                number: true
            }
       
        },
        messages: {
        	'suatChieuSom': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn vui lòng nhập đúng định dạng số."
            },
            'suatChieuCuoiTuan': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn vui lòng nhập đúng định dạng số."
            },
            'thuTuVuiVe': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn vui lòng nhập đúng định dạng số."
            },
            'ngayLe': {
            	required: "Bạn vui lòng nhập dữ liệu."
            	
            },
            'giaNgayLe': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn vui lòng nhập đúng định dạng số."
            }
           
        },
        // the errorPlacement has to take the table layout into account
        errorPlacement: function (error, element) {
        		console.log('validate');
                error.appendTo(element.next());
        },
 
        // set this class to error-labels to indicate valid fields
        success: function (label) {
            // set &nbsp; as text for IE
            label.html("&nbsp;").remove();
            
        },
        highlight: function (element, errorClass) {
            
            $(element).addClass('parsley-validated parsley-error');

        },
        //
        unhighlight: function( element, errorClass, validClass ){
           
            $(element).removeClass('parsley-validated parsley-error');
        }
    });
     
     

        
});

function insertComma(){
	
	console.log('insert comma');
	
	$('.row.panel.my-panel-default ').find('.money').each(function () {
		
		var text = $(this).val();
		text = text.replace(",","");
		
		console.log('gan text='+text);
		$(this).val(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}

