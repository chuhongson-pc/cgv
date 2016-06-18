function init_Login() {
	
    if($('.dropdown').length > 0){
    	//có tồn tại
    	console.log("addClass");
    	$('.users__login.current__user').addClass('has-dropdown');
    	$('#login-topbar').on('click',function(){
    		$('.users__login.current__user').toggleClass('hover');
    	});
    	
    	$('.dropdown').on("mouseleave", function () {
    		$('.users__login.current__user').removeClass('hover');
        });
    }
    else
    {
    	console.log("apend");
    	$('.users__login.current__user').append('<a href="login.html">Đăng nhập / Đăng ký</a>');
    }
}
