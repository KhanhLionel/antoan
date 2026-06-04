<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Quản Lí Vé</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/quan_ly_ve/quan_ly_ve.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/StyleAdminMenuSidebar.css">
</head>
<body>
<%@ include file="../../admin/AdminMenuSidebar.jsp" %>
    <div class="container">
        <div class="searching-bar">
            <div class="search-form">
                <form action="${pageContext.request.contextPath}/admin/TimKiemVe" method="get" class="search-by-flight-code">
                    <input type="text" name="input" placeholder="Tìm kiếm theo số hiệu chuyến bay (VD: VJ123)...">
                    <button type="submit" class="btn-search">Tìm kiếm</button>
                </form>
            </div>

            <div class="add-ticket">
                <a href="${pageContext.request.contextPath}/page/admin/quan_ly_ve/them_ve.jsp" class="btn-add-ticket">
                    <span class="btn-add-ticket-icon">+</span>Thêm vé
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
                    <th style="text-align: center">ID chuyến bay</th>
                    <th style="text-align: center">ID Loại vé</th>
                    <th style="text-align: center">Giá</th>
                    <th style="text-align: center">Số lượng tồn</th>
                    <th style="text-align: center" class="action">Thao Tác</th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${listVe}" var="item">
                    <tr>
                        <td style="text-align: center" class="flight-code">${item.id}</td>
                        <td style="text-align: center">${item.idChuyenBay}</td>
                        <td style="text-align: center">${item.idLoaiVe}</td>
                        <td style="text-align: center" class="flight-price">${item.gia}</td>
                        <td style="text-align: center">Còn ${item.soLuongTon} vé</td>
                        <td style="text-align: center">
                            <div class="act-btns">
                                    <a href="${pageContext.request.contextPath}/admin/SuaVeController?id=${item.id}" class="btn-act btn-edit" title="Sửa">✏️</a>
                                    <form action="${pageContext.request.contextPath}/admin/XoaVeController" method="post">
                                        <input type="hidden" name="id" value="${item.id}">
                                        <button class="btn-act btn-delete" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa vé này?');">🗑️</button>
                                    </form>

                            </div>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>

        </table>
        <div class="page-moving">
            <c:if test="${currentPage > 3}">
                <a href="VeController?page=1" class="page-link">Đầu</a>
            </c:if>

            <c:if test="${currentPage > 1}">
                <a href="VeController?page=${currentPage - 1}" class="page-link">«</a>
            </c:if>

            <c:forEach begin="${currentPage - 2 > 0 ? currentPage - 2 : 1}"
                       end="${currentPage + 2 < totalPages ? currentPage + 2 : totalPages}"
                       var="i">
                <a href="VeController?page=${i}"
                   class="page-link ${i == currentPage ? 'active' : ''}">
                        ${i}
                </a>
            </c:forEach>

            <c:if test="${currentPage < totalPages}">
                <a href="VeController?page=${currentPage + 1}" class="page-link">»</a>
            </c:if>

            <c:if test="${currentPage < totalPages - 2}">
                <a href="VeController?page=${totalPages}" class="page-link">Cuối (${totalPages})</a>
            </c:if>
        </div>

    </div>
</body>
</html>
