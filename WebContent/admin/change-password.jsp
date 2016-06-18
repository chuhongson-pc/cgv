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

<title>ĐỔI MẬT KHẨU - ICHI ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">


<style type="text/css">
label {
	padding-top: 10px;
	padding-bottom: 10px;
}

.errors {
	position: relative;
	padding: 7px 30px 5px 10px;
	width: 100%;
}

.row {
	padding: 0px 20px 0px 10px;
	table-layout: fixed;
	overflow-y: hidden; /* Trigger vertical scroll    */
	overflow-x: auto; /* Hide the horizontal scroll */
}

table td,table th {
	overflow: hidden;
	white-space: nowrap;
}
</style>
</head>

<body>
	<div id="wrapper">
		
		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h5 class="page-header">ĐỔI MẬT KHẨU</h5>
				</div>

			</div>
			<div class="row">
				<div class="col-lg-12">

					<!-- chèn nội dung -->
				
					<html:form action="/changePasswordAd">
						<br>
						<div class="row">
							<label for="firstname" class="col-sm-2 control-label">
								Mật khẩu cũ</label>
							<div class="col-sm-5">
								<html:password styleClass="form-control" property="matKhauCu" maxlength="14"/>
							</div>
							<div class="col-sm-5">
								<html:messages id="cu" property="matKhauCuError">
									<div class="alert-danger errors" role="alert">
										<span class="glyphicon glyphicon-exclamation-sign"
											aria-hidden="true"></span> <span class="sr-only">Lỗi:</span>
										<bean:write name="cu" />
									</div>
								</html:messages>
							</div>
						</div>
						<div class="row">
							<label for="firstname" class="col-sm-2 control-label">
								Mật khẩu mới</label>
							<div class="col-sm-5">
								<html:password styleClass="form-control" property="matKhauMoi"  maxlength="14"/>
							</div>
							<div class="col-sm-5">
								<html:messages id="moi" property="matKhauMoiError">
									<div class="alert-danger errors" role="alert">
										<span class="glyphicon glyphicon-exclamation-sign"
											aria-hidden="true"></span> <span class="sr-only">Lỗi:</span>
										<bean:write name="moi" />
									</div>
								</html:messages>
							</div>
						</div>
						<div class="row">
							<label for="firstname" class="col-sm-2 control-label">
								Xác nhận mật khẩu</label>
							<div class="col-sm-5">
								<html:password styleClass="form-control" property="xacNhan" maxlength="14"/>
							</div>
							<div class="col-sm-5">
								<html:messages id="xacnhan" property="xacNhanError">
									<div class="alert-danger errors" role="alert">
										<span class="glyphicon glyphicon-exclamation-sign"
											aria-hidden="true"></span> <span class="sr-only">Lỗi:</span>
										<bean:write name="xacnhan" />
									</div>
								</html:messages>
							</div>
						</div>

						<div class="row">
							<div class="col-sm-3"></div>
							<div class="col-sm-2">
								<html:reset styleClass="btn btn-default" value="Reset" />
							</div>
							<div class="col-sm-2">
								<html:submit styleClass="btn btn-info" value="Update" />
							</div>
						</div><br>
						<div class="row">
							<div class="col-sm-2"></div>
							<div class="col-sm-5">
								<html:messages id="bao" property="alertError">
									<div class="alert-danger errors" role="alert">
										<span class="glyphicon glyphicon-exclamation-sign"
											aria-hidden="true"></span> <span class="sr-only">Lỗi:</span>
										<bean:write name="bao" />
									</div>
								</html:messages>
								<html:messages id="thanhcong" property="alertSuccess">
									<div class="alert-success errors" role="alert">
										<span class="glyphicon glyphicon-exclamation-sign"
											aria-hidden="true"></span> <span class="sr-only">Lỗi:</span>
										<bean:write name="thanhcong" />
									</div>
								</html:messages>
							</div>
						</div>
					</html:form>
					
				</div>
			</div>
		</div>
	</div>
</body>
</html>