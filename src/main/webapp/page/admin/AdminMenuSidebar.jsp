<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div id="admin-loading-overlay">
    <div class="admin-loading-spinner"></div>
</div>

<div class="sidebar-menu">
    <a href="${pageContext.request.contextPath}/admin/user-controller" class="admin-nav-link">
        <h2><span class="emoji">⚙️</span> Quản Trị Hệ Thống</h2>
    </a>

    <div class="menu-group">
        <h3>Người Dùng</h3>
        <a href="${pageContext.request.contextPath}/admin/user-controller" class="menu-link admin-nav-link" data-table="users">
            <span class="emoji">🧑‍💻</span> Tài Khoản Khách Hàng
        </a>
    </div>

    <div class="menu-group">
        <h3>Hàng Không</h3>
        <a href="${pageContext.request.contextPath}/admin/SoHieuChuyenBayController" class="menu-link admin-nav-link" data-table="chuyen_bay">
            <span class="emoji">✈️</span> Quản Lý Số Hiệu Chuyến Bay
        </a>
        <a href="${pageContext.request.contextPath}/admin/ChuyenBayController" class="menu-link admin-nav-link">
            <span class="emoji">✈️</span> Quản Lý Chuyến Bay
        </a>
        <a href="${pageContext.request.contextPath}/admin/HangBayController" class="menu-link admin-nav-link" data-table="hang_bay">
            <span class="emoji">🏢</span> Quản Lý Hãng Bay
        </a>
        <a href="${pageContext.request.contextPath}/admin/SanBayController" class="menu-link admin-nav-link" data-table="san_bay">
            <span class="emoji">🛫</span> Quản Lý Sân Bay
        </a>
        <a href="${pageContext.request.contextPath}/admin/DaiLyController" class="menu-link admin-nav-link" data-table="gioi_thieu_chuyen_bay">
            <span class="emoji">ℹ️</span> Thông Tin Giới Thiệu
        </a>
    </div>

    <div class="menu-group">
        <h3>Đặt Vé</h3>
        <a href="${pageContext.request.contextPath}/admin/LoaiVe" class="menu-link admin-nav-link" data-table="loai_ve">
            <span class="emoji">💺</span> Loại Vé
        </a>
        <a href="${pageContext.request.contextPath}/admin/VeController" class="menu-link admin-nav-link" data-table="loai_ve">
            <span class="emoji">💺</span> Danh Sách Ve
        </a>
    </div>
    <div class="menu-group">
        <h3>Trang chủ</h3>
        <a href="${pageContext.request.contextPath}/index.jsp" class="admin-nav-link">
            <span style="color: white; padding-left: 20px" class="emoji">⚙️  Trang Chủ</span>
        </a>
    </div>
</div>

<script>
document.addEventListener("DOMContentLoaded", function () {
    var adminLoader = document.getElementById('admin-loading-overlay');
    if (adminLoader) {
        setTimeout(function() {
            adminLoader.classList.add('admin-loading-hidden');
        }, 150);
    }

    var menuLinks = document.querySelectorAll('.admin-nav-link');
    menuLinks.forEach(function (link) {
        link.addEventListener('click', function () {
            if (adminLoader) {
                adminLoader.classList.remove('admin-loading-hidden');
            }
        });
    });

    document.body.addEventListener('submit', function () {
        if (adminLoader) {
            adminLoader.classList.remove('admin-loading-hidden');
        }
    });
});
</script>