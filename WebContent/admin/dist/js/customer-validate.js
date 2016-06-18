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
    	
     var validator = $("#customer_form").validate({
        
        rules: {
        	'currentCustomer.username':{
                required: true,
                minlength: 3,
                maxlength: 50
            },
        	'currentCustomer.fullname':{
                required: true,
                minlength: 5,
                maxlength: 50
            },
            'currentCustomer.address': {
                required: true,
                minlength: 10,
                maxlength: 100
            },
            'currentCustomer.email': {
                required: true,
                email: true,
                maxlength: 50
            },
            'currentCustomer.dob': {
                required: true,
                anyDate: true
            },
            'currentCustomer.phonenumber': {
                required: true,
                minlength: 5,
                maxlength: 11,
                number: true
               
            },
            'currentCustomer.cmnd': {
                required: true,
                minlength: 9,
                maxlength: 9
            },

            'currentCustomer.soDuTaiKhoan':{
            	required: true,
            	number: true
            },
            'currentCustomer.password':{
            	minlength: 3,
                maxlength: 50
            },
            'currentCustomer.diemTichLuy': {
            	required: true,
            	number: true
            }
       
        },
        messages: {
        	'currentCustomer.username': {
            	required: "Bạn vui lòng nhập tên đăng nhập.",
            	minlength: jQuery.validator.format("Tên tài khoản phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Tên tài khoản nhiều nhất {0} ký tự")
            },
            'currentCustomer.fullname': {
            	required: "Bạn vui lòng nhập tên đăng nhập.",
            	minlength: jQuery.validator.format("Tên đầy đủ phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("TTên đầy đủ nhiều nhất {0} ký tự")
            },
            'currentCustomer.address': {
                required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Địa chỉ phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Địa chỉ diễn nhiều nhất {0} ký tự")
            },
            'currentCustomer.email': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	email: "Bạn vui lòng nhập đúng định dạng email.",
            	maxlength: jQuery.validator.format("Email nhiều nhất {0} ký tự")
            },
            'currentCustomer.dob': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                anyDate: "Nhập ngày hợp lệ theo định dạng dd-mm-yyyy."
            },
            'currentCustomer.phonenumber': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn phải nhập số điên thoại.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            },
            'currentCustomer.cmnd': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                number: "Bạn phải nhập số.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
                
            },
           
            'currentCustomer.soDuTaiKhoan':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn phải nhập số."
            	
            },
            'currentCustomer.password':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            	
            },
            'currentCustomer.diemTichLuy':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	number: "Bạn phải nhập số."	
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

