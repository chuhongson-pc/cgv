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
        <title>DANH SÁCH PHIM</title>
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
										
										<li class="hide-for-small active"><html:link action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>
										
										<li class="hide-for-small "><html:link action="/showtimes.html">LỊCH CHIẾU</html:link></li>
										
										<li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>
										
										<li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
									</ul>
								</section>
                                </nav>
                                <h6 class="menu__title ">CHỌN PHIM</h6>
                            </section>
                            <!--content main-->

                            <section>
                                <div class="filter__group grid-view">
                                    <dl class="movie__menu hide-for-small">
                                    <html:hidden property="listType" name="moviesForm"/>
                                        <dd class=""><html:link action="/movies.html?action=showing">PHIM ĐANG CHIẾU</html:link></dd>
                                        <dd class=""><html:link action="/movies.html?action=coming_soon">PHIM SẮP CHIẾU</html:link></dd>
                                    </dl>
                                    <!--filter-->
                                    <div class="filters">
                                        <div class="filter language multiSelect selected" data-filter-type="multiSelect" data-filter-name="type">
                                            <div class="filter__action">
                                                <a class="filter__by">LOẠI PHIM</a>
                                                <ul class="filter__list" style="display: none;">
                                                    <li class="filter__list__item" data-filter-value="3d">
                                                        <a>3D</a>
                                                    </li>
                                                    <li class="filter__list__item" data-filter-value="voice">
                                                        <a>LỒNG TIẾNG</a>
                                                    </li>
                                                    <li class="filter__list__item" data-filter-value="sub">
                                                        <a>PHỤ ĐỀ</a>
                                                    </li>
                                                    <li class="filter__list__item" data-filter-value="g">
                                                        <a>G</a>
                                                    </li>
                                                    <li class="filter__list__item" data-filter-value="16">
                                                        <a>16+</a>
                                                    </li>
       
                                                </ul>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <section class="movie__listing now-showing">
                                    <ul class="movie__grid">
                                        <!--  vòng lặp hiển thị phim -->
                                        <logic:iterate id="film_current" name="moviesForm" property="movies" indexId="indexId">
                                        
                                         <li class="movie__grid__item show">
                                            <div class="movie__item">
                                            	<!-- hidden tag -->
                                            	
                                            	<html:hidden name="film_current" property="doTuoiChoPhep"/>
                                            	<html:hidden name="film_current" property="ngonNgu"/>
                                            	<html:hidden name="film_current" property="type3d"/>
                                            	
                                            	
                                            	
												<bean:define id="id_film" name="film_current" property="maPhim"></bean:define>
                                                <html:link action="/movieinfo.html?action=show" paramName="id_film" paramId="movie">
                                                    <img src="<bean:write name="film_current" property="thumbnail"/>" title="<bean:write name="film_current" property="tenPhim"/>" alt="<bean:write name="film_current" property="tenPhim"/>">
                                                </html:link>
                                                
                                                <dl>
                                                    <dt title="<bean:write name="film_current" property="tenPhim"/>" class="movie__name">
                                                    <html:link action="/movieinfo.html?action=show" paramName="id_film" paramId="movie"><bean:write name="film_current" property="tenPhim"/></html:link>
                                                    </dt>
                                                    
                                                  	<dd class="bottom-thumbnail"></dd>
                                                    
                                                </dl>

                                            </div>
                                         </li>
                                         
                                        </logic:iterate>

                                           <!-- end vòng lặp hiển thị phim -->  

                                    </ul>
      
                                </section>
                            </section>

                            <a class="exit-off-canvas"></a>
                            </section> 
                    </div> 
                   
                </div>

            </article>
           
            <jsp:include page="footer.jsp"></jsp:include>
        </div>
 
	<!-- Custom -->
	<script src="resources/js/movies.js"></script>
	
    </body>

</html>