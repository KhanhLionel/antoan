<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Danh sách vé đã đặt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/list_ve_da_dat/list_ve_da_dat.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleHeader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleFooter.css">

</head>
<body>
<%@ include file="../../layout/Header.jsp" %>
<%
    String messageThanhToan = (String) request.getAttribute("messageThanhToan");
    if (messageThanhToan != null) {
%>
<script>
    alert("<%= messageThanhToan %>")
</script>
<%
    }
%>
<div class="list-ve">
    <h1>Danh sách vé máy bay đã đặt</h1>

    <div class="list-ve-item">
        <c:choose>
            <c:when test="${not empty listVeDaDat}">
                <c:forEach var="item" items="${listVeDaDat}">
            <div class="booked-card">
                <div class="booked-header" style="display: flex; justify-content: space-between; align-items: flex-start;">
                    <div class="airline-brand">
                        <span class="plane-icon">✈</span>
                        <span class="airline-name">${item.veDto.hangBay}</span>
                    </div>

                    <div class="booking-status-wrapper" style="display: flex; flex-direction: column; align-items: flex-end; gap: 5px;">
                        <div class="booking-status" style="background-color: #2ecc71; color: white; padding: 3px 8px; border-radius: 4px; font-size: 11px; font-weight: bold;">
                            Đã đặt thành công
                        </div>

                        <c:choose>
                            <c:when test="${item.signatureStatus == 1}">
                                <div class="sig-status valid" style="background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; padding: 4px 10px; border-radius: 4px; font-size: 11px; font-weight: bold; display: inline-flex; align-items: center; gap: 4px;">
                                    Chữ ký số: HỢP LỆ
                                </div>
                            </c:when>
                            <c:when test="${item.signatureStatus == 2}">
                                <div class="sig-status tampered" style="background-color: #f8d7da; color: #721c24; border: 2px solid #f5c6cb; padding: 6px 12px; border-radius: 4px; font-size: 11px; font-weight: bold; display: inline-flex; align-items: center; gap: 4px; animation: blinker 1.5s linear infinite;">
                                    CẢNH BÁO: Thông tin bị thay đổi trái phép!
                                </div>
                                <style>
                                    @keyframes blinker {
                                        50% { opacity: 0.3; }
                                    }
                                </style>
                            </c:when>
                            <c:otherwise>
                                <div class="sig-status unsigned" style="background-color: #e2e3e5; color: #383d41; border: 1px solid #d6d8db; padding: 4px 10px; border-radius: 4px; font-size: 11px; font-weight: bold;">
                                    Chưa bảo mật bằng chữ ký số
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
                    <div class="flight-card">
                        <div class="card-header">
                            <span class="airline-name">
                                <svg class="plane-icon" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                    <path d="M21 16V14.5L14 10V3C14 2.45 13.55 2 13 2H11C10.45 2 10 2.45 10 3V10L3 14.5V16L10 13V20L8.5 22H9.5L12 21L14.5 22H15.5L14 20V13L21 16Z" fill="currentColor"/>
                                </svg>
                                ${item.veDto.hangBay}
                            </span>
                            <div class="flight-times">
                                <div class="time-detail departure">
                                    <span class="label">Cất cánh</span>
                                    <span>${item.veDto.thoiGianKhoiHanh}</span>
                                </div>
                                <div class="time-detail arrival">
                                    <span class="label">Hạ cánh</span>
                                    <span>${item.veDto.thoiGianHaCanh}</span>
                                </div>
                            </div>
                        </div>

                        <hr class="separator">

                        <div class="card-body">
                            <div class="airport departure">
                                <span class="airport-code">${item.veDto.diemDi}</span>
                                <span class="airport-name">${item.veDto.khoiHanh}</span>
                            </div>
                            <div class="route-icon">
                                <span class="arrow">→</span>
                            </div>
                            <div class="airport arrival">
                                <span class="airport-code">${item.veDto.diemDen}</span>
                                <span class="airport-name">${item.veDto.haCanh}</span>
                            </div>
                        </div>

                        <div class="card-footer">
                            <span class="quantity">Số lượng: ${item.soLuong}</span>
                            <span class="price">Giá: ${item.veDto.gia}đ</span>
                            <form action="${pageContext.request.contextPath}/HuyVeController" method="post">
                                <input type="hidden" name="idVe" value="${item.veDto.idVe}">
                                <input type="hidden" name="idUser" value="${sessionScope.user.id}">
                                <button class="cancel-button">Hủy vé</button>
                            </form>

                        </div>
                    </div>
                </c:forEach>
            </c:when>


            <c:otherwise>
                <div class="no-tickets-message" style="text-align: center; padding: 50px; background: #fff; border-radius: 8px; box-shadow: 0 2px 10px rgba(0,0,0,0.1);">
                    <p style="font-size: 1.2rem; color: #666; margin-top: 20px;">Bạn chưa có vé máy bay nào đã đặt.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<%@ include file="../../layout/Footer.jsp" %>

<script src="list_ve_da_dat.js"></script>
</body>
</html>