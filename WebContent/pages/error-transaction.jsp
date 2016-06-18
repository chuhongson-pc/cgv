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

        <title>LỖI GIAO DỊCH</title>

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
                                            <li class="hide-for-small active"><a href="home.html"><i class="icon-home"></i></a></li>

                                            <li class="hide-for-small"><a href="movies.html?action=showing">DANH SÁCH PHIM</a></li>

                                            <li class="hide-for-small "><a href="showtimes.html">LỊCH CHIẾU</a></li>
                                            
                                            <li class="hide-for-small "><html:link action="articles.html?action=toShow">TIN TỨC</html:link></li>

                                            <li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">LỖI GIAO DỊCH</h6>
    
                            </section>
                            <!-- ticket -->
                            
                            
                            <section class="final_ticket">
                                <div class="order">
                                    <img class="order__images" alt="" src="resources/images/tickets.png">
                                    <p class="order__title">LỖI TRONG QUÁ TRÌNH GIAO DỊCH<br><span class="order__descript">Quý khách vui lòng thử lại. Xin cảm ơn !</span></p>
                                </div>     

                            </section>
                            
                            <!-- end ticket -->
                        </section>
                        <a class="exit-off-canvas"></a> 
                    </div> 
                </div>

            </article>
			<jsp:include page="footer.jsp"></jsp:include>
        </div>

    </body>
</html>
