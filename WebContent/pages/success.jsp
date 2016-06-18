<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="../resources/images/favicon.ico" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <link rel="stylesheet" type="text/css" href="resources/styles/cgv.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/normalize.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css"/>

        <title>SUCCESS</title>

    </head>
    <body>

        <div class="page__body page-name">
        
			
			
            <article class="row page__content">
              
              <jsp:include page="header.jsp"></jsp:include>

                <div class="off-canvas-wrap">
                    <div class="inner-wrap">
                        <nav class="tab-bar show-for-small">
                            <section class="tab-bar-section">
                                <a class="logo small" href="/home.html"></a>
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
                                            <li class="hide-for-small "><a href="home.html"><i class="icon-home"></i></a></li>

                                            <li class="hide-for-small "><a href="movies.html?action=showing">DANH SÁCH PHIM</a></li>

                                            <li class="hide-for-small "><a href="showtimes.html">LỊCH CHIẾU</a></li>
                                            
                                            <li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>

                                            <li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
                                            
                                            <li class="hide-for-small active"><a>ĐẶT VÉ</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">GIAO DỊCH THÀNH CÔNG</h6>
    
                            </section>
                            <!-- ticket -->
                            
                            
                            <section class="final_ticket">
                                <div class="order">
                                    <img class="order__images" alt="" src="resources/images/tickets.png">
                                    <p class="order__title">CẢM ƠN QUÝ KHÁCH<br><span class="order__descript">Hẹn sớm gặp lại bạn tại CGV Cinemas.</span></p>
                                </div>     

                                <form id="register_form" data-validate="parsley" autocomplete="off">

                                    <section >
                                        <div class="ticket">
                                            <div class="ticket-position">
                                                <div class="ticket__indecator indecator--pre"><div class="indecator-text pre--text">online ticket</div> </div>
                                                <div class="ticket__inner">
													<bean:define id="session_current" name="bookingForm" property="sessionOfBooking"/>
													<bean:define id="movie_current" name="session_current" property="movieOfSession"/>
													<bean:define id="room_current" name="session_current" property="roomOfSession"/>
                                                    <div class="ticket-secondary">
                                                        <span class="ticket__item"><strong class="ticket__number">CGV ĐÀ NẴNG</strong></span>
                                                        <span class="ticket__item ticket__cinema">MÃ SỐ: <bean:write name="bookingForm" property="transactionCode"/></span>
                                                        <span class="ticket__item ticket__date"><bean:write name="session_current" property="ngayChieu"/></span>
                                                        <span class="ticket__item ticket__time"><bean:write name="session_current" property="gioChieu"/></span>
                                                        <span class="ticket__item"><span class="ticket__cinema"><bean:write name="room_current" property="tenPhong"/></span></span>                                                        
                                                        <span class="ticket__item ticket__price">TỔNG TIỀN: <strong class="ticket__cost"><bean:write name="bookingForm" property="tongCong"/></strong>VNĐ</span>
                                                    </div>

                                                    <div class="ticket-primery">
                                                        <span class="ticket__item ticket__item--primery ticket__film">Phim<br><strong class="ticket__movie"><bean:write name="movie_current" property="tenPhim"/></strong></span>
                                                        <span class="ticket__item ticket__item--primery">Ghế: <span class="ticket__place"><bean:write name="bookingForm" property="seatsYour"/></span></span>
                                                    </div>


                                                </div>
                                                <div class="ticket__indecator indecator--post"><div class="indecator-text post--text">online ticket</div></div>
                                            </div>
                                        </div>

                                    </section>

                                </form>


                            </section>
                            
                            <!-- end ticket -->
                        </section>
                        <a class="exit-off-canvas"></a> 
                    </div> 
                </div>

            </article>
			<jsp:include page="footer.jsp"></jsp:include>
        </div>
        <!--  
        <script src="resources/js/jquery-ui.js"></script>
		-->
		 <script src="resources/js/hashtable.js"></script>
        <script src="resources/js/numberformatter.js"></script>
		<script src="resources/js/success.js"></script>
    </body>
</html>
