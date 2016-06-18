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
        <link rel="stylesheet" href="resources/styles/themes/default/default.css" type="text/css" media="screen" />
        <link rel="stylesheet" href="resources/styles/nivo-slider.css" type="text/css" media="screen" />
        <link href="resources/styles/style-home.css" rel="stylesheet" />

        <link rel="stylesheet" type="text/css" href="resources/styles/cgv.css" />
        <link rel="stylesheet" type="text/css" href="resources/styles/normalize.css" />
        <link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css" />
        <script type="text/javascript" src="resources/js/home.js"></script>

        <title>CVG</title>
    </head>
    <body>

        <div class="page__body page-name">

            <article class="row page__content">

                <jsp:include page="header.jsp"></jsp:include>

                <div class="off-canvas-wrap">
                    <div class="inner-wrap">
                 
                        <section class="main-section">
                            <section class="menu">
                                <nav class="top-bar main tab hide-for-small">
                                    <section class="top-bar-section">
                                        <ul>
                                            <li class="hide-for-small active"><html:link
                                                action="/home">
                                                <i class="icon-home"></i>
                                            </html:link></li>

                                            <li class="hide-for-small "><html:link
                                                action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>

                                            <li class="hide-for-small "><html:link
                                                action="/showtimes.html">LỊCH CHIẾU</html:link></li>
                                                
                                            <li class="hide-for-small "><html:link action="articles.html?action=toShow">TIN TỨC</html:link></li>

                                            <li class="hide-for-small "><a href="cinemas.html">RẠP
                                                    CHIẾU</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">HOME</h6>
                            </section>
                            <!--content main-->

                            <section>

                                <!-- home -->

                                <section class="cinemas home">
                                    <div class="carousel__container type-1">
                                        <div class="owl-carousel owl-theme cinemas__carousel home"
                                             style="opacity: 1; display: block;">
                                            <!---insert -->

                                            <div class="fluid_container">



                                                <div class="slider-wrapper theme-default">
                                                    <div id="slider" class="nivoSlider">
                                                        <logic:iterate id="currentPoster" name="homeForm" property="posterList">
                                                        
                                                        <a href="<bean:write property="link" name="currentPoster"/> ">
                                                        	<img src="<bean:write property="poster" name="currentPoster"/>" alt="<bean:write property="moTa" name="currentPoster"/>" title="" />
                                                        </a>
                                                        
                                                        </logic:iterate>
                                                    </div>

                                                </div>



                                            </div>
                                            <!-- .fluid_container -->
                                            <div style="clear: both; display: block; height: 100px"></div>

                                            <!--movie best -->

                                            <div class="movie-best">
                                                <div class="col-sm-10 col-sm-offset-1 movie-best__rating">TOP
                                                    5 PHIM</div>
                                                <div class="col-sm-12 change--col">
                                                    <logic:iterate id="movie_current" name="homeForm"
                                                                   property="topMovies">

                                                        <div class="movie-beta__item ">
                                                            <img
                                                                alt="<bean:write name="movie_current" property="tenPhim"/>"
                                                                src="<bean:write name="movie_current" property="thumbnail"/>" />
                                                            <span class="best-rate">5.0</span>

                                                            <ul class="movie-beta__info">
                                                                <li><span class="best-voted"><bean:write
                                                                            name="movie_current" property="tenPhim" /></span></li>
                                                                <li>
                                                                    <p class="movie__time">
                                                                <bean:write name="movie_current" property="thoiLuong" />
                                                                Phút
                                                                </p>
                                                                <p>
                                                                <bean:write name="movie_current" property="theLoai" />
                                                                </p>

                                                                </li>
                                                                <li class="last-block"><bean:define id="id_film"
                                                                                                    name="movie_current" property="maPhim" /> <html:link
                                                                                                    action="/movieinfo.html?action=show" paramName="id_film"
                                                                                                    paramId="movie" styleClass="slide__link">MORE</html:link>
                                                                </li>
                                                            </ul>
                                                        </div>


                                                    </logic:iterate>

                                                </div>
                                                <div class="col-sm-10 col-sm-offset-1 movie-best__check">ĐẶT
                                                    VÉ NGAY</div>
                                            </div>



                                            <!-- end movie best --->

                                            <!----end--->
                                        </div>
                                    </div>

                                </section>

                            </section>

                        </section>

                        <a class="exit-off-canvas"></a>
                    </div>
                </div>

            </article>
            <!-- footer -->
            <jsp:include page="footer.jsp"></jsp:include>


            <!-- footer -->
        </div>
        <script type="text/javascript" src="resources/js/jquery.nivo.slider.js"></script>
        <script type="text/javascript">
            $(window).load(function () {
                $('#slider').nivoSlider();
            });
        </script>

    </body>

</html>