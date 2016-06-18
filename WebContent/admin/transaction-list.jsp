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

<title>QUẢN LÝ GIAO DỊCH - ICHI ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">
<link href="dist/css/mydatatable.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="transaction" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">DANH SÁCH GIAO DỊCH</h5>

				</div>
			</div>
			<div class="row panel my-panel-default">
				<div class="panel-body movie-list-panel">

					<table class="table table-striped table-bordered table-hover" id="transactionTable">
						<thead>
							<tr>
								<th>Mã GD</th>
								<th>Tài khoản</th>
								<th>Phim</th>
								<th>Thời gian GD</th>
								<th>Tổng tiền</th>
								<th>Thao tác</th>							
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								<th></th>
								
							</tr>
						</tfoot>
					</table>


				</div>
			</div>

		</div>

	</div>
	
	
	<div class="modal fade" id="infoTransactionModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<form id="infoTicketModal_Form" action="">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">CHI TIẾT GIAO DỊCH</h4>
						</div>
						
						<div class="modal-body">
						<div class="panel-body">
							<div class="row panel-modal">
									<div class="row">
										Mã GD: <span id="maGD"></span>
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
										<tr>
											<th>Tên</th>
											<th>Số lượng</th>
										</tr>
										<tbody>
										
										
										<tr class="tr_tongCong">
										<td>Tổng cộng</td>
										<td>
											<span id="tongCong"></span> VNĐ
										</td>
										</tr>
										
										</tbody>

										</table>
										
									
									</div>

									<div class="row alert alert-success" id="seatList">
									
										<!-- seats list -->
									
									</div>

									
								
							</div>
							</div>
						</div>
						<div class="modal-footer">
							
							<button id="info_print_button" type="button" class="btn btn-success">In vé</button>
							<button id="info_close_button" type="button" class="btn btn-default" data-ticket-id="" data-dismiss="modal">Đóng</button>
							
							
						</div>

					</div>

				</div>
				</form>
				<!-- /.modal-content -->

				<!-- end modal -->
				<div id="confirm" style="display: none;" title="Thông báo">Bạn có thực sự muốn hủy giao dịch ?</div> 
			</div>

	<script src="dist/js/jquery.dataTables.columnFilter.js"></script>
	<script type="text/javascript" src="dist/js/plugin/fnStandingRedraw.js"></script>
	<script type="text/javascript"src="dist/js/plugin/fnSetFilteringDelay.js"></script>
	<script src="dist/js/plugin/hashtable.js"></script>
	<script src="dist/js/plugin/numberformatter.js"></script>
	<script src="dist/js/jquery-ui.js"></script>
	<script type="text/javascript" src="dist/js/transaction.js"></script>

</body>
</html>