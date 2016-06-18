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

    <title>ICHI ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="quanlykhachhang" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h3 class="page-header">ICHI - ĐÀ NẴNG</h3>
                </div>

            </div>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        
                       <div class="text-center">
                       
                       	<h5>HỆ THỐNG HỖ TRỢ QUẢN LÝ RẠP CHIẾU PHIM ICHI - ĐÀ NẴNG.</h5>
                       
                       </div>
                        

                    </div>
                </div>
            </div>
        </div>
        
</div>
</body>
</html>