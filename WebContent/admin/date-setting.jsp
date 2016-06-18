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
	
    <title>CÀI ĐẶT TÙY CHỈNH GIÁ VÉ - CGV ĐÀ NẴNG</title>
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
                        <h5 class="title-page">CÀI ĐẶT CÁC NGÀY ĐẶC BIỆT</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">
                <html:form action="date.html" method="post" styleId="date_setting_form">
               
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Thông tin cài đặt
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Giá giảm trừ ở suất chiếu sớm (trước 12h)</label>
                                        <html:text property="suatChieuSom" name="dateAdminForm" styleClass="form-control money"/>
                                    	<p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Giá cộng thêm ở suất cuối tuần (T7, CN)</label>
                                        <html:text property="suatChieuCuoiTuan" name="dateAdminForm" styleClass="form-control money"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                     <div class="form-group">
                                        <label>Giá ngày thứ 4 Vui Vẻ</label>
                                        <html:text property="thuTuVuiVe" name="dateAdminForm" styleClass="form-control money"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                </div>
                            </div>


                            <div class="col-lg-6">

                                <div class="panel-body">
                                
                                    <div class="form-group">
                                        <label>Ngày lễ</label>
                                        <html:textarea property="ngayLe" name="dateAdminForm" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Giá cộng thêm ở ngày lễ</label>
                                        <html:text property="giaNgayLe" name="dateAdminForm" styleClass="form-control money"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                </div>
                            </div>
                            
                            <div class="text-center">
                                        <html:submit property="action" value="save" styleClass="btn btn-edit"/>
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
		<div id="insertDone" style="display: none;" title="Thao tác">Thông tin cài đặt đã được lưu !</div>
		<div id="insertError" style="display: none;" title="Thao tác">Lỗi, vui lòng thử lại !</div>
		
		<script src="dist/js/jquery.validate.js"></script>
		<script src="dist/js/plugin/hashtable.js"></script>
		<script src="dist/js/plugin/numberformatter.js"></script>
		<script src="dist/js/date-setting.js"></script>
		<script src="dist/js/dialog.js"></script>
</body>
</html>