$(document).ready(function () {
	console.log('Run');
    //
	$.validator.addMethod(
		    "anyDate",
		    function(value, element) {
		        // put your own logic here, this is just a (crappy) example
		        return value.match(/^\d\d?\-\d\d?\-\d\d\d\d$/);
		    },
		    "Please enter a date in the format dd/mm/yyyy."
		);
    //
    	
     var validator = $("#edit_News_Form").validate({
        
        rules: {
        	'currentNews.tieuDe':{
                required: true,
                minlength: 3,
                maxlength: 50
            },
        	'currentNews.batDau':{
                required: true,
                anyDate : true
            },
            'currentNews.ketThuc': {
                required: true,
                anyDate : true
        
            }
       
        },
        messages: {
        	'currentNews.tieuDe': {
            	required: "Bạn vui lòng nhập tiêu đề bài viết.",
            	minlength: jQuery.validator.format("Tên phim phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Tên phim nhiều nhất {0} ký tự")
            },
            'currentNews.batDau': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                anyDate: "Nhập ngày hợp lệ theo định dạng dd-mm-yyyy."
            },
            'currentNews.ketThuc': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                anyDate: "Nhập ngày hợp lệ theo định dạng dd-mm-yyyy."
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

