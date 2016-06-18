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

    <title>QUẢN LÝ TIN TỨC - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="news" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12 page-header-movie-list">
                        <h5 class="title-page">DANH SÁCH TIN TỨC</h5>
   <!--  
                        <div class="search-form-list">
                        
                            <div class="input-group custom-search-form">
                                <input type="text" name="searchContent" class="form-control" placeholder="Tìm kiếm">
                                <span class="input-group-btn">
                                
                                <button class="btn btn-default" type="button">
                                
                                        <i class="fa fa-search"></i>
                                
                                </button>
                                
                                </span>
                            </div>
                          
                        </div>
                  
                        <div class="movie-showing-type">
                            <div class="form-group input-group">
                                <span class="input-group-addon">TÙY CHỌN</span>
                                <select  name="list-type" class="form-control select-type">
                                    <option value="0">Tất cả</option>
                                    <option value="1">Tin khuyến mãi</option>
                                    <option value="2">Tin ngoài lề</option>
                                </select>
                            </div>
                        </div>
     -->   
                    </div>
                </div>
                <div class="row panel my-panel-default">
                    <div class="panel-body movie-list-panel">
                    
                    <!-- new list -->
 					<table class="table table-striped table-bordered table-hover" id="news_Table">
						<thead>
							<tr>
								<th>Thumbnail</th>
								<th>Tiêu đề</th>
								<th>Loại</th>
								<th>Trạng thái</th>
								<th>Thao tác</th>
							</tr>
						</thead>
					</table>


					<!--  -->
					<div class="text-center button-group">
					
						<html:link action="news.html?action=toAdd"><button class="btn btn-add" ></button></html:link>

					</div>
                    </div>
                </div>
       </div>
        
	</div>
	<script type="text/javascript" src="dist/js/news.js"></script>
</body>
</html>