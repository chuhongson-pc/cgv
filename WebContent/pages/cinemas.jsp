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
        
        <title>GIỚI THIỆU CGV</title>
        <style type="text/css">
			.about-cinemas {
				background: #f8f8f8 url('../images/b1.png');
			}
            .steps__container {
                text-transform: none;
            }

            .std {
                padding: 20px 30px 20px 30px;
                color: #636363;
                font-family: Verdana,Arial,sans-serif;
                font-size: 14px;
                line-height: 24px;
            }

            .video {
                text-align: center;
            }

            .title-about-cgv {
                text-align: center;
                color: white;
                padding: 20px 30px;
                font-size: 35px;
                color: #E07306
            }

            .about-image-cgv {
                float: left;
                width: 100%;
            }

        </style>
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
										
										<li class="hide-for-small active"><a href="cinemas.html">RẠP CHIẾU</a></li>
										</ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">GIỚI THIỆU VỀ CGV</h6>
                            </section>
                            <!--content main-->

                            <section class="about-cinemas">
                                <!--thongtinphim-->
                                <div class="steps__container">
									
									<div class="std">
                                        <div class="title-about-cgv">
                                            GIỚI THIỆU RẠP CHIẾU PHIM
                                        </div>
                                        <div class="col2-set content-seperator">
                                            <div class="video">
                                                <p><iframe src="//www.youtube.com/embed/e1ZSLVTZ47M?rel=0&amp;wmode=transparent;" frameborder="0" width="725px" height="450px"></iframe></p>
                                            </div>

                                            <div class="std content-cms-about-us">
                                                <p>
                                                <strong>CJ CGV </strong> là đơn vị thuộc <strong>CJ Group</strong> , một trong những tập đoàn kinh tế đa ngành lớn nhất của Hàn Quốc có mặt ở 21 quốc gia trên thế giới.
                                                    <strong>CJ CGV</strong> có trụ sở chính đặt tại Seoul và hiện tại là cụm rạp chiếu phim lớn nhất Hàn Quốc với hơn 50% thị phần và 8 năm liên tiếp được người tiêu dùng công nhận là thương hiệu rạp chiếu phim tốt nhất.
                                                <strong>CGV </strong> đã mở ra quan niệm độc đáo về rạp chiếu phim với ý tưởng tổ hợp văn hóa - cultureplex nơi khán giả không chỉ đến xem phim tại các rạp chiếu phim tiêu chuẩn quốc tế mà còn có thể thưởng thức ẩm thực đa dạng, mua sắm các sản phẩm liên quan đến văn hóa giải trí, tạo nên những trải nghiệm Vượt Xa Điện Ảnh với những công nghệ xem phim tiên tiến như:
                                                </p>
                                                <ul>
                                                    <li><span>4DX </span>: rạp chiếu phim 4D thỏa mãn mọi giác quan;</li>
                                                    <li><span>ScreenX</span>: phòng chiếu phim 270 độ đầu tiên trên thế giới;</li>
                                                    <li><span>Starium</span>: phòng chiếu với màn hình lớn nhất trên thế giới được kỷ lục Guiness xác nhận.</li>
                                                </ul>
                                                <p><strong>CJ CGV</strong>đã có mặt ở nhiều quốc gia có nền điện ảnh phát triển nhất thế giới như: Hàn Quốc, Mỹ, Trung Quốc, Indonesia. Tại Việt Nam, thông qua việc tiếp quản MegaStar, CGV hiện đang sở hữu số lượng rạp chiếu phim và phòng chiếu lớn nhất cả nước với 16 cụm rạp chiếu phim trong đó có 78 phòng chiếu phim 2D và 32 phòng chiếu phim 3D.</p>
                                                <p>Đến năm 2017 <strong>CGV</strong> sẽ có tổng cộng 30 cụm rạp chiếu phim trên toàn quốc, giữ vững vị trí là nhà phát hành phim, rạp chiếu phim hàng đầu Việt Nam và là đơn vị đóng góp tích cực cho sự phát triển của nền điện ảnh Việt Nam thông qua những chương trình vì cộng đồng của CGV: Điện ảnh cho mọi người, lớp học làm phim Toto, tổ chức các liên hoan phim như: Liên Hoan Phim Việt Nam tại Hàn Quốc, tham gia các hoạt động phát triển tài năng làm phim trẻ như: Ycineff.</p>
                                                <p><strong> Bên cạnh đó, CGV còn là thương hiệu hoạt động trong lĩnh vực giải trí đứng đầu danh sách top 100 nơi làm việc tốt nhất Việt Nam theo báo cáo chuyên sâu từ Anphabe &amp; Nielsen. </strong></p>
                                            </div>

                                        </div>
                                    </div>
                            		
                                </div>

                            </section>

                            <a class="exit-off-canvas"></a> 
                        </section>  <!-- end main section -->
                    </div> 

                </div>
            </article>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>



    </body>
    </html>