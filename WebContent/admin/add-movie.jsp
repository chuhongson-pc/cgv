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

    <title>THÊM PHIM - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="movie2" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">
                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÊM PHIM</h5>

                    </div>
                    <div class="movie-detail-head">

                    </div>


                </div>
                <div class="row panel my-panel-default">
                <html:form action="movie.html" method="post" enctype="multipart/form-data" styleId="movie_form">
               
                    <div class="panel panel-default panel-info-add-movie">
                        <div class="panel-heading">
                            Thông tin phim
                        </div>
                        <div class="panel-body">
                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Tên phim</label>
                                        <html:text property="currentMovie.tenPhim" name="moviesAdminForm" styleClass="form-control" styleId="tenPhim"/>
                                    	<p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Đạo diễn</label>
                                        <html:text property="currentMovie.daoDien" name="moviesAdminForm" styleClass="form-control" styleId="daoDien"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thể loại</label>
                                        <html:text property="currentMovie.theLoai" name="moviesAdminForm" styleClass="form-control" styleId="theLoai"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Nhà SX</label>
                                        <html:text property="currentMovie.nhaSX" name="moviesAdminForm" styleClass="form-control"  styleId="nhaSX"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Diễn viên</label>
                                        <html:text property="currentMovie.dienVien" name="moviesAdminForm" styleClass="form-control" styleId="dienVien"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thời lượng (Phút)</label>
                                        <html:text property="currentMovie.thoiLuong" name="moviesAdminForm" styleClass="form-control" styleId="thoiLuong"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Thumbnail</label>
                                        <html:file property="thumbnailFile" size="50" name="moviesAdminForm" styleClass="form-control" styleId="thumbnail"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="form-group">
                                        <label>Banner</label>                                                                               
                                        <html:file property="bannerFile" size="50" name="moviesAdminForm" styleClass="form-control"  styleId="banner"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                </div>
                            </div>


                            <div class="col-lg-6">

                                <div class="panel-body">

                                    <div class="form-group">
                                        <label>Loại</label>
                                        <html:select property="currentMovie.type3d" name="moviesAdminForm" styleClass="form-control" styleId="typ3d">
                                            <html:option value="0">2D</html:option>
                                            <html:option value="1">2D/3D</html:option>
                                        </html:select>
                                    </div>
                                    <div class="form-group">
                                        <label>Ngôn ngữ</label>
                                        <html:select property="currentMovie.ngonNgu" name="moviesAdminForm" styleClass="form-control" styleId="ngonNgu">
                                            <html:option value="sub">Phụ đề</html:option>
                                            <html:option value="voice">Lồng tiếng</html:option>
                                        </html:select>
                                    </div>

                                    <div class="form-group">
                                        <label>Độ tuổi cho phép</label>
                                        <html:select property="currentMovie.doTuoiChoPhep" name="moviesAdminForm" styleClass="form-control"  styleId="doTuoiChoPhep">
                                            <html:option value="G">G</html:option>
                                            <html:option value="16">16+</html:option>
                                        </html:select>
                                    </div>

                                    <div class="form-group">
                                        <label>Thời gian công chiếu</label>
                                        <html:text property="currentMovie.thoiGianCongChieu" name="moviesAdminForm" styleClass="form-control date_picker"  styleId="thoiGianCongChieu"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Trailer (Youtube Link)</label>
                                        <html:text property="currentMovie.trailer" name="moviesAdminForm" styleClass="form-control" styleId="trailer"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label>Giới thiệu</label>
                                        <html:textarea property="currentMovie.moTa" name="moviesAdminForm" styleClass="form-control"  styleId="moTa"/>
                                        <p class="help-block custom-error-message"></p>
                                    </div>

                                    <div class="text-center">
                                        <html:submit property="action" value="add" styleClass="btn btn-add"/>
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
        <script src="dist/js/movie-validate.js"></script>
</body>
</html>