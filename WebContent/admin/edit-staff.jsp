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
	
    <title>THÔNG TIN NHÂN VIÊN - CGV ĐÀ NẴNG</title>
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
                        <h5 class="title-page">THÔNG TIN NHÂN VIÊN</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">
                <html:form action="staff.html" method="post" styleId="staff_form">
               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thông tin tài khoản nhân viên
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                            <label>Tên tài khoản</label>
                                            <bean:define id="staff" name="staffAdminForm" property="currentStaff"/>
                                            <p class="form-control-static username-label"><bean:write property="username" name="staff"/></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Tên đầy đủ</label>
                                        <html:text property="currentStaff.fullname" name="staffAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Ngày sinh</label>
                                        <html:text property="currentStaff.dob" name="staffAdminForm" styleClass="form-control date_picker"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Địa chỉ</label>
                                        <html:text property="currentStaff.address" name="staffAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    
                                     <div class="form-group">
                                        <label>Số điện thoại</label>
                                        <html:text property="currentStaff.phonenumber" name="staffAdminForm" styleClass="form-control" />
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">                                   
                                        <label>Giới tính</label>
                                        <label class="radio-inline">                               
                                        <input type="radio" name="currentStaff.gender" value="Nam" checked="checked">Nam
                                        </label>
                                        <label class="radio-inline">  
										<input type="radio" name="currentStaff.gender" value="Nữ">Nữ
                                        </label>                                                                              
                                    </div>
                                    
                                     

                                    
                                </div>
                            </div>


                            <div class="col-lg-6">

                                <div class="panel-body">
                                
                                 	<div class="form-group">
                                        <label>Email</label>
                                        <html:text property="currentStaff.email" name="staffAdminForm" styleClass="form-control" />
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                                                                                      
                                    <div class="form-group">
                                        <label>Số CMND</label>
                                        <html:text property="currentStaff.cmnd" name="staffAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Mật khẩu</label>
                                        <html:password property="currentStaff.password" name="staffAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    
                                    <div class="form-group">
                                        <label>Trạng thái</label>                                                                               
                                        <html:select property="currentStaff.trangThai" name="staffAdminForm" styleClass="form-control">
                                        	<html:option value="1">Activated</html:option>
                                        	<html:option value="2">Banned</html:option>
                                        </html:select>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Ngày khởi tạo</label>                                                                                                                       
                                        <p class="form-control-static"><bean:write property="ngayKhoiTao" name="staff"/></p>
                                        
                                    </div>
                                    
                                </div>
                            </div>
                            
                            
                            <div class="text-center">
                                        <html:submit property="action" value="edit" styleClass="btn btn-edit"/>
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

		<div id="insertDone" style="display: none;" title="Thao tác">Tài khoản đã được cập nhật !</div>
		<div id="insertError" style="display: none;" title="Thao tác">Lỗi, vui lòng thử lại !</div>
				
        <script src="dist/js/jquery.validate.js"></script>
        <script src="dist/js/staff-validate.js"></script>
        <script src="dist/js/dialog.js"></script>
</body>
</html>