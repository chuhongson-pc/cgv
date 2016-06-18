
$(document).ready(function(){
	
		loadTable();	
			
		//click modal detail
			
		$('.row.panel.my-panel-default ').on('click', 'button[data-target="#infoTransactionModal"]', function(){
				
				ajax_LoadTransactionInfo($(this));
			
		});
		
		
		//click delete
		$('.row.panel.my-panel-default ').on('click', '.btn.btn-danger', function(){
			var button = $(this);
			$('#confirm').dialog({ 
	             autoOpen: false, 
	             buttons: {
	                OK: function(){
	                    $(this).dialog("close");
	                    ajax_DeleteTransaction(button);
	                },
	                CANCEL: function(){
	                	$(this).dialog("close");
	                }
	             }
	          });
	          
	          $('#confirm').dialog( "open" );
			
			
	
		});
		
		//
		$('#infoTransactionModal').on('click', '#info_print_button', function(){
			
			console.log('in ve');
			
			PrintElem($('#infoTransactionModal').find('.panel-body'));
	
		});
	
	   	
});

function PrintElem(elem)
{
	var data = elem.html();
	console.log(data);
    Popup(data);
}

function Popup(data) 
{
    var mywindow = window.open('', 'IN VÉ', 'height=400,width=600');
    mywindow.document.write('<html><head><title>IN VÉ</title>');
    mywindow.document.write('</head><body >');
    mywindow.document.write(data);
    mywindow.document.write('</body></html>');

    mywindow.document.close(); // necessary for IE >= 10
    mywindow.focus(); // necessary for IE >= 10

    mywindow.print();
    mywindow.close();

    return true;
}


function ajax_DeleteTransaction(button){
	
	var id = button.data('ticket-id');
	
	 $.ajax({  
        url:"../transaction.html?action=delete&id="+id,  
        type:'post',  
        dataType:'json',  
        success:function(data)
        {  			
       	 var result = data.result;
       	 
	        	 if(result == 1) 
	        	 {
	        		 reloadTable();
	        	 } else {
	        		 alert("Lỗi, vui lòng thử lại !");
	        	 }
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	 }); 
}

function reloadTable(){
	
	$('#transactionTable_wrapper').remove();

	var append = '<table class="table table-striped table-bordered table-hover" id="transactionTable">'
					+'<thead>'
					+'<tr>'
					+'<th>Mã GD</th>'
					+'<th>Tài khoản</th>'
					+'<th>Phim</th>'
					+'<th>Thời gian GD</th>'
					+'<th>Tổng tiền</th>'
					+'<th>Thao tác</th>'					
					+'</tr>'
					+'</thead>'
					+'<tfoot>'
					+'<tr>'
					+'<th></th>'
					+'<th></th>'
					+'<th></th>'
					+'<th></th>'
					+'<th></th>'
					+'<th></th>'
					+'</tr>'
					+'</tfoot>'
					+'</table>';
	
	$('.panel-body.movie-list-panel').append(append);
	
	console.log('before loadTable');
	
	loadTable();
}

function loadTable(){
	console.log('load table');
	$('#transactionTable').dataTable({
		//"sScrollX": "100%", //This is what made my columns increase in size.
        "bScrollCollapse": true,
        //"sScrollY": "400px",
        "bAutoWidth": false,
        "aoColumns": [
            { "sWidth": "15%" }, // 1st column width 
            { "sWidth": "null" }, // 2nd column width 
            { "sWidth": "null" }, // 3nd column width 
            { "sWidth": "null" }, // 4rd column width
            { "sWidth": "null" }, // 5th column width 
            { "sWidth": "14%" }, // 6th column width

            ],
		  	"bPaginate": true,
		  	"order": [ 3, 'desc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,
		  	"bProcessing" : true,
		 	"bServerSide" : true,
		 	"sAjaxSource" : "../transaction.html?action=list",
		 	"dom": 'C<"clear">lfrtip',
		 	colVis: {
				"align": "right",
	            restore: "Restore",
	            showAll: "Show all",
	            showNone: "Show none",
				order: 'alpha',
				"buttonText": "columns <img src=\"dist/images/caaret.png\"/>"
	        },
	        "dom": 'Cf<"toolbar"">rtip'
	       
	      })
			.columnFilter({
				  aoColumns: [ 
					             { type: "text"},
					             { type: "text"},
						         { type: "text" },
						         { type: "text" },
							],
						bUseColVis: true
			}).fnSetFilteringDelay();
	
			$("#personTable_length").hide();
			$("div.toolbar").append('<div class="btn-group"><button class="btn btn-default" id="refreshbtn" style="background:none;border:1px solid #ccc;height:30px" type="button"><span class="glyphicon glyphicon-refresh" style="padding:3px"></span></button></div>');
			$("div.toolbar").css("float","right");
			$('#refreshbtn').click(function(){
				   table.fnStandingRedraw();
		   });
			
		setTimeout(insertComma,1000);
		insertComma();
}

function insertComma(){
	
	console.log('insert comma');
	
	$('.row.panel.my-panel-default').find('.money').each(function (){
		
		console.log('item');
		var text = $(this).text();
		text = text.replace(",","");
		console.log(text);
		$(this).text(text);
		
        $(this).formatNumber({format: "#,###", locale: "us"});
    });
}

function ajax_LoadTransactionInfo(button){
	
	 var id = button.data('ticket-id');	
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
        	 
        	 var tenPhim = $('.ticket-name[data-id='+id+']').html();
        	 console.log('tenPhim='+tenPhim);
        	 
        	 var thoiGian = $('.description-ticket[data-id='+id+']').html();
        	 console.log('time='+thoiGian);
        	 
        	 $('#maGD').text(data.transactionId);
        	 $('#infoTransactionModal').find('h3').append(tenPhim);
        	 $('#infoTransactionModal').find('.lead').append(thoiGian);
        	 
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