<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<head>
	<base href="${host}/CGV/admin/">
    <meta charset="utf-8">
    
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ICHI - LOGIN ADMIN CP</title>

    <!-- Bootstrap Core CSS -->
    <link href="bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">


</head>

<body>

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">ICHI - ĐĂNG NHẬP QUẢN LÝ</h3>
                    </div>
                    <div class="panel-body">
                        <html:form action="/admincp.html">
                            <fieldset>
                            
                                <div class="form-group">
                                
                                    <input class="form-control" placeholder="Tài khoản" name="username" type="text" autofocus>
                                    
                                </div>
                                
                                <div class="form-group">
                                
                                    <input class="form-control" placeholder="Mật khẩu" name="password" type="password" value="">
                                    
                                </div>
                                 <logic:messagesPresent message="false">
	                                <div class="form-group">
		                                <div class="alert alert-danger">
			                              
		                                                <html:messages id="aMsg" message="false">
		                                                    <logic:present name="aMsg">
		                                                        <!-- Warnings-->
		                                                        <div class="errors">
		                                                            <bean:write name="aMsg" filter="false" />
		                                                        </div>
		                                                    </logic:present>
		                                                </html:messages>
		                                    
		                                </div>
	                                </div>
         						</logic:messagesPresent>
         						
                                <html:submit styleClass="btn btn-lg btn-success btn-block">ĐĂNG NHẬP</html:submit>
                                
                            </fieldset>
                        </html:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="bower_components/jquery/dist/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="bower_components/metisMenu/dist/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="dist/js/sb-admin-2.js"></script>
    


</body>

</html>