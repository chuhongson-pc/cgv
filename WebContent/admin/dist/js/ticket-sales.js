$(document).ready(function(){
	
	//get movie showing
	
	getMovieShowing();
	
	$('#phimChieu').on('change', function(){
		
		var selected = $(this).val();
		
		if(selected != '0'){
			
			//get days show of movie
			getDatesOfMovie(selected);
			
		}
	});
	
	//
	$('#ngayChieu').on('change', function(){
		
		var selected_date = $(this).val();
		var movieId = $('#phimChieu').val();
		
		if(selected_date != '0' && movieId != '0'){
			
			//get room list
			getRoomsOfMovie(movieId, selected_date);
		}
		
	});
	
	//
	$('#phongChieu').on('change', function(){
		
		var movieId = $('#phimChieu').val();
		var selected_date = $('#ngayChieu').val();
		var roomId = $(this).val();
		
		if(movieId != '0' && selected_date != '0' && roomId != '0'){
			
			//get session list
			getSessionOfMovie(movieId, selected_date, roomId);
			
		}
		
	});
	
	$('#suatChieu').on('change', function(){
		
		var sessionId  = $(this).val();
		if(sessionId != '0'){
			getSessionInfo(sessionId);
		}
	
	});
	

	$('#detail-sale-table').on('change', 'select[class="soLuongVe"]', function(){
		
			console.log('change');
		
			var soGheThuong = $(this).data('normal');
			var soGheVip = $(this).data('vip');
			var maLoaiVe = $(this).attr('id');	

			var soLuongVe = parseInt($(this).val());
			var giaTien = parseInt($(this).data('price'));
			
			var thanhTien = soLuongVe * giaTien;
			
			console.log(soGheThuong + ", "+soGheVip+", "+maLoaiVe+", "+soLuongVe+","+giaTien);
			
			//set thanhTien 
			$('span[id='+maLoaiVe+']').text(thanhTien);
			
			//update
			updateSum(1);

			//comma
			insertComma();
			
			//clear selected seats
			
			$('.row.panel.my-panel-default').find('.sits__place.sits-state-room.sits-state--your').removeClass('sits-state--your');
			$('.seat_label').html("");
			$('input[name=seatsYour]').val('');
			
	});
	
	//choose seat
	 $('.row.panel.my-panel-default').on('click', '.sits__place.sits-state-room', function (e) {
		 
		 	e.preventDefault();
		 	
	        var soGheThuongDuocChon = parseInt($('input[name="soGheThuongDuocChon"]').val());
	        var soGheVipDuocChon = parseInt($('input[name="soGheVipDuocChon"]').val());
	        
	        console.log('THUONG='+soGheThuongDuocChon+" | VIP="+soGheVipDuocChon);

	        var data_type = $(this).attr('data-type');
	        var place = $(this).attr('data-place');
	        

	        //chưa chọn
	        if (!$(e.target).hasClass('sits-state--your')) {

	            if (data_type == 2 && soGheVipDuocChon > 0) {
	                //chọn ghế vip
	                add_Seat_Selected($(this), soGheThuongDuocChon, soGheVipDuocChon, place, data_type);

	            } else if (data_type == 2 && soGheVipDuocChon == 0) {
	                //thông báo không thể chọn ghế vip
	                get_Dialog(2);
	            }

	            else if (data_type == 1 && soGheThuongDuocChon > 0) {
	                //chọn ghế thường
	                add_Seat_Selected($(this), soGheThuongDuocChon, soGheVipDuocChon, place, data_type);

	            } else if (data_type == 1 && soGheThuongDuocChon == 0) {
	                //thông báo không thể chọn thêm ghế thường
	                get_Dialog(1);
	            }
	        }
	        else //đã chọn
	        {
	            //xóa ghế vừa chọn
	            remove_Seat_Selected($(this),soGheThuongDuocChon, soGheVipDuocChon, place, data_type);
	        }
	 });
	 
	 //fast food load
	 $('#fastfood_button').click(function(e){
		 
		 event.preventDefault();
		 
		 var numNormal = parseInt($('input[name="soGheThuongDuocChon"]').val());
		 var numVip = parseInt($('input[name="soGheVipDuocChon"]').val());
		 
		 var numSelected =  $('.row.panel.my-panel-default').find('.sits__place.sits-state-room.sits-state--your');
		 
		 console.log('numVip='+numVip+", numNor="+numNormal);
		 
		 if((numVip > 0 || numNormal > 0)){
			 //select seats dialog
			 
			 e.preventDefault();
			 //if(numSelected.length == 0){
				 get_Dialog(3);
			 //}
		 }
		 else 
		 {
			 //ajax get data fastfood list
			 getFastFoodList();
			 
			 $('#ffListModal').modal();
			 
			 //select fast food			 
			 initSelectFastFood();
			 
			 
		 }
		 
	 });
	 
	 //checkout
	 $('#submit_button').on('click', function(){
		 
		 //get string data
		 console.log("Vao day _____________________________________");
		 
		 var ticket_str = '';
		 var ff_str='';
		 
		 $('#detail-sale-table').find('select[class="soLuongVe"]').each(function(){
			 
			 if($(this).val() != 0){
				 var ticket = $(this).attr('id') + '-' + $(this).val();
				 ticket_str += ' ' + ticket; 
			 }

		 });
		 
		 $('#detail-sale-table').find('select[class="soLuongFF"]').each(function(){
			 
			 if($(this).val() != 0){
				 var ff = $(this).attr('id') + '-' + $(this).val();
				 ff_str += ' ' + ff;
			 }
			 
		 });
		 console.log("---------------------------------");
		 console.log('sessionId='+$('#suatChieu').val());
		 console.log('ticket='+ticket_str);
		 console.log('ff='+ff_str);
		 console.log('seats='+$('input[name="seatsYour"]').val());
		 var seatsYour = $('input[name="seatsYour"]').val();
		 var sessionId = $('#suatChieu').val();
		 
		 
		 //check condition before call action
		 
		 
		 //call ajax add transaction
		 
		 saveTransaction(sessionId, ticket_str, ff_str, seatsYour);
		 
		 
		 //clear table info

		 $('.row.panel.my-panel-default').find('#tongCong').text('0');
		 
	 });
	 
	 
	 //print ticket
	 $('#infoTransactionModal').on('click', '#info_print_button', function(){
			
			console.log('in ve');
			
			PrintElem($('#infoTransactionModal').find('.panel-body'));
	
	 });
	 
	
});

function saveTransaction(sessionId, ticket_str, ff_str, seatsYour){
	
	var data = {
			
			sessionId : sessionId,
			ticketTypeSelected : ticket_str,
			fastFoodTypeSelected: ff_str,
			seatsSelected : seatsYour	
	}
	
	$.ajax({  
        url:"../sale.html?action=save",
        data: data,
        type:'post',  
        dataType:'json',  
        success:function(data)
        {

       	 	var transactionId = data.result;
       	 
	       	 if(transactionId != '0') {
	       		getSessionInfo(sessionId);
	       		
	       		//get_Dialog('ok');
	       		//call modal transactionId
	       		
	       		ajax_LoadTransactionInfo(transactionId);
	       		
	       		//show modal
	       		$('#infoTransactionModal').modal('show');
	       		

	       		
	       	 }
	       	 else {
	       		 alert('error');
	       	 }
        		
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
	}); 
	
	
}

function ajax_LoadTransactionInfo(id){
	
	 $.ajax({  
        url:"../transaction.html?action=info&id="+id,  
        type:'post',  
        dataType:'json',  
        success:function(data){  			
       	 
       	 
       	 //clear modal
       	 $('#maGD').empty();
       	 $('#infoTransactionModal').find('h3').empty();
       	 $('#infoTransactionModal').find('.lead').empty();
       	 
       	 $('#detailTable').find('tbody').find('tr').each(function(){
       		 
       		 if(!$(this).hasClass('tr_tongCong')) $(this).remove();
       		 
       	 });
       	 
       	 //tieu de
       	 
       	 var tenPhim = $('#phimChieu option:selected').text();
       	 console.log('tenPhim='+tenPhim);
       	 
       	 var gioChieu = $('#suatChieu option:selected').text();
       	 console.log('time='+gioChieu);
       	 
       	 var ngayChieu = $('#ngayChieu option:selected').text();
       	 var phongChieu = $('#phongChieu option:selected').text();
       	 
       	 $('#maGD').text(data.transactionId);
       	 $('#infoTransactionModal').find('h3').append(tenPhim);
       	 $('#infoTransactionModal').find('.lead').append(ngayChieu + " | " + gioChieu + " | " + phongChieu);
       	 
       	 var ticket = data.ticket;
       	 for(var i in ticket){
       		 
       		 var item = ticket[i];
       		 
       		 var append = '<tr>'
       			 		+'<td>'
       			 		+item[0]
       			 		+'</td>'
       			 		+'<td>'
       			 		+item[1]
       			 		+'</td>'
       			 		+'</tr>';
       		
       		$(append).insertBefore('tr[class="tr_tongCong"]');	 
       	 }
       	 
       	 var ff = data.ff;
       	 for(var i in ff){
       		 
       		 var item = ff[i];
       		 
       		 var append = '<tr>'
       			 		+'<td>'
       			 		+item[0]
       			 		+'</td>'
       			 		+'<td>'
       			 		+item[1]
       			 		+'</td>'
       			 		+'</tr>';
       		
       		$(append).insertBefore('tr[class="tr_tongCong"]');	 
       	 }
       	 
       	 //tongCong
       	 $('span[id="tongCong"]').text(data.tongTien);
       	 
       	 //seatList
       	 $('#seatList').html(data.seats);
       	 
       	 
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	 }); 
}


function PrintElem(elem)
{
	var data = elem.html();
	console.log(data);
    Popup(data);
}

function Popup(data) 
{
    var mywindow = window.open('', 'IN VÉ', 'height=400,width=600');
  
    mywindow.document.write(data);
    

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}


function initSelectFastFood(){
	
	$('#ffListModal').on('change','select[class="soLuongFF"]', function(){
		
		var maFF = $(this).attr('id');
		var giaTien = parseInt($(this).data('price'));
		var soLuong = parseInt($(this).val());
		
		$(this).find('option').each(function(){
			$(this).removeAttr('selected');
		});
		
		//
		$(this).find('option[value='+soLuong+']').attr('selected','selected');

		var thanhTien = giaTien * soLuong;
		
		$('span[id='+maFF+']').text(thanhTien);
		
		insertComma();	
	});
	
	$('.row.panel.my-panel-default').find('#ffListModal').on('click', '#add_save_button', function(){
		
		console.log('click save');
		
		
		//clear detail table
		$('.row.panel.my-panel-default').find('#detail-sale-table').find('select[class="soLuongFF"]').parent().parent().remove();
		

		//copy tr --> hidden tr with value = 0
		$('.row.panel.my-panel-default').find('select[class="soLuongFF"]').each(function(){
			
			var selected_value = $(this).val();
			
			console.log('select='+selected_value);
			
			if(selected_value != '0') {
				$(this).parent().parent().clone().insertBefore('#tr_TongCong');
			}
	});
		
		//hide
		$('.row.panel.my-panel-default').find('#ffListModal').modal('hide');

		//update sum
		updateSum(2); //not update soGheDuocChon
		
		//comma
		insertComma();
		
	});
	
}

function getFastFoodList(){
	
	var modal = $('.row.panel.my-panel-default').find('#ffListModal');
	
	$.ajax({  
        url:"../sale.html?action=fastfood",
        type:'post',  
        dataType:'json',  
        success:function(data)
        {
        		
        	//clear table
        	modal.find('tbody').find('tr').remove();
        	
        	
        	//append row
        	for(var i in data){
        			
        		var row_append = '<tr>'
        						+'<td>'+data[i].tenFF+'</td>'
        						+'<td class="money">'+data[i].giaTien+'</td>'
        						+'<td>'
        						
        						+'<select id="'+data[i].maFF+'" data-price="'+data[i].giaTien+'" class="soLuongFF">'
        						+'<option value="0">0</option>'
        						+'<option value="1">1</option>'
        						+'<option value="2">2</option>'
        						+'<option value="3">3</option>'
        						+'<option value="4">4</option>'
        						+'<option value="5">5</option>'
        						+'<option value="6">6</option>'
        						+'<option value="7">7</option>'
        						+'<option value="8">8</option>'
        						+'<option value="9">9</option>'
        						+'<option value="10">10</option>'
                                +'</select>'
	
        						+'</td>'
        						+'<td><span id="'+data[i].maFF+'" class="money"></span> VNĐ</td>'
        						+'</tr>';
        		
        		modal.find('tbody').append(row_append);
        		
        	}
        	
        	//comma
        	insertComma();
        		
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
	}); 	
}


function get_Dialog(type) {
	
	var dialog_element;
	if(type == 1) dialog_element = $("#fullNormal");
	else if (type == 3) dialog_element = $("#mustChooseSeat");
	else if(type == 'ok') dialog_element = $('#transactionDone');
	else dialog_element = $("#fullVip");
	
    var current_seat = this;
    dialog_element.dialog({
        autoOpen: false,
        buttons: {
            OK: function () {
                $(this).dialog("close");
            }
        },
        title: "Thông báo"
    });

    dialog_element.dialog("open");
}

function add_Seat_Selected(element, soGheThuongDuocChon, soGheVipDuocChon, place, data_type) {

    if (!element.hasClass('sits-state--not')) {
        element.addClass('sits-state--your');

        //cập nhật danh sách chọn
        var list = $(':hidden[name="seatsYour"]').val();
        
        $('.seat_label').html(list + " " + place);
        
        $(':hidden[name="seatsYour"]').val(list + " " + place);

        //cập nhật số ghế đã chọn (vip-normal)
        if (data_type == 1) {
            soGheThuongDuocChon = soGheThuongDuocChon - 1;
            $(':hidden[name="soGheThuongDuocChon"]').val(soGheThuongDuocChon);
        }
        else
        {
            soGheVipDuocChon = soGheVipDuocChon - 1;
            $(':hidden[name="soGheVipDuocChon"]').val(soGheVipDuocChon);
        }

    }
}

function remove_Seat_Selected(element, soGheThuongDuocChon, soGheVipDuocChon, place, data_type) {
    element.removeClass('sits-state--your');

    //cập nhật lại 2 danh sách
    var list = $(':hidden[name="seatsYour"]').val();

    list = list.replace(' ' + place, '');

    //cập nhật ds hidden
    $(':hidden[name="seatsYour"]').val(list);

    //cập nhật ds hiện
    console.log(list.toString());
    $('.seat_label').html(list.toString());

    //cập nhật số ghế đã chọn (vip-normal)
    if (data_type == 1) {
        soGheThuongDuocChon = soGheThuongDuocChon + 1;
        $(':hidden[name="soGheThuongDuocChon"]').val(soGheThuongDuocChon);
    }
    else
    {
        soGheVipDuocChon = soGheVipDuocChon + 1;
        $(':hidden[name="soGheVipDuocChon"]').val(soGheVipDuocChon);
    }
}


function updateSum(type){
	var sum=0;
	var numvip=0;
	var numnormal=0;
	$('#detail-sale-table').find('select').each(function(){
		
		var soLuongVe = parseInt($(this).val());
		
	
		if($(this).hasClass('soLuongVe')){
			var soGheVip = $(this).data('vip');
			var soGheThuong = $(this).data('normal');
			numvip += soGheVip * soLuongVe;
			numnormal += soGheThuong * soLuongVe;
		}
		
		var maLoaiVe = $(this).attr('id');
		var giaTien = parseInt($(this).data('price'));
		
		sum += soLuongVe * giaTien;
		
	});
	
	$('#tongCong').text(sum);
	$('#numberVip').text(numvip);
	$('#numberNormal').text(numnormal);
	
	if(type != 2) //close ff list not update value 
	{
		$('input[name="soGheThuongDuocChon"]').val(numnormal);
		$('input[name="soGheVipDuocChon"]').val(numvip);
	}

}

function insertComma(){
	
	console.log('insert comma');
	
	$('.row.panel.my-panel-default ').find('.money').each(function () {
		
		var text = $(this).text();
		text = text.replace(",","");
		$(this).text(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}


function getSessionInfo(sessionId){
	
	console.log('sessionId='+sessionId);
	
	
	$.ajax({  
        url:"../sale.html?action=sessioninfo&sessionId="+sessionId,
        type:'post',  
        dataType:'json',  
        success:function(data)
        {
 
        	//clear
        	clear_SessionInfo();
        	
        	//append table

        	var ticket_array = data.ticket;
        	var seatsMap = data.seatsMap.trim();
        	var seatsBooked = data.seatsBooked;
        	
        	//show seats map
        	show_Room_Status(seatsMap, seatsBooked);
        	
        	for(var i in ticket_array){
        			
        		var row_append = '<tr>'
        						+'<td>'+ticket_array[i].tenLoaiVe+'</td>'
        						+'<td class="money">'+ticket_array[i].giaTien+'</td>'
        						+'<td>'
        						
        						+'<select id="'+ticket_array[i].maLoaiVe+'" data-price="'+ticket_array[i].giaTien+'" data-vip="'+ticket_array[i].soGheVip+'" data-normal="'+ticket_array[i].soGheThuong+'" class="soLuongVe">'
        						+'<option value="0">0</option>'
        						+'<option value="1">1</option>'
        						+'<option value="2">2</option>'
        						+'<option value="3">3</option>'
        						+'<option value="4">4</option>'
        						+'<option value="5">5</option>'
        						+'<option value="6">6</option>'
        						+'<option value="7">7</option>'
        						+'<option value="8">8</option>'
        						+'<option value="9">9</option>'
        						+'<option value="10">10</option>'
                                +'</select>'
	
        						+'</td>'
        						+'<td><span id="'+ticket_array[i].maLoaiVe+'" class="money"></span> VNĐ</td>'
        						+'</tr>';
        		
        		$(row_append).insertBefore('#tr_TongCong');
        		
        		
        	}
        	
        	//comma
        	insertComma();
        		
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
	});  
	
}

function clear_SessionInfo(){
	console.log('clear session info');
	//clear table
	$('#detail-sale-table > tbody').find('tr:not(#tr_TongCong)').remove();
	//clear seats map
	$('.row.panel.my-panel-default').find('span').each(function(){
		if($(this).hasClass('sits__place')){
			$(this).removeClass();
			$(this).addClass('sits__place');
		}
		
	});
	//clear label
	$('.seat_label').html('');
	$('input[name="seatsYour"]').val('');
	//clear value
	$('#numberVip').text('0');
	$('#numberNormal').text('0');
	$('input[name="soGheThuongDuocChon"]').val(0);
	$('input[name="soGheVipDuocChon"]').val(0);
}

function getSessionOfMovie(movieId, selected_date, roomId){
	
	$.ajax({  
        url:"../sale.html?action=sessions&movieId="+movieId+"&date="+selected_date+"&roomId="+roomId,
        type:'post',  
        dataType:'json',  
        success:function(data)
        {
        			
        			$('#suatChieu').find('option').remove();      			
        			$('#suatChieu').append('<option value="0">Chọn phòng chiếu</option>'); 
        			
        			
                   $.each(data,function(i){                             	
                            $('#suatChieu').append('<option value='+data[i].maSuat+'>'+data[i].gioChieu+'</option>');
                    });  
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
	});  
}


function show_Room_Status(str_seats, booked_array) {

    console.log('show_Room_Status');
    
    var array_seats = str_seats.split(" ");

    jQuery.each(array_seats, function (i, val) {
        //console.log(val);
        var array_info = val.split("-");
        var data_x;
        var data_y;
        var data_place;
        var data_type;
        jQuery.each(array_info, function (j, val) {
            //console.log('i='+j+", val="+val);
            if (j == 0)
                data_x = val;
            else if (j == 1)
                data_y = val;
            else if (j == 2)
                data_place = val;
            else if (j == 3)
                data_type = val;
        });
        //có thông số -> set 

        var cell = $('.sits__row[data-y="' + data_y + '"]').find('.sits__place[data-x="' + data_x + '"]');
        cell.addClass('sits-state-room').attr('data-place', data_place).attr('data-type', data_type);
        if (data_type == 2) {
            cell.addClass('sits-price--expensive');
        }

        cell.text(data_place.substring(1, data_place.length));
    });


    //.Disable seat booked
    
    booked_array = booked_array.split(" ");
    jQuery.each(booked_array, function (i, val) {
        //console.log(val);
        $('.sits__place.sits-state-room').each(function () {
            //console.log(val + '=' + $(this).attr('data-place'));
            if (val === $(this).attr('data-place')) {
                //console.log('addClass');
                $(this).addClass('sits-state--not');
            }
        });
    });
}


function getRoomsOfMovie(movieId, selected_date){
	
	$.ajax({  
        url:"../sale.html?action=rooms&movieId="+movieId+"&date="+selected_date,
        type:'post',  
        dataType:'json',  
        success:function(data)
        {
        			$('#phongChieu').find('option').remove();
        			$('#suatChieu').find('option').remove();
        			
        			$('#phongChieu').append('<option value="0">Chọn phòng chiếu</option>'); 
        			
                   $.each(data,function(i){                             	
                            $('#phongChieu').append('<option value='+data[i].maPhong+'>'+data[i].tenPhong+'</option>');
                    });  
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
	});  
	
}

function getDatesOfMovie(movieId){
	
	$.ajax({  
        url:"../sale.html?action=dates&id="+movieId,
        type:'post',  
        dataType:'json',  
        success:function(data)
        {
        			$('#ngayChieu').find('option').remove();
        			$('#phongChieu').find('option').remove();
        			$('#suatChieu').find('option').remove();
        			
        			$('#ngayChieu').append('<option value="0">Chọn ngày chiếu</option>'); 
        			
                   $.each(data,function(i){                             	
                            $('#ngayChieu').append('<option value='+data[i].ngayChieu+'>'+data[i].ngayChieu+'</option>');
                    });  
        },  
        error:  function()
        {  
             alert("LỖI !");  
        }                     
});  
	
}

function getMovieShowing(){
	 
     $.ajax({  
                 url:"../sale.html?action=nowshowing",
                 type:'post',  
                 dataType:'json',  
                 success:function(data)
                 {
                 			$('#phimChieu').find('option').remove();

                 			$('#phimChieu').append('<option value="0">Chọn phim</option>'); 
                 			
                            $.each(data,function(i){                             	
                                     $('#phimChieu').append('<option value='+data[i].maPhim+'>'+data[i].tenPhim+'</option>'); 
                             });  
                 },  
                 error:  function()
                 {  
                      alert("LỖI !");  
                 }                     
      });  
}