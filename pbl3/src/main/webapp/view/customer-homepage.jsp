<%@page import="datdocantin.Model.LoaithucanModel"%>
<%@page import="datdocantin.Model.CartModel"%>
<%@page import="datdocantin.Model.LichsutimkiemModel"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="datdocantin.Model.KhachHangModel" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>
<%@ page import="datdocantin.Model.MonAnModel"%>
<%@ page import="java.util.Enumeration" %>
<%@page import="datdocantin.Model.CanteenModel"%>
<%	
	KhachHangModel khachhang = (KhachHangModel)session.getAttribute("khachhang");
	List<LichsutimkiemModel> searchHistory = (List<LichsutimkiemModel>)session.getAttribute("searchHistory"); 
	List<List<MonAnModel>> MonanListList = (List<List<MonAnModel>>)session.getAttribute("listMonan");
	List<CanteenModel> canteenList = (List<CanteenModel>)session.getAttribute("canteenList");
	List<CartModel> carts = (List<CartModel>)session.getAttribute("carts");
	List<LoaithucanModel> loaithucans = (List<LoaithucanModel>) session.getAttribute("loaithucan");
	String[] loaithucanSelected = (String[]) session.getAttribute("LoaithucanSelect");
	String[] thanhphanSelected = (String[]) session.getAttribute("ThanhphanSelect");
	String[] huongviSelected = (String[]) session.getAttribute("HuongviSelect");
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
                    
                    <form class="header__search" method="POST" action="./search">
                        <div class="header__search-input-wrap">
                            <input type="text" class="header__search-input" placeholder="Tìm kiếm món ăn" name="txtSearch" value="${txtSearch}">
                            <div class="header__search-history">
                                <!-- History Search -->
                                <ul class="header__search-history-list">
                                <%if (searchHistory!=null){ %> 
                            	<c:set var="lichsutimkiem" value="<%=searchHistory%>"/>
	                            	<c:forEach items="${lichsutimkiem}" var="lichsu">
								    	<li class="header__search-history-item">
	                                    	<a class="header__search-history-item-link" href="./search?txtSearch=${lichsu.getNoidung()}" >${lichsu.getNoidung()}</a>
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
                        <!-- Search Cantin -->
                            <!-- category -->
                        <form action="./Locmonan" method="POST">
                            <nav class="category" id="category">
                                <h3 class="category-heading">
                                    <i class="category-heading-icon fas fa-list-ul"></i> Bộ lọc tìm kiếm
                                </h3>
                                <div class="category-group"> 
                                    <div class="category-group-title">Loại thức ăn</div>
                                    <ul class="category-group-list">
                                    	<c:set var="loaithucans" value="<%=loaithucans%>"/> 
						                <c:forEach items="${loaithucans}" var="loaithucan"> 
						                	<li class="category-group-item">
						                		<c:set var="check" value="${false}"/>
												<c:set var="IDselected" value="<%=loaithucanSelected%>"/>
												<c:forEach items="${IDselected}" var="ID">
											        <c:if test="${loaithucan.getID_loaithucan() == ID}">
											            <c:set var="check" value="${true}"/>
											        </c:if>
											    </c:forEach>
	                                            <input type="checkbox" class="category-group-item-check" name="txtloaimonan" value="${loaithucan.getID_loaithucan()}" <c:if test="${check}">checked="checked"</c:if>> ${loaithucan.getLoaithucan()}
	                                        </li>
						                </c:forEach>
                                    </ul>
                                </div>
                                <div class="category-group">
                                    <div class="category-group-title">Thành phần chính</div>
                                    <ul class="category-group-list">
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="thanhphans" value="<%=thanhphanSelected%>"/>
											<c:forEach items="${thanhphans}" var="thanhphan">
										        <c:if test="${thanhphan == 'ga'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtThanhphan" value="ga" <c:if test="${check}">checked="checked"</c:if>> Thịt gà
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="thanhphans" value="<%=thanhphanSelected%>"/>
											<c:forEach items="${thanhphans}" var="thanhphan">
										        <c:if test="${thanhphan == 'heo'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtThanhphan" value="heo"<c:if test="${check}">checked="checked"</c:if>> Thịt heo
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="thanhphans" value="<%=thanhphanSelected%>"/>
											<c:forEach items="${thanhphans}" var="thanhphan">
										        <c:if test="${thanhphan == 'bo'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtThanhphan" value="bo"<c:if test="${check}">checked="checked"</c:if>> Thịt bò 
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="thanhphans" value="<%=thanhphanSelected%>"/>
											<c:forEach items="${thanhphans}" var="thanhphan">
										        <c:if test="${thanhphan == 'hai san'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtThanhphan" value="hai san"<c:if test="${check}">checked="checked"</c:if>> Hải sản
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="thanhphans" value="<%=thanhphanSelected%>"/>
											<c:forEach items="${thanhphans}" var="thanhphan">
										        <c:if test="${thanhphan == 'rau cu'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtThanhphan" value="rau cu"<c:if test="${check}">checked="checked"</c:if>> Rau củ
                                        </li>
                                    </ul>
                                </div>
                                <div class="category-group">
                                    <div class="category-group-title">Hương vị</div>
                                    <ul class="category-group-list">
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="huongvis" value="<%=huongviSelected%>"/>
											<c:forEach items="${huongvis}" var="huongvi">
										        <c:if test="${huongvi == 'chua'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtHuongvi" value="chua"<c:if test="${check}">checked="checked"</c:if>> Chua          
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="huongvis" value="<%=huongviSelected%>"/>
											<c:forEach items="${huongvis}" var="huongvi">
										        <c:if test="${huongvi == 'cay'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtHuongvi" value="cay"<c:if test="${check}">checked="checked"</c:if>> Cay
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="huongvis" value="<%=huongviSelected%>"/>
											<c:forEach items="${huongvis}" var="huongvi">
										        <c:if test="${huongvi == 'man'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtHuongvi" value="man"<c:if test="${check}">checked="checked"</c:if>> Mặn
                                        </li>
                                        <li class="category-group-item">
                                        	<c:set var="check" value="${false}"/>
											<c:set var="huongvis" value="<%=huongviSelected%>"/>
											<c:forEach items="${huongvis}" var="huongvi">
										        <c:if test="${huongvi == 'ngot'}">
										            <c:set var="check" value="${true}"/>
										        </c:if>
										    </c:forEach>
                                            <input type="checkbox" class="category-group-item-check" name="txtHuongvi" value="ngot"<c:if test="${check}">checked="checked"</c:if>> Ngọt
                                        </li>
                                    </ul>
                                </div>
                            
                                <div class="category-group">
                                    <div class="category-group-title">Khoảng Giá</div>
                                    <div class="category-group-filter">
                                        <input type="number" placeholder="VNĐ TỪ" class="category-group-filter-input" name="GiaStart" value='${String.format("%.3f", giabatdau).replace(",", ".")}'> 
                                        <i class="fas fa-arrow-right"></i>
                                        <input type="number" placeholder="VNĐ ĐẾN" class="category-group-filter-input" name="GiaEnd" value='${String.format("%.3f", giaketthuc).replace(",", ".")}'>                           
                                    </div>
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

                                <button type="submit" class="btn btn--primary category-group-filter-btn category-group--margin">LÀM MỚI</button>
                            </nav>
                    	</form>
                    </div>
                    <div class="col l-10 ">
                        <!-- home filter -->
                        <div class="home-filter">
                            <div class="home-filter-control">
                                <p class="home-filter-title">Sắp xếp theo:</p>
                                <a class="btn home-filter-btn ${tag == 'moinhat' ? 'btn--primary':''} ${tag == null ? 'btn--primary':''}" href="./moinhat">Mới nhất</a>
                                <a class="btn home-filter-btn ${tag == 'goiy' ? 'btn--primary':''}" href="./goiy">Gợi ý</a>
                                <a class="btn home-filter-btn ${tag == 'banchay' ? 'btn--primary':''}" href="./banchay">Bán chạy</a>
                                <div class="btn home-filter-sort ${tag == 'tangdan' ? 'btn--primary':''} ${tag == 'giamdan' ? 'btn--primary':''}">
                                    <p class="home-filter-sort-btn">Giá</p>
                                    <i class="fas fa-sort-amount-down-alt"></i>
                                    <ul class="home-filter-sort-list">
                                        <li>
                                            <a href="./giamdan" class="home-filter-sort-item-link ${tag == 'giamdan' ? 'color-index':''}">
                                                Giảm dần
                                                <i class="fas fa-sort-amount-down-alt"></i>
                                            </a>
                                        </li>
                                        <li> 
                                            <a href="./tangdan" class="home-filter-sort-item-link ${tag == 'tangdan' ? 'color-index':''}">
                                                Tăng dần
                                                <i class="fas fa-sort-amount-up-alt"></i>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="home-filter-page">
                            	<div class="home-filter-page-number" id="page1">
                                    <p class="home-filter-page-now">1</p>/<%=MonanListList.size()%>
                                </div>
                            <% for (int i=2; i<=MonanListList.size(); i++) { %>
                                <div class="home-filter-page-number hide-block" id="page<%=i%>">
                                    <p class="home-filter-page-now"><%=i%></p>/<%=MonanListList.size()%>
                                </div>
                            <% } %>
                                <div class="home-filter-page-control">
                                    <a href="#" class="home-filter-page-btn home-filter-page-btn--disable" id="page-back">
                                        <i class="fas fa-angle-left"></i>
                                    </a>
                                    <% if (MonanListList.size()<2) { %>
	                                    <a href="#" class="home-filter-page-btn home-filter-page-btn--disable" id="page-next">
	                                        <i class="fas fa-angle-right"></i>
	                                    </a>
                                    <% } else { %>
                                    	<a href="#" class="home-filter-page-btn" id="page-next">
	                                        <i class="fas fa-angle-right"></i>
	                                    </a>
                                    <% }  %>
                                </div>
                            </div>
                        </div>
                      
                        <!-- home product -->
                        <% if (MonanListList.size()>0) { %>
                        		<div class="home-product" id="1">        
		                            <div class="row sm-gutter">
		                            	<c:set var="MonanList" value="<%=MonanListList.get(0)%>"/>
		                            	<c:forEach items="${MonanList}" var="monan">
		                            		<div class="col l-2-4 product-link">
			                                        <a class="home-product-item-link" href="#">
		  	                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())});"></div>
		 	                                            <div class="home-product-item__info">
			                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
			                                                <div class="home-product-item__price"> 
				                                                <c:if test="${monan.getGiacu() > monan.getGiahientai()}">
				                                                    <p class="home-product-item__price-old">${String.format("%.3f", monan.getGiacu())} VNĐ</p>
					                                                </c:if>
				                                                    <p class="home-product-item__price-new">${String.format("%.3f", monan.getGiahientai())} VNĐ</p>
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
			                                        </a>
			                                       <a class="home-product-item-footer" href="./addtocart?id_monan=${monan.getID_monan()}">Thêm vào giỏ hàng </a>
			                                    </div>
		                            	</c:forEach>
		                            </div>
		                        </div>  
		                <% } %>
                        <% for (int i=1; i<MonanListList.size(); i++) { %>
		                        <div class="home-product hide-block" id="<%=i+1%>">        
		                            <div class="row sm-gutter">
		                            	<c:set var="MonanList" value="<%=MonanListList.get(i)%>"/>
		                            	<c:forEach items="${MonanList}" var="monan">
		                            		<div class="col l-2-4 product-link">
			                                        <a class="home-product-item-link" href="#">
		  	                                            <div class="home-product-item__img" style="background-image: url(data:image/jpeg;base64,${Base64.getEncoder().encodeToString(monan.getHinhanhchinh())});"></div>
		 	                                            <div class="home-product-item__info">
			                                                <h4 class="home-product-item__name">${monan.getTenmon()}</h4>
			                                                <div class="home-product-item__price"> 
				                                                <c:if test="${monan.getGiacu() > monan.getGiahientai()}">
				                                                    <p class="home-product-item__price-old">${String.format("%.3f", monan.getGiacu())} VNĐ</p>
					                                                </c:if>
				                                                    <p class="home-product-item__price-new">${String.format("%.3f", monan.getGiahientai())} VNĐ</p>
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
			                                        </a>
			                                       <a class="home-product-item-footer" href="./addtocart?id_monan=${monan.getID_monan()}">Thêm vào giỏ hàng </a>
			                                    </div>
		                            	</c:forEach>
		                            </div>
		                        </div>   
	                    <% } %>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- modal -->
    
    <!-- Signup Form -->
    
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
				<form action="./ChangeInfo" method="post" class="form-info" enctype="multipart/form-data">
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
		
		<div class="modal  ${showcanteen == 'flex' ? 'display-flex' : ''}" id="form-chosseCantin">
			<div class="modal__body">
				<form class="auth-form list__custumer" method="POST" action="./SearchCanteen">
					<div class="auth-form__container">
						<div class="auth-form__header">
							<h3 class="auth-form__heading">Tìm kiếm Cantin </h3>
						</div>
						<div class="search-group form-choose-canteen">
							<div class="">
								<div class="search-group-title">Nhập tên hoặc mã Cantin</div>
								<input class="search-group-input" type="search" name="txtSearchCanteen" value="${txtSearchCanteen}">
							</div>
							
							<div class="">
								<div class="search-group-title">Chọn địa chỉ để tìm kiếm</div>
								<select name="txtTinh" class="search-group-item" id="province">
									<option value="-1">Chọn tỉnh thành</option>
								</select>
								<select name="txtHuyen" class="search-group-item" id="district">
									<option value="-1">Chọn quận/huyện</option>
								</select>
								<select name="txtXa" class="search-group-item" id="town">
									<option value="-1">Chọn phường/xã</option>
								</select>
								<div style="display: none">
		                        	<span id="tinh">${tinh}</span>
		                           	<span id="huyen">${huyen}</span>
		                           	<span id="xa">${xa}</span>
		                        </div>
							</div>
						</div>
						<div class="search-group">
							<div class="row sm-gutter">
							<%if (canteenList!=null){ %> 
                            	<c:set var="canteenList" value="<%=canteenList%>"/>
                            	<c:forEach items="${canteenList}" var="Canteen">
                            		<div class="col l-2-4">
		                                    <a class="home-product-item-link" href="./ChangeCanteen?id_canteen=${Canteen.getID_canteen()}">
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
						<div class="auth-form__control">
							<a class="btn auth-form__back" href="./">QUAY LẠI</a>
							<button class="btn btn--primary" type="submit">TÌM KIẾM</button>
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
    <script src="./assets/js/customer.js"></script>
</body>
</html>