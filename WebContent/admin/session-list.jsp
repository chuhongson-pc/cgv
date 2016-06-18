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

<title>LỊCH CHIẾU- CGV ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">
<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>

</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="sessionlist" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">LỊCH CHIẾU</h5>
				</div>
			</div>
			<div class="row panel my-panel-default ">
				<div class="panel-body movie-list-panel">

                            <div class ="panel panel-info">
                                <div class="panel-heading">
                                    Thông tin lịch chiếu
                                </div>
                                <div class="panel-body"> 
                                    <div class="col-lg-3">    
	                                    <div class="form-group">
	                                            <label>Ngày chiếu</label>
	                                            <input type="text" id="ngayChieu" class="form-control session_date_picker"/>
	                                            <p class="help-block">Bạn chọn ngày cần xem lịch chiếu.</p>
	                                    </div>
                                    </div>
                                    
                                    
									<div class="session_panel session_list"></div>
                                    
                                    
                                                              
                                </div>
                            </div>
					
				</div>
				
				
				<div class="modal fade" id="infoSessionModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">THÔNG TIN SUẤT CHIẾU</h4>
						</div>
						
						<div class="modal-body">
						<div class="panel-body">
							<div class="row panel-modal">
									<div class="row">
									Mã suất: <span id="maSuat"></span>
									</div>
									<div class="row">
										<h3></h3>
									</div>
									<div class="row">
										<p class="lead"></p>
									</div>
									<div class="row" >
									
										<!-- table -->
										<table class="table" id="detailTable">
											<thead>
												<tr>
													<th>Loại vé</th>
													<th>Giá tiền</th>
												</tr>
											</thead>
											
											<tbody>
																						
											</tbody>

										</table>
										
									</div>

							</div>
							</div>
						</div>
						<div class="modal-footer">
							<button id="confirm_button" type="button" class="btn btn-primary">Duyệt</button>
							<button id="delete_button" type="button" class="btn btn-danger">Xóa</button>
							<button id="info_close_button" type="button" class="btn btn-default" data-ticket-id="" data-dismiss="modal">Đóng</button>
						</div>

					</div>

				</div>
				<!-- /.modal-content -->

				<!-- end modal -->
			</div>
				
				
			</div>
			
			</div>
			
			
		<div id="confirm_Delete" style="display: none;" title="Thông báo">Bạn có chắc chắn xóa suất chiếu này ?</div> 	
		</div>
		<script src="dist/js/plugin/hashtable.js"></script>
		<script src="dist/js/plugin/numberformatter.js"></script>
		<script src="dist/js/session-list.js"></script>		
</body>
</html>