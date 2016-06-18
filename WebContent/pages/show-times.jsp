<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="shortcut icon" href="../resources/images/favicon.ico" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="resources/styles/cgv.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css" />
<title>LỊCH CHIẾU</title>
</head>
<body>

	<div class="page__body page-name" >
	
		
		
		<article class="row page__content">

			<jsp:include page="header.jsp"></jsp:include>

			<div class="off-canvas-wrap">
				<div class="inner-wrap">

					<section class="main-section">
						<section class="menu">
							<nav class="top-bar main tab hide-for-small">
								<section class="top-bar-section">
									<ul>
										<li class="hide-for-small "><html:link action="/home"><i class="icon-home"></i></html:link></li>
										
										<li class="hide-for-small "><html:link action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>
										
										<li class="hide-for-small active"><html:link action="/showtimes.html">LỊCH CHIẾU</html:link></li>
										
										<li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>
										
										<li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
									</ul>
								</section>
							</nav>
							<h6 class="menu__title ">CHỌN SUẤT CHIẾU</h6>
						</section>
						<section class="showtime">
						<!-- chèn chọn ngày -->
						<section class="filter__group">
						    <div class="filters">
                                        <div class="filter show-date hide-for-small applied selected">
                                            <h6 class="filter__title">NGÀY CHIẾU</h6>
                                            <div class="filter__action">
                                             <ul class="filter__list-inline">
                                            	<logic:iterate id="date" name="showTimesForm" property="scheduledDates">
                                            	<html:hidden property="currentDate" name="showTimesForm"/>
                                            	<bean:define id="date_current" name="date" property="ngayThang"/>
                                                  <li class="filter__list__item">
                                                  		
                                                        <html:link action="/showtimes.html" paramId="date" paramName="date_current" styleClass="change-date">
                                                            <html:hidden name="date" property="coPhim"/>
                                                            <span><bean:write name="date" property="thu"/></span>
                                                            <span><bean:write name="date" property="ngay"/></span>
                                                        </html:link>
                                                    </li>
                                            
                                            	</logic:iterate>
                                              </ul>
                                            </div>
                                        </div>
                                    </div>
						</section>
						<!-- end chèn chọn ngày -->
							<section class="movie__session">
								<div class="movie__filter show-time">
									<!--head table-->
									<div class="filters hide-for-small">
										<div class="session__meta">
											<div class="filter movie multiSelect"
												data-filter-type="multiSelect" data-filter-name="movie">
												<div class="filter__action">
													<a class="filter__by" data-default-value="Movie">PHIM</a>
													<ul class="filter__list" style="display: none">
													
														<!-- vòng lặp ở đây -->
														
														<logic:iterate id="row_current" name="showTimesForm" property="showTimes">
														
															<bean:define id="movie_current" name="row_current" property="movieOfRow"/>
															<bean:define id="id_movie" name="movie_current" property="maPhim"/>
															<li class="filter__list__item" data-filter-value="<%=id_movie%>">
																<a><bean:write name="movie_current" property="tenPhim"/></a>
															</li>
															
														</logic:iterate>
														
														<!-- end vòng lặp tùy chọn phim -->
													</ul>
												</div>
											</div>
											<div class="cinema-experience">
												<div class="filter experience multiSelect"
													data-filter-type="multiSelect"
													data-filter-name="experience">
													<div class="filter__action">
														<a class="filter__by" data-default-value="Experience">PHÒNG CHIẾU</a>
														<ul class="filter__list" style="display: none">
															<!-- vòng lặp tùy chọn phòng -->
															
															<logic:iterate id="row_current" name="showTimesForm" property="showTimes">
															
																<bean:define id="room_current" name="row_current" property="roomOfRow"/>
																<bean:define id="room_id" name="room_current" property="maPhong"/>
																<li class="filter__list__item" data-filter-value="<%=room_id%>">
																	<a><bean:write name="room_current" property="tenPhong"/></a>
																</li>
																
															</logic:iterate>
															
															<!-- end vòng lặp tùy chọn phòng -->
														</ul>
													</div>
												</div>
											</div>
										</div>

										<div class="session__time">
											<div class="filter time" data-filter-type="multiSelect"
												data-filter-name="time">
												<div class="filter__action">
													<a class="filter__by" data-default-value="ShowTime">SUẤT
														CHIẾU</a>
													<ul class="filter__list" style="display: none">
														<li class="filter__list__item" data-filter-value="Noon">
															<a>SUẤT CHIẾU SỚM</a>
														</li>
														<li class="filter__list__item" data-filter-value="Matinee">
															<a>SUẤT CHIẾU MUỘN</a>
														</li>
														<li class="filter__list__item" data-filter-value="Evening">
															<a>3D</a>
														</li>

													</ul>
												</div>
											</div>
										</div>
									</div>
								</div>
								<!--row table-->
								
								<!-- vòng lặp row phim -->
								<logic:iterate id="row_current" name="showTimesForm" property="showTimes">
								<bean:define id="movie_current" name="row_current" property="movieOfRow"/>
								<bean:define id="room_current" name="row_current" property="roomOfRow"/>
								<div class="movie__show show show-time ">

									<div class="session__meta">
										<div class="movie">
											
											<div class="movie__name-language">
												<span class="filter-value movie__name" data-filter-value="<bean:write name="movie_current" property="maPhim"/>"><bean:write name="movie_current" property="tenPhim"/></span>
												<div class="language-time">

													<span class="movie__language"><bean:write name="movie_current" property="theLoai"/></span> <span>| <span>
															<span class="movie__time"><bean:write name="movie_current" property="thoiLuong"/> Phút</span>
													</span>
													</span>
												</div>
											</div>
										</div>

										<div class="cinema-experience">
											<div class="cinema">
												<span class="filter-value" data-filter-value="<bean:write name="room_current" property="maPhong"/>">
													<span class="screen-name"><bean:write name="room_current" property="tenPhong"/></span>
												</span>
											</div>
										</div>
									</div>

									<div class="session__time">
										<div class="time movie-name">
											<ul class="sessions">

												<!-- vòng lặp suất chiếu -->
												<logic:iterate id="session_current" name="row_current" property="sessionList">
												<bean:define id="id" name="session_current" property="maSuat"/>
												<html:link action="/booking.html?action=step1" paramName="id" paramId="session">
												
													<li class="session available">
														
														<html:hidden name="session_current" property="statusFull"/>
                                                        <html:hidden name="session_current" property="type3d"/>
														
															<span class="show_hour">
																<bean:write name="session_current" property="gioChieu"/>
															</span>
														
													</li>
												</html:link>
												</logic:iterate>
											</ul>

										</div>
									</div>

								</div>
								</logic:iterate>
								<!-- end vòng lặp row phim -->
								<!--end row-->
								
							</section>
						</section>
						<a class="exit-off-canvas"></a>
					</section>
				</div>
			</div>

			<!--open/close-->
			<!--login form-->
			


		</article>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>

	<!-- Custom -->
	<script src="resources/js/showtimes.js"></script>
	
</body>

</html>