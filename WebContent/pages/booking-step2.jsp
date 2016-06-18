<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="../resources/images/favicon.ico">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <link rel="stylesheet" type="text/css" href="resources/styles/cgv.css">
        <link rel="stylesheet" type="text/css" href="resources/styles/normalize.css">
        <link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css">
        <!-- jquery ui -->
        <link rel="stylesheet" type="text/css" href="resources/styles/jquery-ui.css"/>
        <!-- Select -->
        <link href="resources/styles/jquery.selectbox.css" rel="stylesheet">
        <link href="resources/styles/style-seat.css" rel="stylesheet">

        <title>ĐẶT VÉ</title>
        <style type="text/css"></style>
    </head>
    <body>

        <div class="page__body page-name" data-page-name="show-times">

            <article class="row page__content">

                <jsp:include page="header.jsp"></jsp:include>

                <div class="off-canvas-wrap">
                    <div class="inner-wrap">
                        <nav class="tab-bar show-for-small">
                            <section class="tab-bar-section">
                                <a class="logo small" href="../index.html"></a>
                            </section>
                            <section class="right">
                                <a class="right-off-canvas-toggle menu-icon">
                                    <span></span>
                                </a>
                            </section>
                        </nav>

                        <section class="main-section">
                            <section class="menu">
                                <nav class="top-bar main tab hide-for-small">
                                    <section class="top-bar-section">
                                        <ul>
                                            <li class="hide-for-small "><html:link action="/home"><i class="icon-home"></i></html:link></li>

                                            <li class="hide-for-small "><html:link action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>

                                            <li class="hide-for-small "><html:link action="/showtimes.html">LỊCH CHIẾU</html:link></li>
                                            
                                            <li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>

                                            <li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
                                            
                                            <li class="hide-for-small active"><a>ĐẶT VÉ</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">CHỌN VỊ TRÍ GHẾ</h6>
                            </section>
                            <!--content main-->

                            <section>
                                <div id="registerContainer" class="steps__container">
                                    <html:form action = "/booking.html">
                                    
										<bean:define id="session_current" name="bookingForm" property="sessionOfBooking"/>		
										<bean:define id="movie_current" name="session_current" property="movieOfSession"/>
										<bean:define id="room_current" name="session_current" property="roomOfSession"/>
                                    
                                        <section>
                                            <div class="choose-sits">
                                                <span class="box-title"><span>THÔNG TIN PHIM</span></span>
                                                <div class="info_film">
                                                    <div class="movie-detail-left">
                                                        <img id="imgDetail" src="<bean:write name="movie_current" property="thumbnail"/>">
                                                    </div>
                                                    <div id="detailHolder" class="movie-detail-right">
                                                        <h3 class="movie-title"><bean:write name="movie_current" property="tenPhim"/></h3>

                                                        <div class="lbl date_current"><bean:write name="session_current" property="ngayChieu"/></div>
                                                        <div class="lbl time">
                                                            <span><bean:write name="session_current" property="gioChieu"/></span> | <bean:write name="room_current" property="tenPhong"/>
                                                        </div>

                                                        <div class="movie-technology-icons">

                                                            <html:hidden name="session_current" property="type3d"/>
                                                            <html:hidden name="movie_current" property="doTuoiChoPhep"/>

                                                        </div>

                                                    </div>
                                                    <!-- info table -->

                                                    <div id="detailBooking" class="movie-detail-booking">

                                                        <table id="detailTable">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col" id="loai">NỘI DUNG</th>
                                                                    <th scope="col" id="sl">SL</th>
                                                                    <th scope="col" id="gia">THÀNH TIỀN</th>
                                                                </tr>                                                                   
                                                            </thead>
                                                            <tbody>
                                                            
                                                            <logic:iterate id="ticketType_current" name="session_current" property="ticketTypeSettingList">
                                                            	<tr id="<bean:write name="ticketType_current" property="maLoaiVe"/>" class="row_detail_selected">
                                                                    <td>
                                                                        <span class="name_of_ticket"><bean:write name="ticketType_current" property="tenLoaiVe"/></span>

                                                                    </td>
                                                                    <td>
                                                                        <html:hidden name="ticketType_current" property="giaTien"/>
                                                                        <span class="number_selected">0</span>
                                                                    </td>

                                                                    <td>
                                                                        <span class="price_ticket_sum">0</span> VNĐ
                                                                    </td>
                                                                    
                                                                </tr>
                                                            </logic:iterate>
                                                            <!-- SUM -->
                                                                <tr>
                                                                    <td colspan="2">
                                                                        <b>Tổng cộng</b>
                                                                    </td>

                                                                    <td>
                                                                        <span id="tongTienVe" class="price_ticket_sum">0</span> VNĐ   
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>

                                                    </div>
                                                    <!--danh sách ghế-->
                                                    <div class="selectedSeats">
                                                        GHẾ ĐÃ CHỌN: <span class="seat_label"></span>
                                                    </div>

                                                </div>

                                                <!-- ô ghế -->

                                                <div class="col-sm-12 col-lg-10 col-lg-offset-1">
                                                    <!--thông tin tình trạng ghế -->
                                                    <html:hidden property="soDoGhe" name="room_current"/>
                                                    <html:hidden property="seatsBooked" name="bookingForm"/>
                                                    <html:hidden property="seatsYour" name="bookingForm"/>
                                                    
                                                    <html:hidden property="ticketTypeSelected" name="bookingForm"/>
                                                    
                                                    <html:hidden property="soGheThuongDuocChon" name="bookingForm"/>
                                                    <html:hidden property="soGheVipDuocChon" name="bookingForm"/>

                                                    <!-- end tt ghế -->
                                                    <span class="box-title"><span>CHỌN VỊ TRÍ GHẾ</span></span>
                                                    <div class="sits-area hidden-xs">
                                                        <div class="ticketPrices">
                                                            <span class="label-vip">VIP</span>
                                                            <span class="label-normal">THƯỜNG</span>
                                                            <span class="label-your-selected">BẠN CHỌN</span>
                                                            <span class="label-booked">ĐÃ ĐẶT</span>
                                                        </div>
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
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="B" data-y="2">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="C" data-y="3">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="D" data-y="4">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="E" data-y="5">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="F" data-y="6">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="G" data-y="7">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="H" data-y="8">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="J" data-y="9">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="K" data-y="10">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="L" data-y="11">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="M" data-y="12">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="N" data-y="13">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="O" data-y="14">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="P" data-y="15">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>

                                                            <div class="sits__row" data-row="Q" data-y="16">
                                                                <span class="sits__place" data-place="" data-x="1" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="2" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="3" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="4" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="5" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="6" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="7" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="8" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="9" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="10" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="11" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="12" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="13" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="14" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="15" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="16" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="17" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="18" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="19" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="20" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="21" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="22" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="23" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="24" data-type="none">&nbsp;</span>
                                                                <span class="sits__place" data-place="" data-x="25" data-type="none">&nbsp;</span>
                                                            </div>
															
															<aside class="sits__line_right">
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

                                                        </div>
                                                    </div>
                                                </div>

                                                   
                                                <!-- end ghế -->
                                                <div id="buttons">
                                                
                                                	<html:submit property="action" value="step1" styleClass="back_Button"/>
                                                	<html:submit property="action" value="step3" styleClass="fastfood_Button"/>
                                                	<html:submit property="action" value="step4" styleClass="checkout_Button"/>
                                                    
                                                </div>

                                            </div>

                                            <!-- end chọn ghế -->
                                        </section>

                                    </html:form>
                                </div>
                                
                 			</section>

                            <a class="exit-off-canvas"></a> 
                        </section>  <!-- end main section -->
                    </div> 

                </div>
                <div id="fullVip" style="display: none;" title="Thông báo">Bạn không thể lựa chọn thêm ghế VIP !</div> 
                <div id="fullNormal" style="display: none;" title="Thông báo">Bạn không thể lựa chọn thêm ghế THƯỜNG !</div> 
                <div id="notSeatDialog" style="display: none;" title="Thông báo">Bạn phải chọn vị trí ghế ngồi trước khi tiếp tục!</div>
            </article>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

        <!-- Custom -->
        <script src="resources/js/jquery-ui.js"></script>
        <script src="resources/js/booking.js"></script>
        <script src="resources/js/hashtable.js"></script>
        <script src="resources/js/numberformatter.js"></script>


        <script type="text/javascript">
            $(document).ready(function () {
                init_Step2();
            });
        </script>

    </body>
</html>