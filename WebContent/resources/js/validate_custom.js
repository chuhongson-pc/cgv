$(document).ready(function () {
	
	//set checked gender when view profile
	var gender = $('#hidden_gender').val();
	console.log('gioi tinh:'+gender);
	
	if(gender == 'female')
	{
		$('.capitalize.radio[for="genderFemale"]').addClass('checked');
		
	}
	else
	{
		$('.capitalize.radio[for="genderMale"]').addClass('checked');
	}
	
    //change gender
    $('.capitalize.radio[for="genderMale"]').click(function () {
        $(this).addClass('checked');
        $('.capitalize.radio[for="genderFemale"]').removeClass('checked');
        $('input:radio[name="gender"]').filter('[value="male"]').attr('checked', true);
        $('input:radio[name="gender"]').filter('[value="female"]').attr('checked', false);
        $('#hidden_gender').val('male');
    });
    $('.capitalize.radio[for="genderFemale"]').click(function () {
        $(this).addClass('checked');
        $('.capitalize.radio[for="genderMale"]').removeClass('checked');
        $('input:radio[name="gender"]').filter('[value="female"]').attr('checked', true);
        $('input:radio[name="gender"]').filter('[value="male"]').attr('checked', false);
        $('#hidden_gender').val('female');
    });
    
    $('label[for="terms-and-condition"]').click(function(){
        console.log('clicked');
        $("#terms").prop("checked", true);
        $(this).toggleClass('parsley-success checked');
    });
    
    //
    $.validator.addMethod(
    	    "myDateFormat",
    	    function(value, element) {   	        
    	        var re = /^\d{1,2}-\d{1,2}-d{4}$/;    	        
    	        return (this.optional(element) && value=="") || re.test(value);
    	    }
    );
    //
    jQuery.validator.addMethod("mydate", function(value, element) { 
    	  return this.optional(element) || /^\d\d?-\d\d-\d\d\d\d/.test(value); 
    }, "Please specify the date in DD-MM-YYYY format");

    // validate signup form on keyup and submit
        var validator = $("#register_form").validate({
        rules: {
            fullname:{
                required: true,
                minlength: 10,
                maxlength: 50
            },
            address: {
                required: true,
                minlength: 10,
                maxlength: 100
            },
            username: {
                required: true,
                minlength: 6,
                maxlength: 30
               
            },
            password: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            password_confirm: {
                required: true,
                minlength: 5,
                maxlength: 50,
                equalTo: "#password"
            },
            email: {
                required: true,
                email: true,
                minlength: 10,
                maxlength: 50
            },

            phonenumber:{
                required: true,
                number:true,
                minlength:10,
                maxlength:11
            },
            cmnd:{
               required: true,
               number:true,
               minlength:9,
               maxlength:9
            },
            terms: "required",
            current_password:{
            	required: true
            },
            address:{
            	required: true,
            	minlength: 10,
            	maxlength: 50
            },
            dob: {
            	required: true,
            	mydate: true
            }
        },
        messages: {
            fullname: {
            	required: "Bạn vui lòng nhập tên đầy đủ.",
            	minlength: jQuery.validator.format("Tên phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Tên nhiều nhất {0} ký tự")
            },
            username: {
                required: "Bạn vui lòng nhập tên đăng nhập.",
                minlength: jQuery.validator.format("Tên đăng nhập phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Tên đăng nhập nhiều nhất {0} ký tự")
            },
            password: {
                required: "Nhập mật khẩu",
                minlength: jQuery.validator.format("Mật khẩu phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Mật khẩu phải ít hơn {0} ký tự"),
            },
            password_confirm: {
                required: "Nhập mật khẩu xác nhận.",
                minlength: jQuery.validator.format("Mật khẩu xác nhận phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Mật khẩu xác nhận phải ít hơn {0} ký tự"),
                equalTo: "Bạn vui lòng nhập mật khẩu xác nhận chính xác."
            },
            email: {
                required: "Bạn vui lòng nhập email.",
                email: "Email không hợp lệ.",
                minlength: jQuery.validator.format("Email phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("Email phải ít hơn {0} ký tự")
            },
            phonenumber: {
            	required: "Bạn vui lòng nhập vào SĐT",
                number: "SĐT không hợp lệ.",
                minlength: jQuery.validator.format("SĐT phải có ít nhất {0} ký tự"),
                maxlength: jQuery.validator.format("SĐT phải ít hơn {0} ký tự")
            },
            cmnd: {
            	required: "Bạn vui lòng nhập vào CMND.",
                number: "CMND không hợp lệ.",
                minlength: jQuery.validator.format("CMND gồm {0} số."),
                maxlength: jQuery.validator.format("CMND gồm {0} số.")
            },
            address:{
            	required: "Bạn vui lòng nhập vào địa chỉ.",
            	minlength: jQuery.validator.format("Địa chỉ phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Địa chỉ phải ít hơn {0} ký tự")
            },
            dob: {
      	      required: "Bạn vui lòng nhập vào ngày sinh.",
    	      // valid date
      	      mydate: "Bạn vui lòng nhập vào một ngày hợp lệ."
            },
            dateformat: "Bạn vui lòng nhập vào một ngày hợp lệ.",
            terms: "Bạn phải chấp nhận điều kiện của CGV."
        },
        // the errorPlacement has to take the table layout into account
        errorPlacement: function (error, element) {
            if (element.is(":radio"))
            {
                console.log('la radio');
               error.appendTo(element.parent().next().next()); 
            } 
            else if (element.is("input:checkbox")){
                console.log('la checkbox');
                error.appendTo(element.parent().next());
            }
                
            else{
                error.appendTo(element.next());
            }
                
        },
        // specifying a submitHandler prevents the default submit, good for the demo
//        submitHandler: function () {
//            //alert("submitted!");
//        		console.log('click button');
//        		$.ajax({
//        		     
//        			  url: "register.doo?", data :"action=register",success: function(result) {
//        			         
//        			  }
//        			});
//        },
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

