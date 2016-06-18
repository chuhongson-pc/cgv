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

    <title>THÊM SUẤT CHIẾU - ICHI ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
	<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
	<link rel="stylesheet" href="dist/css/add-session.css">
	
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="addsession" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

         <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÊM LỊCH CHIẾU</h5>
                    </div>

                </div>
                <div class="row panel my-panel-default">
					
                    <html:form action="session.html" styleId="add_session">
                        <h3>CÀI ĐẶT THỜI GIAN, PHÒNG</h3>
                        <section> 
                            <div class ="panel panel-info">
                                <div class="panel-heading">
                                    Thông tin cài đặt
                                </div>
                                <div class="panel-body"> 
                                    <div class="col-lg-3">    
	                                    <div class="form-group">
	                                            <label>Ngày chiếu</label>
	                                            <html:text property="currentSession.ngayChieu" name="sessionsAdminForm" styleClass="form-control session_date_picker"/>
	                                            <p class="help-block">Bạn chọn ngày sẽ sắp lịch.</p>
	                                    </div>
                                    </div>
                                    
                                    <div class="col-lg-3">   
	                                    <div class="form-group">
	                                            <label>Phim</label>
	                                            <html:select property="currentSession.movieOfSession.maPhim" styleId="select_movie" styleClass="form-control">
	                                            	<option value="">Chọn phim</option>
	                                            </html:select>
	                                            <p class="help-block">Chọn phim cần sắp lịch.</p>
	                                    </div>
                                    </div>
                                    
                                    <div class="col-lg-2">   
	                                    <div class="form-group">
	                                            <label>Loại suất chiếu</label>
	                                            <select id="session-type-select" class="form-control">
	                       
	                                            </select>
	                                            <p class="help-block">Chọn loại suất chiếu cần sắp lịch.</p>
	                                    </div>
                                    </div>
                                    <div class="col-lg-4 info_movie_current">   
	                                    
                                    </div>
                                    
									<div class="session_panel"></div>
                                    
                                    
                                                              
                                </div>
                            </div>
                        </section>
                        <h3>CÀI ĐẶT LOẠI VÉ</h3>
                        <section>   
                            <div class ="panel panel-info">

                                <div class="panel-heading">
                                    Thông tin cài đặt
                                </div>
                                <div class="panel-body ticket-type-panel">
                                    <table class="table table-striped table-bordered table-hover table-type-ticket">
                                            <thead>
                                                <tr>
                                                    <th style="width: 5%;">Mã</th>
                                                    <th style="width: 20%;">Tên loại vé</th>
                                                    <th style="width: 40%;">Mô tả</th>
                                                    <th style="width: 10%;">Loại</th>
                                                    <th>Giá tiền</th>
                                                    <th style="width: 15%;">Tùy chọn</th>

                                                </tr>
                                            </thead>
                                            <tbody class="tbody_ticket_type">

                                            </tbody>
                                        </table>               
                                </div>

                            </div>
                        </section>

                        <h3>TÙY CHỈNH GIÁ VÉ</h3>
                        <section>   
                            <div class ="panel panel-info">

                                <div class="panel-heading">
                                    Tùy chỉnh giá vé
                                </div>
                                <div class="panel-body panel-setting-ticket-price">
                                   
                                   
                                   
                                </div>
                            </div>
                            
                            <div class ="panel panel-info">
	
                                <div class="panel-heading">
                                    Các tùy chọn giá vé
                                </div>
                                <div class="panel-body date-setting-price">
                                   
                                   
                                   
                                </div>
                            </div>
                            
                        </section>

                    </html:form>


                </div>
            </div>

		<!-- Modal -->
		<div id="dialog3d" style="display: none;" title="Thao tác">Bạn có thể xóa hoặc cài đặt lên suất 3D</div>
		<div id="type_session" style="display: none;" title="Thao tác">Bạn phải chọn loại suất chiếu cần cài đặt !</div>
		<div id="empty_session" style="display: none;" title="Thao tác">Bạn phải chọn suất chiếu !</div>
		<div id="empty_ticket_type" style="display: none;" title="Thao tác">Bạn phải chọn loại vé !</div>
		<div id="date_not_valid" style="display: none;" title="Thao tác">Ngày bạn chọn không phù hợp !</div>
		<div id="typeRoomNotVaild" style="display: none;" title="Thao tác">Không thể xếp suất 3D vào phòng chiếu 2D !</div>
		
		
</div>
		
        <script src="dist/js/jquery.validate.js"></script>
        
        <script src="dist/js/jquery.steps.js"></script>
        <script src="dist/js/session.js"></script>

</body>
</html>