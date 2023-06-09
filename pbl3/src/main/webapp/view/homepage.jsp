<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="java.util.Enumeration" %>
<%@page import="datdocantin.Model.CanteenModel"%>
<%@ page import="datdocantin.Model.MonAnModel"%>
<%	
	List<CanteenModel> canteenList = (List<CanteenModel>)session.getAttribute("canteenList");
	List<MonAnModel> MonanList = (List<MonAnModel>)request.getAttribute("menu");
	CanteenModel Canteen = (CanteenModel)session.getAttribute("Canteen");
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
						<li class="header__nav-item header__nav-item--bold header__nav-item--separate">
			            	<a href="#" id="signup-link" class="header__nav-item-link" >Đăng ký</a>
			            </li>
			            <li class="header__nav-item header__nav-item--bold">
			                <a href="#" id="login-link" class="header__nav-item-link" >Đăng nhập</a>
	                    </li>
                    </ul>
                </nav>
                <!-- search -->
                <div class="header__contain">
                    <div class="header__logo">
                        <a href="#" class="header__logo-link">
                            <img src="./assets/img/logo/logo.png" class="header__logo-img">
                        </a>
                    </div>
                    
                    <form class="header__search" method="POST" action="./search">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="">
                            <div class="header__search-history">

                            </div>
                        </div>
                        <button class="btn header__search-btn" type="submit">
                            <i class="header__search-btn-icon fas fa-search"></i>
                        </button>
                    </form>
                    <div class="header__cart header__cart--has-cart">
                    </div>
                </div>
            </div>
        </header>
        <!-- container -->
        <div class="container">
            <div class="grid wide">
                <div class="row sm-gutter">
                    <div class="col l-2">
                        <!-- Search Cantin -->
							<nav class="search-cantin" id="search-cantin">
								<form action="./SearchCanteen" method="post">
									<h3 class="search-heading">
										<i class="search-heading-icon fas fa-search"></i> Tìm kiếm Cantin
									</h3>
									<div class="search-group">
										<div class="search-group-title">Nhập thông tin Cantin</div>
										<input class="search-group-input" type="search" placeholder="Tên, sđt, mã ID" name="txtSearchCanteen" value="${txtSearchCanteen}">
									</div>
									<div class="search-group">
										<div class="search-group-title">Chọn địa chỉ</div>
										<select name="txtTinh" class="search-group-item" id="province">
											<option value="-1">Chọn tỉnh thành</option>
										</select> 
										<select name="txtHuyen" class="search-group-item" id="district">
											<option value="-1">Chọn quận/huyện</option>
										</select> 
										<select name="txtXa" class="search-group-item" id="town">
											<option value="-1">Chọn phường/xã</option>
										</select>
									</div>
									<div style="display: none">`
		                           		<span id="tinh">${tinh}</span>
		                           		<span id="huyen">${huyen}</span>
		                           		<span id="xa">${xa}</span>
		                           	</div>
									<button class="btn btn--primary category-group-filter-btn category-group--margin" type="submit">Tìm kiếm</button>
								</form>
							</nav>
                    </div>
                    <div class="col l-10">
                        <!-- home filter -->
                        
                      
                        <!-- home product -->
                        <div class="home-product">    
                        	<%if (Canteen!=null){ %>    
                       			<h3 class="title-list-product">Danh sách các món ăn của canteen: <%=Canteen.getTen() %></h3>
                       		<%} %>
                            <!-- <div id="list-product" class="row sm-gutter"> -->
                            <div class="row sm-gutter">
                            <%if (MonanList!=null){ %> 
                            	<c:set var="monanList" value="<%=MonanList%>"/>
                            	<c:forEach items="${monanList}" var="monan">
                            		<div class="col l-2-4">
	                                        <a class="home-product-item-link" href="#">
  	                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())});"></div>
 	                                            <div class="home-product-item__info">
	                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
	                                                <div class="home-product-item__price"> 
		                                                <c:if test="${monan.getGiacu() > monan.getGiahientai()}">
		                                                    <p class="home-product-item__price-old">${monan.getGiacu()}VNĐ</p>
		                                                </c:if>
	                                                    <p class="home-product-item__price-new">${monan.getGiahientai()}VNĐ</p>
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
	                                                    <div class="home-product-item__saled">Đã bán ${monan.getDaban()}</div>
	                                                </div>
	                                                <c:if test="${monan.getGiacu() > monan.getGiahientai()}">
														<c:set var="giamgia" value="${Math.round((monan.giacu - monan.giahientai) / monan.giacu * 100)}" />
		                                               	<div class="home-product-item__sale-off">
													    	<div class="home-product-item__sale-off-value">${giamgia} %</div>
															<div class="home-product-item__sale-off-label">GIẢM</div>
														</div>	 
													</c:if>
	                                            </div>
	                                            <div class="home-product-item-footer">Xem chi tiết</div>
	                                        </a>
	                                    </div>
                            	</c:forEach>
                            <%} else if (canteenList!=null){ %> 
                            	<c:set var="canteenList" value="<%=canteenList%>"/>
                            	<c:forEach items="${canteenList}" var="Canteen">
                            		<div class="col l-2-4">
		                                    <a class="home-product-item-link" href="./Menu?id_canteen=${Canteen.getID_canteen()}">
		                                    	<c:if test="${Canteen.getAvatar()==null}">
					                            	<div class="home-product-item__img" style="background-image: url(./assets/img/avatarDefault.jpg);"></div>
					                            </c:if>
					                            <c:if test="${Canteen.getAvatar()!=null}">
					                            	<div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,${Base64.getEncoder().encodeToString(Canteen.getAvatar())});"></div>
					                            </c:if>
		                                        <div class="home-product-item__info">
		                                            <h4 class="home-product-item__name">${Canteen.getTen()}</h4>
		                                            
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
		                                            </div>
		                                        </div>
		                                    </a>
		                                </div>
                            	</c:forEach>
                            <%}%>
                            </div>
                        </div>   
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- modal -->
    
    <!-- Signup Form -->
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
		                        <a class="btn auth-form__back" href="./">QUAY LẠI</a>
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
		                    <a class="btn auth-form__back" href="./">QUAY LẠI</a>
		                    <button class="btn btn--primary" type="submit">ĐĂNG NHẬP</button>
		                </div>
		            </div>
		        </form>
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
    <script src="./assets/js/home.js"></script>
</body>
</html>