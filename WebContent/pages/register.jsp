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
        <link href="resources/styles/bootstrap.css" rel="stylesheet">
        <link href="resources/styles/datepicker.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="resources/styles/cgv.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/normalize.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/ion.calendar.css">
        <title>ĐĂNG KÝ THÀNH VIÊN</title>

    </head>
    <body>

        <div class="page__body page-name">
        
			
			
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
										<li class="hide-for-small active"><html:link action="/home"><i class="icon-home"></i></html:link></li>
										
										<li class="hide-for-small"><html:link action="/movies.html?action=showing">DANH SÁCH PHIM</html:link></li>
										
										<li class="hide-for-small "><html:link action="/showtimes.html">LỊCH CHIẾU</html:link></li>
										
										<li class="hide-for-small "><html:link action="/articles.html?action=toShow">TIN TỨC</html:link></li>
										
										<li class="hide-for-small "><a href="cinemas.html">RẠP CHIẾU</a></li>
									</ul>
								</section>
                                </nav>
                                <h6 class="menu__title ">ĐĂNG KÝ THÀNH VIÊN</h6>
                            </section>
                            <section class="user__registration">

                                <div id="registerContainer">
                                <html:form action="/register" styleId="register_form">
                                        <section>
                                            <h6>THÔNG TIN ĐĂNG NHẬP</h6><span class="border"></span>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="username" class="necessary">TÊN ĐĂNG NHẬP</label></dt>
                                                <dd class="necessary-field">

                                                    <html:text property="username" styleId="username"/>
                                                   
                                                    <div class="custom-error-message"></div>
                                                    <!-- login not valid -->
													<html:messages id="err_name" property="usernameMessageError">
  													<div class="custom-error-message error">
															<bean:write name="err_name"/>
													</div>
													</html:messages>

                                                </dd>
                                            </dl>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="password" class="necessary">MẬT KHẨU</label></dt>
                                                <dd class="necessary-field">

                                                    <html:password property="password" styleId="password"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="password_confirm" class="necessary">Xác nhận mật khẩu</label></dt>
                                                <dd class="necessary-field">

                                                    <html:password property="password_confirm" styleId="password_confirm"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                        </section>
                                        <section class="spacer">
                                            <h6>THÔNG TIN CÁ NHÂN</h6><span class="border"></span>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="fullname" class="necessary">HỌ VÀ TÊN</label></dt>
                                                <dd class="necessary-field">

                                                    <html:text property="fullname" styleId="fullname"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="cmnd" class="necessary">SỐ CMND</label></dt>
                                                <dd class="necessary-field">

                                                    <html:text property="cmnd" styleId="cmnd"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                        </section>


                                        <section>
                                            <dl class="input dob">
                                                <dt><label for="dob" class="necessary">Ngày sinh</label></dt>
                                                <dd class="necessary-field">

                                                    <html:text property="dob" styleId="dob"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="gender" class="necessary">Giới tính</label></dt>
                                                <dd class="gender">
                                                    <dl>
                                                        <dt></dt>
                                                        <dd>
                                                            <label class="capitalize radio checked" for="genderMale">
                                                                <span class="indicator"></span>
                                                                <span>Nam</span>
                                                                <html:radio property="gender" value="male" styleId="genderMale" ></html:radio>
                                                                
                                                            </label>
                                                        </dd>
                                                    </dl>
                                                    <dl>
                                                        <dd>
                                                            <label class="capitalize radio" for="genderFemale">
                                                                <span class="indicator"></span>
                                                                <span>Nữ</span>
                                                                <html:radio property="gender" value="female" styleId="genderFemale"></html:radio>
                                                                
                                                            </label>
                                                        </dd>
                                                    </dl>
                                                </dd>
                                            </dl>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="name" class="necessary">EMAIL</label></dt>
                                                <dd class="necessary-field">

                                                     <html:text property="email" styleId="email"/>
                                                     <div class="custom-error-message"></div>


                                                </dd>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="phonenumber" class="necessary">Số điện thoại</label></dt>
                                                <dd class="necessary-field">

                                                    <html:text property="phonenumber" styleId="phonenumber"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                        </section>

                                        <section>
                                            <dl class="input">
                                                <dt>
                                                <label for="address" class="necessary">ĐỊA CHỈ</label>
                                                </dt>
                                                <dd class="necessary-field">

                                                     <html:text property="address" styleId="address"/>
                                                    <div class="custom-error-message"></div>

                                                </dd>
                                            </dl>
                                        </section>

                                        <section class="spacer">
                                            <h6>ĐIỀU KHOẢN CAM KẾT</h6><span class="border"></span>
                                        </section>

                                        <section class="condition actions">
                                            <dl class="input">
                                                <dt class="necessary-field">
                                                <label class="checkbox parsley-success" for="terms-and-condition">
                                                    <span class="indicator"></span>
                                                    <span class="capitalize">Đồng ý với quy định của website.</span>
                                                     <input id="terms" name="terms" type="checkbox" class="parsley-validated">
                                                    
                                                </label>
                                               <ul id="parsley-agreement" class="parsley-error-list">
                                               </ul>
                                            </dl>

                                            <span class="border"></span>
                                        </section>
                                        <section class="actions">
                                            <center>
                                            	<html:submit property="action" value="register" styleId="signupsubmit" styleClass="button submit"/>
                                            </center>
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
        <!-- jQuery 1.9.1--> 
        <script src="resources/js/jquery-1.9.1.min.js"></script>
        <!-- Custom -->
        <script src="resources/js/lib/jquery.mockjax.js"></script>
        <script src="resources/js/jquery.validate.js"></script>
        <script src="resources/js/validate_custom.js"></script>
        <script src="resources/js/bootstrap-datepicker.js"></script>
        <script type="text/javascript">
        $(function () {
        	$('#dob').datepicker({
            	format: 'dd-mm-yyyy'
        	});
        });
        </script>
    </body>
</html>
