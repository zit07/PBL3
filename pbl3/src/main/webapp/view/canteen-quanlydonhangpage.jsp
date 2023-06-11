<%@page import="javax.servlet.jsp.tagext.Tag"%>
<%@page import="datdocanteen.Model.BankModel"%>
<%@page import="datdocanteen.Model.LoaithucanModel"%>
<%@page import="datdocanteen.Model.DiachiModel"%>
<%@page import="datdocanteen.Model.LichsutimkiemModel"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="datdocanteen.Model.CanteenModel"%>
<%@page import="datdocanteen.Model.GiohoatdongModel"%> 
<%@page import="datdocanteen.Model.MonAnModel"%> 
<%@page import="datdocanteen.Model.HoadonModel"%>
<%@page import="datdocanteen.Model.HoadonchitietModel"%>
<%	
	CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
	List<LichsutimkiemModel> searchHistory = (List<LichsutimkiemModel>)session.getAttribute("searchHistory");  
	List<GiohoatdongModel> giohoatdongList = (List<GiohoatdongModel>) session.getAttribute("listGiohoatdong");
	DiachiModel diachi = (DiachiModel) session.getAttribute("diachi");
	List<HoadonModel> hoadons = (List<HoadonModel>) session.getAttribute("hoadons");
	List<List<HoadonchitietModel>> hdctList = (List<List<HoadonchitietModel>>) session.getAttribute("hoadonchitiets");
	BankModel bank = (BankModel) session.getAttribute("bank");
	String tag = (String) session.getAttribute("tag");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt đồ ăn cantin</title>
    <link rel="icon" href="./assets/img/logo/logo.png" type="image/x-icon">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="./assets/css/base.css">
    <link rel="stylesheet" href="./assets/css/style.css">
    <link rel="stylesheet" href="./assets/css/grid.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
	<!-- main -->
	<c:if test="${canteen!=null }">
		<div class="app">
	        <!-- header -->
	        <header class="header">
	            <div class="grid wide">
	                <!-- navbar -->
	                <nav class="header__navbar"> 
	                    <ul class="header__nav-list">
	                        <li class="header__nav-item header__show-note">
	                            <a href="#" class="header__nav-item-link">
	                                <i class="header__nav-icon far fa-bell"></i>
	                                Thông báo
	                            </a>
	                            <!-- thông báo -->
	                            <div class="header__notifi">
	                                <header class="header__notifi-header">
	                                    <h3>Thông Báo Mới Nhận</h3>
	                                </header>
	                                <ul class="header__notifi-list">
	                                    
	                                </ul>
	                                <footer class="header__notifi-footer">
	                                    <a href="#" class="header__notifi-footer-btn">Xem tất cả</a>
	                                </footer>
	                            </div>
	                        </li>
	                        <li class="header__nav-item">
	                            <a href="#" class="header__nav-item-link">
	                                <i class="header__nav-icon far fa-question-circle"></i>
	                                Hỗ trợ
	                            </a>
	                        </li>
								<li class="header__nav-item header__nav-user">
			                    	<c:if test="${canteen.getAvatar()==null}">
		                            	<img src="./assets/img/avatarDefault.jpg" class="header__nav-user-avt">
		                            </c:if>
		                            <c:if test="${canteen.getAvatar()!=null}">
		                           		<img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(canteen.getAvatar()) %>" class="header__nav-user-avt"/>
		                            </c:if>
			                    	<a href="#" class="header__nav-item-link header__nav-item--bold">${canteen.getTen()}</a>
									<ul class="header__nav-user-menu">
										<li class="header__nav-user-item" id="info-link">
											<a href="">Tài khoản của tôi</a>
										</li>
										<li class="header__nav-user-item" id="changeinfoBank-link">
											<a href="">Sửa thông tin ngân hàng</a>
										</li>
										<li class="header__nav-user-item" id="changeTimeOpen-link">
											<a href="">Sửa giờ hoạt động</a>
										</li>
										<li class="header__nav-user-item" id="changepass-link">
											<a href="./Changepassword">Đổi mật khẩu</a>
										</li>
										<li class="header__nav-user-item" id="changepin-link">
											<a href="./ChangePin">Đổi mã xác thực</a></li>
										</li>
										<li class="header__nav-user-item">
											<a href="./Logout">Đăng xuất</a>
										</li>
									</ul> 
			                	</li> 
	                    </ul>
	                </nav>
	                <!-- search -->
	                <div class="header__contain">
	                    <div class="header__logo">
	                        <a href="./" class="header__logo-link">
	                            <img src="./assets/img/logo/logo.png" class="header__logo-img">
	                        </a>
	                    </div>
	                    
	                    <form class="header__search" method="POST" action="./search">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="${Search}">
                            <div class="header__search-history">
                                <!-- History Search -->
                                <ul class="header__search-history-list">
	                            	<c:forEach items="${searchHistory}" var="lichsu">
								    	<li class="header__search-history-item">
	                                    	<a class="header__search-history-item-link" href="./search?txtSearch=${lichsu.getNoidung()}" >${lichsu.getNoidung()}</a>
	                                    	<a class="btn-del-history" href="./delHistorySearch?id=${lichsu.getID_lichsutimkiem()}">
                                            	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                                	<path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                                </svg>
                                             </a>
	                                    </li>
									</c:forEach> 
	                        	</ul>
                            </div>
                        </div>
                        <button class="btn header__search-btn" type="submit">
                            <i class="header__search-btn-icon fas fa-search"></i>
                        </button>
                    </form>
	
	                    <div class="btn btn--primary btn-while" id="add-product-link">
	                        <a class="btn-add-product" href="#">Thêm món ăn</a>
	                    </div>
	                </div>
	            </div>
	        </header>
	        <!-- container -->
	        <div class="container">
	            <div class="grid wide">
	                <div class="row sm-gutter">
						<div class="col l-2">
							<!-- category -->
							<nav class="category" id="category">
								<h3 class="category-heading">
									<i class="category-heading-icon fas fa-list-ul"></i> HOME
								</h3>
								<div class="navbar">
									<a class="navbar-link" href="./">Quản lý món ăn</a> 
									<a class="navbar-link" href="./loaithucan">Quản lý loại thức ăn</a>
									<a class="navbar-link choose" href="./quanlydonhang">Quản lý đơn hàng</a>
									<a class="navbar-link" href="./doanhthungay">Xem doanh thu</a> 
								</div>
							</nav>
						</div>
						<div class="col l-10">
	                        <!-- home filter -->
							<div class="home-filter">
								<div class="home-filter-control nav-bar__filter">
									<div class="">
										<a class="btn home-filter-btn ${tag == 'choxacnhan' ? 'btn--primary':''}" href="./donchoxacnhan">Đơn chờ xác nhận</a>
										<a class="btn home-filter-btn ${tag == 'dangchuanbi' ? 'btn--primary':''}" href="./dondangchuanbi">Đơn đang chuẩn bị</a>
										<a class="btn home-filter-btn ${tag == 'dahoantat' ? 'btn--primary':''}" href="./dondahoantat">Đơn đã hoàn tất</a>
										<a class="btn home-filter-btn" href="./timkiemhoadon">Tìm kiếm đơn hàng</a>
									</div> 
									<div class="">
									<% if (tag.equals("choxacnhan")) { %>
										<form action="./donchoxacnhan">
									<% } else if (tag.equals("dangchuanbi")) { %>
										<form action="./dondangchuanbi">
									<% } else if (tag.equals("dahoantat")) { %>
										<form action="./dondahoantat">
									<% } %>
											<input type="date" class="input_date" id="date_input" name="ngay" value="${ngayloc}">
											<button type="submit" class="btn btn--primary">Lọc</button>
										</form>
									</div>
								</div>
							</div>
	                        <!-- home product -->
	                        
	                        	<c:set var="hoadons" value="<%=hoadons%>"/>
	                        	<c:if test="${tag.equals('choxacnhan')}">
				                    <div class="home-product">
				                        <h3 class="auth-form__heading table-list-user">ĐƠN HÀNG CHỜ XÁC NHẬN ĐÃ THANH TOÁN</h3>
					                    <div class="list-user">
						                    <table border ="1" width ="100%">
							                    <tr>
							                    	<th>Mã đơn hàng</th>
					                              	<th>Ngày tạo</th>
						                         	<th>Trạng thái</th>
					                            	<th>Tổng tiền</th>
						                          	<th>Chi tiết</th>
						                          	<th>Xác nhận</th>
					                            </tr>
					                            <c:forEach items="${hoadons}" var="hoadon"> 
									                <tr>
										                <td>${hoadon.getMadon()}</td>
								                        <td>${hoadon.getNgaytao()}</td>
								                        <td>${hoadon.getTrangthai()}</td>
									                    <td>${String.format("%.3f", hoadon.getTongtien())} VNĐ</td>
										        		<td><a class="btn btn--primary home-product-btn" name="link-cart-detail" id="${hoadon.getMadon()}">Xem</a></td>
									            		<td><a class="btn btn--primary home-product-btn delete-product" href="./Xulyhoadon?id_hoadon=${hoadon.getID_hoadon()}&tag=dangchuanbi" >Đã nhận được tiền</a></td>
								                    </tr>
								                </c:forEach>
						                    </table>
					                    </div>
				                    </div>
				                </c:if>
				                <c:if test="${tag.equals('dangchuanbi')}">
				                    <div class="home-product">
					                    <h3 class="auth-form__heading table-list-user">ĐƠN HÀNG ĐANG CHUẨN BỊ</h3>
					                    <div class="list-user">
						                    <table border ="1" width ="100%">
							                    <tr>
							                    	<th>Mã đơn hàng</th>
					                              	<th>Ngày tạo</th>
						                         	<th>Trạng thái</th>
					                            	<th>Tổng tiền</th>
						                          	<th>Chi tiết</th>
						                          	<th>Chuẩn bị món </th>
					                            </tr>
					                            <c:forEach items="${hoadons}" var="hoadon"> 
									                <c:if test="${hoadon.getTrangthai().equals('đang chuẩn bị món') }">
											             <tr>
										                    <td>${hoadon.getMadon()}</td>
								                            <td>${hoadon.getNgaytao()}</td>
								                            <td>${hoadon.getTrangthai()}</td>
										                    <td>${String.format("%.3f", hoadon.getTongtien())} VNĐ</td>
										            		<td><a class="btn btn--primary home-product-btn" name="link-cart-detail" id="${hoadon.getMadon()}">Xem</a></td>
										            		<td><a class="btn btn--primary home-product-btn delete-product" href="./Xulyhoadon?id_hoadon=${hoadon.getID_hoadon()}&tag=daxong" >Đã xong</a></td>
									                    </tr>
									                </c:if>
								                </c:forEach>
						                    </table>
					                    </div>
				                    </div>
				                </c:if>
				                <c:if test="${tag.equals('dahoantat')}">
				                    <div class="home-product">
					                    <h3 class="auth-form__heading table-list-user">ĐƠN HÀNG ĐÃ HOÀN THÀNH</h3>
					                    <div class="list-user">
						                    <table border ="1" width ="100%">
							                    <tr>
							                    	<th>Mã đơn hàng</th>
					                              	<th>Ngày tạo</th>
						                         	<th>Trạng thái</th>
					                            	<th>Tổng tiền</th>
						                          	<th>Chi tiết</th>
					                            </tr>
					                            <c:forEach items="${hoadons}" var="hoadon"> 
									                <c:if test="${hoadon.getTrangthai().equals('đã chuẩn bị xong') }">
									                    <tr>
										                    <td>${hoadon.getMadon()}</td>
								                            <td>${hoadon.getNgaytao()}</td>
								                            <td>${hoadon.getTrangthai()}</td>
										                    <td>${String.format("%.3f", hoadon.getTongtien())} VNĐ</td>
										            		<td><a class="btn btn--primary home-product-btn" name="link-cart-detail" id="${hoadon.getMadon()}">Xem</a></td>
									                    </tr>
									                </c:if>
								                </c:forEach>
						                    </table>
					                    </div>
				                    </div>
				                </c:if>
	                        </div>   
	                    </div>
	                </div>
	            </div>
	        </div>
			<footer class="footer">
            <!-- main footer -->
	            <div class="main-footer">
	                <div class="grid wide">
	                    <!-- copyright -->
	                    <div class="row">
	                        <div class="grid">
	                            <p class="copyright-title">
	                                © 2023 CanteenFood copyright - Công ty TNHH TST - Product by TST
	                            </p>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <!-- other footer -->
	            <div class="other-footer">
	                <div class="grid wide">
	                    <div class="row other-footer-heading">
	                        <div class="col l-2">
	                            <a href="#" class="other-footer-link">
	                                CHÍNH SÁCH BẢO MẬT
	                            </a>
	                        </div>
	                        <div class="col l-2">
	                            <a href="#" class="other-footer-link">
	                                QUY CHẾ HOẠT ĐỘNG
	                            </a>
	                        </div>
	                        <div class="col l-2">
	                            <a href="#" class="other-footer-link">
	                                TRẢ HÀNG VÀ HOÀN TIỀN
	                            </a>
	                        </div>
	                    </div>
	                    <div class="row">
	                        <div class="grid other-footer-info">
	                            <p class="other-footer-title">Thông tin về CanteenFood</p>
	                            <p class="other-footer-more">
	                                Địa chỉ: 64 Nguyễn Lương Bằng, phường Hoà Khánh Bắc, quân Liên Chiểu, Thành phố Đà Nẵng, Việt Nam.
	                                Tổng đài hỗ trợ: 19001234 - Email: cskh@hotro.canteenfood.vn
	                            </p>
	                            <p class="other-footer-more">
	                                Chịu Trách Nhiệm Quản Lý Nội Dung: Ngô Đỗ Nguyễn Hải Sơn - Trần Thị Kim Tiến - Lê bá Thuận -
	                                Điện thoại liên hệ: 024 73081221 (ext 4678)
	                            </p>
	                            <p class="other-footer-more">
	                                Mã số doanh nghiệp: 0106773786 do Sở Kế hoạch & Đầu tư
	                                TP Đà Nẵng cấp lần đầu ngày 10/06/2023
	                            </p>
	                            <p class="other-footer-more">
	                                Ngày sản xuất 2023 -
	                                Bản quyền gốc thuộc về Công ty TNHH CanteenFood
	                            </p>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </footer>
	    </div>
	    <!-- modal -->
	    <div class="modal" id="form-info">
	        <div class="modal__body" >
	            <!-- authen change info-->
	            <form action="./ChangeInfo" method="post" class="form-info" enctype="multipart/form-data">
	                <div class="avatar">
	                    <c:if test="${canteen.getAvatar()==null}">
							<img src="./assets/img/avatarDefault.jpg" class="avatar-form__img" id="img-form"/>
						</c:if>
						<c:if test="${canteen.getAvatar()!=null}">
							<img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(canteen.getAvatar())%>" class="avatar-form__img" id="img-form"/>
						</c:if>
	                    <input class="avatar-form__input" type="file" name="avatar" id="input-img-form">
	                </div>
	                <div class="auth-form__info">
	                    <div class="auth-form__container">
	                        <div class="auth-form__header">
	                            <h3 class="auth-form__heading">Thông tin Canteen</h3>
								<div class="auth-form__switch-btn">
									<a href="#" id="changeTimeOpen-link2" class="auth-form__switch-btn--link">Sửa giờ hoạt động</a>
								</div>
	                        </div>
	                        <div class="auth-form__form">
	                            <div class="auth-form__title">
	                                <span>Tên Canteen:</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="text" class="auth-form__input_info" name="txtTencanteen" value="${canteen.getTen() }">
	                            </div>
	                            <div class="auth-form__title">
	                                <span>Số điện thoại:</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="text" value="${canteen.getSodienthoai() }" name="txtSodienthoai" class="auth-form__input_info" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
	                            </div>
	                            <div class="auth-form__title">
	                                <span>Email:</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="email" class="auth-form__input_info" name="txtEmail" value="${canteen.getEmail() }">
	                            </div>
	                           	<div style="display: none">
	                           		<span id="tinh">${diachi.getTinh()}</span>
	                           		<span id="huyen">${diachi.getHuyen()}</span>
	                           		<span id="xa">${diachi.getXa()}</span>
	                           	</div>
	                            <div class="auth-form__title">
	                                <div class="search-group-title">Địa chỉ</div>
	                                <select name="tinh" class="search-group-item select-address" id="province">
	                                    <option value="-1">Chọn tỉnh thành</option>
	                                </select>
	                                <select name="huyen" class="search-group-item select-address" id="district">
	                                    <option value="-1">Chọn quận/huyện</option>
	                                </select>
	                                <select name="xa" class="search-group-item select-address" id="town">
	                                    <option value="-1">Chọn phường/xã</option>
	                                </select>
	                            </div>
	                        </div>
	                        <div class="auth-form__control_info">
	                            <a class="btn auth-form__back" href="./">QUAY LẠI</a>
	                            <button class="btn btn--primary" type="submit">Lưu</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
        <div class="modal" id="form-changepassword" style="display: ${display_form__changepass}" >
            <div class="modal__body">
                <form action="./ChangePassword" method="post" class="formChangePass">
                    <div class="auth-form">
                        <div class="auth-form__container">
                            <div class="auth-form__header">
                                <h3 class="auth-form__heading">Đổi mật khẩu</h3>
                                <div class="auth-form__switch-btn">
                                    <a href="#" id="changepin-link2" class="auth-form__switch-btn--link">Đổi mã xác thực</a>
                                </div>
                            </div>
                            <div class="auth-form__form">
                                <div class="auth-form__group--noti" id="notiChangePassConfirm">
                                    <span>Mật khẩu mới và xác nhận mật khẩu mới không giống nhau.</span>
                                </div>
                                <div class="auth-form__group--noti" id="notiErrorOldPass" style="display: ${notiErrorOldPass}">
                                    <span>Sai mật khẩu cũ.</span>
                                </div>
                                <div class="auth-form__group--noti" id="notiSuccessNewPass" style="display: ${notiSuccessNewPass}">
                                    <span>Cập nhật mật khẩu mới thành công.</span>
                                </div>
                                <div class="auth-form__group">
                                    <input type="text" placeholder="Mật khẩu cũ" class="auth-form__input" name="txtOldPass" required>
                                </div>
                                <div class="auth-form__group">
                                    <input type="password" placeholder="Mật khẩu mới" class="auth-form__input" name="txtNewPass" required>
                                </div>
                                <div class="auth-form__group">
                                    <input type="password" placeholder="Nhập lại mật khẩu mới" class="auth-form__input" name="txtConfirmNewPass" required>
                                </div>
                            </div>
                            <div class="auth-form__control">
                                <a class="btn auth-form__back" href="./">QUAY LẠI</a>
                                <button class="btn btn--primary" type="submit">Xác nhận</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div> 
        </div>
    
        <!-- Change PIN form -->
        <div class="modal" id="form-changepin" style="display: ${display_form__changepin}">
            <div class="modal__body">
                <form action="./ChangePin" method="post" class="auth-form">
                    <div class="auth-form">
                        <div class="auth-form__container">
                            <div class="auth-form__header">
                                <h3 class="auth-form__heading">Đổi mã xác thực</h3>
                                <div class="auth-form__switch-btn">
                                    <a href="#" id="changepass-link2" class="auth-form__switch-btn--link">Đổi mật khẩu</a>
                                </div>
                            </div>
                            <div class="auth-form__form">
                                <div class="auth-form__group--noti" id="notiErrorOldPass" style="display: ${notiErrorOldPass}">
                                    <span>Sai mật khẩu cũ.</span>
                                </div>
                                <div class="auth-form__group--noti" id="notiSuccessNewPin" style="display: ${notiSuccessNewPin}">
                                    <span>Cập nhật mã xác thực mới thành công.</span>
                                </div>
                                <div class="auth-form__group">
                                    <input type="text" placeholder="Nhập mật khẩu" class="auth-form__input" name="txtOldPass" required>
                                </div>
                                <div class="auth-form__group">
                                    <input type="text" placeholder="Nhập mã xác thực mới" class="auth-form__input" name="txtNewPin" required>
                                </div>
                            </div>
                            <div class="auth-form__control">
                                <a class="btn auth-form__back" href="./">QUAY LẠI</a>
                                <button class="btn btn--primary" type="submit">Xác nhận</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div> 
        </div>
        <div class="modal" id="form-infobank">
	        <div class="modal__body">
	            <form action="./updateBank" method="post" enctype="multipart/form-data">
	                <div class="auth-form">
	                    <div class="auth-form__container">
	                        <div class="auth-form__header">
	                            <h3 class="auth-form__heading">Thông tin ngân hàng</h3>
	                        </div>
	                        <div class="auth-form__form">
								<div class="auth-form__title">
									<span>Tên ngân hàng:</span>
								</div>
	                            <div class="auth-form__group">
	                                <input type="text" placeholder="MBbank, VietCombank, Viettinbank,..." class="auth-form__input" name="txtTennganhang" value="${bank.getTennganhang()}" required>
	                            </div>
								<div class="auth-form__title">
									<span>Số tài khoản:</span>
								</div>
	                            <div class="auth-form__group">
	                                <input type="text" placeholder="123456789" class="auth-form__input" name="txtStk" value="${bank.getStk()}" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
	                            </div>
	                            <div class="auth-form__title">
									<span>Họ và tên chủ tài khoản:</span>
								</div>
	                            <div class="auth-form__group">
	                                <input type="text" placeholder="Nguyễn Văn A" class="auth-form__input" name="txthoten" value="${bank.getHovaten()}" required>
	                            </div>
								<div class="auth-form__title">
									<span>Mã QR:</span>
								</div>
								<div class="auth-form__group">
									<c:if test="${bank.getMaQR()!=null}">
										<img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(bank.getMaQR())%>" class="avatar-form__img qrcode" />
									</c:if>
	                    			<input class="avatar-form__input" type="file" name="maQR">
	                            </div>
	                        </div>
	                        <div class="auth-form__control">
	                        	<a class="btn auth-form__back" href="./">QUAY LẠI</a>
	                            <button class="btn btn--primary" type="submit">Xác nhận</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
        <div class="modal" id="form-addProduct">
	        <div class="modal__body" >
	        <!-- authen change info-->
	            <form action="./Addproduct" method="post" class="form__info" enctype="multipart/form-data">
	                <div class="auth-form__addproduct">
	                    <div class="auth-form__container">
	                        <div class="auth-form__header">
	                            <h3 class="auth-form__heading">Thêm món mới</h3>
	                        </div>
	                        <div class="auth-form__form">
	                            <div class="auth-form__title">
	                                <span>Tên món ăn:</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="text" class="auth-form__input_info" name="txtTenmon" value="" required>
	                            </div>
	                            <div class="auth-form__title">
	                                <span>Mô tả:</span>
	                            </div>
	                            <div class="auth-form__group" >
	                                <textarea class="auth-form__input_info" name="txtMota" value="" style="height: 80px; margin: none;" required></textarea>
	                            </div>
	                            <div class="auth-form__title">
	                                <span>Thành phần:</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="text" class="auth-form__input_info" name="txtThanhphan" placeholder="Ví dụ: 500g thịt gà, 1 quả trứng,..." required>
	                            </div>
	                            <div class="auth-form__group_row">
		                       		<div class="auth-form__title_row">
		                    	        <span>Hương vị:</span>
		                            </div>
			                        <div class="auth-form__title_row">
		    	                        <span>Giá:</span>
		                            </div>
		                        </div>
		                            <div class="auth-form__group_row">
		                                <input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: Chua,cay,mặn,..." name="txtHuongvi" required> 
										<input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: 50000" name="txtGia" pattern="[0-9]+" required>
		                            </div>
								<div class="auth-form__title">
		                            <span>Loại thức ăn:</span>
		                        </div>
									<div class="auth-form__group input__checkbox">
										<c:forEach items="${loaithucan}" var="loai"> 
											<div class="">
												<input type="checkbox" name="loaithucans" class="auth-form-input__checkbox" value="${loai.getID_loaithucan()}"> ${loai.getLoaithucan()}
											</div>
		                                </c:forEach>
									</div>
	                            <div class="auth-form__title"> 
	                                <span>Thêm hình ảnh (ít nhất 1 ảnh, ảnh đầu tiên được lấy làm ảnh chính):</span>
	                            </div>
	                            <div class="auth-form__group add-product-img">
									<% for (int i = 1; i <= 8; i++) { %>
									  <div class="img-add-product">
										<label for="input-img-form-<%= i %>">
										  <img src="./assets/img/addproduct.png" class="img-add-product__img" id="img-form-<%= i %>"/>
										</label>
										<input class="img-add-product__input" type="file" name="img<%= i %>" id="input-img-form-<%= i %>" <% if (i == 1) { %>required<% } %>>
									  </div>
									<% } %>
								  </div>								  
	                        </div>
	                        <div class="auth-form__control_info">
	                            <a class="btn auth-form__back" href="./">QUAY LẠI</a>
	                            <button class="btn btn--primary" type="submit">Lưu</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
		<div class="modal" id="form-changeTimeOpen" style="display: none;">
	        <div class="modal__body" >
	        <!-- authen change info-->
	            <form action="./ChangeTimeOpen" method="post" id="form-editTime">
	                <div class="auth-form">
	                    <div class="auth-form__container">
	                        <div class="auth-form__header">
	                            <h3 class="auth-form__heading">Sửa giờ hoạt động ${giohoatdongList.get(0).getGiodongcua()}</h3>
								<div class="auth-form__switch-btn">
									<a href="#" id="info-link2" class="auth-form__switch-btn--link">Thông tin Canteen</a>
								</div>
	                        </div>
	                        <div class="auth-form__form">
								<%
								String[] weekdays = {"Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ nhật"};
								String[] weekdaysnumber = {"2", "3", "4", "5", "6", "7", "8"};
								for (int i = 0; i < weekdays.length; i++) {
								%>
								<div class="auth-form__group_row">
									<div class="auth-form__title_row-time">
										<span><%=weekdays[i]%>:</span>
									</div>
									<div class="auth-form__title_row-time">
										<span>Từ</span>
									</div>
									<div class="auth-form__input_info_row">
										<select name="txtTimeOpen<%=weekdaysnumber[i] %>" class="auth-form__input_info_select" >
											<option value="-1">Đóng cửa</option>
											<%for (int j = 0; j < 24; j++) {
												String t = String.valueOf(j);
												if (j<10) t="0"+t;
												if (giohoatdongList.get(i).getGiomocua().equals(t+":00")) { 
											%> 
													<option value="<%=j%>:00" selected='selected'> <%=j%>:00</option> 
												<%} else {%>
													<option value="<%=j%>:00"> <%=j%>:00</option>
								            	<%}
											}%> 
										</select>
									</div>
									<div class="auth-form__title_row-time">
										<span>Đến</span>
									</div>
									<div class="auth-form__input_info_row">
										<select name="txtTimeClose<%=weekdaysnumber[i] %>" class="auth-form__input_info_select" >
											<option value="-1">Đóng cửa</option>
											<%for (int j = 0; j < 24; j++) {
												String t = String.valueOf(j);
												if (j<10) t="0"+t;
												if (giohoatdongList.get(i).getGiodongcua().equals(t+":00")) { 
											%> 
													<option value="<%=j%>:00" selected='selected'> <%=j%>:00</option> 
												<%} else {%>
													<option value="<%=j%>:00"> <%=j%>:00</option>
								            	<%}
											}%> 
										</select>
									</div>
								</div>
								<%}%>
							</div> 
	                        <div class="auth-form__control_info">
	                            <a class="btn auth-form__back" href="./">QUAY LẠI</a>
	                            <button class="btn btn--primary" type="submit">Lưu</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
	    <div class="modal" name="form-editProduct" id="${monan.getID_monan()}">
		</div>
		<% for (int i=0; i<hoadons.size(); i++) {%>
	    	<div class="modal" name="form-cart-detail" id="<%=hoadons.get(i).getMadon()%>">
				<div class="modal__body">
					<div class="auth-form list__custumer">
						<div class="auth-form__container">
							<div class="auth-form__form">
								<h3 class="auth-form__heading table-list-user">CHI TIẾT ĐƠN HÀNG <%=hoadons.get(i).getMadon()%></h3>
			                        <div class="result-search-user">
			                          	<table border ="1" width ="100%">
				                            <tr>
				                            	<th>Mã món ăn</th>
				                              	<th>Tên món ăn</th>
				                              	<th>Số lượng</th>
				                              	<th>Đơn giá</th>
				                              	<th>Thành tiền</th>
				                            </tr>      
				                            <c:set var="HDchitiets" value="<%=hdctList.get(i)%>"/>
	   										<c:forEach items="${HDchitiets}" var="HDchitiet">	
		   										<tr>
		   											<td>${HDchitiet.getID_monan()}</td>
							                    	<td>${HDchitiet.getTenmon()}</td>
							                        <td>${HDchitiet.getSoluong()}</td>
								                    <td>${String.format("%.3f", HDchitiet.getGia())} VNĐ</td>
								                    <td>${String.format("%.3f", HDchitiet.getGia() * HDchitiet.getSoluong())} VNĐ</td>
									            </tr> 
	   										</c:forEach>
	   										<tr>
		   										<td></td>
							                    <td></td>
							                    <td></td>
								                <td><h3>Tổng tiền: </h3></td>
								                <td><h3><%=String.format("%.3f", hoadons.get(i).getTongtien())%> VNĐ</h3></td>
									        </tr> 
			                          	</table>
			                        </div>
							</div>
							<div class="auth-form__control btn-back">
								<a class="btn auth-form__back " href="./">QUAY LẠI</a>
							</div>
						</div>
					</div>
				</div> 
			</div>
		<% } %>
	</c:if>
	
    <!-- script js -->
    <!-- <script src="./assets/js/product.js"></script> -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous">
    </script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous">
    </script>
    <script src="./assets/data/address.json"></script>
    <script src="./assets/js/cantin.js"></script>
</body>
</html>