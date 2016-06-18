function init_Room() {
    "use strict";
    
    jQuery(function($) {
    	  $('.messages').bind('beforeShow', function() {
    	    	 $(this).remove();
    	         $('#updateDone').dialog({ 
    	             autoOpen: false, 
    	             buttons: {
    	                OK: function(){
    	                    $(this).dialog("close");
    	                }
    	             }
    	          });
    	          
    	          $("#updateDone" ).dialog( "open" );
    	    }).show();
    	  
    	  
    	  
    	  $('.errors').bind('beforeShow', function() {
 	    	 $(this).remove();
 	         $('#updateError').dialog({ 
 	             autoOpen: false, 
 	             buttons: {
 	                OK: function(){
 	                    $(this).dialog("close");
 	                }
 	             }
 	          });
 	          
 	          $("#updateError" ).dialog( "open" );
 	    }).show();
    	   
   });
    
    
    restore_SeatsMap();
    

    $('.sits__place').click(function (e) {
        e.preventDefault();
        var place = $(this).attr('data-place');
        var ticketPrice = $(this).attr('data-price');

        if (!$(e.target).hasClass('sits-state--your')) {

            if (!$(this).hasClass('sits-state--not')) {
                $(this).addClass('sits-state--your');
                
                reload_Seats(this);
                update_SeatsMap();
            }
        }

        else {
           
            console.log('Dialog');
            //tùy chọn set lên ghế VIP hoặc xóa ghế đã chọn
            var current_seat = this;
            $('#mydialog').dialog({ 
               autoOpen: false, 
               buttons: {
                  XÓA: function() {
                      $(this).dialog("close");
                      $(current_seat).removeClass('sits-state--your').removeClass('sits-price--expensive').text("null").attr('data-type','none');
                      //cập nhật
                      reload_Seats(current_seat);
                      update_SeatsMap();
                  },
                  VIP: function(){
                      $(this).dialog("close");
                      $(current_seat).addClass('sits-price--expensive').attr('data-type',2);
                      update_SeatsMap();
                  }
               },
               title: "Thao tác"
            });
            
            $( "#mydialog" ).dialog( "open" );
            
        }
        
        
    });


}
function restore_SeatsMap(){
	//hiển thị trạng thái từ hidden 
    var str_seats = $('input[name="soDoGhe"]').val().trim();
    
    console.log(str_seats);
    
    if(str_seats != ""){
    	var array_seats = str_seats.split(" ");
        
        jQuery.each( array_seats, function( i, val ) {
            console.log(val);
            var array_info = val.split("-");
            var data_x;
            var data_y;
            var data_place;
            var data_type;
            jQuery.each(array_info, function (j, val){
                console.log('i='+j+", val="+val);
                if(j==0) data_x = val;
                else if(j==1) data_y = val;
                else if(j==2) data_place = val;
                else if(j==3) data_type = val;
            });
            //có thông số -> set 
            
            var cell = $('.sits__row[data-y="'+data_y+'"]').find('.sits__place[data-x="'+data_x+'"]');
            cell.addClass('sits-state--your').attr('data-place',data_place).attr('data-type',data_type);
            if(data_type == 2){
                cell.addClass('sits-price--expensive');
            }

            cell.text(data_place.substring(1,data_place.length));
        });
    }
    
}

function update_SeatsMap(){
        console.log('update map');
        var soGhe = 0;
        var str ="";
        $('.sits__place.sits-state--your').each(function(){
        	soGhe++;
            str+=" "+$(this).attr('data-x')+"-"+$(this).parent().attr('data-y')+"-"+$(this).attr('data-place')+"-"+$(this).attr('data-type');
        });
        $('input[name="soGhe"]').val(soGhe);
        //
        $('input[name="soDoGhe"]').val(str);  
}

function reload_Seats(element){
    var row_current = $(element).parent();
    var row_letter = row_current.attr("data-row");
    //vòng for từ phải qua
    var i = 0;
    row_current.children('.sits__place.sits-state--your').each(function () {
    	i++;
        $(this).text(i).attr("data-place", row_letter + i).attr('data-type','1');
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