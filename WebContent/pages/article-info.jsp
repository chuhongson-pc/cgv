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
										
										<li class="hide-for-small active"><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>
										
										<li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
										</ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">CHI TIẾT TIN TỨC</h6>
                            </section>
                            
                            <section>
                            
                             <div class="articles_panel">
                             
	                             <div class="articles_title">
	                             	<bean:write property="currentNew.tieuDe" name="newsForm"/>
	                             </div>
                             	
                             	<div class="articles_time">
                             		<bean:write property="currentNew.thoiGian" name="newsForm"/>
                             	</div>
                             	
                             	<div class="articles_content">
                             		<html:hidden property="currentNew.noiDung" name="newsForm"/>
                             	</div>
                                                          
                             </div>                             
                            </section>

                            

                            <a class="exit-off-canvas"></a> 
                        </section> 
                    </div> 

                </div>
				
            </article>
            <jsp:include page="footer.jsp"></jsp:include>
        </div>

    </body>
    <script>
    
    $(document).ready(function(){
    	var content = $('input[name="currentNew.noiDung"]').val();
    	$('.articles_content').append(content);
    	
    });
    
    </script>
    
    </html>