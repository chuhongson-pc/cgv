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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<link rel="stylesheet" type="text/css" href="resources/styles/cgv.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="resources/styles/font-awesome.css" />
<title>THÔNG TIN PHIM</title>
</head>
<body>

	<div class="page__body page-name" data-page-name="show-times">

		<article class="row page__content">

			<jsp:include page="header.jsp"></jsp:include>

			<div class="off-canvas-wrap">
				<div class="inner-wrap">

					<section class="main-section">
						<section class="menu">
							<nav class="top-bar main tab hide-for-small">
								<section class="top-bar-section">
									<ul>
										<li class="hide-for-small "><html:link action="/home">
												<i class="icon-home"></i>
											</html:link></li>

										<li class="hide-for-small active"><html:link
												action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>

										<li class="hide-for-small "><html:link
												action="/showtimes.html">LỊCH CHIẾU</html:link></li>

										<li class="hide-for-small "><html:link
												action="/articles.html?action=toShow">TIN TỨC</html:link></li>

										<li class="hide-for-small "><a href="cinemas.html">RẠP
												CHIẾU</a></li>
									</ul>
								</section>
							</nav>
							<h6 class="menu__title ">THÔNG TIN PHIM</h6>
						</section>
						<!--content main-->
						<section class="movie__detail">
							<bean:define id="movie_current" name="movieInfoForm"
								property="movieCurrent"></bean:define>
							<section class="movie__info">
								<section class="movie__data">
									<div class="movie__title">
										<div class="movie__name-language">
											<h4 class="left">
												<bean:write name="movie_current" property="tenPhim" />
											</h4>

										</div>
										<div class="experience hide-for-small">
											<dl>
												<dd>
													<html:hidden name="movie_current" property="doTuoiChoPhep" />
													<html:hidden name="movie_current" property="ngonNgu" />
													<html:hidden name="movie_current" property="type3d" />
													<!--  
                                                        <span class="movie-16-big" data-filter-value="16">G</span>
                                                        <span class="movie-g-big" data-filter-value="g">16+</span>
                                                        <span class="movie-3d-big" data-filter-value="3d">3D</span>
                                                        <span class="movie-sub-big" data-filter-value="sub">PHỤ ĐỀ</span>
                                                        <span class="movie-voice-big" data-filter-value="voice">LỒNG TIẾNG</span>
-->
												</dd>
											</dl>
										</div>
									</div>
								</section>
							</section>

							
							
									<section class="movie__poster">
										<img
											src="<bean:write name="movie_current" property="banner"/>"
											title="<bean:write name="movie_current" property="tenPhim"/>"
											alt="<bean:write name="movie_current" property="tenPhim"/>" />
									</section>
								
							
									<section class="movie-xtra-info">
										<section class="synopis-schedule">
											<div class="movie__meta-data">
												<div class="movie__additional-info synopsis">
													<div class="movie__additional-info-title">Mô tả</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="moTa" />
													</div>
												</div>
												<div class="movie__additional-info">
													<div class="movie__additional-info-title">Thể loại</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="theLoai" />
													</div>
												</div>
												<div class="movie__additional-info">
													<div class="movie__additional-info-title">Đạo diễn</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="daoDien" />
													</div>
												</div>
												<div class="movie__additional-info">
													<div class="movie__additional-info-title">Diễn viên</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="dienVien" />
													</div>
												</div>
												<div class="movie__additional-info">
													<div class="movie__additional-info-title">Nhà sản
														xuất</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="nhaSX" />
													</div>
												</div>
												<div class="movie__additional-info">
													<div class="movie__additional-info-title">Thời lượng</div>
													<div class="movie__additional-info-description">
														<bean:write name="movie_current" property="thoiLuong" />
														Phút
													</div>
												</div>

											</div>
											<div>
												<form>
													<!-- hidden type -->
													<html:hidden name="movieInfoForm" property="currentDate" />
													<section class="filter__group">
														<div class="filters date-ticket">
															<!--chọn ngày-->
															<div class="filter show-date hide-for-small">
																<h6 class="filter__title">NGÀY</h6>

																<div class="filter__action">
																	<ul class="filter__list-inline">
																		<logic:iterate id="date" name="movieInfoForm"
																			property="scheduledDates">
																			<li class="filter__list__item available"><bean:define
																					id="date_current" name="date" property="ngayThang"></bean:define>
																				<html:link action="/movieinfo.html?action=to"
																					paramName="date_current" paramId="date">
																					<html:hidden name="date" property="coPhim" />
																					<span><bean:write name="date" property="thu" /></span>
																					<span><bean:write name="date"
																							property="ngay" /></span>
																				</html:link></li>
																		</logic:iterate>

																	</ul>
																</div>
															</div>

														</div>
													</section>
													<section class="movie__session movie-name">
														<div class="movie__filter movie-detail">

															<div class="filters hide-for-small">
																<div class="session__meta__info">
																	<div class="cinema-experience">
																		<div class="filter cinema"
																			data-filter-type="multiSelect"
																			data-filter-name="cinema">
																			<div class="filter__action">
																				<a class="filter__by" data-default-value="Cinema">PHÒNG</a>

																			</div>
																		</div>

																	</div>
																</div>

																<div class="session__time__info">
																	<div class="filter time" data-filter-type="multiSelect"
																		data-filter-name="time">
																		<div class="filter__action">
																			<a class="filter__by" data-default-value="ShowTime">SUẤT
																				CHIẾU</a>

																		</div>
																	</div>
																</div>
															</div>
															<logic:iterate id="row_ThongTinPhim" name="movieInfoForm"
																property="showTimeRows">
																<bean:define id="room_current" name="row_ThongTinPhim"
																	property="roomOfRow" />
																<div class="movie__show show-time show movie-detail">

																	<div class="session__meta__info">
																		<div class="cinema-experience">
																			<div class="cinema">
																				<span class="filter-value"> <span
																					class="screen-name"><bean:write
																							name="room_current" property="tenPhong" /></span>
																				</span>
																			</div>

																		</div>
																	</div>

																	<div class="session__time__info">
																		<div class="time">
																			<ul class="sessions">

																				<!-- vòng lặp in suất -->
																				<logic:iterate id="session_current"
																					name="row_ThongTinPhim" property="sessionList">
																					<bean:define id="id_suat" name="session_current"
																						property="maSuat" />
																					<html:link action="/booking.html?action=step1"
																						paramName="id_suat" paramId="session">
																						<li class="session available"><html:hidden
																								name="session_current" property="statusFull" />
																							<html:hidden name="session_current"
																								property="type3d" /> <span class="filter-value">
																								<span class="show_hour"><bean:write
																										name="session_current" property="gioChieu" /></span>
																						</span></li>
																					</html:link>
																				</logic:iterate>
																			</ul>

																		</div>
																	</div>

																</div>
															</logic:iterate>
														</div>
													</section>
												</form>
											</div>
										</section>
										<section class="poster-trailer-review image__source">
											<div class="trailers">
												<h5>Trailers &amp; Clips</h5>
												<div class="owl-carousel owl-theme"
													style="opacity: 1; display: block;">
													<!--  
													<iframe width="305" height="200" src="<bean:write name="movie_current" property="trailer"/>" frameborder="0" allowfullscreen></iframe>
													-->
													<iframe width="305" height="200"
														src="<bean:write name="movie_current" property="trailer"/>"></iframe>
												</div>
											</div>
										</section>
										<!-- end trailer -->
										<div class="clear"></div>
									</section>
								
							<!-- comment box -->
							<div class="fb_comment"></div>
						</section>

					</section>
				</div>

			</div>

		</article>
		<jsp:include page="footer.jsp"></jsp:include>
	</div>


	<!-- Custom -->
	<script src="resources/js/movie_info.js"></script>
	
</body>

</html>


