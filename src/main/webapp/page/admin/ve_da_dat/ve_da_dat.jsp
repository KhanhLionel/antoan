<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Vé đã đặt</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/admin/ve_da_dat/ve_da_dat.css">
</head>
<body>
    <div class="container">

        <div class="searching">
            <div class="search-box">
                <input type="text" placeholder="Tìm theo Mã Khách Hàng hoặc Mã Chuyến Bay">
                <button class="btn-search">Tìm kiếm</button>
            </div>
        </div>

        <table class="data-table">
            <thead>
            <tr>
                <th>Mã Đơn</th>
                <th>Mã Khách Hàng</th>
                <th>Mã Chuyến Bay</th>
                <th>Số Lượng</th>
                <th>Ngày Đặt</th>
                <th>Thao Tác</th>
            </tr>
            </thead>
            <tbody>

            <tr>
                <td class="id-col">101</td>
                <td>
                    <div class="main-info">Nguyễn Văn A</div>
                </td>
                <td>
                    <div class="main-info">VJ101</div>
                </td>
                <td>2</td>
                <td>2026-01-10 08:30</td>
                <td class="action-cell">
                    <button class="btn-action btn-detail" title="Xem">📄</button>
                    <button class="btn-action btn-delete" title="Hủy" onclick="return confirm('Bạn có chắc chắn muốn hủy đơn đặt vé này?');">🗑️</button>
                </td>
            </tr>

            <tr>
                <td class="id-col">102</td>
                <td>
                    <div class="main-info">Trần Thị B</div>
                </td>
                <td>
                    <div class="main-info">VN204</div>
                </td>
                <td>1</td>
                <td>2025-12-25 14:15</td>
                <td class="action-cell">
                    <button class="btn-action btn-detail" title="Xem">📄</button>
                    <button class="btn-action btn-delete" title="Hủy" onclick="return confirm('Bạn có chắc chắn muốn hủy đơn đặt vé này?');">🗑️</button>
                </td>
            </tr>

            </tbody>
        </table>

        <div class="pagination">
            <a href="#" class="page-link">&laquo;</a>
            <a href="#" class="page-link active">1</a>
            <a href="#" class="page-link">2</a>
            <a href="#" class="page-link">&raquo;</a>
        </div>
    </div>
</body>
</html>
