<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>THÊM TIN TỨC - CGV ĐÀ NẴNG</title>
        <base href="${host}/CGV/admin/">
        <link rel="stylesheet" type="text/css" href="dist/css/jquery-ui.css"/>
        <link href="dist/css/summernote.css" rel="stylesheet">

    </head>

    <body>
        <div id="wrapper">
            <input type="hidden" value="quanlykhachhang" id="selected_tab_panel"/>

            <!-- thêm vào mục header quản lý -->
            <jsp:include page="header-admin.jsp"></jsp:include>

            <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÊM BÀI VIẾT</h5>

                    </div>

                </div>
                <div class="row panel my-panel-default">                    
                        <div class="row">                       
                            <div class="col-lg-12">
							<html:form action="news.html" method="post" enctype="multipart/form-data" styleId="add_News_Form">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        Thông tin 
                                    </div>

                                    <div class="panel-body">
                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>Tiêu đề</label>                            
                                                <html:text property="currentNews.tieuDe"  name="newsAdminForm" styleClass="form-control"/>
                                                <p class="help-block custom-error-message"></p>
                                            </div>
                                        </div>

                                        <div class="col-lg-12">
                                            <div class="form-group">
                                                <label>Ảnh thumbnail</label>                            
                                                <html:file property="thumbnailFile" size="50" styleClass="form-control"></html:file>
                                                <p class="help-block custom-error-message"></p>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Thời gian bắt đầu</label> 
                                                <html:text property="currentNews.batDau" name="newsAdminForm" styleClass="form-control date_picker"/>
                                                <p class="help-block custom-error-message"></p>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Thời gian kết thúc</label> 
                                                <html:text property="currentNews.ketThuc" name="newsAdminForm" styleClass="form-control date_picker"/>
                                                <p class="help-block custom-error-message"></p>
                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Loại</label> 
                                                <html:select styleClass="form-control" name="newsAdminForm"  property="currentNews.loaiTT">
                                                    <html:option value="N">Tin tức</html:option>
                                                    <html:option value="P">Tin KM</html:option>
                                                </html:select>

                                            </div>
                                        </div>

                                        <div class="col-lg-6">
                                            <div class="form-group">
                                                <label>Trạng thái</label> 
                                                <html:select styleClass="form-control" name="newsAdminForm" property="currentNews.trangThai">
                                                    <html:option value="1">Activated</html:option>
                                                    <html:option value="2">Blocked</html:option>
                                                </html:select>
                                                <p class="help-block custom-error-message"></p>
                                            </div>
                                        </div>

                                        <div class="text-center">
                                            <html:submit property="action" value="add" styleClass="btn btn-edit"/>
                                            <logic:messagesPresent message="true">
                                                <html:messages id="aMsg" message="true">
                                                    <logic:present name="aMsg">
                                                        <!-- Messages -->
                                                        <div class="messages">
                                                            <bean:write name="aMsg" filter="false" />
                                                        </div>
                                                    </logic:present>
                                                </html:messages>
                                            </logic:messagesPresent>

                                            <logic:messagesPresent message="false">
                                                <html:messages id="aMsg" message="false">
                                                    <logic:present name="aMsg">
                                                        <!-- Warnings-->
                                                        <div class="errors">
                                                            <bean:write name="aMsg" filter="false" />
                                                        </div>
                                                    </logic:present>
                                                </html:messages>
                                            </logic:messagesPresent>
                                        </div>

                                    </div>

                                </div>
                                <html:hidden property="currentNews.noiDung" name="newsAdminForm"/>
                                </html:form>
                            </div>
                        </div> <!-- end row first -->

                        <div class="row row-news">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    Nội dung
                                </div>
                                
                                <div class="panel-body">
                                    <div id="summernote"></div>
                                </div>
                            </div>
                        </div>


                    
                </div>       

            </div>

            <div id="insertDone" style="display: none;" title="Thao tác">Bài viết đã được cập nhật !</div>
            <div id="insertError" style="display: none;" title="Thao tác">Lỗi, vui lòng thử lại !</div>

            <script src="dist/js/jquery.validate.js"></script>
            <script src="dist/js/add-news-validate.js"></script>
            <script src="dist/js/dialog.js"></script>
            <script src="dist/js/summernote.min.js"></script>
            <script src="dist/js/add-news.js"></script>
            <script src="dist/js/dialog.js"></script>
</div>
    </body>
</html>