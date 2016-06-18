$(document).ready(function(){
	
	callDialogResult();
	
});


function callDialogResult(){
		jQuery(function($) {
	  	  $('.messages').bind('beforeShow', function() {
	  	    	 $(this).remove();
	  	         $('#insertDone').dialog({ 
	  	             autoOpen: false, 
	  	             buttons: {
	  	                OK: function(){
	  	                    $(this).dialog("close");
	  	                }
	  	             }
	  	          });
	  	          
	  	          $("#insertDone" ).dialog( "open" );
	  	    }).show();
	  	  
	
	  	  $('.errors').bind('beforeShow', function() {
	  		  
	  		  	 var content = $(this).text();
	  		  	 var idDialog = "#insertError";
	  		  	 if(content.length > 1) {
	  		  		idDialog = "#existDialog";
	  		  	 }
	  		  	 
		    	 $(this).remove();
		         $(idDialog).dialog({ 
		             autoOpen: false, 
		             buttons: {
		                OK: function(){
		                    $(this).dialog("close");
		                }
		             }
		          });
		          
		          $(idDialog).dialog( "open" );
		    }).show();
	  	   
	});
}



jQuery(function($) {

	  var _oldShow = $.fn.show;

	  $.fn.show = function(speed, oldCallback) {
	    return $(this).each(function() {
	      var obj         = $(this),
	          newCallback = function() {
	            if ($.isFunction(oldCallback)) {
	              oldCallback.apply(obj);
	            }
	            obj.trigger('afterShow');
	          };

	      // you can trigger a before show if you want
	      obj.trigger('beforeShow');

	      // now use the old function to show the element passing the new callback
	      _oldShow.apply(obj, [speed, newCallback]);
	    });
	  }
	});