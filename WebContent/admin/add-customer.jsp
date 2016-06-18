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
	
    <title>THÊM KHÁCH HÀNG - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
	<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
	
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="quanlykhachhang" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÊM KHÁCH HÀNG</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">
                <html:form action="customer.html" method="post" styleId="customer_form">
               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thông tin tài khoản khách hàng
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Tên tài khoản</label>
                                        <html:text property="currentCustomer.username" name="customerAdminForm" styleClass="form-control"/>
                                    	<p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Tên đầy đủ</label>
                                        <html:text property="currentCustomer.fullname" name="customerAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    

                                    <div class="form-group">
                                        <label>Ngày sinh</label>
                                        <html:text property="currentCustomer.dob" name="customerAdminForm" styleClass="form-control date_picker"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Địa chỉ</label>
                                        <html:text property="currentCustomer.address" name="customerAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                    
                                        <label>Giới tính</label>
                                        <label class="radio-inline">                               
                                        <input type="radio" name="currentCustomer.gender" value="Nam" checked="checked">Nam
                                        </label>
                                        <label class="radio-inline">  
										<input type="radio" name="currentCustomer.gender" value="Nữ">Nữ
                                        </label>
                                       
                                        
                                    </div>

                                   

                                </div>
                            </div>


                            <div class="col-lg-6">

                                <div class="panel-body">
                                
                                    <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <html:text property="currentCustomer.phonenumber" name="customerAdminForm" styleClass="form-control" />
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                     <div class="form-group">
                                        <label>Email</label>
                                        <html:text property="currentCustomer.email" name="customerAdminForm" styleClass="form-control" />
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Số CMND</label>
                                        <html:text property="currentCustomer.cmnd" name="customerAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Mật khẩu</label>
                                        <html:password property="currentCustomer.password" name="customerAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Số dư tài khoản (Đơn vị VNĐ)</label>                                                                               
                                        <html:text property="currentCustomer.soDuTaiKhoan" name="customerAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>


                                </div>
                            </div>
                             <div class="text-center">
                                        <html:submit property="action" value="add" styleClass="btn btn-add"/>
                                        <logic:messagesPresent message="true">
												    <html:messages id="aMsg" message="true">
												        <logic:present name="aMsg">
												            <!-- Messages -->
												            <div class="messages">
												                <bean:write name="aMsg" filter="false" />
												            </div>
												        </logic:present>
												    </html:messages>
										</logic:messagesPresent>
												
										<logic:messagesPresent message="false">
												    <html:messages id="aMsg" message="false">
												        <logic:present name="aMsg">
												            <!-- Warnings-->
												            <div class="errors">
												                <bean:write name="aMsg" filter="false" />
												            </div>
												        </logic:present>
												    </html:messages>
										</logic:messagesPresent>
										
                       </div>
                        </div>
                       
                    </div>
                    </html:form>
                </div>
            </div>       
        
</div>
		<div id="insertDone" style="display: none;" title="Thao tác">Tài khoản đã được thêm vào !</div>
		<div id="insertError" style="display: none;" title="Thao tác">Lỗi, vui lòng thử lại !</div>
		<div id="existDialog" style="display: none;" title="Thao tác">Tên đăng nhập đã tồn tại !</div>
		
        <script src="dist/js/jquery.validate.js"></script>
        <script src="dist/js/customer-validate.js"></script>
        <script src="dist/js/dialog.js"></script>
</body>
</html>