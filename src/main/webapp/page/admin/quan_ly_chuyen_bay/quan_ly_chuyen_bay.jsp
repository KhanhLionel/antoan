<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Quản Lí Chuyến Bay</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/quan_ly_chuyen_bay/quan_ly_chuyen_bay.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/StyleAdminMenuSidebar.css">
</head>
<body>
<%@ include file="../../admin/AdminMenuSidebar.jsp" %>
<div class="container">
    <div class="searching-bar">
        <div class="search-form">
            <form action="${pageContext.request.contextPath}/admin/TimKiemChuyenBayController" method="get" class="search-by-flight">
                <input type="text" name="input" placeholder="Tìm kiếm...">
                <button type="submit" class="btn-search">Tìm kiếm</button>
            </form>
        </div>

        <div class="add-ticket">
            <a href="${pageContext.request.contextPath}/page/admin/quan_ly_chuyen_bay/them_chuyen_bay.jsp" class="btn-add-ticket">
                <span class="btn-add-ticket-icon">+</span>Thêm
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
            <th style="text-align: center">ID</th>
            <th style="text-align: center">Thời gian khởi hành</th>
            <th style="text-align: center">Thời gian hạ cánh</th>
            <th style="text-align: center">ID số hiệu chuyến bay</th>
            <th style="text-align: center" class="action">Thao Tác</th>
        </tr>
        </thead>

        <tbody>
        <c:forEach items="${listChuyenBay}" var="item">
            <tr>
                <td style="text-align: center">${item.id}</td>
                <td style="text-align: center">${item.thoiGianKhoiHanh}</td>
                <td style="text-align: center">${item.thoiGianHaCanh}</td>
                <td style="text-align: center">${item.idSoHieuChuyenBay}</td>
                <td style="text-align: center">
                    <div class="act-btns">
                        <a href="${pageContext.request.contextPath}/admin/SuaChuyenBayController?id=${item.id}" class="btn-act btn-edit" title="Sửa">✏️</a>
                        <form action="${pageContext.request.contextPath}/admin/XoaChuyenBayController" method="post">
                            <input type="hidden" name="id" value="${item.id}">
                            <button class="btn-act btn-delete" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa chuyến bay này?');">🗑️</button>
                        </form>
                    </div>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>
    <div class="page-moving">
        <c:if test="${currentPage > 3}">
            <a href="ChuyenBayController?page=1" class="page-link">Đầu</a>
        </c:if>

        <c:if test="${currentPage > 1}">
            <a href="ChuyenBayController?page=${currentPage - 1}" class="page-link">«</a>
        </c:if>

        <c:forEach begin="${currentPage - 2 > 0 ? currentPage - 2 : 1}"
                   end="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages}"
                   var="i">
            <a href="ChuyenBayController?page=${i}"
               class="page-link ${i == currentPage ? 'active' : ''}">
                    ${i}
            </a>
        </c:forEach>

        <c:if test="${currentPage < totalPages}">
            <a href="ChuyenBayController?page=${currentPage + 1}" class="page-link">»</a>
        </c:if>

        <c:if test="${currentPage < totalPages - 2}">
            <a href="ChuyenBayController?page=${totalPages}" class="page-link">Cuối (${totalPages})</a>
        </c:if>
    </div>

</div>
</body>
</html>
