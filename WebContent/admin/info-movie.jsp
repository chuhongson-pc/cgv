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

    <title>THÔNG TIN PHIM - ICHI ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="quanlykhachhang" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>

        <div id="page-wrapper">

                <div class="row">
                    <div class="col-lg-12">
                        <h5 class="title-page">THÔNG TIN PHIM</h5>
                    </div>

                </div>
                <div class="row panel my-panel-default">
                
				<bean:define id="movie_current" name="moviesAdminForm" property="currentMovie"/>
				
                    <div class="movie-detail-head">
                        <span class="movie-name-info"><i class="fa fa-film"></i>  <bean:write name="movie_current" property="tenPhim"/></span>
                        <div class="movie-detail-button-head">
                        <bean:define id="movieId" name="movie_current" property="maPhim"/>
                        <html:link action="movie.html?action=edit" paramName="movieId" paramId="movieId">
                            <input type="submit" name="action" value="edit" class="btn btn-update">
                        </html:link>
                        
                        <html:link action="movie.html?action=delete" paramName="movieId" paramId="movieId" onclick="return $(this).checkDecideDel();">
                            <input type="submit" name="action" value="delete" class="btn btn-delete">
                         </html:link>    
                        </div>
                    </div>

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


                    <div class="panel panel-info movie-panel-detail">
                        <div class="panel-heading">
                            <i class="fa fa-info-circle fa-fw"></i> Thông tin phim
                        </div>
                        <div class="panel-body">
                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Đạo diễn: </label><span> <bean:write name="movie_current" property="daoDien"/></span>

                            </div>
                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Thể loại: </label><span> <bean:write name="movie_current" property="theLoai"/></span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Nhà sản xuất:</label><span> <bean:write name="movie_current" property="nhaSX"/></span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Diễn viên:</label><span> <bean:write name="movie_current" property="dienVien"/></span>
                            </div>

                            <div class="form-group">
                            	<bean:define id="type" name="movie_current" property="type3d" type="java.lang.Integer"/>
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Loại:</label><span> <% if(type.intValue() == 1) out.write("2D/3D"); else if(type.intValue() == 0) out.write("2D"); %></span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Kiểu:</label><span> <bean:write name="movie_current" property="ngonNgu"/></span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Độ tuổi cho phép:</label><span> <bean:write name="movie_current" property="doTuoiChoPhep"/></span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Thời lượng:</label><span>  <bean:write name="movie_current" property="thoiLuong"/> phút</span>
                            </div>

                            <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Thời gian công chiếu:</label><span>  <bean:write name="movie_current" property="thoiGianCongChieu"/></span>
                            </div>
                            
                             <div class="form-group">
                                <label><i class="fa fa-chevron-circle-right fa-fw"></i>Trailer (Youtube Link):</label><span>  <bean:write name="movie_current" property="trailer"/></span>
                            </div>
                            
                        </div>
                    </div>


                    <div class="panel panel-info movie-panel-description">
                        <div class="panel-heading">
                            <i class="fa fa-info-circle fa-fw"></i>Lời giới thiệu
                        </div>
                        <div class="panel-body">
                            <p>
                                <bean:write name="movie_current" property="moTa"/>
                            </p>
                        </div>
                    </div>


                </div>
            </div>
        
</div>
<div id="confirmDelete" style="display: none;" title="Thao tác">Bạn có thực sự muốn xóa phim này ? Khi bạn xóa, tất cả các suất chiếu đã cài đặt với phim sẽ bị xóa theo.</div>
<script type="text/javascript">

$.fn.checkDecideDel = function() {
	var rg = window.confirm("Bạn thực sự muốn xóa phim này ?")
	if (rg != true) {
		return false;
	} else
		return true;
};

</script>
</body>
</html>