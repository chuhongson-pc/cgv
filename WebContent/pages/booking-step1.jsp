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
										
										<li class="hide-for-small"><html:link action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>
										
										<li class="hide-for-small "><html:link action="/showtimes.html">LỊCH CHIẾU</html:link></li>
										
										<li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>
										
										<li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
										
										<li class="hide-for-small active"><a>ĐẶT VÉ</a></li>
										</ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">CHỌN LOẠI VÉ</h6>
                            </section>
                            <!--content main-->

                            <section>
                                <!--thongtinphim-->
                                <div id="registerContainer" class="steps__container">
                                    <html:form action="booking.html">
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
                                                </div>


                                                <div class="col-sm-12 col-lg-10 col-lg-offset-1">
                                                    <!-- thêm bảng chọn số vé hoăc combo vào đây -->
                                                    <span class="box-title"><span>Vui lòng chọn vé</span></span>
													<div class="info_user_booking">
														<label>SỐ DƯ TÀI KHOẢN:&nbsp;</label><span class="account_balance"><bean:write name="bookingForm" property="soDuTK"/></span><label>&nbsp;VNĐ</label>
													</div>
                                                    <div id="showtime" class="box-content"><div id="hint">
                                                            <span id="lblAboveTicketDetail" class="TicketPageText"><br>Vui lòng chọn số lượng và loại vé mà bạn muốn mua. Bạn có thể mua tối đa 10 vé cho mỗi lần giao dịch.<br><br>Trên website chỉ liệt kê <b>vé dành cho người lớn</b>.<br><br>Khi nhận vé tại rạp, vui lòng cung cấp <b>Mã đặt vé</b> và CMND hoặc thẻ có ảnh nhận diện khác (Bằng lái xe, Thẻ HS-SV, ...) để nhân viên quầy vé kiểm tra thông tin.<br><br>Mọi thắc mắc xin vui lòng liên hệ: <a href="mailto:xxxxxx"><font color="#3BB9FF">supports@cgv.vn</font></a> để được giải đáp!</span>
                                                        </div>

                                                        <!--table -->

                                                        <table id="combo_Table">
                                                            <thead>
                                                                <tr>
                                                                    <th scope="col" id="loaiVe">LOẠI VÉ</th>
                                                                    <th scope="col" id="soLuong">SL</th>
                                                                    <th scope="col" id="gia">GIÁ</th>
                                                                    <th scope="col" id="tong">TỔNG</th>

                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                            <logic:iterate id="ticketType_current" name="session_current" property="ticketTypeSettingList">
                                                                <tr>
                                                                    <td>
                                                                        <div class="item_name">
                                                                        	
                                                                        	<bean:write name="ticketType_current" property="tenLoaiVe"/>
                                                                        </div>
                                                                        <div class="description">
                                                                        	<span><bean:write name="ticketType_current" property="moTa"/></span>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <!--hidden -->
                                                                        
                                                                        
																		<html:hidden name="ticketType_current" property="soGheThuong"/>
																		<html:hidden name="ticketType_current" property="soGheVip"/>
                                                                        <!-- end hidden -->
                                                                        <select id="<bean:write name="ticketType_current" property="maLoaiVe"/>" data-price="<bean:write name="ticketType_current" property="giaTien"/>" class="soLuongVe">
                                                                        	<option value="0">0</option>
                                                                            <option value="1">1</option>
                                                                            <option value="2">2</option>
                                                                            <option value="3">3</option>
                                                                            <option value="4">4</option>
                                                                            <option value="5">5</option>
                                                                            <option value="6">6</option>
                                                                            <option value="7">7</option>
                                                                            <option value="8">8</option>
                                                                            <option value="9">9</option>
                                                                            <option value="10">10</option>
                                                                        </select>
                                                                    </td>
                                                                    <td>
                                                                        <span class="price_ticket_str"><bean:write name="ticketType_current" property="giaTien"/></span> VNĐ
                                                                    </td>
                                                                    <td>
                                                                        <span id="<bean:write name="ticketType_current" property="maLoaiVe"/>" class="price_ticket_sum">0</span> VNĐ
                                                                    </td>
                                                                </tr>
                                                                </logic:iterate>
                                                                <!-- SUM -->
                                                                <tr>
                                                                    <td colspan="3">
                                                                        <b>Tổng cộng</b>
                                                                    </td>

                                                                    <td id="tongCong">
                                                                        <span id="tongCong" class="price_ticket_sum">0</span> VNĐ   
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>

                                                        <!-- end table -->

                                                        <div id="buttons">
                                                        
                                                        	<html:hidden name="bookingForm" property="ticketTypeSelected"/>
                                                        	<html:hidden name="bookingForm" property="soGheThuongDuocChon"/>
                                                            <html:hidden name="bookingForm" property="soGheVipDuocChon"/>
                                                            <html:hidden name="bookingForm" property="soDuTK"/>
                                                            <html:hidden name="bookingForm" property="tongCong"/>
                                                        	
                                                        	<html:submit property="action" value="step2" styleClass="chooseat_Button"/>
   	
                                                        </div>
                                                    </div>


                                                    <!-- end thêm bảng chọn -->
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
				<div id="overNumberSeatDialog" style="display: none;" title="Thông báo">Bạn chỉ có thể chọn tối đa 10 ghế !</div> 
            	<div id="notTicketDialog" style="display: none;" title="Thông báo">Bạn phải chọn loại vé để tiếp tục !</div> 
            	<div id="notEnoughMoney" style="display: none;" title="Thông báo">Tài khoản của bạn hiện tại không đủ tiền để thực hiện thanh toán !</div> 
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
                  init_Step1();
             });
        </script>

    </body>
    </html>