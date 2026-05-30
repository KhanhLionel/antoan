
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
</div>


<%@ include file="layout/Footer.jsp"%>


</body>
</html>
