<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/page/thong_tin_khach_hang/thong_tin_khach_hang.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleHeader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleFooter.css">
</head>
<body>
<!--start header-->
<%@include file="../../layout/Header.jsp"%>
<!--end header-->

<!--form info-->
    <div class="user-info">
        <h1 class="title-form-info">Thông Tin Khách Hàng</h1>
        <div class="info-group">
            <label>Họ và tên</label>
            <div class="info-box">
                <c:if test="${info.ho == null || info.ten == null}">
                    <span>
                        chưa có
                    </span>
                </c:if>
                <c:if test="${info.ho != null && info.ten != null}">
                    <span>
                            ${info.ho}
                    </span>
                    <span>
                            ${info.ten}
                    </span>
                </c:if>

            </div>
        </div>

        <div class="info-group">
            <label>Email</label>
            <div class="info-box">${user.email == null ? "chưa có" : user.email}</div>
        </div>
        <div class="info-group">
            <label>Giới tính</label>
            <div class="info-box">${info.gioiTinh == null ? "chưa có" : info.gioiTinh}</div>
        </div>
        <div class="info-group">
            <label>Ngày sinh</label>
            <div class="info-box">${info.ngaySinh == null ? "chưa có" : info.ngaySinh}</div>
        </div>

        <div class="info-group">
            <label>Địa chỉ</label>
            <div class="info-box">${info.diaChi == null ? "chưa có" : info.diaChi}</div>
        </div >

        <div class="btn-update">
            <a href="${pageContext.request.contextPath}/CapNhatThongTinNguoiDungController?id=${info.id}">Cập Nhật Thông Tin</a>
        </div>
    </div>
<div class="profile-card full-width" style="margin-top: 20px;">
    <div class="profile-header">
        <h2> Quản Lý Khóa Xác Thực Chữ Ký Số</h2>
    </div>

    <c:if test="${requestScope.keyMessage != null}">
        <div style="padding: 10px; margin-bottom: 15px; border-radius: 4px; font-weight: bold; font-size: 13px;
                            ${requestScope.keyStatus == 'success' ? 'color: green; background-color: #eef9ee; border: 1px solid green;' : 'color: red; background-color: #fdf7f7; border: 1px solid red;'}">
                ${requestScope.keyMessage}
        </div>
    </c:if>

    <div style="background: #f8f9fa; border-left: 4px solid #2980b9; padding: 15px; margin-bottom: 20px; border-radius: 4px;">
        <h3 style="margin-top: 0; font-size: 15px; color: #2c3e50;">Hướng dẫn sử dụng Công cụ ký số độc lập:</h3>
        <p style="font-size: 13px; color: #555; line-height: 1.5; margin-bottom: 10px;">
            Để đảm bảo an toàn tối đa cho hóa đơn đặt vé, hệ thống bắt buộc áp dụng công nghệ ký số bảo mật RSA-2048. Khóa bí mật (Private Key) sẽ do bạn tự lưu trữ trên máy tính và hệ thống không có quyền can thiệp.
        </p>
        <a href="${pageContext.request.contextPath}/assets/tools/DigitalSignatureTool.jar"
           style="display: inline-block; background: #34495e; color: #fff; padding: 8px 15px; border-radius: 4px; text-decoration: none; font-size: 12px; font-weight: bold;">
            TẢI PHẦN MỀM KÝ SỐ OFFLINE (.JAR)
        </a>
        <span style="font-size: 11px; color: #7f8c8d; margin-left: 8px;">(Yêu cầu máy tính chạy hệ điều hành có cài đặt môi trường Java JRE/JDK)</span>
    </div>

    <c:choose>
        <c:when test="${requestScope.hasActiveKey}">
            <div style="background: #eef9ee; border: 1px solid #27ae60; padding: 15px; border-radius: 6px; text-align: left;">
                <h4 style="color: #27ae60; margin: 0 0 8px 0; font-size: 14px;"> Hệ thống đang bảo mật tài khoản bằng Chữ ký số</h4>
                <p style="font-size: 13px; color: #333; margin-bottom: 12px;">
                    Khóa công khai (Public Key) của bạn đã được cấu hình đồng bộ thành công vào cơ sở dữ liệu hệ thống lúc:
                    <strong style="color: #2c3e50;">${requestScope.keyCreatedAt}</strong>.
                </p>

                <label style="font-size: 12px; font-weight: bold; color: #555;">Public Key hiện tại trên máy chủ:</label>
                <textarea readonly style="width: 100%; font-family: monospace; padding: 6px; font-size: 11px; background: #fff; border: 1px solid #ccc; border-radius: 4px; margin: 5px 0 15px 0; resize: none;" rows="3">${requestScope.activePublicKey}</textarea>

                <form  id="reportLostForm" action="${pageContext.request.contextPath}/QuanLyKhoaController" method="post" >
                    <input type="hidden" name="action" value="reportLost">
                        <%--                            <button type="submit" style="background: #d9534f; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: bold;">--%>
                        <%--                                BÁO MẤT KHÓA--%>
                        <%--                            </button>--%>
                    <button type="button" id="btnSendOtp"
                            style="background:#d9534f;color:white;border:none;padding:8px 16px;border-radius:4px;cursor:pointer;">
                        BÁO MẤT KHÓA
                    </button>

                    <div id="otpSection" style="display:none; margin-top:15px;">
                        <input type="text" id="otpCode"
                               placeholder="Nhập mã OTP"
                               maxlength="6">

                        <button type="button" id="btnVerifyOtp">
                            Xác nhận OTP
                        </button>
                    </div>
                </form>
            </div>
        </c:when>
        <c:otherwise>
            <div style="background: #fff9e6; border: 1px solid #f39c12; padding: 15px; border-radius: 6px; text-align: left;">
                <h4 style="color: #e67e22; margin: 0 0 8px 0; font-size: 14px;">⚠️ Tài khoản chưa được cấu hình Khóa bảo mật</h4>
                <p style="font-size: 13px; color: #444; margin-bottom: 12px; line-height: 1.4;">
                    Để kích hoạt chức năng mua vé máy bay, bạn hãy làm theo các bước sau:<br>
                    1. Mở phần mềm <strong>DigitalSignatureTool.jar</strong> vừa tải bên trên lên.<br>
                    2. Chọn tab sinh khóa để tạo cặp khóa bảo mật $\rightarrow$ Tiến hành lưu lại file khóa bí mật (`.key`) và file khóa công khai (`.pub`).<br>
                    3. Click nút chọn tệp bên dưới và chọn file chứa <strong>Khóa công khai (Public Key)</strong> để tải lên hệ thống.
                </p>

                <form   action="${pageContext.request.contextPath}/QuanLyKhoaController" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="action" value="uploadKey">

                    <div style="margin-bottom: 15px;">
                        <label style="display: block; font-weight: bold; font-size: 12px; margin-bottom: 5px;">Chọn file Khóa công khai của bạn (.pub hoặc .txt):</label>
                        <input type="file" name="publicKeyFile" accept=".pub,.txt" required style="font-size: 13px;">
                    </div>

                    <button type="submit" style="background: #27ae60; color: white; border: none; padding: 8px 18px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: bold;">
                        TẢI KHÓA LÊN & KÍCH HOẠT TÀI KHOẢN
                    </button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>
</div>


<%@include file="../../layout/Footer.jsp"%>
</body>
</html>