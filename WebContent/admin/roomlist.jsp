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
    <title>PHÒNG CHIẾU - CGV ĐÀ NẴNG</title>
	<base href="${host}/CGV/admin/">
</head>

<body>
<div id="wrapper">
		<input type="hidden" value="room" id="selected_tab_panel"/>

		<!-- thêm vào mục header quản lý -->
		<jsp:include page="header-admin.jsp"></jsp:include>
		<input type="hidden" id="tab" value="roomlist"/>
		
        <div id="page-wrapper">

            <div class="row">
                <div class="col-lg-12 page-header-movie-list">	
                    <h5 class="title-page">DANH SÁCH PHÒNG CHIẾU</h5>
                </div>

            </div>
            <div class="row panel my-panel-default">
            <div class="panel-body movie-list-panel">
                <div class="col-lg-12">
                    <div class="row">                      	
                       	<logic:iterate id="room_current" name="roomManagementForm" property="roomList">
                                <div class="col-lg-3 col-md-6">
                                    <div class="panel panel-green">
                                        <div class="panel-heading">
                                            <div class="row">
                                                <div class="col-xs-3">
                                                    <i class="glyphicon glyphicon-ok-circle fa-5x"></i>
                                                </div>
                                                <div class="col-xs-9 text-right">
                                                    <div class="room_name"><bean:write name="room_current" property="tenPhong"/></div>
                                                    <div>PHÒNG <bean:write name="room_current" property="loaiPhong"/></div>
                                                    
                                                    <html:hidden name="room_current" property="trangThai"/>
                                                    <div id="trangThaiPhong"></div>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                        <bean:define id="id_room" name="room_current" property="maPhong"/>
                                        <html:link action="/room.html?action=view" paramName="id_room" paramId="room">
                                            <div class="panel-footer">
                                                <span class="pull-left">Thông tin chi tiết...</span>
                                                <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                                                <div class="clearfix"></div>
                                            </div>
                                        </html:link>
                                    </div>
                                </div>
							</logic:iterate>
                                

 
                            <div class="col-lg-3 col-md-6">    
				                <html:link action="/room.html?action=toadd">
				                
                                    <div class="panel panel-green">
                                        <div class="panel-heading">
                                            <div class="row">						                      					                            
						                         <div class="text-center">                                            
						                              <i class="fa glyphicon fa-plus-circle fa-5x"></i>
						                          </div>
						                   	</div>
						                  </div>
						              
						
						                  <div class="panel-footer text-center">
						                        <span class="pull-left">THÊM PHÒNG</span>
						                        <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
						                        <div class="clearfix"></div>
						                   </div>
						
						              </div>       
						         
				                 </html:link>
                              </div>  
                                
                                
                            </div> <!-- end row -->
                        

                </div>
                </div>
            </div>
        </div>
        
</div>
 <script type="text/javascript">
        $(document).ready(function () {
        	$('.panel.panel-green').each(function(){
        		
                var trangThai = $(this).find('input[name="trangThai"]').val();
                if(trangThai == '1')
                {
                	$(this).find('#trangThaiPhong').append("Khả dụng");
                } else if (trangThai == '2') {
                	$(this).find('.glyphicon').removeClass('glyphicon-ok-circle').addClass('glyphicon-remove-sign');
               	 	$(this).removeClass('panel-green').addClass('panel-red');
               		$(this).find('#trangThaiPhong').append("Vô hiệu");
                }
                
        	});

         });
        </script>
</body>

</html>