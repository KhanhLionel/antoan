<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<footer class="main-footer">
    <div class="footer-container">

        <div class="footer-left">
            <h3><strong>Dịch vụ</strong></h3>
            <ul>
                <li><a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
                <li>
                    <c:url var="urlTimVe" value="/ThongTinTimVeController">
                        <c:param name="khoiHanh" value="Việt Nam"/>
                        <c:param name="haCanh" value="Việt Nam"/>
                    </c:url>
                    <a href="${urlTimVe}">Đặt Vé</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/page/thong_tin_ve_noi_dia/ve_noi_dia.jsp">Vé nội địa</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/page/thong_tin_ve_quoc_te/ve_quoc_te.jsp">Vé quốc tế</a>
                </li>
                <li><a href="${pageContext.request.contextPath}/ThongTinLienHeController">Thông tin liên hệ</a></li>
            </ul>
        </div>

        <div class="footer-right">
            <h3><strong>Trang bán vé máy bay số 1 Việt Nam</strong></h3>
            <p>${daiLy.tenDiaChi}</p>
            <p>${daiLy.email}</p>
            <p>Tổng đài: ${daiLy.soDienThoai}</p>

            <div class="social-links">
                <a href="${daiLy.linkYt}">
                    <img src="${pageContext.request.contextPath}/access/logoYoutube.png" alt="YouTube">
                </a>
                <a href="${daiLy.linkFb}">
                    <img src="${pageContext.request.contextPath}/access/logoFacebook.png" alt="Facebook">
                </a>
                <a href="${daiLy.linkTiktok}">
                    <img src="${pageContext.request.contextPath}/access/logoTiktok.jpg" alt="TikTok">
                </a>
            </div>
        </div>

    </div>
</footer>

<!-- ✅ Nút Back to Top - Issue #125 -->
<a id="back-to-top" href="#" title="Về đầu trang">&#8679;</a>

<script>
    (function () {
        var btn = document.getElementById('back-to-top');

        window.addEventListener('scroll', function () {
            if (window.scrollY > 300) {
                btn.classList.add('show');
            } else {
                btn.classList.remove('show');
            }
        });

        btn.addEventListener('click', function (e) {
            e.preventDefault();
            window.scrollTo({ top: 0, behavior: 'smooth' });
        });
    })();
</script>