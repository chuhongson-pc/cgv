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

<title>POSTER - CGV ĐÀ NẴNG</title>
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
					<h5 class="title-page">DANH SÁCH POSTER</h5>

					<div class="button-on-title">
						<html:link action="poster.html?action=toAdd">
	                                <button type="submit" class="btn btn-success">THÊM POSTER</button>
	                    </html:link>
                    </div>
				</div>
			</div>
			<div class="row panel my-panel-default ">
				<div class="panel-body movie-list-panel">

					
					<logic:iterate id="currentPoster" name="posterAdminForm" property="posterList">
					
						<div class="row row-poster">
                            
                            <div class="col-md-8">
                                
                                <img src="../<bean:write property="poster" name="currentPoster"/>" alt="" style="width: 100%;"/>
                                
                            </div>
                            
                            <div class="col-md-4">
                                <div class="form-group">
                                            <label>Mô tả Poster</label>
                                            <textarea class="form-control" rows="3" disabled="disabled"><bean:write property="moTa" name="currentPoster"/></textarea>
                                </div>
                                <div class="form-group">
                                      <label>Link liên kết</label>
                                      
                                      <input type="text" value="<bean:write property="link" name="currentPoster"/>" class="form-control" disabled/>
                                </div>
                                <bean:define id="id" name="currentPoster" property="maPoster"/>
                                
                                <html:link action="poster.html?action=toEdit" paramId="id" paramName="id">
                                	<button type="submit" class="btn btn-primary">Sửa</button>
                                </html:link>
                                
                                <html:link action="poster.html?action=delete" paramId="id" paramName="id">
                                <button type="submit" class="btn btn-danger">Xóa</button>
                                </html:link>
                            </div>
                            
                        </div>
					
					</logic:iterate>

					
					
				</div>
			</div>
			
			</div>

		</div>		
</body>
</html>