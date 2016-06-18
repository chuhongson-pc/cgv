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

    <title>QUẢN LÝ PHIM - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="movie1" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12 page-header-movie-list">
                        <h5 class="title-page">DANH SÁCH PHIM</h5>

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
                                <html:select  property="showType" name="moviesAdminForm" styleClass="form-control select-type">
                                    <option value="0">Tất cả</option>
                                    <option value="1">Đang chiếu</option>
                                    <option value="2">Sắp chiếu</option>
                                </html:select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row panel my-panel-default">
                    <div class="panel-body movie-list-panel">
                    
                    <!-- movie list -->
                    <logic:iterate id="movie_current" name="moviesAdminForm" property="movieList">
                        <div class="movie-item">
                            <div class="panel panel-default ">
                            <bean:define id="movieId" name="movie_current" property="maPhim"/>
                                <html:link action="movie.html?action=info" paramName="movieId" paramId="movieId">
                                    <img class="thumbnail-movie" src="../<bean:write property="thumbnail" name="movie_current"/>" title="<bean:write property="tenPhim" name="movie_current"/>"/>
                                    <div class="panel-footer-movie">
                                        <div class=movie-name><bean:write property="tenPhim" name="movie_current"/></div>
                                    </div>
                                </html:link>
                            </div>
                        </div>
					</logic:iterate>
        
                    </div>

                    <!--phân trang -->
                    <html:hidden property="currentPage" name="moviesAdminForm"/>
                    <html:hidden property="totalPage" name="moviesAdminForm"/>
                    
                    <div class="col-sm-6 row paging-row">
                        <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                            <ul class="pagination">
                            
                                <bean:define id="soTrang" name="moviesAdminForm" property="totalPage" type="java.lang.Integer"/>
                                <bean:define id="trangDangChon" name="moviesAdminForm" property="currentPage" type="java.lang.Integer"/>


                                <%
                                	int number_of_page = soTrang.intValue();
                                	int current_page = trangDangChon.intValue();
                                	
                                	if(current_page>1){
                                	%> 
                                		<li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
                                		<a href="../movie.html?action=list&page=<%=current_page-1 %>">Previous</a>
                                		</li>
                                		
                                	<% } else if (current_page == 1){
                                	%>
                                		<li class="paginate_button previous disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous">
                                		<a>Previous</a>
                                		</li>
                                	<%
                                	}
                                	
                                	for(int i=1; i<= number_of_page; i++){
                                		if(i==current_page) {
                                	%>
                                	<li class="paginate_button active" aria-controls="dataTables-example" tabindex="0">
                                    	<a href="../movie.html?action=list&page=<%=i %>"><%=i %></a>
                                	</li>
                               		<% } else {
                               		%>
                               			<li class="paginate_button" aria-controls="dataTables-example" tabindex="0">
                                    		<a href="../movie.html?action=list&page=<%=i %>"><%=i %></a>
                                		</li>
                               		<% 	}
                                		}
                                	
                                	if(current_page<number_of_page){
                                	%>
                                		<li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
                                   			 <a href="../movie.html?action=list&page=<%=current_page+1 %>">Next</a>
                                		</li>	
                                	<%} else {
                                	%>
                                		<li class="paginate_button next disabled" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next">
                                   			 <a>Next</a>
                                		</li>	
                                	<% }
                                	
                                	%>
                                                               
                                
                            </ul>
                        </div>
                    </div>
                </div>

       </div>
        
	</div>
	<script type="text/javascript" src="dist/js/movielist.js"></script>
</body>
</html>