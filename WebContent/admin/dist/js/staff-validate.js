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
    	
     var validator = $("#staff_form").validate({
        
        rules: {
        	'currentStaff.username':{
                required: true,
                minlength: 3,
                maxlength: 50
            },
        	'currentStaff.fullname':{
                required: true,
                minlength: 5,
                maxlength: 50
            },
            'currentStaff.address': {
                required: true,
                minlength: 10,
                maxlength: 100
            },
            'currentStaff.email': {
                required: true,
                email: true,
                maxlength: 50
            },
            'currentStaff.dob': {
                required: true,
                anyDate: true
            },
            'currentStaff.phonenumber': {
                required: true,
                minlength: 5,
                maxlength: 11,
                number: true
               
            },
            'currentStaff.cmnd': {
                required: true,
                minlength: 9,
                maxlength: 9
            },
            'currentStaff.password':{
            	minlength: 3,
                maxlength: 50
            }
       
        },
        messages: {
        	'currentStaff.username': {
            	required: "Bạn vui lòng nhập tên đăng nhập.",
            	minlength: jQuery.validator.format("Tên tài khoản phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Tên tài khoản nhiều nhất {0} ký tự")
            },
            'currentStaff.fullname': {
            	required: "Bạn vui lòng nhập tên đăng nhập.",
            	minlength: jQuery.validator.format("Tên đầy đủ phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("TTên đầy đủ nhiều nhất {0} ký tự")
            },
            'currentStaff.address': {
                required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Địa chỉ phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Địa chỉ diễn nhiều nhất {0} ký tự")
            },
            'currentStaff.email': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	email: "Bạn vui lòng nhập đúng định dạng email.",
            	maxlength: jQuery.validator.format("Email nhiều nhất {0} ký tự")
            },
            'currentStaff.dob': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                anyDate: "Nhập ngày hợp lệ theo định dạng dd-mm-yyyy."
            },
            'currentStaff.phonenumber': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn phải nhập số điên thoại.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            },
            'currentStaff.cmnd': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                number: "Bạn phải nhập số.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
                
            },
           
            'currentStaff.password':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            	
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

