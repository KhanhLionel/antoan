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
		<div style="display: flex; gap: 20px; margin-bottom: 25px; justify-content: center; font-size: 1.1rem;">
			<label style="cursor: pointer; display: flex; align-items: center; gap: 8px;">
				<input type="radio" name="loai_hinh" value="one_way" checked onclick="toggleReturnDate(false)"> Một chiều
			</label>
			<label style="cursor: pointer; display: flex; align-items: center; gap: 8px;">
				<input type="radio" name="loai_hinh" value="round_trip" onclick="toggleReturnDate(true)"> Khứ hồi
			</label>
		</div>
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
			<div style="display: flex; align-items: center; gap: 20px;">
				<div>
					<label>Ngày khơi hành: </label>
					<input class="input-ngay-khoi-hanh" type="date" name="ngay_di" id="" required>
				</div>
				<div id="return-date-container" style="display: none;">
					<label>Ngày về: </label>
					<input class="input-ngay-khoi-hanh" type="date" name="ngay_ve" id="ngay_ve">
					<div style="margin-top: 5px; display: flex; align-items: center; gap: 5px;">
						<input class="cb" type="checkbox" name="ngay_ve_linh_hoat" id="ngay_ve_linh_hoat">
						<label for="ngay_ve_linh_hoat" style="font-size: 0.9rem; color: #555; cursor: pointer;">Chọn ngày về linh hoạt (±3 ngày)</label>
					</div>
				</div>
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

    function toggleReturnDate(show) {
        var returnContainer = document.getElementById('return-date-container');
        var ngayVeInput = document.getElementById('ngay_ve');
        if (show) {
            returnContainer.style.display = 'block';
            ngayVeInput.setAttribute('required', 'required');
        } else {
            returnContainer.style.display = 'none';
            ngayVeInput.removeAttribute('required');
            ngayVeInput.value = '';
        }
    }
</script>
</body>
</html>