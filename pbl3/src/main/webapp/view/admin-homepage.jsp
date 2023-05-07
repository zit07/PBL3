<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
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
	                                    
	                                </ul>
	                                <footer class="header__notifi-footer">
	                                    <a href="#" class="header__notifi-footer-btn">Xem tất cả</a>
	                                </footer>
	                            </div>
	                        </li>
	                       
								<li class="header__nav-item header__nav-user">
			                    	<img src="./assets/img/avatarDefault.jpg" class="header__nav-user-avt"> 
			                    	<a href="#" class="header__nav-item-link header__nav-item--bold">${khachhang.getHoten()}</a>
									<ul class="header__nav-user-menu">
										<li class="header__nav-user-item" id="changepass-link">
											<a href="./Changepassword">Đổi mật khẩu</a>
										</li>
										<li class="header__nav-user-item">
											<a href="./Logout">Đăng xuất</a>
										</li>
									</ul> 
			                	</li> 
	                    </ul>
	                </nav>
	                <!-- search -->
	           
	            </div>
	        </header>
	        <!-- container -->
	        <div class="container">
	            <div class="grid admin">
	                <div class="row sm-gutter">
						<div class="col l-2 m-0 c-0">
							<!-- category -->
							<nav class="category" id="category">
								<h3 class="category-heading">
									<i class="category-heading-icon fas fa-list-ul"></i> HOME
								</h3>
								<div class="navbar">
									<a class="navbar-link" href="./listCanteen" target="list">Xem thông tin Canteen</a> 
									<a class="navbar-link" href="">Xem thông tin khách hàng</a> 
									<a class="navbar-link" href="">Tìm kiếm người dùng</a> 
									<a class="navbar-link" href="">Tài khoản đã bị khoá</a> 
									<a class="navbar-link" href="">Xem khiếu nại</a> 
								</div>
							</nav>
						</div>
						<div class="col l-10 m-12 c-12">
	                        <div class="home-product">
								<form action="">
									<div class="search-group-title">Lọc theo địa chỉ</div>
									<select name="" class="search-group-item" id="province">
										<option value="-1">Chọn tỉnh thành</option>
									</select>
									<select name="" class="search-group-item" id="district">
										<option value="-1">Chọn quận/huyện</option>
									</select>
									<select name="" class="search-group-item" id="town">
										<option value="-1">Chọn phường/xã</option>
									</select>
									<button class="btn btn--primary" type="submit">Lọc</button>
								</form>
								<h3 class="auth-form__heading table-list-user">Danh sách Canteen trên toàn quốc</h3>
								<div class="list-user">     
									<table border ="1" width ="100%">
										<tr>
											<th>Mã Canteen</th>
											<th>Tên Canteen</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Địa chỉ</th>
											<th>Danh sách khách hàng</th>
											<th>Tuỳ chọn</th>
										</tr>
										<!-- Chỗ này để vòng for lặp lại các Catneen -->
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
									</table>
								</div> 


								<!-- <h3 class="auth-form__heading table-list-user">Danh sách khách hàng khu vực toàn quốc</h3>
								<div class="list-user">
									<table border ="1" width ="100%">
										<tr>
											<th>ID</th>
											<th>Họ và tên</th>
											<th>Giới tính</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Canteen</th>
											<th>Tuỳ chọn</th>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										

									</table>
								</div> -->


								<!-- <form class="body_search" method="POST" action="./search">
									<div class="header__search-input-wrap">
										<input type="text" class="header__search-input" placeholder="Tìm kiếm người dùng" name="txtSearch" value="Nguyen Van A">
										<div class="header__search-history">
			
										</div>
									</div>
									<button class="btn header__search-btn" type="submit">
										<i class="header__search-btn-icon fas fa-search"></i>
									</button>
								</form>

								<h3 class="auth-form__heading table-list-user">Khách hàng</h3>
								<div class="result-search-user">
									<table border ="1" width ="100%">
										<tr>
											<th>ID</th>
											<th>Họ và tên</th>
											<th>Giới tính</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Canteen</th>
											<th>Tuỳ chọn</th>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
									</table>
								</div>

								<h3 class="auth-form__heading table-list-user">Canteen</h3>
								<div class="result-search-user">
									<table border ="1" width ="100%">
										<tr>
											<th>Mã Canteen</th>
											<th>Tên Canteen</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Địa chỉ</th>
											<th>Danh sách khách hàng</th>
											<th>Tuỳ chọn</th>
										</tr> 
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
											</tr>
									</table>
								</div> -->

								<!-- <h3 class="auth-form__heading table-list-user">Khách hàng</h3>
								<div class="result-search-user">
									<table border ="1" width ="100%">
										<tr>
											<th>ID</th>
											<th>Họ và tên</th>
											<th>Giới tính</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Canteen</th>
											<th>Tuỳ chọn</th>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td>Canteen trường ĐH Bách Khoa ĐN</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
										</tr>
										
									</table>
								</div>

								<h3 class="auth-form__heading table-list-user">Canteen</h3>
								<div class="result-search-user">
									<table border ="1" width ="100%">
										<tr>
											<th>Mã Canteen</th>
											<th>Tên Canteen</th>
											<th>Số điện thoại</th>
											<th>Email</th>
											<th>Địa chỉ</th>
											<th>Danh sách khách hàng</th>
											<th>Tuỳ chọn</th>
										</tr> 
										<!-- Chỗ này để vòng for lặp lại các Catneen -->
											<!--<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											<tr>
												<td>10002</td>
												<td>Canteen Dai hoc bach khoa DN</td>
												<td>0777555264</td>
												<td>abc@gmail.com</td>
												<td>Hoa Khanh Bac, Lien Chieu, Da Nang</td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user" id="list-cus-link">Xem</a> </td>
												<td> <a href="" class="btn btn--primary home-product-btn btn-list-user">Mở khoá</a></td>
											</tr>
											
									</table>
								</div> -->
	                        </div>   
	                    </div>
	                </div>
	            </div>
	        </div>
	
	    </div>

        <div class="modal" id="form-changepassword">
            <div class="modal__body">
                <form action="./ChangePassword?id=" method="post" class="formChangePass">
                    <div class="auth-form">
                        <div class="auth-form__container">
                            <div class="auth-form__header">
                                <h3 class="auth-form__heading">Đổi mật khẩu</h3>
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
                                <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
                                <button class="btn btn--primary" type="submit">Xác nhận</button>
                            </div>
                        </div>
                    </div>
                </form>
            </div> 
        </div>

		<div class="modal" id="form-list__custumer">
			<div class="modal__body">
                <div class="auth-form list__custumer">
                    <div class="auth-form__container">
                        <div class="auth-form__form">
							<h3 class="auth-form__heading table-list-user">Danh sách khách hàng của canteen ABC</h3>
							<div class="list-user">
								<table border ="1" width ="100%">
									<tr>
										<th>ID</th>
										<th>Họ và tên</th>
										<th>Giới tính</th>
										<th>Số điện thoại</th>
										<th>Email</th>
										<th>Tuỳ chọn</th>
									</tr>
									<!-- Chỗ này để vòng for lặp lại các khách hàng -->
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										<tr>
											<td>10002</td>
											<td>Nguyễn Văn A</td>
											<td>Nam</td>
											<td>0777555264</td>
											<td>abc@gmail.com</td>
											<td> <a href="" class="btn btn--primary home-product-btn btn-list-user delete-product">Khoá</a></td>
										</tr>
										
								</table>
							</div>
                        </div>
                        <div class="auth-form__control btn-back">
                            <a class="btn auth-form__back " href="./">TRANG CHỦ</a>
                        </div>
                    </div>
                </div>
            </div> 
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
    <script src="./assets/js/admin.js"></script>
</body>
</html>
