<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Số Hiệu Chuyến Bay</title>
    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/page/admin/so_hieu_chuyen_bay/so_hieu_chuyen_bay.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/StyleAdminMenuSidebar.css">
</head>
<body>
<%@include file="../AdminMenuSidebar.jsp" %>
<div class="container">
    <div class="searching-bar">
        <div class="search-form">
            <form action="${pageContext.request.contextPath}/admin/TimKiemSoHieuChuyenBayController" method="get" class="search-flight">
                <input type="text" name="input" placeholder="Nhập id, mã chuyến bay,...">
                <button type="submit" class="btn-search">Tìm kiếm</button>
            </form>
        </div>

        <div class="add-ticket">
            <a href="${pageContext.request.contextPath}/page/admin/so_hieu_chuyen_bay/them_so_hieu_chuyen_bay.jsp" class="btn-add-ticket">
                <span class="btn-add-ticket-icon">+</span>Thêm Chuyến Bay
            </a>
        </div>
    </div>
    <c:if test="${not empty sessionScope.messageRemove}">
        <div>
            ${sessionScope.messageRemove}
        </div>
        <c:remove var="messageRemove" scope="session" />
    </c:if>
<%--    <table class="example-flights">--%>
<%--        <thead>--%>

<%--        <tr>--%>
<%--            <th style="text-align: center; width: 60px;">ID</th>--%>
<%--            <th>Mã Chuyến Bay</th>--%>
<%--            <th>ID - Sân Bay Đi</th>--%>
<%--            <th>ID - Sân Bay Đến</th>--%>
<%--            <th>ID - Hãng Bay</th>--%>
<%--            <th class="action-from-so-hieu-chuyen-bay">Thao tác</th>--%>
<%--        </tr>--%>


<%--        </thead>--%>

<%--        <tbody>--%>
<%--        <c:forEach items="${listSoHieuChuyenBay}" var="soHieuChuyenBay">--%>
<%--            <tr>--%>
<%--                <th style="text-align: center; width: 60px;">${soHieuChuyenBay.id}</th>--%>
<%--                <th>${soHieuChuyenBay.maChuyenBay}</th>--%>
<%--                <th>${soHieuChuyenBay.idSanBayDi}</th>--%>
<%--                <th>${soHieuChuyenBay.idSanBayDen}</th>--%>
<%--                <th>${soHieuChuyenBay.idHangBay}</th>--%>
<%--                <th style="display: flex; justify-content: center; gap: 10px;" class="action-from-so-hieu-chuyen-bay">--%>
<%--                    <a href="${pageContext.request.contextPath}/SuaSoHieuChuyenBayController?id=${soHieuChuyenBay.id}">--%>
<%--                        sửa--%>
<%--                    </a>--%>
<%--                    <a href="${pageContext.request.contextPath}/XoaSoHieuChuyenBayController?id=${soHieuChuyenBay.id}">--%>
<%--                        xoá--%>
<%--                    </a>--%>
<%--                </th>--%>
<%--            </tr>--%>
<%--        </c:forEach>--%>
<%--        </tbody>--%>
<%--    </table>--%>

    <div class="table-container">
        <table class="flights" id="flightTable">
            <thead>
            <tr>
                <th style="text-align: center; width: 60px;">ID</th>
                <th>Mã Chuyến Bay</th>
                <th>ID - Sân Bay Đi</th>
                <th>ID - Sân Bay Đến</th>
                <th>ID - Hãng Bay</th>
                <th class="action">Thao tác</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${listSoHieuChuyenBay}" var="soHieuChuyenBay">
                <tr class="flight-row">
                    <td style="text-align: center;">${soHieuChuyenBay.id}</td>
                    <td class="flight-code-cell">${soHieuChuyenBay.maChuyenBay}</td>
                    <td>${soHieuChuyenBay.idSanBayDi}</td>
                    <td>${soHieuChuyenBay.idSanBayDen}</td>
                    <td>${soHieuChuyenBay.idHangBay}</td>
                    <td style="display: flex; justify-content: center; gap: 10px;">
                        <a href="${pageContext.request.contextPath}/admin/SuaSoHieuChuyenBayController?id=${soHieuChuyenBay.id}" class="action-btn edit" title="Sửa">✏️</a>
                        <a href="${pageContext.request.contextPath}/admin/XoaSoHieuChuyenBayController?id=${soHieuChuyenBay.id}" class="action-btn delete" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa số hiệu chuyến bay này?');">🗑️</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    </div>
</div>
</body>
</html>
