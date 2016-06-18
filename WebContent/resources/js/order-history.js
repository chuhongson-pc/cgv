$(document).ready(function(){


	$('#orderHistory_Table').dataTable({
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
            { "sWidth": "14%" }, // 6th column width

            ],
		  	"bPaginate": true,
		  	"order": [ 4, 'desc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,		  	
		 	"sAjaxSource" : "orderHistory.html?action=list"
	      });


});