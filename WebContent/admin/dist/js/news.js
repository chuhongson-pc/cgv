$(document).ready(function(){
	
	load_DataTable();
	
});


function load_DataTable(){
	$('#news_Table').dataTable({
		//"sScrollX": "100%", //This is what made my columns increase in size.
        "bScrollCollapse": true,
        //"sScrollY": "400px",
        "bAutoWidth": false,
        "aoColumns": [
                      { "bSortable": false },
                      null,
                      null,
                      null,
                      null
                      ],
        "aoColumns": [
            { "sWidth": "null" }, // 1 
            { "sWidth": "null" }, // 2 
            { "sWidth": "null" }, // 3
            { "sWidth": "null" }, // 4
            { "sWidth": "14%" }, // 5

            ],
		  	"bPaginate": true,
		  	"order": [ 0, 'asc' ],
		  	"bInfo": true,
		  	"iDisplayStart":0,		  	
		 	"sAjaxSource" : "../news.html?action=list"
	      });
}