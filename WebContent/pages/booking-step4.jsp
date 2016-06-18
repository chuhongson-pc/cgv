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
                                <h6 class="menu__title ">XÁC NHẬN THÔNG TIN GIAO DỊCH</h6>
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
                                                            
                                                            <!-- fastfood -->
                                                             <logic:iterate id="fastFood_current" name="bookingForm" property="fastFoodList">
                                                            	<tr id="<bean:write name="fastFood_current" property="maFF"/>" class="row_detail_selected">
                                                                    <td>
                                                                        <span class="name_of_ticket"><bean:write name="fastFood_current" property="tenFF"/></span>

                                                                    </td>
                                                                    <td>
                                                                        <html:hidden name="fastFood_current" property="giaTien"/>
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
                                                    	<html:hidden name="bookingForm" property="seatsYour"/>
                                                        GHẾ ĐÃ CHỌN: <span class="seat_label"></span>
                                                    </div>

                                                </div>

                                                <!-- ô ghê -->

                                                <div class="col-sm-12 col-lg-10 col-lg-offset-1">
                                                    <!-- thêm bảng chọn số vé hoăc combo vào đây -->
                                                    <span class="box-title"><span>THÔNG TIN THANH TOÁN</span></span>
													<div class="info_user_booking">
														<label>SỐ DƯ TÀI KHOẢN:&nbsp;</label><span class="account_balance"><bean:write name="bookingForm" property="soDuTK"/></span><label>&nbsp;VNĐ</label>
													</div>
                                                    <div id="showtime" class="box-content"><div id="hint">
                                                            <span id="lblAboveTicketDetail" class="TicketPageText">Bạn vui lòng kiểm tra lại danh sách ghế, đồ ăn mà bạn đã chọn. Để hoàn tất giao dịch bạn vui lòng bấm "Xác nhận". Xin cảm ơn !<br></span>
                                                        </div>


                                                        <div id="buttons">
                                                        	<html:hidden name="bookingForm" property="fastFoodTypeSelected"/>
                                                       		<html:hidden name="bookingForm" property="ticketTypeSelected"/>
                                                       		<html:hidden name="bookingForm" property="seatsYour"/>
                                                       		<html:hidden name="bookingForm" property="tongCong"/>
                                                       		
                                                    		<html:submit property="action" value="step3" styleClass="back_Button"/>
                                                			<html:submit property="action" value="step5" styleClass="confirm_Button"/>
   	
                                                        </div>
                                                    </div><!-- end thêm bảng chọn -->

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
                init_Step4();
            });
        </script>

    </body>
</html>