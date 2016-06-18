var table;
$(document).ready(function(){
	
	table = $('#personTable').dataTable({
		//"sScrollX": "100%", //This is what made my columns increase in size.
        "bScrollCollapse": true,
        //"sScrollY": "400px",
        "bAutoWidth": false,
        "aoColumns": [
            { "sWidth": "null" }, // 1st column width 
            { "sWidth": "null" }, // 2nd column width 
            { "sWidth": "null" }, // 3rd column width
            { "sWidth": "null" }, // 4th column width 
            { "sWidth": "null" }, // 5th column width 
            { "sWidth": "null" }, // 5th column width 
            { "sWidth": "14%" }, // 6th column width

            ],
		  	"bPaginate": true,
		  	"order": [ 0, 'asc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,
		  	"bProcessing" : true,
		 	"bServerSide" : true,
		 	"sAjaxSource" : "../customer.html?action=list",
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
						         { type: "text" },
						         { type: "text" },
						         { type: "text" },
		                         { type: "text" }
							],
						bUseColVis: true
			}).fnSetFilteringDelay();
	
			$("#personTable_length").hide();
			$("div.toolbar").append('<div class="btn-group"><button class="btn btn-default" id="refreshbtn" style="background:none;border:1px solid #ccc;height:30px" type="button"><span class="glyphicon glyphicon-refresh" style="padding:3px"></span></button></div>');
			$("div.toolbar").css("float","right");
			$('#refreshbtn').click(function(){
				   table.fnStandingRedraw();
		   });

			
		//tùy chỉnh table
			
	   	
});
	

