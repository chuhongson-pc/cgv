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
<link rel="stylesheet" type="text/css" href="resources/styles/cgv.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/normalize.css" />
<link rel="stylesheet" type="text/css" href="resources/styles/font-awesome.css" />

<title>ĐĂNG NHẬP</title>
</head>
<body>
    <div class="page__body page-name">
        <div id="loginModal" class="user__login reveal-modal small open"
             data-dialog-name="loginFormModal">
            <ul>
                <li class="login__form">
                    <div id="loginForm">
                        <h6>ĐĂNG NHẬP</h6>
                        <html:form action="/login">
                            <dl>
                                <dt>
                                <label for="userEmail" class="necessary">Tên đăng nhập</label>
                                </dt>
                                <dd class="necessary-field">
                                     <html:text property="username" styleClass="focus"/>
                                     <label id="dis-username" class="parsley-error-list"></label>
                                </dd>
                                <dt>
                                <label for="userPassword" class="necessary">Mật khẩu</label>
                                </dt>
                                <dd class="necessary-field">
									<html:password property="password"/>
									<label id="dis-password" class="parsley-error-list"></label>
                                </dd>
                            </dl>
                            <!-- lỗi sai username password -->
                        
                        	<!-- login not valid -->
							<html:messages id="err_name" property="loginMessageError">
								<div class="parsley-error-list">
									<bean:write name="err_name"/>
								</div>
							</html:messages>
							
                            <div class="actions login">
                                <html:submit styleClass="button login-button" value="ĐĂNG NHẬP"/>
                            </div>
                        </html:form>

                    </div>

                </li>
                <li class="login__form">
                    <div id="loginForm">
                    <ul>
                		<li><html:link action="/home" styleClass="logo-login"></html:link></li>
                		<li><center><html:link action="/register.do?action=show" styleClass="regiser-link">ĐĂNG KÝ TÀI KHOẢN</html:link></center></li>
                	</ul>
                	</div>
                </li>
            </ul>
        </div>
        
        <!--end login form-->
        </div>
        <!-- jQuery 1.9.1-->
		<script src="resources/js/jquery-1.9.1.min.js"></script>
		<!-- Custom -->
		<script src="resources/js/login.js"></script>
    </body>
</html>