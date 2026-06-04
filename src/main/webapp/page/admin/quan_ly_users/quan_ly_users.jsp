<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Quản lý user</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/quan_ly_users/quan_ly_users.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/StyleAdminMenuSidebar.css">
</head>
<body>

<main>
    <%@include file="../AdminMenuSidebar.jsp" %>
    <div class="main-content">
        <h1><span class="emoji">🧑‍💻</span> Quản Lý Tài Khoản Người Dùng</h1>

        <div class="toolbar">
            <form action="${pageContext.request.contextPath}/admin/TimKiemUserController" method="get">
                <input type="text" id="searchUser" name="input" placeholder="Tìm kiếm theo Username, Email">
                <button class="btn btn-primary">Tìm</button>
            </form>

            <a href="${pageContext.request.contextPath}/page/admin/quan_ly_users/them_user.jsp" class="btn btn-primary">+
                Thêm Người Dùng Mới</a>
        </div>


        <div class="user-table-container">

                <%= request.getAttribute("messageXoaUser")%>


            <table>
                <thead>
                <tr>
                    <th>ID User</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>SĐT</th>
                    <th>Hạng xác thực</th>
                    <th>code xác thực</th>
                    <th>Thao Tác</th>
                </tr>
                </thead>
                <tbody>

                <c:forEach items="${listUsers}" var="user">
                    <tr>

                        <td>${user.id}</td>
                        <td>${user.username}</td>
                        <td>${user.email}</td>
                        <td>${user.soDienThoai}</td>
                        <td>${user.hangXacThuc}</td>
                        <td>${user.codeXacThuc}</td>
                        <td>
                            <a class="btn-thao-tac btn-sua" href="${pageContext.request.contextPath}/admin/suaUser?id=${user.id}" title="Sửa">✏️</a>
                            <a class="btn-thao-tac btn-hoso" href="${pageContext.request.contextPath}/admin/xemHoSo?id=${user.id}" title="Hồ Sơ">👤</a>
                            <a class="btn-thao-tac btn-xoa" href="${pageContext.request.contextPath}/admin/XoaUser?idUser=${user.id}" title="Xóa" onclick="return confirm('Bạn có chắc chắn muốn xóa người dùng này?');">🗑️</a>
                        </td>
                    </tr

                </c:forEach>
                </tbody>
            </table>
        </div>


    </div>
</main>


</body>
</html>
