<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="datdocantin.Model.KhachHangModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%
	KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
	List<String> searchHistory = (List<String>) session.getAttribute("searchHistory");
	String sdt = (String)session.getAttribute("sdt");
	
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
                        <c:if test="${khachhang==null}">				        
									<li class="header__nav-item header__nav-item--bold header__nav-item--separate">
			                            <a href="#" id="signup-link" class="header__nav-item-link" >Đăng ký</a>
			                        </li>
			                        <li class="header__nav-item header__nav-item--bold">
			                            <a href="#" id="login-link" class="header__nav-item-link" >Đăng nhập</a>
	                       			</li>
						</c:if>
						<c:if test="${khachhang!=null}">
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
		                                    <a href="#">Đơn đã mua</a>
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
						</c:if>
                    </ul>
                </nav>
                <!-- search -->
                <div class="header__contain">
                    <div class="header__logo">
                        <a href="#" class="header__logo-link">
                            <img src="./assets/img/logo/logo.png" class="header__logo-img">
                        </a>
                    </div>
                    
                    <form class="header__search" method="POST" action="./search?id_user=${khachhang.getIDKH()}">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="">
                            <div class="header__search-history">
                                <!-- History Search -->
                                <ul class="header__search-history-list">
                                    <c:forEach items="${searchHistory}" var="i">
							        	<li class="header__search-history-item">
                                        	<a href="#">${i}</a>
                                    	</li>
									</c:forEach> 
									
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
                        <div class="header__cart-count">7</div>
                        
                        <div class="header__cart-list no-cart">
                            <img src="./assets/img/sp/no-cart.png" class="header__no-cart-img">
                            <p class="header__no-cart-text">Chưa có sản phẩm</p>
                        </div>
                        <!-- Giỏ hàng -->
                        <div class="header__cart-list has-cart">
                            <h4 class="header__cart-heading">Sản phẩm đã chọn</h4>
                            <ul class="header__cart-list-item">
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/1.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name">Bánh tráng</h3>
                                            <p class="header__cart-item-price">100.000đ</p>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 2</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/2.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name">Bánh mỳ</h3>
                                            <p class="header__cart-item-price">15.000đ</p>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 1</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/3.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name">Cơm tấm</h3>
                                            <p class="header__cart-item-price">35.000đ</p>
                                        </div>  
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 1</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/4.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name">Cơm gà</h3>
                                            <p class="header__cart-item-price">30.000đ</p>
                                        </div>  
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 3</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/5.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name"> Cơm gà chiên mắm</h3>
                                            <p class="header__cart-item-price">35.000đ</p>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 2</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/4.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name">Cơm gà xối mỡ</h3>
                                            <p class="header__cart-item-price">150.000đ</p>
                                        </div>  
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 3</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="header__cart-item">
                                    <img src="./assets/img/buy/5.png" class="header__cart-item-img">
                                    <div class="header__cart-item-info">
                                        <div class="header__cart-item-heading">
                                            <h3 class="header__cart-item-name"> Cơm gà chiên mắm</h3>
                                            <p class="header__cart-item-price">100.000đ</p>
                                        </div>
                                        <div class="header__cart-item-body">
                                            <p class="header__cart-item-number">x 2</p>
                                            <div class="header__cart-item-close">
                                                Xoá
                                                <i class="fas fa-times"></i>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                            <div class="header__cart-footer">
                                <a href="#" class="btn btn--primary header__cart-see-cart">Xem giỏ hàng</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <ul class="header__sort-bar">
                <li class="header__sort-item">
                    <a href="#" class="header__sort-link">Liên quan</a>
                </li>
                <li class="header__sort-item header__sort-item--active">
                    <a href="#" class="header__sort-link">Mới nhất</a>
                </li>
                <li class="header__sort-item">
                    <a href="#" class="header__sort-link">Bán chạy</a>
                </li>
                <li class="header__sort-item">
                    <a href="#" class="header__sort-link">Giá</a>
                </li>
            </ul>
        </header>
        <!-- container -->
        <div class="container">
            <div class="grid wide">
                <div class="row sm-gutter">
                    <div class="col l-2 m-0 c-0">
                        <!-- Search Cantin -->
						<c:if test="${khachhang==null}">
							<nav class="search-cantin" id="search-cantin">
								<form action="">
									<h3 class="search-heading">
										<i class="search-heading-icon fas fa-search"></i> Tìm kiếm
										Cantin
									</h3>
									<div class="search-group">
										<div class="search-group-title">Nhập tên hoặc mã Cantin</div>
										<input class="search-group-input" type="search">
									</div>
									<div class="search-group">
										<div class="search-group-title">Chọn địa chỉ</div>
										<select name="" class="search-group-item" id="province">
											<option value="-1">Chọn tỉnh thành</option>
										</select> <select name="" class="search-group-item" id="district">
											<option value="-1">Chọn quận/huyện</option>
										</select> <select name="" class="search-group-item" id="town">
											<option value="-1">Chọn phường/xã</option>
										</select>
									</div>
									<button
										class="btn btn--primary category-group-filter-btn category-group--margin"
										type="submit">Tìm kiếm</button>
								</form>
							</nav>
						</c:if>
						<c:if test="${khachhang!=null}">
                            <!-- category -->
                            <nav class="category" id="category">
                                <h3 class="category-heading">
                                    <i class="category-heading-icon fas fa-list-ul"></i>
                                    Bộ lọc tìm kiếm
                                </h3>
                                <div class="category-group">
                                    <div class="category-group-title">Loại thức ăn</div>
                                    <ul class="category-group-list">
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món thịt
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món hải sản 
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món nước
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món khô
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món chiên
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món xào
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Món chay
                                        </li>
                                    </ul>
                                </div>
                                <div class="category-group">
                                    <div class="category-group-title">Thành phần chính</div>
                                    <ul class="category-group-list">
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Thịt gà
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Thịt heo
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Thịt bò 
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Hải sản
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Rau củ
                                        </li>
                                    </ul>
                                </div>
                                <div class="category-group">
                                    <div class="category-group-title">Hương vị</div>
                                    <ul class="category-group-list">
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Chua 
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Cay
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Mặn
                                        </li>
                                        <li class="category-group-item">
                                            <input type="checkbox" class="category-group-item-check">
                                            Ngọt
                                        </li>
                                    </ul>
                                </div>
                            
                                <div class="category-group">
                                    <div class="category-group-title">Khoảng Giá</div>
                                    <div class="category-group-filter">
                                        <input type="number" placeholder="đ TỪ" class="category-group-filter-input">
                                        <i class="fas fa-arrow-right"></i>
                                        <input type="number" placeholder="đ ĐẾN" class="category-group-filter-input">
                                    </div>
                                    <!-- <button class="btn btn--primary category-group-filter-btn">Tìm kiếm</button> -->
                                </div>
                            
                                <div class="category-group">
                                    <div class="category-group-title">Đánh Giá</div>
                                    <div class="rating-star">
                                        <input type="checkbox" class="category-group-item-check">
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                    </div>
                                    <div class="rating-star">
                                        <input type="checkbox" class="category-group-item-check">
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                    </div>
                                    <div class="rating-star">
                                        <input type="checkbox" class="category-group-item-check">
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                    </div>
                                    <div class="rating-star">
                                        <input type="checkbox" class="category-group-item-check">
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                    </div>
                                    <div class="rating-star">
                                        <input type="checkbox" class="category-group-item-check">
                                        <i class="star-checked far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                        <i class="star-uncheck far fa-star"></i>
                                    </div>
                                </div>

                                <button class="btn btn--primary category-group-filter-btn category-group--margin">LÀM MỚI</button>
                            </nav>
                        </c:if>
                    </div>
                    <div class="col l-10 m-12 c-12">
                        <!-- home filter -->
                        <div class="home-filter">
                            <div class="home-filter-control">
                                <p class="home-filter-title">Sắp xếp theo:</p>
                                <button class="btn btn--primary home-filter-btn">Mới nhất</button>
                                <button class="btn home-filter-btn">Bán chạy</button>
                                <div class="btn home-filter-sort">
                                    <p class="home-filter-sort-btn">Giá</p>
                                    <i class="fas fa-sort-amount-down-alt"></i>
                                    <ul class="home-filter-sort-list">
                                        <li>
                                            <a href="#" class="home-filter-sort-item-link">
                                                Giảm dần
                                                <i class="fas fa-sort-amount-down-alt"></i>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="#" class="home-filter-sort-item-link">
                                                Tăng dần
                                                <i class="fas fa-sort-amount-up-alt"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="home-filter-page">
                                <div class="home-filter-page-number">
                                    <p class="home-filter-page-now">1</p>
                                    /14
                                </div>
                                <div class="home-filter-page-control">
                                    <a href="#" class="home-filter-page-btn home-filter-page-btn--disable">
                                        <i class="fas fa-angle-left"></i>
                                    </a>
                                    <a href="#" class="home-filter-page-btn">
                                        <i class="fas fa-angle-right"></i>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <!-- home product -->
                        <div class="home-product">        
                            <!-- <div id="list-product" class="row sm-gutter"> -->
                            <div class="row sm-gutter">
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/1.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Tráng Chấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">80.000đ</p>
                                                <p class="home-product-item__price-new">50.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/2.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Mì</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">20.000đ</p>
                                                <p class="home-product-item__price-new">15.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/3.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Tấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">40.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,2k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">15%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/4.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm gà</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">35.000đ</p>
                                                <p class="home-product-item__price-new">30.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,5k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/5.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Gà Chiên Mắm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">50.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                            
                            
                            
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/1.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Tráng Chấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">80.000đ</p>
                                                <p class="home-product-item__price-new">50.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/2.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Mì</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">20.000đ</p>
                                                <p class="home-product-item__price-new">15.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/3.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Tấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">40.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,2k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">15%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/4.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm gà</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">35.000đ</p>
                                                <p class="home-product-item__price-new">30.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,5k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/5.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Gà Chiên Mắm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">50.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                            

                            
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/1.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Tráng Chấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">80.000đ</p>
                                                <p class="home-product-item__price-new">50.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/2.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Bánh Mì</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">20.000đ</p>
                                                <p class="home-product-item__price-new">15.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,8k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/3.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Tấm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">40.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,2k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">15%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/4.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm gà</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">35.000đ</p>
                                                <p class="home-product-item__price-new">30.000đ</p>
                                                <!-- <i class="home-product-item__ship fas fa-shipping-fast"></i> -->
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 4,5k</div>
                                            </div>
                                            <!-- <div class="home-product-item__origin"></div> -->
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">25%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <!-- <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> -->
                                    </a>
                                </div>
                                <div class="col l-2-4 m-3 c-6 home-product-item">
                                    <a class="home-product-item-link" href="#">
                                        <div class="home-product-item__img" style="background-image: url(./assets/img/home/5.png);"></div>
                                        <div class="home-product-item__info">
                                            <h4 class="home-product-item__name">Cơm Gà Chiên Mắm</h4>
                                            <div class="home-product-item__price">
                                                <p class="home-product-item__price-old">50.000đ</p>
                                                <p class="home-product-item__price-new">35.000đ</p>
                                            </div>
                                            <div class="home-product-item__footer">
                                                <div class="home-product-item__save">
                                                    <input type="checkbox" id="heart-save-1">
                                                    <label for="heart-save-1" class="far fa-heart"></label>
                                                </div>
                                                <div class="home-product-item__rating-star">
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-checked far fa-star"></i>
                                                    <i class="star-uncheck far fa-star"></i>
                                                </div>
                                                <div class="home-product-item__saled">Đã bán 3,8k</div>
                                            </div>
                                            <div class="home-product-item__favourite">
                                                Yêu thích
                                            </div>
                                            <div class="home-product-item__sale-off">
                                                <div class="home-product-item__sale-off-value">30%</div>
                                                <div class="home-product-item__sale-off-label">GIẢM</div>
                                            </div>
                                        </div>
                                        <div class="home-product-item-footer">Tìm sản phẩm tương tự</div> 
                                    </a>
                                </div>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- modal -->
    
    <!-- Signup Form -->
    <c:if test="${khachhang==null}"> 
		<div class="modal" id="form-signup" style="display: ${display_form__signup}">
		    <div class="modal__body">
		        <form class="formSignup" method="POST" action="./Signup">
			        <div class="auth-form">
		                <div class="auth-form__container">
		                    <div class="auth-form__header">
		                        <h3 class="auth-form__heading">Đăng Ký</h3>
		                        <div class="auth-form__switch-btn">
		                            <a href="#" id="login-link2" class="auth-form__switch-btn--link">Đăng nhập</a>
		                        </div>
		                    </div>
		                    <div class="auth-form__form">
								<div class="auth-form__group--noti" id="notiSignupConfirmPass">
									<span>Mật khẩu và xác nhận mật khẩu không giống nhau.</span>
								</div>
								<div class="auth-form__group--noti" id="notiSignupsdt" style="display: ${display_noti__signup}">
									<span>Số điện thoại này đã được đăng ký.</span>
								</div>
								<div class="auth-form__group">
		                            <input type="text" placeholder="Họ và tên" name="txtHoten" class="auth-form__input" required>
		                        </div>
								<div class="auth-form__group">
									<input type="text" value="${sdt}" name="txtSdt" placeholder="Số điện thoại" class="auth-form__input" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
								</div>
		                        <div class="auth-form__group">
		                            <input type="password" placeholder="Mật khẩu" name="txtPassword" class="auth-form__input" required>
		                        </div>
		                        <div class="auth-form__group">
		                            <input type="password" placeholder="Nhập lại mật khẩu" name="txtConfirmPassword" class="auth-form__input" required>
		                        </div> 
		                        <div class="auth-form__radio">
		                            <label class="auth-form__label">Loại tài khoản:</label>
		                            <div class="auth-form__radio-group">
		                                <input type="radio" name="typeUser" checked="checked" id="customer" value="customer">
		                                <label class="auth-form__label" for="customer">Khách hàng</label>
		                                <input type="radio" name="typeUser" id="cantin" value="cantin">
		                                <label class="auth-form__label" for="cantin">Cantin</label>
		                            </div>
		                        </div>
		                    </div>
		                    <div class="auth-form__control">
		                        <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
		                        <button class="btn btn--primary" type="submit">ĐĂNG KÝ</button>
		                    </div>
		                </div>
		            </div>
		        </form>
		    </div> 
		</div>
	
		<!-- Login Form -->
		<div class="modal" id="form-login" style="display: ${display_form__login}">
		    <div class="modal__body">
		        <form class="auth-form" method="POST" action="./Login">
		            <div class="auth-form__container">
		                <div class="auth-form__header">
		                    <h3 class="auth-form__heading">Đăng Nhập</h3>
		                    <div class="auth-form__switch-btn">
		                        <a href="#" id="signup-link2" class="auth-form__switch-btn--link">Đăng ký</a>
		                    </div>
		                </div>
		                <div class="auth-form__form">
		                	<div class="auth-form__group--noti" style="display: ${noti_login__ErrorSdt}">
	                            <span>Số điện thoại này chưa được đăng ký.</span>
	                        </div>
	                        <div class="auth-form__group--noti" style="display: ${noti_login__ErrorPass}">
	                            <span>Mật khẩu bạn vừa nhập không chính xác.</span>
	                        </div>
		                    <div class="auth-form__group">
		                        <input type="text" placeholder="Số điện thoại" name="txtSdt" class="auth-form__input" value="${sdt}" oninput="this.value=this.value.replace(/[^0-9]/g,'');" required>
		                    </div>
		                    <div class="auth-form__group">
		                        <input type="password" placeholder="Mật khẩu" class="auth-form__input" name="txtPassword" required>
		                    </div>
		                </div>
		                <div class="auth-form__help">
		                    <a href="#" class="auth-form__help-link auth-form__help-forgot">Quên Mật Khẩu</a>
		                    <div class="auth-form__help--separate"></div>
		                    <a href="#" class="auth-form__help-link">Cần trợ giúp?</a>
		                </div>
		                <div class="auth-form__control">
		                    <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
		                    <button class="btn btn--primary" type="submit">ĐĂNG NHẬP</button>
		                </div>
		            </div>
		        </form>
		    </div> 
		</div>
	</c:if>
	<c:if test="${khachhang!=null}">
		<div class="modal" id="form-changepassword" style="display: ${display_form__changepass}" >
	        <div class="modal__body">
	            <form action="./ChangePassword?id_user=${khachhang.getIDKH()}" method="post" class="formChangePass">
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
	                            <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
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
	            <form action="./ChangePin?id_user=${khachhang.getIDKH()}" method="post" class="auth-form">
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
	                            <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
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
				<form action="./ChangeInfo?id_user=${khachhang.getIDKH()}" method="post" class="form-info" enctype="multipart/form-data">
					<div class="avatar">
						<c:if test="${khachhang.getAvatar()==null}">
							<img src="./assets/img/avatarDefault.jpg" class="avatar-form__img" id="img-form"/>
						</c:if>
						<c:if test="${khachhang.getAvatar()!=null}">
							<img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(khachhang.getAvatar()) %>" class="avatar-form__img" id="img-form"/>
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
											<option value="nam"
												${khachhang.getGioitinh() == 'nam' ? 'selected' : ''}>Nam</option>
											<option value="nu"
												${khachhang.getGioitinh() == 'nu' ? 'selected' : ''}>Nữ</option>
										</select>

									</div>
									<input type="text" class="auth-form__input_info_row"
										placeholder="Ví dụ: 1.7" name="txtChieucao"
										value="${khachhang.getChieucao()}"> <input type="text"
										class="auth-form__input_info_row" placeholder="Ví dụ: 54.8"
										name="txtCannang" value="${khachhang.getCannang()}">
								</div>
								<div class="auth-form__title">
									<span>Số điện thoại:</span>
								</div>
								<div class="auth-form__group">
									<input type="text" value="${khachhang.getSodienthoai()}"
										name="txtSdt" class="auth-form__input_info"
										oninput="this.value=this.value.replace(/[^0-9]/g,'');"
										required>
								</div>
								<div class="auth-form__title">
									<span>Email:</span>
								</div>
								<div class="auth-form__group">
									<input type="email" class="auth-form__input_info"
										name="txtEmail" value="${khachhang.getEmail()}">
								</div>
								<div class="auth-form__title">
									<span>Cantin:</span>
								</div>
								<div class="auth-form__group">
									<input type="text"
										class="auth-form__input_info input-chosseCantin"
										name="txtIDCantin" value="${khachhang.getIDCantin()}" readonly>
									<a class="btn btn--primary btn-ChosseCantin" href="./">Chọn
										Cantin</a>
								</div>
								<div class="auth-form__title">
									<span>Món yêu thích:</span>
								</div>
								<div class="auth-form__group">
									<input type="text" class="auth-form__input_info"
										placeholder="Ví dụ: Rau, Cá, Thịt gà,..."
										name="txtMonyeuthich" value="${khachhang.getMonyeuthich()}">
								</div>
							</div>
							<div class="auth-form__control_info">
								<a class="btn auth-form__back" href="./">TRANG CHỦ</a>
								<button class="btn btn--primary" type="submit">Lưu</button>
							</div>
						</div>
					</div>
				</form>
			</div> 
	    </div>
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
    <script src="./assets/js/index.js"></script>
</body>
</html>