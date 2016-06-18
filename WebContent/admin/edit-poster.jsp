<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

<title>THÔNG TIN POSTER</title>
<base href="${host}/CGV/admin/">
<link href="dist/css/mydatatable.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="ticket" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">THÔNG TIN POSTER</h5>
				</div>
			</div>
			<div class="row panel my-panel-default ">
				<div class="panel-body movie-list-panel">

					
					<html:form action="poster.html" method="post" enctype="multipart/form-data" styleId="edit_Poster">
					
						<div class="row row-poster">
                            
                            <div class="col-md-8">
                                
                                <img src="../<bean:write property="currentPoster.poster" name="posterAdminForm"/>" alt="" style="width: 100%;"/>
                                
                            </div>
                            
                            <div class="col-md-4">
                            
                            	<div class="form-group">
                                        <label>Poster</label>
                                        <html:file property="posterFile" name="posterAdminForm" size="50" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                 </div>
                            	
                                <div class="form-group">
                                            <label>Mô tả Poster</label>
                                            
                                            <html:textarea property="currentPoster.moTa" styleClass="form-control"></html:textarea>
                                </div>
                                <div class="form-group">
                                
                                      <label>Link liên kết</label>
                                      <html:text property="currentPoster.link" styleClass="form-control"/>
                                      
                                </div>
                               
                                <html:submit property="action" value="edit" styleClass="btn btn-edit">Lưu</html:submit>
                                
                                
                            </div>
                            
                        </div>
					
					</html:form>
	
				</div>
			</div>
			
			</div>

		</div>	
		 <script src="dist/js/jquery.validate.js"></script>
        <script src="dist/js/poster-validate.js"></script>	
</body>
</html>