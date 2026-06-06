<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Title</title>
    <link rel="stylesheet" href="ve_noi_dia.css">
	<link rel="stylesheet" href="../../layout/StyleHeader.css">
	<link rel="stylesheet" href="../../layout/StyleFooter.css">
</head>
<body>
<!--start header-->
<%@ include file="../../layout/Header.jsp" %>
<!--end header-->

<!--main-->
<main class="container">
    <h1 class="page-title">Vé Máy Bay Nội Địa</h1>
    <div class="articles">
        <c:if test="${empty listVeNoiDia}">
            <div style="text-align: center; padding: 40px; font-size: 18px; color: #666; width: 100%;">
                Hiện tại chưa có chuyến bay nội địa nào hoạt động.
            </div>
        </c:if>
        <c:forEach var="ve" items="${listVeNoiDia}">
            <article class="article" style="margin-bottom: 20px; border: 1px solid #eee;">
                <div class="picture" style="min-width: 220px; max-width: 220px;">
                    <c:choose>
                        <c:when test="${ve.diemDen.contains('Hà Nội')}">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-ho-guom.webp" alt="">
                        </c:when>
                        <c:when test="${ve.diemDen.contains('Hồ Chí Minh')}">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-sai-gon.webp" alt="">
                        </c:when>
                        <c:when test="${ve.diemDen.contains('Đà Nẵng')}">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-da-nang.webp" alt="">
                        </c:when>
                        <c:when test="${ve.diemDen.contains('Huế')}">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-hue.webp" alt="">
                        </c:when>
                        <c:when test="${ve.diemDen.contains('Khánh Hòa') || ve.diemDen.contains('Nha Trang')}">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-nha-trang.webp" alt="">
                        </c:when>
                        <c:otherwise>
                            <img src="${pageContext.request.contextPath}/access/trang_chu/anh-nha-trang.webp" alt="">
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="infomations" style="display: flex; flex-direction: column; justify-content: space-between; width: 100%; padding: 20px;">
                    <div>
                        <h2 style="font-size: 22px; color: #438bf1; margin-bottom: 8px;">
                            ${ve.diemDi} ➔ ${ve.diemDen}
                        </h2>
                        <div style="font-size: 14px; color: #555; margin-bottom: 12px;">
                            <strong>Hãng bay:</strong> ${ve.hangBay} (${ve.soHieuChuyenBay}) | <strong>Hạng ghế:</strong> ${ve.hangGhe}
                        </div>
                        <div style="font-size: 14px; color: #666; line-height: 1.6;">
                            <div><strong>Sân bay đi:</strong> ${ve.sanBayDi} (${ve.diemDi}) | <strong>Giờ bay:</strong> ${ve.thoiGianKhoiHanh}</div>
                            <div><strong>Sân bay đến:</strong> ${ve.sanBayDen} (${ve.diemDen}) | <strong>Giờ hạ cánh:</strong> ${ve.thoiGianHaCanh}</div>
                        </div>
                    </div>
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 15px; border-top: 1px dashed #ddd; padding-top: 15px;">
                        <span style="font-size: 22px; font-weight: bold; color: #ff5722;">${ve.gia}đ</span>
                        <a href="${pageContext.request.contextPath}/DatVeController?idVe=${ve.idVe}&soLuong=1" style="background-color: #438bf1; color: white; padding: 10px 24px; border-radius: 6px; text-decoration: none; font-weight: bold; font-size: 15px; transition: background-color 0.2s;">
                            Đặt Vé Ngay
                        </a>
                    </div>
                </div>
            </article>
        </c:forEach>
    </div>
</main>
<!--end main-->

<%@include file="../../layout/Footer.jsp"%>

</body>
</html>
