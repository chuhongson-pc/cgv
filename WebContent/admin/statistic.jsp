<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	
    <title>THỐNG KÊ - ICHI ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
	<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
	
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="statistic" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THỐNG KÊ</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thống kê từng tháng
                        </div>
                        <div class="panel-body">
							
							<div class="row">
								<div class="col-lg-4">
									<div class="form-group">
	                                        <label>Loại thống kê</label>
	                                        <select id="loaiTK" class="form-control">
	                                           
	                                            <option value="doanhThu">Theo doanh thu</option>
	                                        </select>
	                                </div>
	                             </div>
	                            <div class="col-lg-3">   
	                                <div class="form-group">
	                                        <label>Chọn năm</label>
	                                        <select id="namTK" class="form-control">
	                                        </select>
	                                </div>
	                             </div>
	                             <div class="col-lg-3">   
	                                <div class="form-group">
	                                        <label>Chọn tháng</label>
	                                        <select id="thangTK" class="form-control">
	                                        </select>
	                              </div>
							</div>
							</div>
							
							<div class="row">
								
								<div id="container" style="height: 400px in-width: 310px; max-width: 800px ;margin: 0 auto;"></div>
							
							</div>
                            
                        </div>
                       
                    </div>
                </div>
                
                
                <div class="row panel my-panel-default">               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thống kê các tháng của năm
                        </div>
                        <div class="panel-body">
							
							<div class="row">
							<div class="col-lg-4">
								<div class="form-group">
                                        <label>Loại thống kê</label>
                                        <select id="loaiTKTheoNam" class="form-control">
                                            
                                            <option value="doanhThu">Theo doanh thu</option>
                                        </select>
                                </div>
                             </div>
                            <div class="col-lg-3">   
                                <div class="form-group">
                                        <label>Chọn năm</label>
                                        <select id="namTKTheoNam" class="form-control">
                                        </select>
                                </div>
                             </div>

							</div>
							
							<div class="row">
								
								<div id="container1" style="height: 400px in-width: 310px; max-width: 800px ;margin: 0 auto;"></div>
							
							</div>
                            
                        </div>
                       
                    </div>
                </div>
                
            </div>       
        
</div>
		
<script src="dist/js/plugin/highcharts.js"></script>
<script src="dist/js/statistic.js"></script>

</body>
</html>