$(document).ready(function(){
	
	var selected_tab = $('#selected_tab_panel:hidden').val();

	if (selected_tab == 'sale') { //
		$('#sale').addClass('active');
		
	} else if (selected_tab == 'movie1') {  
		$('#movie1').addClass('active');
	
	} else if (selected_tab == 'movie2') {  
		$('#movie2').addClass('active');

	} else if (selected_tab == 'sessionlist') { 
		$('#sessionlist').addClass('active');

	} else if (selected_tab == 'addsession') { 
		$('#addsession').addClass('active');
		
	} else if (selected_tab == 'session3')  
	{
		$('#session3').addClass('active');
		
	} else if (selected_tab == 'room') { //
		
		$('#room').addClass('active');

	} else if(selected_tab == 'ticket'){ //
		$('#ticket').children().addClass('active');
		
	} else if(selected_tab == 'fastfood'){ //
		$('#fastfood').addClass('active');
		
	} else if(selected_tab == 'poster'){ //
		$('#poster').addClass('active');
		
	} else if(selected_tab == 'transaction'){ //
		$('#transaction').addClass('active');
		
	} else if(selected_tab == 'news'){ //
		$('#news').addClass('active');
		
	} else if(selected_tab == 'customer'){ //
		$('#customer').addClass('active');
		
	} else if(selected_tab == 'staff'){ //
		$('#staff').addClass('active');
		
	} else if(selected_tab == 'statistic'){ //
		$('#statistic').addClass('active');
		
	} 
	
});