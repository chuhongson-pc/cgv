<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<!-- jQuery 1.9.1-->
<script src="resources/js/jquery-1.9.1.min.js"></script>
<!-- Custom -->
<script src="resources/js/header.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        init_Login();
    });
</script>
<header class="hide-for-small">

            <a class="logo left" href="home.html"></a>

            <div class="right">
                <nav class="top-bar user" data-topbar="" data-options="is_hover: false">
                    <section class="top-bar-section">
                        <ul>
                            <!--<a class="sign-in" data-reveal-id="loginModal">Log In / Sign Up</a>-->
                            <li id="login-topbar" class="users__login current__user">

                            <logic:present name="user" scope="session">

                                <a class="allow-click user-top"> <span class="app-icon user"></span>
                                    <span class="user__name"><bean:write name="user"/></span>
                                </a>
                                <!-- dropdown list -->
                                <ul class="dropdown">
                                    <li class="title back js-generated">
                                        <h5>
                                            <a href="#">Back</a>
                                        </h5>
                                    </li>

                                    <li><html:link action="/profile.html?action=show">
                                        <span class="app-icon profile"></span>
                                        <span>Thông tin cá nhân</span></html:link>
                                    </li>

									<li>
                                    <html:link action="/changePassword.html?action=show">
                                        <span class="app-icon profile"></span>
                                        <span>Đổi mật khẩu</span>
                                    </html:link>
                                    </li>

                                    <li>
                                    <html:link action="/orderHistory.html?action=toShow">
                                        <span class="app-icon history"></span>
                                        <span>Lịch sử giao dịch</span>
                                    </html:link>
                                    </li>

                                    <li><html:link action="/logout.html"><span class="app-icon logout"></span><span>Logout</span></html:link></li>

                                </ul> <!-- danh sách dropdown menu -->
                            </logic:present>	
                            
                            </li> <!-- thẻ bên trong sau khi login -->

                        </ul><!-- khi bao kieu list -->
                    </section>
                </nav>



                <!--end đã đăng nhập-->
                <div class="social__links">
                    <ul class="right">
                        <li><a href="" target="_blank" class="icon-facebook icon-2"></a></li>
                        <li><a href="" target="_blank" class="icon-twitter icon-2"></a></li>
                    </ul>
                    <h6 class="social__message"></h6>
                </div>
            </div>
</header>

