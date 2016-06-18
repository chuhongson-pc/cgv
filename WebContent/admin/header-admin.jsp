<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation"
	style="margin-bottom: 0">

	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
			data-target=".navbar-collapse">
			<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>

	<a class="navbar-brand" href="dashboard.jsp">HỆ THỐNG QUẢN LÝ RẠP CHIẾU PHIM ĐÀ NẴNG</a>
	</div>
	<!-- /.navbar-header -->
	<logic:present name="useradmin" scope="session">
		<ul class="nav navbar-top-links navbar-right">

			<!-- /.dropdown -->
			<li class="dropdown">
			<a class="dropdown-toggle"
				data-toggle="dropdown" href="#"> <i class="fa fa-user fa-fw"></i>
					<bean:write name="useradmin" /><i class="fa fa-caret-down"></i>
			</a>
				<ul class="dropdown-menu dropdown-user">


					<li><html:link href="change-password.jsp">
							<i class="fa fa-user fa-fw"></i>Đổi mật khẩu</html:link></li>

					<li class="divider"></li>
					<li><html:link action="/logoutadmin.html">
							<i class="fa fa-sign-out fa-fw"></i> Đăng xuất</html:link></li>
				</ul>
			</li>
			<!-- /.dropdown -->
		</ul>
	</logic:present>
	
	
	
	<div class="navbar-default sidebar" role="navigation">
		<!-- logo -->

		<div class="logo shadow">
			<a class="left" href=""></a>
		</div>

		<div class="sidebar-nav navbar-collapse">
			<ul class="nav" id="side-menu">
			
				<li>
                    <html:link action="/sale.html?action=toShow" styleId="sale"><i class="fa fa-dashboard fa-fw"></i>QUẢN LÝ ĐẶT/BÁN VÉ</html:link>
                            
                 </li>
                 
                 <li>
                    <html:link action="/transaction.html?action=toShow" styleId="transaction"><i class="fa fa-money fa-fw"></i>QUẢN LÝ GIAO DỊCH</html:link>
                            
                 </li>
                    
                    
				<li>
					<!-- thêm class active vào mục được chọn --> <!-- a class thêm class class="active" -->
					<html:link action="" styleId="movie">
						<i class="fa fa-film fa-fw"></i>QUẢN LÝ PHIM<span class="fa arrow"></span>
					</html:link>
					<ul class="nav nav-second-level collapse in">
						<li id="movie1"><html:link action="/movie.html?action=list">Danh sách phim</html:link>
						</li>
						<li id="movie2"><html:link action="/movie.html?action=toAdd">Thêm phim</html:link></li>

					</ul>
				</li>
				<li><html:link action="" styleId="session">
						<i class="fa fa-calendar fa-fw"></i>QUẢN LÝ LỊCH CHIẾU<span
							class="fa arrow"></span>
					</html:link>
					<ul class="nav nav-second-level collapse in">
						<li id="sessionlist"><html:link action="/session.html?action=toSessionList">Lịch chiếu</html:link>
						</li>
						<li id="addsession"><html:link action="/session.html?action=show">Thêm lịch chiếu</html:link>
						</li>		
						<li id="datesetting"><html:link action="/date.html?action=toShow">Tùy chọn giá vé</html:link>
						</li>		
						
					</ul></li>

				<li>
					<html:link action="/room.html?action=list" styleId="room">
						<i class="fa fa-bank fa-fw"></i>QUẢN LÝ PHÒNG CHIẾU
					</html:link>
				</li>


				<li>
						<html:link action="/ticket.html?action=toShow"
						styleId="ticket">
						<i class="fa fa-ticket fa-fw"></i>QUẢN LÝ LOẠI VÉ</html:link>
				</li>
				
				<li>
						<html:link action="/fastfood.html?action=toShow"
						styleId="fastfood">
						<i class="fa fa-coffee fa-fw"></i>QUẢN LÝ ĐỒ ĂN</html:link>
				</li>
				
				<li>
						<html:link action="/news.html?action=toShow"
						styleId="news">
						<i class="fa fa-comments fa-fw"></i>QUẢN LÝ TIN TỨC</html:link>
				</li>

				<li><html:link action="/staff.html?action=toShow"
						styleId="staff">
						<i class="fa fa-user fa-fw"></i>QUẢN LÝ NHÂN VIÊN</html:link>
				</li>
				
				<li><html:link action="/customer.html?action=toShow"
						styleId="customer">
						<i class="fa fa-users fa-fw"></i>QUẢN LÝ KHÁCH HÀNG</html:link>
				</li>
				<li><html:link action="/poster.html?action=list"
						styleId="poster">
						<i class="fa fa-video-camera fa-fw"></i>QUẢN LÝ POSTER</html:link>
				</li>
				<li><html:link action="statistic.html?action=toShow" styleId="statistic">
						<i class="fa fa-bar-chart-o fa-fw"></i>THỐNG KÊ</html:link></li>
			</ul>
		</div>
		<!-- /.sidebar-collapse -->
	</div>
	<!-- /.navbar-static-side -->
</nav>

<link rel="stylesheet" href="bower_components/bootstrap/dist/css/bootstrap.css">
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/datepicker.css">
<link rel="stylesheet" href="bower_components/bootstrap/dist/css/clockpicker.css">
<link href="bower_components/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" rel="stylesheet">
<link href="dist/css/sb-admin-2.css" rel="stylesheet">
<link href="bower_components/font-awesome/css/font-awesome.css" rel="stylesheet" type="text/css">

<!-- jQuery -->
<script src="bower_components/jquery/dist/jquery.min.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="dist/js/sb-admin-2.js"></script>
<script src="dist/js/jquery-ui.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap-clockpicker.js"></script>
<script src="bower_components/bootstrap/dist/js/bootstrap-datepicker.js"></script>
<script src="bower_components/datatables/media/js/jquery.dataTables.min.js"></script>
<script src="bower_components/datatables/media/js/dataTables.bootstrap.js"></script>
<!-- Show and Search -->
<script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>


<script src="dist/js/header.js"></script>


