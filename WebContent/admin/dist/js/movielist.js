$(function() {
	$('.form-control.select-type').change(function(){
		
				console.log('change');
	
	            var choose = $('.form-control.select-type').val();
	            console.log('chon:'+choose);
	            

	            
	            if(choose != 0)
	            	{
	            	  $.ajax({  
	                        url:"../movie.html?action=reloadlist&type="+choose,  
	                         
	                        type:'post',  
	                        dataType:'json',  
	                        success:function(data){
	                        	
	                        	//clear
	                        	$('.movie-item').each(function(){
	                        		$(this).remove();
	                        	});
	                        	
	                        	$('.col-sm-6.row.paging-row').remove();
	                        	
	                        	for (var i in data) {
	                        		console.log(data[i].banner);
	                        		$('.panel-body.movie-list-panel').append('<div class="movie-item"><div class="panel panel-default "><a href="/CGV/movie.html?action=info&movieId='+data[i].maPhim+'"><img class="thumbnail-movie" src="../'+data[i].thumbnail+'" title="'+data[i].tenPhim+'"><div class="panel-footer-movie"><div class="movie-name">'+data[i].tenPhim+'</div></div></a></div></div>');

	                        	}
	                        	
	                            },  
	                            error:  function(){  
	                                alert("LỖI !");  
	                            }                     
	                });  
	          }
	            else 
	            {
					
					var url = "../movie.html?action=list";    
					$(location).attr('href',url);
					
	            }
	            
	          
	});
	
	//-----------------------------------------------------------
	$('input[name="searchContent"]').bind("enterKey",function(e){
		searchContent();
	});
	
	$('.btn.btn-default').click(function(){
		searchContent();
	});
	
	$('input[name="searchContent"]').keyup(function(e){
		    if(e.keyCode == 13)
		    {
		        $(this).trigger("enterKey");
		    }
	});
	

});

function searchContent(){
		console.log('search');
		
		var content = $('input[name="searchContent"]').val();
		
		if(content != "")
			{
				$.ajax({  
			        url:"../movie.html?action=search&content="+content,  			         
			        type:'post',  
			        dataType:'json',  
			        success:function(data){
			        	
			        	//clear
			        	$('.movie-item').each(function(){
			        		$(this).remove();
			        	});
			        	
			        	$('.col-sm-6.row.paging-row').remove();
			        	
			        	for (var i in data) {
			        		console.log(data[i].banner);
			        		$('.panel-body.movie-list-panel').append('<div class="movie-item"><div class="panel panel-default "><a href="/CGV/movie.html?action=info&movieId='+data[i].maPhim+'"><img class="thumbnail-movie" src="../'+data[i].thumbnail+'" title="'+data[i].tenPhim+'"><div class="panel-footer-movie"><div class="movie-name">'+data[i].tenPhim+'</div></div></a></div></div>');
			
			        	}
			        	
			            },  
			            error:  function(){  
			                alert("LỖI !");  
			            }                     
			    });  
			}    
}