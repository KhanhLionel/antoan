<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>danh sách vé</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/list_ve/list_ve.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleHeader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleFooter.css">
</head>
<body>
<%@include file="../../layout/Header.jsp" %>
<div class="container" style="margin-top: 20px;">
    <c:if test="${isRoundTrip}">
        <div style="background-color: #e6f7ff; padding: 15px; border-radius: 8px; margin-bottom: 20px; border-left: 5px solid #1890ff;">
            <h3 style="margin: 0; color: #0050b3; font-size: 1.25rem;">Hành trình khứ hồi</h3>
            <p style="margin: 5px 0 0 0; color: #595959; font-size: 1rem;">
                Chiều đi: <strong>${param.diem_di}</strong> đến <strong>${param.diem_den}</strong> ngày <strong>${param.ngay_di}</strong>
                <br/>
                Chiều về: <strong>${param.diem_den}</strong> đến <strong>${param.diem_di}</strong> ngày <strong>${ngayVe}</strong> 
                <c:if test="${isReturnFlexible}"> (Tìm kiếm linh hoạt ±3 ngày)</c:if>
            </p>
        </div>
    </c:if>
</div>

<div class="container flex" style="align-items: flex-start; gap: 40px;">
    <div class="list-ve" style="flex: 1; min-width: 0;">
        <h1>
            <c:choose>
                <c:when test="${isRoundTrip}">1. Vé chiều đi (Outbound)</c:when>
                <c:otherwise>Danh sách vé máy bay</c:otherwise>
            </c:choose>
        </h1>

        <c:choose>
            <c:when test="${not empty listVeRes}">
                <c:forEach items="${listVeRes}" var="item">
                    <div class="list-ve-item">
                        <div class="info">
                            <img src="${pageContext.request.contextPath}/access/trang_chu/Logo.png" alt="">
                            <div class="info-ve">
                                <div style="display: flex; justify-content: space-between" class="info-hang-bay">
                                    <div>
                                        <span class="text-bold text-hang-bay">Hãng bay: ${item.hangBay}</span>
                                    </div>
                                    <div>
                                        <span class="text-bold text-hang-bay">Hạng ghế: ${item.hangGhe}</span>
                                    </div>
                                    <div>
                                        <span class="text-bold">Giá: ${item.gia} đồng</span>
                                    </div>
                                </div>
                                <div class="info-dia-diem">
                                    <div>
                                        <span class="text-hang-bay">Khởi Hành: ${item.sanBayDi}</span>
                                    </div>
                                    <div style="margin: 0px 20px 0px 20px">======></div>
                                    <div>
                                        <span class="text-hang-bay"> Hạ Cánh: ${item.sanBayDen}</span>
                                    </div>
                                </div>
                            </div>
                            <div class="info-thoi-gian">
                                <div>
                                    <span class="cat-canh">Cất cánh: ${item.thoiGianKhoiHanh}</span>
                                </div>
                                <div>
                                    <span class="ha-canh"> Hạ Cánh: ${item.thoiGianHaCanh}</span>
                                </div>
                            </div>
                        </div>

                        <div class="btn-dat-ve">
                            <button type="submit">
                                <c:url var="urlDatVe" value="/DatVeController">
                                    <c:param name="idVe" value="${item.idVe}" />
                                    <c:param name="soLuong" value="1" />
                                </c:url>
                                <a href="${urlDatVe}">Chọn</a>
                            </button>
                            <form action="${pageContext.request.contextPath}/GioHangController" method="post"
                                  style="display:inline; margin-left: 10px">
                                <input type="hidden" name="action" value="add">
                                <input type="hidden" name="idVe" value="${item.idVe}">
                                <button type="submit" class="btn-add-cart">
                                    Thêm vào giỏ
                                </button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <div class="no-tickets" style="text-align: center; padding: 50px; font-size: 1.2rem; color: #666;">
                    <img src="../../access/trang_chu/no-data-icon.png" alt="" style="width: 100px; margin-bottom: 20px;">
                    <p>Rất tiếc, hiện tại không có vé nào phù hợp với chiều đi của bạn.</p>
                </div>
            </c:otherwise>
        </c:choose>
    </div>

    <c:if test="${isRoundTrip}">
        <div class="list-ve" style="flex: 1; min-width: 0;">
            <h1>2. Vé chiều về (Return)</h1>
            <c:choose>
                <c:when test="${not empty listVeReturn}">
                    <c:forEach items="${listVeReturn}" var="item">
                        <div class="list-ve-item">
                            <div class="info">
                                <img src="${pageContext.request.contextPath}/access/trang_chu/Logo.png" alt="">
                                <div class="info-ve">
                                    <div style="display: flex; justify-content: space-between" class="info-hang-bay">
                                        <div>
                                            <span class="text-bold text-hang-bay">Hãng bay: ${item.hangBay}</span>
                                        </div>
                                        <div>
                                            <span class="text-bold text-hang-bay">Hạng ghế: ${item.hangGhe}</span>
                                        </div>
                                        <div>
                                            <span class="text-bold">Giá: ${item.gia} đồng</span>
                                        </div>
                                    </div>
                                    <div class="info-dia-diem">
                                        <div>
                                            <span class="text-hang-bay">Khởi Hành: ${item.sanBayDi}</span>
                                        </div>
                                        <div style="margin: 0px 20px 0px 20px">======></div>
                                        <div>
                                            <span class="text-hang-bay"> Hạ Cánh: ${item.sanBayDen}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="info-thoi-gian">
                                    <div>
                                        <span class="cat-canh">Cất cánh: ${item.thoiGianKhoiHanh}</span>
                                    </div>
                                    <div>
                                        <span class="ha-canh"> Hạ Cánh: ${item.thoiGianHaCanh}</span>
                                    </div>
                                </div>
                            </div>

                            <div class="btn-dat-ve">
                                <button type="submit">
                                    <c:url var="urlDatVe" value="/DatVeController">
                                        <c:param name="idVe" value="${item.idVe}" />
                                        <c:param name="soLuong" value="1" />
                                    </c:url>
                                    <a href="${urlDatVe}">Chọn</a>
                                </button>
                                <form action="${pageContext.request.contextPath}/GioHangController" method="post"
                                      style="display:inline; margin-left: 10px">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="idVe" value="${item.idVe}">
                                    <button type="submit" class="btn-add-cart">
                                        Thêm vào giỏ
                                    </button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <div class="no-tickets" style="text-align: center; padding: 50px; font-size: 1.2rem; color: #666;">
                        <img src="../../access/trang_chu/no-data-icon.png" alt="" style="width: 100px; margin-bottom: 20px;">
                        <p>Rất tiếc, hiện tại không có vé nào phù hợp với chiều về của bạn.</p>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </c:if>
</div>

<%@include file="../../layout/Footer.jsp" %>

</body>
</html>