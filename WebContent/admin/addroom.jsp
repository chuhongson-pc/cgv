<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<base href="${host}/CGV/admin/">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
    <link href="dist/css/jquery.selectbox.css" rel="stylesheet" />
    <link href="dist/css/style-room.css" rel="stylesheet" />

    <title>THÊM PHÒNG CHIẾU - CGV ĐÀ NẴNG</title>
	
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="room" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h5 class="title-page"><html:link action="/room?action=list">[DANH SÁCH PHÒNG CHIẾU]</html:link> > THÊM PHÒNG CHIẾU</h5>
                </div>

            </div>
            <div class="row">
            
				<div class="col-lg-12">
				<html:form action="/room.html" styleId="addRoomForm">
                        <div class="panel my-panel-default">
                            <div class="panel panel-info">
                                <div class="panel-heading">
                                    THÔNG TIN PHÒNG CHIẾU
                                </div>
                                <div class="panel-body">
                                    <div class="row">
                                        <div class="col-lg-3">
                                            <div class="form-group input-group">
                                                <span class="input-group-addon">TÊN PHÒNG</span>                                                
                                                <html:text property="tenPhong" styleClass="form-control" />
                                            </div>
                                            <p class="help-block custom-error-message"></p>
                                        </div>
                                        <div style="float: left;width: 14%;">
                                            <div class="form-group input-group" style="width: 90%;">
                                            <span class="input-group-addon">LOẠI</span>
												<html:select property="loaiPhong" styleClass="form-control">												    
													<html:option value="2D">2D</html:option>
													<html:option value="3D">3D</html:option>
												</html:select>
                                            </div>
                                        </div>
                                        
                                        <div class="col-lg-2">
                                            <div class="form-group input-group" style="width: 90%;">
                                            <span class="input-group-addon">SỐ GHẾ</span>
                                            <html:hidden property="soGhe"/>
                                            <html:text property="soGhe" styleClass="form-control" disabled="true"/>
                                            </div>
                                        </div>
                                        
                                        <div style="float: left;width: 23%;">
                                            <div class="form-group input-group">                                
                                            <span class="input-group-addon">TRẠNG THÁI</span>
												<html:select property="trangThai" styleClass="form-control">												    
													<html:option value="1">KHẢ DỤNG</html:option>
													<html:option value="2">VÔ HIỆU</html:option>
												</html:select>
                                            </div>
                                        </div>

                                        <div class="col-lg-3">
                                            <div class="form-group">

                                                <html:hidden property="soDoGhe"/>
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
                                </div>
                            </div>

                        </div>


                        <div class="panel panel-info">
                            <div class="panel-heading">
                                SƠ ĐỒ GHẾ
                            </div>
                            <div class="panel-body">
                                <div class="row seats-map">

                                    <div class="sits-area hidden-xs">
                                        <div class="sits-anchor">screen</div>

                                        <div class="sits">
                                            <aside class="sits__line">
                                                <span class="sits__indecator">A</span>
                                                <span class="sits__indecator">B</span>
                                                <span class="sits__indecator">C</span>
                                                <span class="sits__indecator">D</span>
                                                <span class="sits__indecator">E</span>
                                                <span class="sits__indecator">F</span>
                                                <span class="sits__indecator">G</span>
                                                <span class="sits__indecator">H</span>
                                                <span class="sits__indecator">J</span>
                                                <span class="sits__indecator">K</span>
                                                <span class="sits__indecator">L</span>
                                                <span class="sits__indecator">M</span>
                                                <span class="sits__indecator">N</span>
                                                <span class="sits__indecator">O</span>
                                                <span class="sits__indecator">P</span>
                                                <span class="sits__indecator">Q</span>
                                            </aside>

                                            <div class="sits__row" data-row="A" data-y="1">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="B" data-y="2">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="C" data-y="3">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="D" data-y="4">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="E" data-y="5">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="F" data-y="6">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="G" data-y="7">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="H" data-y="8">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="J" data-y="9">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="K" data-y="10">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="L" data-y="11">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="M" data-y="12">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="N" data-y="13">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="O" data-y="14">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="P" data-y="15">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <div class="sits__row" data-row="Q" data-y="16">
                                                <span class="sits__place" data-place="" data-x="1" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="2" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="3" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="4" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="5" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="6" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="7" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="8" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="9" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="10" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="11" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="12" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="13" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="14" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="15" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="16" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="17" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="18" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="19" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="20" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="21" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="22" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="23" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="24" data-type="none">null</span>
                                                <span class="sits__place" data-place="" data-x="25" data-type="none">null</span>
                                            </div>

                                            <footer class="sits__number">
                                                <span class="sits__indecator">1</span>
                                                <span class="sits__indecator">2</span>
                                                <span class="sits__indecator">3</span>
                                                <span class="sits__indecator">4</span>
                                                <span class="sits__indecator">5</span>
                                                <span class="sits__indecator">6</span>
                                                <span class="sits__indecator">7</span>
                                                <span class="sits__indecator">8</span>
                                                <span class="sits__indecator">9</span>
                                                <span class="sits__indecator">10</span>
                                                <span class="sits__indecator">11</span>
                                                <span class="sits__indecator">12</span>
                                                <span class="sits__indecator">13</span>
                                                <span class="sits__indecator">14</span>
                                                <span class="sits__indecator">15</span>
                                                <span class="sits__indecator">16</span>
                                                <span class="sits__indecator">17</span>
                                                <span class="sits__indecator">18</span>
                                                <span class="sits__indecator">19</span>
                                                <span class="sits__indecator">20</span>
                                                <span class="sits__indecator">21</span>
                                                <span class="sits__indecator">22</span>
                                                <span class="sits__indecator">23</span>
                                                <span class="sits__indecator">24</span>
                                                <span class="sits__indecator">25</span>
                                            </footer>
                                        </div>
                                    </div>

                                </div> <!--end row sodoghe-->
                            </div>
                        </div>
                        <div id="mydialog" style="display: none;" title="Thao tác">Bạn có thể xóa ghế hoặc cài đặt thành ghế VIP</div>
						<div id="updateDone" style="display: none;" title="Thao tác">Thông tin phòng đã được cập nhật !</div>
						<div id="updateError" style="display: none;" title="Thao tác">Lỗi cập nhật, vui lòng thử lại !</div>
						</html:form>
                    </div>
            </div>
        </div>
        
</div>
<script src="dist/js/jquery-ui.js"></script>
<script src="dist/js/jquery.validate.js"></script>
<script src="dist/js/room.js"></script>

<script type="text/javascript">
      $(document).ready(function () {
         init_Room();
         
         
         
         var validator = $("#addRoomForm").validate({
             
             rules: {
             	'tenPhong':{
                     required: true,
                     minlength: 10,
                     maxlength: 50
                 }
             },
             messages: {
             	'tenPhong': {
                 	required: "Bạn vui lòng nhập tên phòng.",
                 	minlength: jQuery.validator.format("Tên phim phải có ít nhất {0} ký tự"),
                 	maxlength: jQuery.validator.format("Tên phim nhiều nhất {0} ký tự")
                 }
                
             },
             // the errorPlacement has to take the table layout into account
             errorPlacement: function (error, element) {
             		console.log('validate');
                     error.appendTo(element.parent().next());
             },
      
             // set this class to error-labels to indicate valid fields
             success: function (label) {
                 // set &nbsp; as text for IE
                 label.html("&nbsp;").remove();
                 
             },
             highlight: function (element, errorClass) {
                 
                 $(element).addClass('parsley-validated parsley-error');

             },
             //
             unhighlight: function( element, errorClass, validClass ){
                
                 $(element).removeClass('parsley-validated parsley-error');
             }
         });
         
      });
</script>
</body>
</html>