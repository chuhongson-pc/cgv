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

    <title>THÔNG TIN PHIM - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">

		<input type="hidden" value="movie" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÔNG TIN PHIM</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">
                <html:form action="movie.html" method="post" enctype="multipart/form-data" styleId="movie_form">
                <bean:define id="movie_current" name="moviesAdminForm" property="currentMovie" />

                    <div class="panel panel-default movie-panel-banner">
                        
                            <div class="panel-heading">
                                <i class="fa fa-image fa-fw"></i> Banner
                            </div>
                            <img class="banner-movie" src="../<bean:write name="movie_current" property="banner"/>" title="<bean:write name="movie_current" property="tenPhim"/>"/>
                        
                    </div>



                    <div class="panel panel-default movie-panel-thumbnail">
                        <div class="panel-heading">
                            <i class="fa fa-image fa-fw"></i> Thumbnail
                        </div>
                        <img class="thumbnail-movie" src="../<bean:write name="movie_current" property="thumbnail"/>" title="<bean:write name="movie_current" property="tenPhim"/>"/>
                    </div>

                    <div class="panel panel-default panel-info-add-movie">
                        <div class="panel-heading">
                            Thông tin phim
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Tên phim</label>
                                        <html:text property="currentMovie.tenPhim" styleClass="form-control" />
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Đạo diễn</label>
                                        <html:text property="currentMovie.daoDien" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thể loại</label>
                                        <html:text property="currentMovie.theLoai" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Nhà SX</label>
                                        <html:text property="currentMovie.nhaSX" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Diễn viên</label>
                                        <html:text property="currentMovie.dienVien" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thời lượng (Phút)</label>
                                        <html:text property="currentMovie.thoiLuong" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thumbnail</label>
                                        <html:file property="thumbnailFile" size="50" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Banner</label>                                                                               
                                        <html:file property="bannerFile" size="50" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                </div>
                            </div>


                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Loại</label>
                                        <html:select property="currentMovie.type3d" styleClass="form-control">
                                            <html:option value="0">2D</html:option>
                                            <html:option value="1">2D/3D</html:option>
                                        </html:select>
                                    </div>
                                    <div class="form-group">
                                        <label>Ngôn ngữ</label>
                                        <html:select property="currentMovie.ngonNgu" styleClass="form-control">
                                            <html:option value="sub">Phụ đề</html:option>
                                            <html:option value="voice">Lồng tiếng</html:option>
                                        </html:select>
                                    </div>

                                    <div class="form-group">
                                        <label>Độ tuổi cho phép</label>
                                        <html:select property="currentMovie.doTuoiChoPhep" styleClass="form-control">
                                            <html:option value="G">G</html:option>
                                            <html:option value="16">16+</html:option>
                                        </html:select>
                                    </div>

                                    <div class="form-group">
                                        <label>Thời gian công chiếu</label>
                                        <html:text property="currentMovie.thoiGianCongChieu" styleClass="form-control date_picker"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Trailer (Youtube Link)</label>
                                        <html:text property="currentMovie.trailer" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Giới thiệu</label>
                                        <html:textarea property="currentMovie.moTa" styleClass="form-control"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="text-center">
                                        <html:submit property="action" value="update" styleClass="btn btn-edit"/>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                    </html:form>
                </div>
            </div>       
</div>
		<script src="dist/js/jquery.mockjax.js"></script>
        <script src="dist/js/jquery.validate.js"></script>
        <script src="dist/js/edit-movie-validate.js"></script>
</body>
</html>