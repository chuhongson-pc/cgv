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

<title>QUẢN LÝ NHÂN VIÊN - CGV ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">
<link href="dist/css/mydatatable.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="staff" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">DANH SÁCH NHÂN VIÊN</h5>

				</div>
			</div>
			<div class="row panel my-panel-default">
				<div class="panel-body movie-list-panel">

					<table class="table table-striped table-bordered table-hover" id="personTable">
						<thead>
							<tr>
								<th>Tài khoản</th>
								<th>Họ và tên</th>
								<th>SĐT</th>
								<th>Email</th>
								<th>CMND</th>							
								<th>TT</th>
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
								<th></th>
							</tr>
						</tfoot>
					</table>

					<div class="text-center">

						
						<html:link action="/staff.html?action=toAdd">
                            <input class="btn btn-add">
                        </html:link>

					</div>



				</div>
			</div>

		</div>

	</div>

	<script
		src="http://jquery-datatables-column-filter.googlecode.com/svn/trunk/media/js/jquery.dataTables.columnFilter.js"></script>
	<script type="text/javascript" src="dist/js/plugin/fnStandingRedraw.js"></script>
	<script type="text/javascript"
		src="dist/js/plugin/fnSetFilteringDelay.js"></script>
	<script type="text/javascript" src="dist/js/staff.js"></script>

</body>
</html>