$(document).ready(function() {
	
  $('#summernote').summernote();
  
  //one time - copy to div
  show_Content();
  
  disable_DatePicker();
  
  $('.note-editable').on('keyup', function(){
	  
	  console.log('change');
	  copyToHidden();
  });
  
  $('.note-toolbar.btn-toolbar').on('click', function(){
	  console.log('click');
	  copyToHidden();
  });
  
  $('select[name="currentNews.loaiTT"]').on('change', function(){
	  
	  disable_DatePicker();
	  
  });
  
  
});

function copyToHidden(){
	
	var sHTML = $('#summernote').code();

	$('input[name="currentNews.noiDung"]').val(sHTML);
	
}

function click_Save(){
	
//	$('input[type="submit"').on('click', function(event){
//		
//		event.preventDefault();
//		
//		//set value to hidden
//		
//		var sHTML = $('#summernote').code();
//		
//		console.log(sHTML);
//		
//		$('input[name="currentNews.noiDung]').val(sHTML)
//		
//		//call action
//		
//		var newsId = $('input[name="currentNews.maTT"]').val();
//		
//		 $.get("../news.html?action=edit", function(data){
//	          //do what you want with data. 
//	    });
//		
//		
//	});
	
}

function show_Content(){
	
	$('#summernote').code($('input[name="currentNews.noiDung"]').val());
		
}

function disable_DatePicker(){

	var type = $('select[name="currentNews.loaiTT"]').val();
	
	if( type == 'N'){
		
		$('input[name="currentNews.batDau"]').attr("disabled", "disabled"); 
		$('input[name="currentNews.ketThuc"]').attr("disabled", "disabled"); 
		
	} else {
		$('input[name="currentNews.batDau"]').removeAttr("disabled"); 
		$('input[name="currentNews.ketThuc"]').removeAttr("disabled"); 
	}
	
}