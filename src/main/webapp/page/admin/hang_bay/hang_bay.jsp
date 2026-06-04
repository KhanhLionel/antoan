<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Hãng Bay</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/hang_bay/hang_bay.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/StyleAdminMenuSidebar.css">
</head>
<body>
<%@include file="../AdminMenuSidebar.jsp" %>
    <div class="container">
        <div class="searching-bar">
            <div class="search-form">
                <form action="${pageContext.request.contextPath}/admin/TimKiemHangBayController" method="get" class="search-by-flight-name">
                    <input type="text" name="input" placeholder="Tìm kiếm...">
                    <button type="submit" class="btn-search">Tìm kiếm</button>
                </form>
            </div>

            <div class="add-ticket">
                <a href="${pageContext.request.contextPath}/page/admin/hang_bay/them_hang_bay.jsp" class="btn-add-ticket">
                    <span class="btn-add-ticket-icon">+</span> Thêm Hãng Bay
                </a>
            </div>
        </div>
        <c:if test="${not empty sessionScope.messageRemove}">
            <div>
                ${sessionScope.messageRemove}
            </div>
            <c:remove var="messageRemove" scope="session" />
        </c:if>
        <table class="flights">
            <thead>
            <tr>
                <th style="text-align: center; width: 60px;">ID</th>
                <th>Tên Hãng Bay</th>
                <th>Quốc Gia</th>
                <th class="action">Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${listHangBay}" var="item">
                <tr>
                    <td style="text-align: center; width: 60px;">${item.id}</td>

                    <td>${item.tenHangBay}</td>

                    <td>${item.quocGia}</td>

                    <td>
                        <div class="action">
                            <a href="${pageContext.request.contextPath}/admin/SuaHangBayController?id=${item.id}" class="btn-action btn-edit" title="Sửa">✏️</a>
                            <form action="${pageContext.request.contextPath}/admin/XoaHangBayController" method="post">
                                <input type="hidden" name="id" value="${item.id}">
                                <button class="btn-action btn-delete" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa hãng bay này?');">🗑️</button>
                            </form>

                        </div>
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</body>
</html>
