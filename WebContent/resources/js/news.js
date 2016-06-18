$(document).ready(function(){
	
	ajax_List(1);
	
	$('#tinTuc').click(function(){
		$('#khuyenMai').parent().removeClass('active');
		$(this).parent().addClass('active');
		ajax_List(2);
	});
	
	$('#khuyenMai').click(function(){
		$('#tinTuc').parent().removeClass('active');
		$(this).parent().addClass('active');
		ajax_List(1);
	});
	
});


function ajax_List(type){
	
	$.ajax({  
        url:"articles.html?action=list&type="+type,  			         
        type:'post',  
        dataType:'json',  
        success:function(data){
        	
        	//clear
        	$('ul[class="movie__grid"]').empty();
        	
        	for (var i in data) {
        		
        		var time_str= '';
        		if(data[i].batDau != null){
        			time_str = 'Từ '+ data[i].batDau;
        		}
        		
        		if(data[i].ketThuc != null){
        			time_str += ' đến ' + data[i].ketThuc;
        		}
        		

        		var append_str = '<li class="movie__grid__item show">'
			        		    +'<div class="movie__item">'
			        		    +'<a href="articles.html?action=info&id='+data[i].maTT+'">'
			        		    +'<img src="'+data[i].thumbnail+'" title="'+data[i].tieuDe+'" alt="'+data[i].tieuDe+'">'
			        		    +'</a>'      	        
			        		    +'<dl>'
			        		    +'<dt title="'+data[i].tieuDe+'" class="movie__name">'
			        		    +'<a href="articles.html?action=info&id='+data[i].maTT+'">'+data[i].tieuDe+'</a>'
			        		    +'</dt>'     	            
			        		    +'<dd class="bottom-thumbnail">'
			        		    +'<span class="time-news">'+time_str+'</span>'
			        		    +'</dd>'			        	            
			        		    +'</dl>'
			        		    +'</div>'
			        		    +'</li>'
			        		    
			    $('ul[class="movie__grid"]').append(append_str);
        		
        		//tab
        		if(type == 1){
        			$('#tinTuc').parent().removeClass('active');
        			$('#khuyenMai').parent().addClass('active');
        		}
        		else if(type == 2){
        			$('#tinTuc').parent().addClass('active');
        			$('#khuyenMai').parent().removeClass('active');
        		}

        	}
        	
            },  
            error:  function(){  
                alert("LỖI !");  
            }                     
    });  
	
}