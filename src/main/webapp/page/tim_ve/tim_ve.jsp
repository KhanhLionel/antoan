<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Tìm vé</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/page/tim_ve/tim_ve.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleHeader.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layout/StyleFooter.css">
</head>
<body>
<!--start header-->
<%@include file="../../layout/Header.jsp"%>
<!--end header-->

<div class="container">
	<div class="img-mau-bay">
		<img class="img-mau-bay-item" src="${pageContext.request.contextPath}/access/trang_chu/trang_tim_ve/anh_may_bay.png" alt="">
	</div>
</div>
<div class="container">
	<form action="${pageContext.request.contextPath}/TimVeController" class="form-tim-ve">
		<div class="form-quoc-gia">
			<div>
				<label>Khởi hành tại: </label>
				<select id="khoiHanh" onchange="updateSearch()" name="khoi_hanh" class="select select-diem-di" required>
                    <option>
                        ${param.get("khoiHanh")}
                    </option>
                    <c:forEach var="quocGia" items="${thongTinTimVeDto.listKhoiHanh}">
                        <option>
                            ${quocGia}
                        </option>
                    </c:forEach>
				</select>
			</div>
			<div class="khoang-cach-form"></div>
			<div>
				<label>Hạ cánh tại: </label>
                <select id="haCanh" onchange="updateSearch()" name="ha_canh" class="select select-diem-di" required>
                    <option>
                        ${param.get("haCanh")}
                    </option>
                    <c:forEach var="quocGia" items="${thongTinTimVeDto.listHaCanh}">
                        <option>
                                ${quocGia}
                        </option>
                    </c:forEach>
                </select>
			</div>
			<div class="khoang-cach-form"></div>
			<div>
				<label>Hạng ghế: </label>
                <select name="hang_ghe" class="select select-diem-di" required>

                    <c:forEach var="hangGhe" items="${thongTinTimVeDto.listHangGhe}">
                        <option>
                                ${hangGhe}
                        </option>
                    </c:forEach>
                </select>
			</div>
		</div>
		<div class="form-tim-ve-item">
			<div class="form-select">
				<div>
					<label>Điểm đi: </label>
                    <select name="diem_di" class="select select-diem-di" required>
                        <c:forEach var="diemDi" items="${thongTinTimVeDto.listDiemDi}">
                            <option <c:if test="${diemDi == param.diemDi}">selected</c:if>>
                                    ${diemDi}
                            </option>
                        </c:forEach>
                    </select>

				</div>


				<div class="khoang-cach-form"></div>

				<div>
					<label>Điểm đến: </label>
                    <select name="diem_den" class="select select-diem-di" required>
                        <c:forEach var="diemDen" items="${thongTinTimVeDto.listDiemDen}">
                            <option <c:if test="${diemDen == param.diemDen}">selected</c:if>>
                                    ${diemDen}
                            </option>
                        </c:forEach>
                    </select>

				</div>

				<div class="khoang-cach-form"></div>

				<div>
					<input class="cb cb-gia-ve" type="checkbox" name="is_xep_ve" id="">
					<label>Chọn vé giá thấp nhất</label>
				</div>
			</div>
			<div>
				<label>Ngày khơi hành: </label>
				<input class="input-ngay-khoi-hanh" type="date" name="ngay_di" id="">
			</div>
		</div>

		<div class="form-tim-ve-item form-tim-ve-item-btn">
			<button type="submit" class="btn-tim-ve">
                Tìm vé
			</button>
		</div>

	</form>
</div>
<%@include file="../../layout/Footer.jsp"%>
<script>
    function updateSearch() {
        var khoiHanh = document.getElementById('khoiHanh').value;
        var haCanh = document.getElementById('haCanh').value;

        var baseUrl = "${pageContext.request.contextPath}/ThongTinTimVeController";

        var url = baseUrl + "?khoiHanh=" + encodeURIComponent(khoiHanh)
            + "&haCanh=" + encodeURIComponent(haCanh);

        window.location.href = url;
    }
</script>
</body>
</html>