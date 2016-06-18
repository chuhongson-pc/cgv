<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
        <link rel="stylesheet" type="text/css" href="resources/styles/ion.calendar.css">
        <link rel="stylesheet" type="text/css" href="resources/styles/jquery.dataTables.css">
        <title>LỊCH SỬ GIAO DỊCH</title>

    </head>
    <body>
        <div class="page__body page-name" data-page-name="home">
            

            <article class="row page__content">

				<jsp:include page="header.jsp"/>
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
                                            <li class="hide-for-small active"><a href="home.html"><i class="icon-home"></i></a></li>

                                            <li class="hide-for-small"><a href="movies.html?action=showing">DANH SÁCH PHIM</a></li>

                                            <li class="hide-for-small "><a href="showtimes.html">LỊCH CHIẾU</a></li>
                                            
                                            <li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>

                                            <li class="hide-for-small "><a href="/articles.html?action=toShow">RẠP CHIẾU</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">QUẢN LÝ THÔNG TIN CÁ NHÂN</h6>
                                
                                <div class="filter__group grid-view">
                                    <dl class="movie__menu hide-for-small hide-for-medium">
										<dd class=""><html:link action="/profile.html?action=show">THÔNG TIN CÁ NHÂN</html:link></dd>
                                        <dd class=""><html:link action="/changePassword.html?action=show">ĐỔI MẬT KHẨU</html:link></dd>
                                        <dd class="active"><html:link action="/orderHistory.html">LỊCH SỬ GIAO DỊCH</html:link></dd>
                                    </dl>
                                </div>
                                
                            </section>
                            <section class="user__registration">
                                <div class="order_history_panel">
                                    
                                        <section>
                                            <h6>THÔNG TIN</h6><span class="border"></span>
                                        </section>
                                        
                                        <section>
                                        
                                        <!-- table here -->
                                        
                                        <table class="table table-striped table-bordered table-hover"
											id="orderHistory_Table">
											<thead>
												<tr>
													<th>Mã GD</th>
													<th>Phim</th>
													<th>Danh sách ghế</th>
													<th>Đồ ăn</th>
													<th>Thời gian GD</th>
													<th>Trạng thái</th>
												</tr>
											</thead>
										</table>
                                        
                                            
                                        </section>
                                </div>
                            </section>
                        </section>
                        <a class="exit-off-canvas"></a> 
                    </div> 
                </div>

            </article>
			<jsp:include page="footer.jsp"></jsp:include>
        </div>
	
	<script src="resources/js/jquery-1.9.1.min.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/order-history.js"></script>
	

    </body>
</html>