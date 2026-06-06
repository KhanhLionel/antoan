
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String quocGiaNhatBan = "\u00a0Nh\u1eadt B\u1ea3n";
    String quocGiaSingapore = "\u00a0Singapore";
    String quocGiaTrungQuoc = "\u00a0Trung Qu\u1ed1c";
    String quocGiaHoaKy = "Hoa K\u1ef3";
    String quocGiaArgentina = "Argentina";
    request.setAttribute("quocGiaNhatBan", quocGiaNhatBan);
    request.setAttribute("quocGiaSingapore", quocGiaSingapore);
    request.setAttribute("quocGiaTrungQuoc", quocGiaTrungQuoc);
    request.setAttribute("quocGiaHoaKy", quocGiaHoaKy);
    request.setAttribute("quocGiaArgentina", quocGiaArgentina);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Web bán vé máy bay</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleHeader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleFooter.css">
</head>

<body>
<%
    String messageLogin = (String) request.getAttribute("messageLogin");
    if (messageLogin != null) {
%>
<script>
    alert("<%= messageLogin %>")
</script>
<%
    }
%>

<%
    String messageVeDaDat = (String) request.getAttribute("messageVeDaDat");
    if (messageVeDaDat != null) {
%>
<script>
    alert("<%= messageVeDaDat %>")
</script>
<%
    }
%>

<%
    String messageAdmin = (String) request.getAttribute("messageAdmin");
    if (messageAdmin != null) {
%>
<script>
    alert("<%= messageAdmin %>")
</script>
<%
    }
%>
<%@ include file="layout/Header.jsp"%>


<div>
    <div class="container">
	<div class="slideshow-container" style="position: relative; max-width: 100%; height: 400px; overflow: hidden; margin-top: 20px; margin-bottom: 20px; border-radius: 12px; box-shadow: 0 6px 15px rgba(0,0,0,0.15);">
		<div class="mySlides fade" style="display: none; height: 100%;">
			<img src="${pageContext.request.contextPath}/access/trang_chu/anh-sai-gon.jpg" style="width: 100%; height: 100%; object-fit: cover;">
			<div class="slide-text" style="position: absolute; bottom: 30px; left: 30px; color: white; background: rgba(0,0,0,0.6); padding: 12px 20px; border-radius: 6px; font-family: Arial, sans-serif;">
				<h2 style="margin: 0 0 5px 0; font-size: 22px;">Khám Phá Sài Gòn Năng Động</h2>
				<p style="margin: 0; font-size: 13px;">Trải nghiệm nhịp sống đô thị sôi động bậc nhất Việt Nam</p>
			</div>
		</div>
		<div class="mySlides fade" style="display: none; height: 100%;">
			<img src="${pageContext.request.contextPath}/access/trang_chu/anh-trungquoc.jpg" style="width: 100%; height: 100%; object-fit: cover;">
			<div class="slide-text" style="position: absolute; bottom: 30px; left: 30px; color: white; background: rgba(0,0,0,0.6); padding: 12px 20px; border-radius: 6px; font-family: Arial, sans-serif;">
				<h2 style="margin: 0 0 5px 0; font-size: 22px;">Vẻ Đẹp Trung Hoa Kỳ Vĩ</h2>
				<p style="margin: 0; font-size: 13px;">Ghé thăm những địa danh lịch sử và phong cảnh tuyệt đẹp</p>
			</div>
		</div>
		<div class="mySlides fade" style="display: none; height: 100%;">
			<img src="${pageContext.request.contextPath}/access/trang_chu/anh-bulgaria.jpg" style="width: 100%; height: 100%; object-fit: cover;">
			<div class="slide-text" style="position: absolute; bottom: 30px; left: 30px; color: white; background: rgba(0,0,0,0.6); padding: 12px 20px; border-radius: 6px; font-family: Arial, sans-serif;">
				<h2 style="margin: 0 0 5px 0; font-size: 22px;">Hương Sắc Hoa Hồng Bulgaria</h2>
				<p style="margin: 0; font-size: 13px;">Khám phá nét cổ kính và lãng mạn của châu Âu</p>
			</div>
		</div>
		<div class="mySlides fade" style="display: none; height: 100%;">
			<img src="${pageContext.request.contextPath}/access/trang_chu/anh-da-nang.webp" style="width: 100%; height: 100%; object-fit: cover;">
			<div class="slide-text" style="position: absolute; bottom: 30px; left: 30px; color: white; background: rgba(0,0,0,0.6); padding: 12px 20px; border-radius: 6px; font-family: Arial, sans-serif;">
				<h2 style="margin: 0 0 5px 0; font-size: 22px;">Thành Phố Đáng Sống Đà Nẵng</h2>
				<p style="margin: 0; font-size: 13px;">Hành trình thư giãn bên bãi biển tuyệt đẹp và các cây cầu lung linh</p>
			</div>
		</div>

		<a class="prev-slide" onclick="plusSlides(-1)" style="cursor: pointer; position: absolute; top: 50%; width: auto; margin-top: -22px; padding: 16px; color: white; font-weight: bold; font-size: 18px; transition: 0.6s ease; border-radius: 0 3px 3px 0; user-select: none; background: rgba(0,0,0,0.3); left: 0;">&#10094;</a>
		<a class="next-slide" onclick="plusSlides(1)" style="cursor: pointer; position: absolute; top: 50%; width: auto; margin-top: -22px; padding: 16px; color: white; font-weight: bold; font-size: 18px; transition: 0.6s ease; border-radius: 3px 0 0 3px; user-select: none; background: rgba(0,0,0,0.3); right: 0;">&#10095;</a>
	</div>

	<div style="text-align:center; margin-bottom: 20px;">
		<span class="slide-dot" onclick="currentSlide(1)" style="cursor: pointer; height: 10px; width: 10px; margin: 0 2px; background-color: #bbb; border-radius: 50%; display: inline-block; transition: background-color 0.6s ease;"></span>
		<span class="slide-dot" onclick="currentSlide(2)" style="cursor: pointer; height: 10px; width: 10px; margin: 0 2px; background-color: #bbb; border-radius: 50%; display: inline-block; transition: background-color 0.6s ease;"></span>
		<span class="slide-dot" onclick="currentSlide(3)" style="cursor: pointer; height: 10px; width: 10px; margin: 0 2px; background-color: #bbb; border-radius: 50%; display: inline-block; transition: background-color 0.6s ease;"></span>
		<span class="slide-dot" onclick="currentSlide(4)" style="cursor: pointer; height: 10px; width: 10px; margin: 0 2px; background-color: #bbb; border-radius: 50%; display: inline-block; transition: background-color 0.6s ease;"></span>
	</div>

	<style>
		.fade {
			animation-name: slideFade;
			animation-duration: 1.5s;
		}
		@keyframes slideFade {
			from {opacity: .4}
			to {opacity: 1}
		}
	</style>

	<script>
		let slideIndex = 0;
		let slideTimeout;
		showSlides();

		function showSlides() {
			let i;
			let slides = document.getElementsByClassName("mySlides");
			let dots = document.getElementsByClassName("slide-dot");
			for (i = 0; i < slides.length; i++) {
				slides[i].style.display = "none";
			}
			slideIndex++;
			if (slideIndex > slides.length) {slideIndex = 1}
			for (i = 0; i < dots.length; i++) {
				dots[i].style.backgroundColor = "#bbb";
			}
			if (slides[slideIndex-1]) {
				slides[slideIndex-1].style.display = "block";
			}
			if (dots[slideIndex-1]) {
				dots[slideIndex-1].style.backgroundColor = "#438bf1";
			}
			slideTimeout = setTimeout(showSlides, 4000);
		}

		function plusSlides(n) {
			clearTimeout(slideTimeout);
			let slides = document.getElementsByClassName("mySlides");
			slideIndex += n - 1;
			if (slideIndex < 0) {
				slideIndex = slides.length - 1;
			}
			showSlides();
		}

		function currentSlide(n) {
			clearTimeout(slideTimeout);
			slideIndex = n - 1;
			showSlides();
		}
	</script>
		<div class="title-selection">
			<h1>Chuyến bay nội địa nổi bật</h1>
		</div>

		<div class="list-card-selection">
			<c:url var="urlHaNoi" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="Việt Nam" />
				<c:param name="diemDen" value="Thủ đô Hà Nội" />
			</c:url>
			<a href="${urlHaNoi}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-ho-guom.webp" alt="">
					<p>Vé máy bay đi Hà Nội</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlSaiGon" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="Việt Nam" />
				<c:param name="diemDen" value="Thành phố Hồ Chí Minh" />
			</c:url>
			<a href="${urlSaiGon}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-sai-gon.webp" alt="">
					<p>Vé máy bay đi TP.HCM</p>
					<span>1.200.000đ</span>
				</div>
			</a>

			<c:url var="urlDaNang" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="Việt Nam" />
				<c:param name="diemDen" value="Thành phố Đà Nẵng" />
			</c:url>
			<a href="${urlDaNang}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-da-nang.webp" alt="">
					<p>Vé máy bay đi Đà Nẵng</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlHue" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="Việt Nam" />
				<c:param name="diemDen" value="Thành phố Huế" />
			</c:url>
			<a href="${urlHue}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-hue.webp" alt="">
					<p>Vé máy bay đi Huế</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlNhaTrang" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="Việt Nam" />
				<c:param name="diemDen" value="Tỉnh Khánh Hòa" />
			</c:url>
			<a href="${urlNhaTrang}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-nha-trang.webp" alt="">
					<p>Vé máy bay đi Nha Trang</p>
					<span>800.000đ</span>
				</div>
			</a>
		</div>

		<div class="btn-dat-ve-selection">
            <c:url var="urlTimVe" value="/ThongTinTimVeController">
                <c:param name="khoiHanh" value="Việt Nam" />
                <c:param name="haCanh" value="Việt Nam" />
            </c:url>
            <a href="${urlTimVe}">Đặt Vé</a>
		</div>

	</div>

	<div class="container">

		<div class="title-selection">
			<h1>Chuyến bay quốc tế nổi bật</h1>
		</div>

		<div class="list-card-selection">
			<c:url var="urlArgentina" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="${quocGiaArgentina}" />
			</c:url>
			<a href="${urlArgentina}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-argentina.jpg" alt="">
					<p>Vé máy bay đi Argentina</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlTrungQuoc" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="${quocGiaTrungQuoc}" />
			</c:url>
			<a href="${urlTrungQuoc}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-trungquoc.jpg" alt="">
					<p>Vé máy bay đi Trung Quốc</p>
					<span>1.200.000đ</span>
				</div>
			</a>

			<c:url var="urlHoaKy" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="${quocGiaHoaKy}" />
			</c:url>
			<a href="${urlHoaKy}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-mi.webp" alt="">
					<p>Vé máy bay đi Mĩ</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlSingapore" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="${quocGiaSingapore}" />
			</c:url>
			<a href="${urlSingapore}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-singapore.webp" alt="">
					<p>Vé máy bay đi Singapore</p>
					<span>800.000đ</span>
				</div>
			</a>

			<c:url var="urlNhatBan" value="/ThongTinTimVeController">
				<c:param name="khoiHanh" value="Việt Nam" />
				<c:param name="haCanh" value="${quocGiaNhatBan}" />
			</c:url>
			<a href="${urlNhatBan}" class="card-selection-link">
				<div class="card-selection">
					<img src="${pageContext.request.contextPath}/access/trang_chu/anh-nhat-ban.webp" alt="">
					<p>Vé máy bay đi Nhật Bản</p>
					<span>800.000đ</span>
				</div>
			</a>
		</div>

		<div class="btn-dat-ve-selection">
            <c:url var="urlTimVeQuocTe" value="/ThongTinTimVeController">
                <c:param name="khoiHanh" value="Việt Nam" />
                <c:param name="haCanh" value="${quocGiaNhatBan}" />
            </c:url>
            <a href="${urlTimVeQuocTe}">Đặt Vé</a>
		</div>

	</div>

	<div class="container selection-ve-quoc-te" style="margin-top: 50px; margin-bottom: 50px;">
		<div class="title-selection">
			<h1>Vé Khuyến Mãi</h1>
		</div>

		<div class="list-card-selection">
			<c:forEach var="item" items="${listDeXuatVe}">
				<a href="${pageContext.request.contextPath}/DatVeController?idVe=${item.veDto.idVe}&soLuong=1&voucherCode=${item.voucherCode}" class="card-selection-link">
					<div class="card-selection promo-card" style="position: relative; border: 1px solid #eee; padding-top: 15px;">
						<div class="badge-discount" style="position: absolute; top: 10px; left: 10px; background-color: #ff5722; color: white; padding: 3px 8px; border-radius: 5px; font-size: 11px; font-weight: bold;">
							${item.voucherCode} (-${item.phanTramGiam}%)
						</div>
						<p style="margin-top: 35px; font-size: 15px; font-weight: bold; text-align: center; color: #333;">
							${item.veDto.diemDi} ➔ ${item.veDto.diemDen}
						</p>
						<div style="font-size: 12px; color: #777; text-align: center; margin-bottom: 10px; line-height: 1.4;">
							<div>${item.veDto.hangBay} (${item.veDto.soHieuChuyenBay})</div>
							<div style="font-size: 11px; color: #999;">${item.veDto.thoiGianKhoiHanh}</div>
						</div>
						<div style="text-align: center; margin-top: 10px;">
							<span style="text-decoration: line-through; color: #999; font-size: 13px; margin-right: 5px;">${item.veDto.gia}đ</span>
							<span style="color: #ff5722; font-weight: bold; font-size: 16px;">${item.giaDaGiam}đ</span>
						</div>
						<div style="font-size: 11px; color: #438bf1; text-align: center; margin-top: 10px; font-weight: bold;">
							Còn lại: ${item.soLuongTon} vé
						</div>
					</div>
				</a>
			</c:forEach>
		</div>
	</div>
</div>


<%@ include file="layout/Footer.jsp"%>


</body>
</html>
