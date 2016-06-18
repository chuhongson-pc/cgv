$(document).ready(function(){
	
	//lay nam thong ke
	getAllYear();
	getAllYear1();
	
	$('#loaiTK').on('change', function(){
		$('#namTK').find('option').remove();
		$('#namTK').append('<option value="0">Chọn năm</option>'); 

		$('#thangTK').find('option').remove();
		$('#thangTK').append('<option value="0">Chọn tháng</option>');
		
		getAllYear();
	});
	
	$('#loaiTKTheoNam').on('change', function(){
		getAllYear1();
	});
	
	$('#namTKTheoNam').on('change', function(){
		getStatistic1();
	});
	
	
	$('#namTK').on('change', function(){
		getAllMonth();
	});
	
	$('#thangTK').on('change', function(){
		getStatistic();
	});

});

function getStatistic1(){
	
	var year =  $('#namTKTheoNam').val();
	var type = $('#loaiTKTheoNam').val();
	var title_chart ="";
	var toolip = "";
	if(type == 'soVe'){
		title_chart = 'Thống kê số vé trong năm';
		toolip = 'Số lượng : <b>{point.y} vé</b>'
	}
	else {
		title_chart = 'Thống kê doanh thu trong năm';
		toolip = 'Tổng tiền : <b>{point.y} VNĐ</b>'
	}
	
	
	if(year != '0'){
		$.ajax({  
	        url:"../statistic.html?action=statistic1&type="+type+"&year="+year,   
	        type:'post',  
	        dataType:'json',  
	        success:function(data){
	        	
	        	console.log('data='+data.data);
	        	
	        	var st_data = '['+data.data+']';
	        	var i;
	        	var processed_json = new Array();   
                for (i = 0; i < data.data.length; i++){
                    processed_json.push([data.data[i].key, parseInt(data.data[i].value)]);
                }
	        		        	
	        	$('#container1').empty();
	        	
	        	     
	        	var chart = $('#container1').highcharts({
	                chart: {
	                	renderTo: 'container',
	                    type: 'column'
	                },
	                title: {
	                    text: title_chart
	                },
	                
	                xAxis: {
	                    type: 'category',
	                    labels: {
	                        rotation: -45,
	                        style: {
	                            fontSize: '13px',
	                            fontFamily: 'Verdana, sans-serif'
	                        }
	                    }
	                },
	                yAxis: {
	                    min: 0,
	                    title: {
	                        text: 'Giá trị'
	                    }
	                },
	                legend: {
	                    enabled: false
	                },
	                tooltip: {
	                    pointFormat: toolip
	                },
	                series: [{
	                	data: processed_json,
	                    dataLabels: {
	                        enabled: true,
	                        rotation: -90,
	                        color: '#FFFFFF',
	                        align: 'right',
	                        format: '{point.y}', // one decimal
	                        y: 10, // 10 pixels down from the top
	                        style: {
	                            fontSize: '13px',
	                            fontFamily: 'Verdana, sans-serif'
	                        }
	                    }
	                }]
	            });
	        			
	            },  
	        error:  function(){  
	                alert("LỖI !");  
	            }                     
		});  	
	}
	
}


function getStatistic(){
	var type = 	$('#loaiTK').val();
	var time =  $('#thangTK').val();
	var title_chart ="";
	var toolip = "";
	if(type == 'soVe'){
		title_chart = 'Thống kê số vé theo phim trong tháng';
		toolip = 'Số lượng : <b>{point.y} vé</b>'
	}
	else {
		title_chart = 'Thống kê doanh thu theo phim trong tháng';
		toolip = 'Tổng tiền : <b>{point.y} VNĐ</b>'
	}
	
	
	if(time != '0'){
		$.ajax({  
	        url:"../statistic.html?action=statistic&type="+type+"&time="+time,   
	        type:'post',  
	        dataType:'json',  
	        success:function(data){
	        	
	        	console.log('data='+data.data);
	        	
	        	var st_data = '['+data.data+']';
	        	var i;
	        	var processed_json = new Array();   
                for (i = 0; i < data.data.length; i++){
                    processed_json.push([data.data[i].key, parseInt(data.data[i].value)]);
                }
	        		        	
	        	$('#container').empty();
	        	
	        	     
	        	var chart = $('#container').highcharts({
	                chart: {
	                	renderTo: 'container',
	                    type: 'column'
	                },
	                title: {
	                    text: title_chart
	                },
	                
	                xAxis: {
	                    type: 'category',
	                    labels: {
	                        rotation: -45,
	                        style: {
	                            fontSize: '13px',
	                            fontFamily: 'Verdana, sans-serif'
	                        }
	                    }
	                },
	                yAxis: {
	                    min: 0,
	                    title: {
	                        text: 'Giá trị'
	                    }
	                },
	                legend: {
	                    enabled: false
	                },
	                tooltip: {
	                    pointFormat: toolip
	                },
	                series: [{
	                	data: processed_json,
	                    dataLabels: {
	                        enabled: true,
	                        rotation: -90,
	                        color: '#FFFFFF',
	                        align: 'right',
	                        format: '{point.y}', // one decimal
	                        y: 10, // 10 pixels down from the top
	                        style: {
	                            fontSize: '13px',
	                            fontFamily: 'Verdana, sans-serif'
	                        }
	                    }
	                }]
	            });
	        			
	            },  
	        error:  function(){  
	                alert("LỖI !");  
	            }                     
		});  	
	}
	
}

function getAllMonth(){
	var year = $('#namTK').val();
	console.log('year='+year);
	if(year != '0'){
		$.ajax({  
	        url:"../statistic.html?action=month&year="+year,   
	        type:'post',  
	        dataType:'json',  
	        success:function(data){
	        		        		        	
	        			$('#thangTK').find('option').remove();
	        			$('#thangTK').append('<option value="0">Chọn tháng</option>');
	        			        			
	                    $.each(data,function(i){
	                    	$('#thangTK').append('<option value='+data[i]+'>Tháng '+data[i].substring(5,7)+'</option>'); 
	                    })  
	            },  
	        error:  function(){  
	                alert("LỖI !");  
	            }                     
		});  	
	}
	
}

function getAllYear(){
	
	$.ajax({  
        url:"../statistic.html?action=year",   
        type:'post',  
        dataType:'json',  
        success:function(data){
        	
        			$('#namTK').find('option').remove();
        			$('#namTK').append('<option value="0">Chọn năm</option>'); 
        	
        			$('#thangTK').find('option').remove();
        			$('#thangTK').append('<option value="0">Chọn tháng</option>');
        			
        			
                    $.each(data,function(i){
                    	$('#namTK').append('<option value='+data[i]+'>'+data[i]+'</option>'); 
                  
                    })  
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	});  

}

function getAllYear1(){
	
	$.ajax({  
        url:"../statistic.html?action=year",   
        type:'post',  
        dataType:'json',  
        success:function(data){
        	
        			$('#namTKTheoNam').find('option').remove();
        			$('#namTKTheoNam').append('<option value="0">Chọn năm</option>'); 
        	

                    $.each(data,function(i){
                    	$('#namTKTheoNam').append('<option value='+data[i]+'>'+data[i]+'</option>'); 
                  
                    })  
            },  
        error:  function(){  
                alert("LỖI !");  
            }                     
	});  

}