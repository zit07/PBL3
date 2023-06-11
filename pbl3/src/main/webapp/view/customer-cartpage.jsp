<%@page import="datdocanteen.Model.HoadonchitietModel"%>
<%@page import="datdocanteen.Model.HoadonModel"%>
<%@page import="datdocanteen.Model.CartModel"%>
<%@page import="datdocanteen.Model.LichsutimkiemModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="datdocanteen.Model.KhachHangModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Enumeration" %>
<%	
	KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
	List<LichsutimkiemModel> searchHistory = (List<LichsutimkiemModel>)session.getAttribute("searchHistory"); 
	List<CartModel> carts = (List<CartModel>)session.getAttribute("carts");
	Double tongtiencart = (Double) session.getAttribute("tongtiencart");
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
                                    <li class="header__notifi-item">
                                        <a href="#" class="header__notifi-link">
                                            <img src="./assets/img/sp/banhmy.png" class="header__notifi-img">
                                            <div class="header__notifi-info">
                                                <div class="header__notifi-name">
                                                    Bánh mỳ thịt nướng
                                                </div>
                                                <div class="header__notifi-desc">
                                                    Món ăn của bạn đã được chuẩn bị xong
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notifi-item">
                                        <a href="#" class="header__notifi-link">
                                            <img src="./assets/img/sp/comga.png" class="header__notifi-img">
                                            <div class="header__notifi-info">
                                                <div class="header__notifi-name">
                                                    Cơm gà
                                                </div>
                                                <div class="header__notifi-desc">
                                                    Món ăn của bạn đang được chuẩn bị
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notifi-item">
                                        <a href="#" class="header__notifi-link">
                                            <img src="./assets/img/sp/comgachienmam.png" class="header__notifi-img">
                                            <div class="header__notifi-info">
                                                <div class="header__notifi-name">
                                                    Cơm gà chiên mắm
                                                </div>
                                                <div class="header__notifi-desc">
                                                    Bạn cảm thấy món ăn thế nào?
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notifi-item">
                                        <a href="#" class="header__notifi-link">
                                            <img src="./assets/img/sp/banhtrang.png" class="header__notifi-img">
                                            <div class="header__notifi-info">
                                                <div class="header__notifi-name">
                                                    Bánh tráng
                                                </div>
                                                <div class="header__notifi-desc">
                                                    Bạn cảm thấy món ăn thế nào?
                                                </div>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notifi-item">
                                        <a href="#" class="header__notifi-link">
                                            <img src="./assets/img/sp/comtam.png" class="header__notifi-img">
                                            <div class="header__notifi-info">
                                                <div class="header__notifi-name">
                                                    Cơm tấm
                                                </div>
                                                <div class="header__notifi-desc">
                                                    Bạn cảm thấy món ăn thế nào?
                                                </div>
                                            </div>
                                        </a>
                                    </li>
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
						        <li class="header__nav-item header__nav-user" >
						        	<c:if test="${khachhang.getAvatar()==null}">
		                            	<img src="./assets/img/avatarDefault.jpg" class="header__nav-user-avt">
		                            </c:if>
		                            <c:if test="${khachhang.getAvatar()!=null}">
		                           		<img src="data:image/jpeg;base64, <%= Base64.getEncoder().encodeToString(khachhang.getAvatar()) %>" class="header__nav-user-avt"/>
		                            </c:if>
		                            <a href="#" class="header__nav-item-link header__nav-item--bold">${khachhang.getHoten()}</a>
		                            <ul class="header__nav-user-menu">
		                                <li class="header__nav-user-item" id="info-link">
		                                    <a href="">Tài khoản của tôi</a>
		                                </li>
		                                <li class="header__nav-user-item">
		                                    <a href="./Donhangdamua">Đơn đã mua</a>
		                                </li>
		                                <li class="header__nav-user-item" id="changepass-link">
		                                    <a href="./Changepassword">Đổi mật khẩu</a>
		                                </li>
		                                <li class="header__nav-user-item" id="changepin-link">
		                                    <a href="./ChangePin" >Đổi mã xác thực</a>
		                                </li>
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
                    
                    <form class="header__search" method="POST" action="./search?id_user=${khachhang.getID_khachhang()}">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="${txtSearch}">
                            <div class="header__search-history">
                                <!-- History Search -->
                                <ul class="header__search-history-list">
                                <%if (searchHistory!=null){ %> 
                            	<c:set var="lichsutimkiem" value="<%=searchHistory%>"/>
	                            	<c:forEach items="${lichsutimkiem}" var="lichsu">
								    	<li class="header__search-history-item">
	                                    	<a class="header__search-history-item-link" href="./search?id_user=${khachhang.getID_khachhang()}&txtSearch=${lichsu.getNoidung()}" >${lichsu.getNoidung()}</a>
	                                    	<a class="btn-del-history" href="./delHistorySearch?id=${lichsu.getID_lichsutimkiem()}">
                                            	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-x-lg" viewBox="0 0 16 16">
                                                	<path d="M2.146 2.854a.5.5 0 1 1 .708-.708L8 7.293l5.146-5.147a.5.5 0 0 1 .708.708L8.707 8l5.147 5.146a.5.5 0 0 1-.708.708L8 8.707l-5.146 5.147a.5.5 0 0 1-.708-.708L7.293 8 2.146 2.854Z"/>
                                                </svg>
                                             </a>
	                                    </li>
									</c:forEach> 
								<%}%> 
	                        	</ul>
                            </div>
                        </div>
                        <button class="btn header__search-btn" type="submit">
                            <i class="header__search-btn-icon fas fa-search"></i>
                        </button>
                    </form>
                    <!-- header__cart--no-cart --><!-- header__cart--has-cart -->
	                    <div class="header__cart header__cart--has-cart">
	                        <i class="header__cart-icon fas fa-shopping-cart"></i>
	                        <div class="header__cart-count">${soluonggiohang}</div>
	                        <!-- Giỏ hàng -->
	                        <div class="header__cart-list has-cart">
	                        
	                        <c:set var="carts" value="<%=carts%>"/>
	                        <c:if test="${carts.size() == 0}">
	                        	<img src="./assets/img/sp/no-cart.png" class="header__no-cart-img">
		                        <p class="header__no-cart-text">Chưa có sản phẩm</p>
		                    </c:if>
		                    
		                    <c:if test="${carts.size() > 0}">
		                            <h4 class="header__cart-heading">Sản phẩm đã chọn</h4>
		                            <ul class="header__cart-list-item">
		                            <c:set var="carts" value="<%=carts%>"/>
			                        <c:forEach items="${carts}" var="cart">
			                        	<li class="header__cart-item">
		                                    <img class="header__cart-item-img" src="data:image/jpeg;base64, ${Base64.getEncoder().encodeToString(cart.getHinhanhchinh())}">
		                                    <div class="header__cart-item-info">
		                                        <div class="header__cart-item-heading">
		                                            <h3 class="header__cart-item-name">${cart.getTenmon()}</h3>
		                                            <p class="header__cart-item-price">${String.format("%.3f", cart.getGia())} x ${cart.getSoluong()} = ${String.format("%.3f", cart.getSoluong()*cart.getGia())} VNĐ</p>
		                                        </div>
		                                        <div class="header__cart-item-body">
	                                              <div class="header__cart-item-number">
	                                                Số lượng: ${cart.getSoluong()}
	                                              </div>
	                                              <a class="header__cart-item-close" href="./ChangCart?id_cart=${cart.getID_cart()}&type=giam&page=home">
	                                              	Giảm <i class="fa-solid fa-minus"></i>
	                                              </a>
	                                              <div class="header__cart-item-number">|</div>
		                                          <a class="header__cart-item-close" href="./ChangCart?id_cart=${cart.getID_cart()}&type=tang&page=home">
		                                          	Tăng <i class="fa-solid fa-plus"></i>
		                                          </a>
	                                              <div class="header__cart-item-number">|</div>
	                                              <a class="header__cart-item-close" href="./ChangCart?id_cart=${cart.getID_cart()}&type=xoa&page=home">
	                                                Xoá <i class="fas fa-times"></i>
	                                              </a>
		                                        </div>
		                                    </div>
		                                </li>
			                        </c:forEach>
		                            </ul>
		                            <div class="header__cart-footer">
		                                <a href="./cart" class="btn btn--primary header__cart-see-cart">Xem giỏ hàng</a>
		                            </div>
		                        </div>
	                    </c:if>
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
                          <i class="category-heading-icon fas fa-list-ul"></i> Giỏ hàng
                        </h3>
                        <div class="navbar">
                          <a class="navbar-link choose" href="./cart">Xem giỏ hàng</a> 
                          <a class="navbar-link" href="./hoadonchothanhtoan">Đơn hàng chờ thanh toán</a> 
                          <a class="navbar-link" href="./hoadonchoxacnhan">Đơn hàng chờ xác nhận</a> 
                          <a class="navbar-link" href="./Donhangdamua">Xem đơn hàng đã mua</a> 
                          <a class="navbar-link ${tag == 'dahuy' ? 'choose':''}" href="./Donhangdahuy">Xem đơn hàng đã huỷ</a> 
                        </div>
                      </nav>
                    </div>
                    <div class="col l-10">
                      <div class="home-product">
                        <h3 class="auth-form__heading table-list-user">Giỏ hàng</h3>
                        	<form action="./taohoadon" method="POST">
		                        <div class="body-cart">
		                          <table border ="1" width ="100%">
		                            <tr>
		                              <th>Hình ảnh</th>
		                              <th>Tên món ăn</th>
		                              <th>Số lượng</th>
		                              <th>Đơn giá</th>
		                              <th>Thành tiền</th>
		                              <th>Thao tác</th>
		                            </tr>
		                            <c:set var="carts" value="<%=carts%>"/>
					                <c:forEach items="${carts}" var="cart">
			                            <tr>
			                              <td>
			                                <input type="checkbox" class="cart-input-checkbox" name="id_carts" checked="checked" value="${cart.getID_cart()}">
			                                <img src="data:image/jpeg;base64, ${Base64.getEncoder().encodeToString(cart.getHinhanhchinh())}" class="cart-img">
			                              </td>
			                              <td>${cart.getTenmon()}</td>
			                              <td class="quantity">
			                                <a href="./ChangCart?id_cart=${cart.getID_cart()}&type=giam&page=cart"><i class="fa-solid fa-minus btn-tang-giam"></i></a> 
			                                ${cart.getSoluong()}
			                                <a href="./ChangCart?id_cart=${cart.getID_cart()}&type=tang&page=cart"><i class="fa-solid fa-plus btn-tang-giam"></i></a>
			                              </td>
			                              <td>${String.format("%.3f", cart.getGia())} VNĐ</td>
			                              <td>${String.format("%.3f", cart.getSoluong()*cart.getGia())} VNĐ</td>
			                              <td> <a href="./ChangCart?id_cart=${cart.getID_cart()}&type=xoa&page=cart" class="btn btn--primary home-product-btn delete-product">Xoá</a></td>
			                            </tr>
			                        </c:forEach>
			                        <% if (tongtiencart != null) { %>
			                        <tr>
			                        	<td></td>
			                        	<td></td>
			                        	<td></td>
			                        	<td><h3>Tổng tiền: </h3> </td>
			                        	<td><h3><%=String.format("%.3f", tongtiencart)%> VNĐ</h3></td>
			                        	<td></td>
			                        </tr>
			                        <% } %>
			                        
		                          </table>
		                        </div>
		                        <c:if test="${soluonggiohang > 0}">
			                        <div class="btn-container">
			                          <button class="btn btn--primary" type="submit"> XÁC NHẬN ĐẶT MÓN </button>
			                      	</div>
			                    </c:if>
                      		</form>
                      		
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
    
    <!-- Signup Form -->
    
		<div class="modal" id="form-changepassword" style="display: ${display_form__changepass}" >
	        <div class="modal__body">
	            <form action="./ChangePassword?id_user=${khachhang.getID_khachhang()}" method="post" class="formChangePass">
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
	            <form action="./ChangePin?id_user=${khachhang.getID_khachhang()}" method="post" class="auth-form">
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
	                                <span>Sai mật khẩu.</span>
	                            </div>
	                            <div class="auth-form__group--noti" id="notiSuccessNewPin" style="display: ${notiSuccessNewPin}">
	                                <span>Cập nhật mã xác thực mới thành công.</span>
	                            </div>
	                            <div class="auth-form__group">
	                                <input type="text" placeholder="Nhập mật khẩu" class="auth-form__input" name="txtPass" required>
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

	    <div class="modal" id="form-info">
	        <div class="modal__body" >
            <!-- authen change info-->
				<form action="./ChangeInfo?id_user=${khachhang.getID_khachhang()}" method="post" class="form-info" enctype="multipart/form-data">
					<div class="avatar">
						<c:if test="${khachhang.getAvatar()==null}">
							<img src="./assets/img/avatarDefault.jpg" class="avatar-form__img" id="img-form"/>
						</c:if>
						<c:if test="${khachhang.getAvatar()!=null}">
							<img src="data:image/jpeg;base64,<%=Base64.getEncoder().encodeToString(khachhang.getAvatar())%>" class="avatar-form__img" id="img-form"/>
						</c:if>
						<input class="avatar-form__input" type="file" name="avatar" id="input-img-form">
					</div>
					<div class="auth-form__info">
						<div class="auth-form__container">
							<div class="auth-form__header">
								<h3 class="auth-form__heading">Thông tin cá nhân</h3>
							</div>
							<div class="auth-form__form">
								<div class="auth-form__title">
									<span>Họ và tên:</span>
								</div>
								<div class="auth-form__group">
									<input type="text" class="auth-form__input_info"
										name="txtHoten" value="${khachhang.getHoten()}">
								</div>
								<div class="auth-form__title">
									<span>Ngày tháng năm sinh:</span>
								</div>
								<div class="auth-form__group">
									<input type="date" class="auth-form__input_info"
										name="txtNgaysinh" value="${khachhang.getNgaysinh()}">
								</div>
								<div class="auth-form__group_row">
									<div class="auth-form__title_row">
										<span>Giới tính:</span>
									</div>
									<div class="auth-form__title_row">
										<span>Chiều cao(mét):</span>
									</div>
									<div class="auth-form__title_row">
										<span>Cân nặng(Kg):</span>
									</div>
								</div>
								<div class="auth-form__group_row">
									<div class="auth-form__input_info_row">
										<select name="txtGioitinh" id=""
											class="auth-form__input_info_select">
											<option value="-1"></option>
											<option value="nam" ${khachhang.getGioitinh() == 'nam' ? 'selected' : ''}>Nam</option>
											<option value="nu" ${khachhang.getGioitinh() == 'nu' ? 'selected' : ''}>Nữ</option>
										</select>
									</div>
									<input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: 1.7" name="txtChieucao" value="${khachhang.getChieucao()}"> 
									<input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: 54.8" name="txtCannang" value="${khachhang.getCannang()}">
								</div>
								<div class="auth-form__title">
									<span>Số điện thoại:</span>
								</div>
								<div class="auth-form__group">
									<input type="text" value="${khachhang.getSodienthoai()}" name="txtSdt" class="auth-form__input_info" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
								</div>
								<div class="auth-form__title">
									<span>Email:</span>
								</div>
								<div class="auth-form__group">
									<input type="email" class="auth-form__input_info" name="txtEmail" value="${khachhang.getEmail()}">
								</div>
								<div class="auth-form__title">
									<span>Cantin:</span>
								</div>
								<div class="auth-form__group form-group-choosecanteen">
									<input type="text" class="auth-form__input_info name-canteen" name="txtIDCantin" value="${TenCanteen}" readonly>
									<c:if test="${TenCanteen == null}">
										<a class="btn btn--primary btn-ChooseCantin" id="choosecanteen">Chọn Cantin</a>
									</c:if>
									<c:if test="${TenCanteen != null}">
										<div id="choosecanteen"></div>
										<a class="btn btn--primary btn-ChooseCantin stop-sold-product" href="./ChangeCanteen">Huỷ chọn Cantin</a>
									</c:if>
								</div>
								<div class="auth-form__title">
									<span>Món yêu thích:</span>
								</div>
								<div class="auth-form__group">
									<input type="text" class="auth-form__input_info" placeholder="Ví dụ: Rau, Cá, Thịt gà,..." name="txtMonyeuthich" value="${khachhang.getYeuthich()}">
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
		<div class="modal" id="form-chosseCantin">
			
		</div>
		
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
    <script src="./assets/js/cart.js"></script>
</body>
</html>