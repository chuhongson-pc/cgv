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
    	
     var validator = $("#movie_form").validate({
        
        rules: {
        	'currentMovie.tenPhim':{
                required: true,
                minlength: 2,
                maxlength: 200
            },
            'currentMovie.daoDien': {
                required: true,
                minlength: 2,
                maxlength: 100
            },
            'currentMovie.theLoai': {
                required: true,
                minlength: 2,
                maxlength: 50
               
            },
            'currentMovie.nhaSX': {
                required: true,
                minlength: 2,
                maxlength: 50
            },
            'currentMovie.dienVien': {
                required: true,
                minlength: 5,
                maxlength: 500
               
            },
            'currentMovie.thoiLuong': {
                required: true,
                number:true
            },
            'currentMovie.trailer':{
            	required: true,
            	minlength: 5,
            	maxlength: 50
            },
            'currentMovie.moTa':{
            	required: true,
            	minlength: 10,
            	maxlength: 1000
            },
            'currentMovie.thoiGianCongChieu': {
            	required : true,
            	anyDate : true
            }
        },
        messages: {
        	'currentMovie.tenPhim': {
            	required: "Bạn vui lòng nhập tên phim.",
            	minlength: jQuery.validator.format("Tên phim phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Tên phim nhiều nhất {0} ký tự")
            },
            'currentMovie.daoDien': {
                required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Tên đạo diễn phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Tên đạo diễn nhiều nhất {0} ký tự")
            },
            'currentMovie.theLoai': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Mật khẩu phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Mật khẩu phải ít hơn {0} ký tự"),
            },
            'currentMovie.nhaSX': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Ít hơn {0} ký tự"),
            },
            'currentMovie.dienVien': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            },
            'currentMovie.thoiLuong': {
            	required: "Bạn vui lòng nhập dữ liệu.",
                number: "Bạn phải nhập số.",
                minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            },
            'currentMovie.trailer':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            	
            },
            'currentMovie.moTa':{
            	required: "Bạn vui lòng nhập dữ liệu.",
            	minlength: jQuery.validator.format("Dữ liệu ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Dự liệu ít hơn {0} ký tự")
            	
            },
            'currentMovie.thoiGianCongChieu': {
            	required: "Bạn vui lòng nhập dữ liệu.",
            	anyDate: "Bạn vui lòng nhập vào một ngày hợp lệ."
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

