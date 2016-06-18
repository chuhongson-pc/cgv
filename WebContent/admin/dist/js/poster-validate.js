$(document).ready(function () {
	console.log('Run');

     var validator = $("#edit_Poster").validate({
        
        rules: {
        	'currentPoster.moTa':{
                required: true,
                minlength: 3,
                maxlength: 50
            }
       
        },
        messages: {
        	'currentPoster.moTa': {
            	required: "Bạn vui lòng nhập mô tả.",
            	minlength: jQuery.validator.format("Dữ liệu phải có ít nhất {0} ký tự"),
            	maxlength: jQuery.validator.format("Dữ liệu nhiều nhất {0} ký tự")
            }
        }
    });
        

        
});

