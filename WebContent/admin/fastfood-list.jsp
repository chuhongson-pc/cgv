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

<title>QUẢN LÝ ĐỒ ĂN - CGV ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">
<link href="dist/css/mydatatable.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>

</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="fastfood" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">DANH SÁCH ĐỒ ĂN</h5>
					

				</div>
			</div>
			<div class="row panel my-panel-default ">
				<div class="panel-body movie-list-panel">

					<table class="table table-striped table-bordered table-hover" id="fastfood_Table">
						<thead>
							<tr>
								<th>Tên</th>
								<th>Loại</th>
								<th>Giá tiền</th>
								<th>Status</th>
								<th>Thao tác</th>								
							</tr>
						</thead>
						
					</table>


					<!--  -->
					<div class="text-center">

						<button data-toggle="modal" data-target="#addFFModal"
							class="btn btn-add"></button>

					</div>
				</div>
			</div>
			<!-- modal -->
			<div class="modal fade" id="addFFModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<form id="addFFModal_Form" action="">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">THÊM ĐỒ ĂN</h4>
						</div>
						<div class="modal-body">
							<div class="row panel-modal">
								<div class="row">
									<div class="col-md-8">
										<div class="form-group">
											<label>Tên đồ ăn</label> <input class="form-control"
												name="tenFF">
										</div>
									</div>
									<div class="col-md-4">
										<div class="form-group">
											<label>Loại</label> <select class="form-control"
												name="loaiFF">
												<option value="food">Thức ăn</option>
												<option value="drink">Thức uống</option>
												<option value="combo">Combo</option>
											</select>
										</div>
									</div>
								</div>
								
								<div class="form-group">
										<label>Mô tả</label>
										<textarea class="form-control" name="moTa"></textarea>
										
								</div>

								<div class="row">
									
									<div class="col-lg-6">
										<div class="form-group">
											<label>Giá tiền</label>
											<input class="form-control" name="giaTien">
											<p class="help-block custom-error-message"></p>
										</div>
									</div>
									
									<div class="col-lg-6">
										<label>Trạng thái</label> 
												<select class="form-control" name="trangThai">
													<option value="1">Hiển thị cho Khách</option>
													<option value="2">Hiển thị cho KM</option>
													<option value="3">Block</option>
										</select>
									</div>

								</div>

							</div>
						</div>
						<div class="modal-footer">
							<button id="add_save_button" type="button"
								class="btn btn-primary">Lưu</button>
							<button id="add_close_button" type="button"
								class="btn btn-default" data-dismiss="modal">Đóng</button>
						</div>
					</div>

				</div>
				<!-- /.modal-content -->
				</form>
			</div>
			<!-- /.modal-dialog -->

			<div class="modal fade" id="infoFFModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<form id="infoFFModal_Form" action="">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">CHI TIẾT ĐỒ ĂN</h4>
						</div>
						
						<div class="modal-body">
							<div class="row panel-modal">
								
									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label>Tên đồ ăn</label> 
												<input class="form-control" name="tenFF"/>
												<p class="help-block custom-error-message"></p>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label>Loại</label> 
												<select class="form-control"
													name="loaiFF">
													<option value="food">Thức ăn</option>
													<option value="drink">Thức uống</option>
													<option value="combo">Combo</option>
												</select>
											</div>
										</div>
									</div>
									
									<div class="form-group">
										<label>Mô tả</label>
										<textarea class="form-control" name="moTa"></textarea>
										<p class="help-block custom-error-message"></p>
									</div>
									
									<div class="row">
									
										<div class="col-lg-6">
											<div class="form-group">
												<div class="form-group">
													<label>Giá tiền</label> 
													<input class="form-control" name="giaTien"/>
												    <p class="help-block custom-error-message"></p>
												</div>
											</div>
										</div>
										
										<div class="col-lg-6">
											<div class="form-group">												 
												<label>Trạng thái</label> 
												<select class="form-control" name="trangThai">
													<option value="1">Hiển thị cho Khách</option>
													<option value="2">Hiển thị cho KM</option>
													<option value="3">Block</option>
												</select>
											</div>
										</div>
									</div>

								
							</div>

						</div>
						<div class="modal-footer">
							<button id="info_save_button" type="button"
								class="btn btn-primary">Lưu</button>
							<button id="info_close_button" type="button"
								class="btn btn-default" data-ticket-id="" data-dismiss="modal">Đóng</button>
						</div>

					</div>

				</div>
				</form>
				<!-- /.modal-content -->

				<!-- end modal -->
			</div>
		</div>
		<div id="confirm" style="display: none;" title="Thông báo">Bạn có thực sự muốn hủy giao dịch ?</div>
	</div>	
		
		<script src="dist/js/jquery.validate.js"></script>
		<script src="dist/js/jquery-ui.js"></script>
		<script type="text/javascript" src="dist/js/fastfood.js"></script>
	
</body>
</html>