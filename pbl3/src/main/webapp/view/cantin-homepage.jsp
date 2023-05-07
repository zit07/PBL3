<%@page import="datdocantin.Model.LichsutimkiemModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="datdocantin.Model.CanteenModel"%>
<%@page import="datdocantin.Model.GiohoatdongModel"%>
<%@page import="datdocantin.Model.MonAnModel"%>
<%	
	CanteenModel canteen = (CanteenModel)session.getAttribute("canteen");
	List<MonAnModel> MonanList = (List<MonAnModel>)session.getAttribute("listMonan");
	List<LichsutimkiemModel> searchHistory = (List<LichsutimkiemModel>)session.getAttribute("searchHistory");  
	List<GiohoatdongModel> giohoatdongList = (List<GiohoatdongModel>) session.getAttribute("listGiohoatdong");
	String danhmuc = (String)request.getAttribute("danhmuc"); 
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
	                        <a href="./getProduct" class="header__logo-link">
	                            <img src="./assets/img/logo/logo.png" class="header__logo-img">
	                        </a>
	                    </div>
	                    
	                    <form class="header__search" method="POST" action="./search?id_user=${canteen.getId()}">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="${txtSearch}">
                            <div class="header__search-history">
                                <!-- History Search -->
                                <ul class="header__search-history-list">
	                            	<c:forEach items="${searchHistory}" var="lichsu">
								    	<li class="header__search-history-item">
	                                    	<a class="header__search-history-item-link" href="./search?id_user=${canteen.getId()}&txtSearch=${lichsu.getNoidung()}" >${lichsu.getNoidung()}</a>
	                                    	<a class="btn-del-history" href="./delHistorySearch?id=${lichsu.getId()}">
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
									<a class="navbar-link <%if(danhmuc==null) {%>choose<% } %>" href="./getProduct"> Tất cả món ăn</a> 
									<a class="navbar-link <%if(danhmuc!=null && danhmuc.equals("dangban")) {%>choose<% } %>" href="./getProduct?danhmuc=dangban"> Món ăn đang kinh doanh</a> 
									<a class="navbar-link <%if(danhmuc!=null && danhmuc.equals("ngungban")) {%>choose<% } %>" href="./getProduct?danhmuc=ngungban">Món ăn ngừng kinh doanh</a> 
									<a class="navbar-link <%if(danhmuc!=null && danhmuc.equals("daxoa")) {%>choose<% } %>" href="./getProduct?danhmuc=daxoa">Món ăn đã xoá</a> 
									<a class="navbar-link <%if(danhmuc!=null && danhmuc.equals("dondatmon")) {%>choose<% } %>" href="./">  Đơn đặt món</a> 
									<a class="navbar-link <%if(danhmuc!=null && danhmuc.equals("doanhthu")) {%>choose<% } %>" href="./">  Xem doanh thu</a> 
								</div>
							</nav>
						</div>
						<div class="col l-10">
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
							
							<%if (danhmuc!=null) { %>
		                        <div class="home-product">  
		                            <div class="row sm-gutter productoverview">
		                                <div class="list-product__title">
		                                    <div class="col l-2-4 home-product-item__title">Tổng quan</div>
		                                    <div class="col l-2-4 home-product-item__title">Mô tả</div>
		                                    <div class="col l-1 home-product-item__title">Thành phần</div>
		                                    <div class="col l-1 home-product-item__title">Hương vị</div>
		                                    <div class="col l-1 home-product-item__title">Ngày tạo</div>
		                                    <div class="col l-1 home-product-item__title">Trạng thái</div>
		                                    <div class="col l-1 home-product-item__title"></div>
		                                </div>
		                            </div>
		                        </div>
							<%} %>
	                        <!-- home product -->
	                        <div class="home-product">   
	                        <%if (danhmuc==null){ %>      
	                        	<div class="row sm-gutter">
	                            <%if (MonanList!=null) { %> 
	                            	<c:set var="MonanList" value="<%=MonanList%>"/>
	                            	<c:forEach items="${MonanList}" var="monan">
	                            		<div class="col l-2-4">
		                                        <a class='home-product-item-link ${monan.getTrangthai().equals("ngung ban") ? "product-deleted" : ""}' href="#">
	  	                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())});"></div>
	 	                                            <div class="home-product-item__info">
		                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
		                                                <div class="home-product-item__price"> 
			                                                <c:if test="${ Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}">
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
		                                                <c:if test="${Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}">
															<c:set var="giamgia" value="${Math.round((monan.giacu - monan.giahientai) / monan.giacu * 100)}" />
		                                                	<div class="home-product-item__sale-off">
														    	<div class="home-product-item__sale-off-value">${giamgia} %</div>
																<div class="home-product-item__sale-off-label">GIẢM</div>
															</div>	 
														</c:if>
		                                            </div>
		                                            <div class="home-product-item-footer">Xem chi tiet</div>
		                                        </a>
		                                    </div>
	                            	</c:forEach>
	                            <%}%> 
	                            </div>
                            <%} else if (!danhmuc.equals("daxoa")) { %> 
	                            <div class="row sm-gutter productoverview">
	                            <%if (MonanList!=null){ %> 
	                            	<c:set var="MonanList" value="<%=MonanList%>"/>
	                            		<c:forEach items="${MonanList}" var="monan"> 
									        <div class="list-product">
			                                    <div class="col l-2-4 home-product-item">
			                                        <a class="home-product-item-link" href="#">
			                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,<c:out value="${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())}"/>);"></div>
			                                            <div class="home-product-item__info">
			                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
			                                                <div class="home-product-item__price">
			                                                	<c:if test='${Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}'>
																    <p class="home-product-item__price-old">${monan.getGiacu()}đ</p>
																</c:if>
			                                                    <p class="home-product-item__price-new">${monan.getGiahientai()}đ</p>
			                                                </div>
			                                                <div class="home-product-item__footer">
			                                                    <div class="home-product-item__rating-star">
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-uncheck far fa-star"></i>
			                                                    </div>
			                                                    <div class="home-product-item__saled">Đã bán ${monan.getDaban()}</div>
			                                                </div>
			                                                <c:if test="${Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}">
																<c:set var="giamgia" value="${Math.round((monan.giacu - monan.giahientai) / monan.giacu * 100)}" />
			                                                	<div class="home-product-item__sale-off">
															    	<div class="home-product-item__sale-off-value">${giamgia} %</div>
																	<div class="home-product-item__sale-off-label">GIẢM</div>
																</div>	 
															</c:if>
			                                            </div>
			                                            <div class="home-product-item-footer">Xem chi tiet</div>
			                                        </a>
			                                    </div>
			                                    <div class="col l-2-4 home-product-item">
			                                        <span class="Product-Description">${monan.getMota()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getThanhphan()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getHuongvi()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getNgaytao()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getTrangthai().equals("dang ban") ? "Đang bán" : "Ngưng bán"}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">
			                                            <a href="" class="btn btn--primary home-product-btn edit-product" name="link-editproduct" id="${monan.getId()}">Sửa</a>
			                                            <c:if test='${monan.getTrangthai().equals("dang ban")}'>
			                                            	<a href="./Editproduct?idmonan=${monan.getId()}&k=ngungban" class="btn btn--primary home-product-btn stop-sold-product">Ngưng bán</a>
			                                            </c:if>
			                                            <c:if test='${monan.getTrangthai().equals("ngung ban")}'>
			                                            	<a href="./Editproduct?idmonan=${monan.getId()}&k=moban" class="btn btn--primary home-product-btn continue-sold-product">Mở bán</a>
			                                            </c:if>
			                                            <a href="./DeleteProduct?idmonan=${monan.getId()}" class="btn btn--primary home-product-btn delete-product">Xoá</a>
			                                        </span>
			                                    </div>
			                                </div>
										</c:forEach>   
									<%}%>                  
	                            </div>
	                        <%} else { %> 
	                            <div class="row sm-gutter productoverview">
	                            <%if (MonanList!=null){ %> 
	                            	<c:set var="MonanList" value="<%=MonanList%>"/>
	                            		<c:forEach items="${MonanList}" var="monan"> 
									        <div class="list-product">
			                                    <div class="col l-2-4 home-product-item">
			                                        <a class="home-product-item-link" href="#">
			                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,<c:out value="${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())}"/>);"></div>
			                                            <div class="home-product-item__info">
			                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
			                                                <div class="home-product-item__price">
			                                                	<c:if test='${Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}'>
																    <p class="home-product-item__price-old">${monan.getGiacu()}đ</p>
																</c:if>
			                                                    <p class="home-product-item__price-new">${monan.getGiahientai()}đ</p>
			                                                </div>
			                                                <div class="home-product-item__footer">
			                                                    <div class="home-product-item__rating-star">
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-checked far fa-star"></i>
			                                                        <i class="star-uncheck far fa-star"></i>
			                                                    </div>
			                                                    <div class="home-product-item__saled">Đã bán ${monan.getDaban()}</div>
			                                                </div>
			                                                <c:if test="${Double.valueOf(monan.getGiacu()) > Double.valueOf(monan.getGiahientai())}">
																<c:set var="giamgia" value="${Math.round((monan.giacu - monan.giahientai) / monan.giacu * 100)}" />
			                                                	<div class="home-product-item__sale-off">
															    	<div class="home-product-item__sale-off-value">${giamgia} %</div>
																	<div class="home-product-item__sale-off-label">GIẢM</div>
																</div>	 
															</c:if>
			                                            </div>
			                                            <div class="home-product-item-footer">Xem chi tiet</div>
			                                        </a>
			                                    </div>
			                                    <div class="col l-2-4 home-product-item">
			                                        <span class="Product-Description">${monan.getMota()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getThanhphan()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getHuongvi()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getNgaytao()}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">${monan.getTrangthai().equals("dang ban") ? "Đang bán" : "Ngưng bán"}</span>
			                                    </div>
			                                    <div class="col l-1 home-product-item">
			                                        <span class="Product-Description">
			                                        	<a href="./Restore?idmonan=${monan.getId()}" class="btn btn--primary home-product-btn ">Khôi phục</a>
			                                            <a href="./DeleteProductForever?idmonan=${monan.getId()}" class="btn btn--primary home-product-btn delete-product">Xoá vĩnh viễn</a>
			                                        </span>
			                                    </div>
			                                </div>
										</c:forEach>   
									<%}%>                  
	                            </div>
	                        <%}%>
	                        </div>   
	                    </div>
	                </div>
	            </div>
	        </div>
	
	    </div>
	    <!-- modal -->
	    <div class="modal" id="form-info">
	        <div class="modal__body" >
	            <!-- authen change info-->
	            <form action="./ChangeInfo?id_canteen=${canteen.getId()}" method="post" class="form-info" enctype="multipart/form-data">
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
	                           		<span id="tinh">${canteen.getTinh()}</span>
	                           		<span id="huyen">${canteen.getHuyen()}</span>
	                           		<span id="xa">${canteen.getXa()}</span>
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
	                            <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
	                            <button class="btn btn--primary" type="submit">Lưu</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
        <div class="modal" id="form-changepassword" style="display: ${display_form__changepass}" >
            <div class="modal__body">
                <form action="./ChangePassword?id_canteen=${canteen.getId()}" method="post" class="formChangePass">
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
                <form action="./ChangePin?id_canteen=${canteen.getId()}" method="post" class="auth-form">
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
                                <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
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
	            <form action="./Addproduct?id_canteen=${canteen.getId()}" method="post" class="form__info" enctype="multipart/form-data">
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
	                                    <span>Loại thức ăn:</span>
	                                </div>
	                                <div class="auth-form__title_row">
	                                    <span>Giá:</span>
	                                </div>
	                            </div>
	                            <div class="auth-form__group_row">
	                                <input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: Chua,cay,mặn,..." name="txtHuongvi" value="" required> 
	                                <div class="auth-form__input_info_row">
	                                     <select name="txtLoai" id="" class="auth-form__input_info_select" required>
	                                        <option value="-1"></option>
	                                        <option value="thit">Món thịt</option>
	                                        <option value="haisan">Món hải sản</option>
	                                        <option value="nuoc">Món nước</option>
	                                        <option value="kho">Món khô</option>
	                                        <option value="chien">Món chiên</option>
	                                        <option value="xao">Món xào</option>
	                                        <option value="chay">Món chay</option>
	                                    </select>
	                                </div>
									<input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: 50000" name="txtGia" value="" pattern="[0-9]+" required>
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
	                            <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
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
	            <form action="./ChangeTimeOpen?id_canteen=${canteen.getId()}" method="post" id="form-editTime">
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
	                            <a class="btn auth-form__back" href="./">TRANG CHỦ</a>
	                            <button class="btn btn--primary" type="submit">Lưu</button>
	                        </div>
	                    </div>
	                </div>
	            </form>
	        </div> 
	    </div>
	    <c:forEach items="${MonanList}" var="monan">
	    	<div class="modal" name="form-editProduct" id="${monan.getId()}">
		        <div class="modal__body" >
		        <!-- authen change info-->
		            <form action="./Editproduct?id_monan=${monan.getId()}&giacu=${monan.getGiahientai()}" method="post" class="form__info" enctype="multipart/form-data">
		                <div class="auth-form__addproduct">
		                    <div class="auth-form__container">
		                        <div class="auth-form__header">
		                            <h3 class="auth-form__heading">Sửa thông tin món ăn</h3>
		                        </div>
		                        <div class="auth-form__form">
		                            <div class="auth-form__title">
		                                <span>Tên món ăn:</span>
		                            </div>
		                            <div class="auth-form__group">
		                                <input type="text" class="auth-form__input_info" name="txtTenmonNew" value="${monan.getTenmon()}" required>
		                            </div>
		                            <div class="auth-form__title">
		                                <span>Mô tả:</span>
		                            </div>
		                            <div class="auth-form__group" >
		                                <textarea class="auth-form__input_info" name="txtMotaNew" style="height: 80px; margin: none;" required>${monan.getMota()}</textarea>
		                            </div>
		                            <div class="auth-form__title">
		                                <span>Thành phần:</span>
		                            </div>
		                            <div class="auth-form__group">
		                                <input type="text" class="auth-form__input_info" name="txtThanhphanNew" value="${monan.getThanhphan()}" required>
		                            </div>
		                            <div class="auth-form__group_row">
		                                <div class="auth-form__title_row">
		                                    <span>Hương vị:</span>
		                                </div>
		                                <div class="auth-form__title_row">
		                                    <span>Loại thức ăn:</span>
		                                </div>
		                                <div class="auth-form__title_row">
		                                    <span>Giá:</span>
		                                </div>
		                            </div>
		                            <div class="auth-form__group_row">
		                                <input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: Chua,cay,mặn,..." name="txtHuongviNew" value="${monan.getHuongvi()}" required> 
		                                <div class="auth-form__input_info_row">
		                                     <select name="txtLoaiNew" id="" class="auth-form__input_info_select" required>
		                                        <option value="-1"></option>
												<option value="thit" ${monan.getLoaithucan().equals("1") ? "selected" : ""}>Món thịt</option>
												<option value="haisan" ${monan.getLoaithucan().equals("haisan") ? "selected" : ""}>Món hải sản</option>
												<option value="nuoc" ${monan.getLoaithucan().equals("nuoc") ? "selected" : ""}>Món nước</option>
												<option value="kho" ${monan.getLoaithucan().equals("kho") ? "selected" : ""}>Món khô</option>
												<option value="chien" ${monan.getLoaithucan().equals("chien") ? "selected" : ""}>Món chiên</option>
												<option value="xao" ${monan.getLoaithucan().equals("xao") ? "selected" : ""}>Món xào</option>
												<option value="chay" ${monan.getLoaithucan().equals("chay") ? "selected" : ""}>Món chay</option>
		                                    </select>
		                                </div>
										<input type="text" class="auth-form__input_info_row" placeholder="Ví dụ: 50000" name="txtGiaNew" value="${monan.getGiahientai().replaceAll('\\.', '')}" pattern="[0-9]+" required>
		                            </div>
		                            <div class="auth-form__title">
		                                <span>Thêm hình ảnh (ít nhất 1 ảnh, ảnh đầu tiên được lấy làm ảnh chính):</span>
		                            </div>
									<div class="auth-form__group add-product-img">
										<%for (int i = 1; i <= 8; i++) {%>
										<div class="img-add-product">
											<label for="img${monan.getId()}<%=i%>"> 
											<% if (i == 1) { %> <img src="data:image/jpeg;base64,<c:out value='${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())}'/>" class="img-add-product__img"/>
											<% } else { %> <img src="./assets/img/addproduct.png" class="img-add-product__img"/> <% } %>  
											</label> 
											<input class="img-add-product__input" type="file" name="img<%=i%>New" id="img${monan.getId()}<%=i%>"/>
										</div> 
										<% } %>
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
		</c:forEach>
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