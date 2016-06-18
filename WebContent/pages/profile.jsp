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

        <link href="resources/styles/datepicker.css" rel="stylesheet">       
        <link rel="stylesheet" type="text/css" href="resources/styles/cgv.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/normalize.css"/>
        <link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css"/>

        <title>THÔNG TIN CÁ NHÂN</title>

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
										<dd class="active"><html:link action="/profile.html?action=show">THÔNG TIN CÁ NHÂN</html:link></dd>
                                        <dd class=""><html:link action="/changePassword.html?action=show">ĐỔI MẬT KHẨU</html:link></dd>
                                        <dd class=""><html:link action="/orderHistory.html?action=toShow">LỊCH SỬ GIAO DỊCH</html:link></dd>
                                    </dl>
                                </div>
                                
                            </section>
                            <section class="user__registration">

                                <div id="registerContainer">
                                <html:form action="/profile.html?action=update" styleId="register_form">
                                        <section>
                                            <h6>THÔNG TIN ĐĂNG NHẬP</h6><span class="border"></span>
                                        </section>
                                        <section>
                                            <dl class="input">
                                                <dt><label for="username">TÊN ĐĂNG NHẬP: <bean:write name="profileForm" property="username"/></label></dt>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="username">SỐ DƯ TÀI KHOẢN: <bean:write name="profileForm" property="soDuTaiKhoan"/> VNĐ</label></dt>
                                            </dl>
                                            <dl class="input">
                                                <dt><label for="username">ĐIỂM TÍCH LŨY: <bean:write name="profileForm" property="diemTichLuy"/> điểm</label></dt>
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
                                            	<html:hidden property="gender" styleId="hidden_gender"/>
                                                <dt><label for="gender" class="necessary">Giới tính</label></dt>
                                                <dd class="gender">
                                                    <dl>
                                                        <dt></dt>
                                                        <dd>
                                                            <label class="capitalize radio" for="genderMale">
                                                                <span class="indicator"></span>
                                                                <span>Nam</span>
                                                                <html:radio property="gender" value="male" styleId="genderMale"></html:radio>
                                                                
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
                                        

                                        <section class="actions">
                                            
                                            	<html:submit property="action" value="Cập nhật" styleId="signupsubmit" styleClass="button submit"/>
                                            	
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
