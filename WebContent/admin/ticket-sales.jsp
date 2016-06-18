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

<title>BÁN VÉ - ICHI ĐÀ NẴNG</title>
<base href="${host}/CGV/admin/">
<link href="dist/css/mydatatable.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
<link href="dist/css/jquery.selectbox.css" rel="stylesheet" />
<link href="dist/css/ticket-sales.css" rel="stylesheet" type="text/css">

</head>

<body>
	<div id="wrapper">
		<input type="hidden" value="sale" id="selected_tab_panel" />

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12 page-header-movie-list">
					<h5 class="title-page">QUẢN LÝ ĐẶT VÉ</h5>


				</div>
			</div>
			<div class="row panel my-panel-default ">
				<div class="panel-body movie-list-panel">

					<!-- panel sale ticket -->
                    <div class="row">
                        <div class="row row-sales">
                            <div class="col-md-4">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Chọn suất chiếu
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-12">
                                                <label>Phim</label>
                                                <select id="phimChieu" class="form-control">
                                                    
                                                </select>
                                                <p class="help-block">Chọn tên phim.</p>

                                                <div class="form-group">
                                                    <div class="form-group">
                                                        <label>Ngày chiếu</label>                                                       
                                                        <select id ="ngayChieu" class="form-control">
                                                       
                                                    	</select>
                                                        <p class="help-block">Chọn ngày chiếu.</p>
                                                    </div>
                                                </div>
                                            </div> 

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Phòng chiếu</label>
                                                    <select id ="phongChieu" class="form-control">
                                                       
                                                    </select>
                                                    <p class="help-block">Chọn phòng chiếu.</p>
                                                </div>
                                            </div>

                                            <div class="col-md-6">
                                                <div class="form-group">
                                                    <label>Suất chiếu</label>
                                                    <select id="suatChieu" class="form-control">

                                                    </select>
                                                    <p class="help-block">Chọn suất chiếu.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-8">
                                <div class="panel panel-default" style="min-height: 326px;">
                                    <div class="panel-heading">
                                        Chọn loại vé và số lượng
                                    </div>
                                    <div class="panel-body">
                                        <div class="table-responsive">
                                            <table class="table" id="detail-sale-table">
                                                <thead>
                                                    <tr>
                                                        <th>Loại vé</th>
                                                        <th>Giá Tiền</th>
                                                        <th>Số lượng</th>
                                                        <th>Thành tiền</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr id="tr_TongCong">
                                                        <td colspan="2">
                                                        	<b>Tổng cộng</b>
                                                        	
                                                        </td>
                                                        <td>
                                                        
                                                        VIP: <span id="numberVip">0</span>
                                                        | THƯỜNG: <span id="numberNormal">0</span>
                                                        
                                                        </td>
                                                        <td>
                                                            <span id="tongCong" class="price_sum money">0</span> VNĐ   
                                                        </td>
                                                     </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        
                                        <div class="alert alert-success">
                                            <div class="seats-code seat_label"></div>
                                        </div>
                                        
                                        
                                     <div class="text-center">
                                        
                                        <button type="button" class="btn btn-primary" id="submit_button">THANH TOÁN</button>
                                    </div>   
                                        
                                    </div>  
                                </div>
                            </div>
                        </div>



                        <div class="col-lg-12">
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
                        </div>

                    </div> <!-- end row -->
			
			
			
			
			
					<!-- end panel sale ticket -->
					<!-- modal -->
					<div class="modal fade" id="ffListModal" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
						style="display: none;">
						<form id="addFFModal_Form" action="">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">×</button>
									<h4 class="modal-title" id="myModalLabel">DANH SÁCH ĐỒ ĂN</h4>
								</div>
								<div class="modal-body">
									<div class="row panel-modal">
										
										<!-- ff list -->
										<div class="table-responsive">
                                            <table class="table" id="fastfood-table">
                                                <thead>
                                                    <tr>
                                                        <th>Tên</th>
                                                        <th>Giá Tiền</th>
                                                        <th>Số lượng</th>
                                                        <th>Thành tiền</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    
                                                </tbody>
                                            </table>
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

			
		</div>
	</div>	

                <input type="hidden" name="seatsYour"/>                                                                                                                      
                <input type="hidden" name="soGheThuongDuocChon"/>
                <input type="hidden" name="soGheVipDuocChon"/>
                
				<div id="fullVip" style="display: none;" title="Thông báo">Bạn không thể lựa chọn thêm ghế VIP !</div> 
                <div id="fullNormal" style="display: none;" title="Thông báo">Bạn không thể lựa chọn thêm ghế THƯỜNG !</div> 
                <div id="notSeatDialog" style="display: none;" title="Thông báo">Bạn phải chọn vị trí ghế ngồi trước khi tiếp tục!</div>
                <div id="mustChooseSeat" style="display: none;" title="Thông báo">Bạn phải chọn vị trí ghế ngồi trước khi tiếp tục!</div>
                <div id="transactionDone" style="display: none;" title="Thông báo">Giao dịch thành công !</div>
		
	</div>
	
	<!-- modal -->
	<div class="modal fade" id="infoTransactionModal" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
				style="display: none;">
				<form id="infoTicketModal_Form" action="">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">×</button>
							<h4 class="modal-title" id="myModalLabel">GIAO DỊCH THÀNH CÔNG - CHI TIẾT GIAO DỊCH</h4>
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
			</div>
	
	
	<!-- end modal -->
</div>
		<script src="dist/js/jquery.validate.js"></script>
		<script src="dist/js/plugin/hashtable.js"></script>
		<script src="dist/js/plugin/numberformatter.js"></script>
		<script type="text/javascript" src="dist/js/ticket-sales.js"></script>
</body>
</html>