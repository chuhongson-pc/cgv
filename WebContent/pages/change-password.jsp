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
        <title>ĐỔI MẬT KHẨU</title>

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

                                            <li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
                                        </ul>
                                    </section>
                                </nav>
                                <h6 class="menu__title ">QUẢN LÝ THÔNG TIN CÁ NHÂN</h6>
                                
                                <div class="filter__group grid-view">
                                    <dl class="movie__menu hide-for-small hide-for-medium">
										<dd class=""><html:link action="/profile.html?action=show">THÔNG TIN CÁ NHÂN</html:link></dd>
                                        <dd class="active"><html:link action="/changePassword.html?action=show">ĐỔI MẬT KHẨU</html:link></dd>
                                        <dd class=""><html:link action="/orderHistory.html?action=toShow">LỊCH SỬ GIAO DỊCH</html:link></dd>
                                    </dl>
                                </div>
                                
                            </section>
                            <section class="user__registration">
                                <div id="registerContainer">
                                    <html:form action="/changePassword.html?action=update" styleId="register_form">
                                        <section>
                                            <h6>THÔNG TIN</h6><span class="border"></span>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="current_password" class="necessary">MẬT KHẨU HIỆN TẠI</label></dt>
                                                <dd class="necessary-field">

                                                    
													<html:password property="current_password" styleId="current_password"/>
													<!-- password not valid -->
													<html:messages id="err_name" property="passwordMessageError" >
  													<div class="custom-error-message error">
															<bean:write name="err_name"/>
														</div>
													</html:messages>
                                                    <div class="custom-error-message"></div>
                                                    <!-- login not valid -->


                                                </dd>
                                            </dl>
                                        </section>

                                        <section>
                                            <dl class="input">
                                                <dt><label for="password" class="necessary">MẬT KHẨU MỚI</label></dt>
                                                <dd class="necessary-field">

                                                    <html:password property="password" styleId="password"/>
                                                    <div class="custom-error-message"></div>


                                                </dd>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="password_confirm" class="necessary">XÁC NHẬN MẬT KHẨU</label></dt>
                                                <dd class="necessary-field">

                                                    <input type="password" name="password_confirm"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                        </section>

                                        <section class="actions">

                                            <html:submit styleId="signupsubmit" styleClass="button submit"/>
                                            <html:reset styleClass="button submit">Reset</html:reset>
                                            
                                            <html:messages id="aMsg" message="true">
        											<logic:present name="aMsg">
            											<!-- Messages -->
            												<div class="messages">
                												<bean:write name="aMsg" filter="false" />
            												</div>
        											</logic:present>
    										</html:messages>

                                        </section>
                                    </html:form>
                                </div>
                            </section>
                        </section>
                        <a class="exit-off-canvas"></a> 
                    </div> 
                </div>

            </article>
			<jsp:include page="footer.jsp"></jsp:include>
        </div>

        <script src="resources/js/lib/jquery.mockjax.js"></script>
        <script src="resources/js/jquery.validate.js"></script>
        <script src="resources/js/validate_custom.js"></script>

    </body>
</html>