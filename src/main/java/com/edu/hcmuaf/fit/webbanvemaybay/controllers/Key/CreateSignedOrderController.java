package com.edu.hcmuaf.fit.webbanvemaybay.controllers.Key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangChiTietDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.UserKeyDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHang;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHangChiTiet;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey;
import com.edu.hcmuaf.fit.webbanvemaybay.services.key.OrderPayloadService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;

@WebServlet(name = "CreateSignedOrderController", value = "/CreateSignedOrderController")
public class CreateSignedOrderController extends HttpServlet {

    private final UserKeyDAO userKeyDAO = new UserKeyDAO();
    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final DonHangChiTietDAO donHangChiTietDAO = new DonHangChiTietDAO();
    private final OrderPayloadService orderPayloadService = new OrderPayloadService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/page/key/create_order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idUser = getCurrentUserId(request);

        Optional<UserKey> activeKeyOpt = userKeyDAO.findActiveKeyByUserId(idUser);

        if (activeKeyOpt.isEmpty()) {
            request.setAttribute("message", "Người dùng chưa có public key ACTIVE. Vui lòng thêm public key trước.");
            request.getRequestDispatcher("/page/key/create_order.jsp").forward(request, response);
            return;
        }

        UserKey activeKey = activeKeyOpt.get();

        try {
            int idVe = parseInt(request.getParameter("idVe"), 9002);
            int soLuong = parseInt(request.getParameter("soLuong"), 1);

            BigDecimal donGia = parseMoney(request.getParameter("donGia"), new BigDecimal("1850000"));
            BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(soLuong));

            String tenNguoiMua = getValue(request.getParameter("tenNguoiMua"), "Nguyen Van A");
            String emailNguoiMua = getValue(request.getParameter("emailNguoiMua"), "user@gmail.com");
            String sdtNguoiMua = getValue(request.getParameter("sdtNguoiMua"), "0900000000");

            String maDonHang = "DH" + System.currentTimeMillis();

            DonHang donHang = new DonHang();
            donHang.setMaDonHang(maDonHang);
            donHang.setIdUser(idUser);
            donHang.setIdUserKey(activeKey.getId());
            donHang.setTenNguoiMua(tenNguoiMua);
            donHang.setEmailNguoiMua(emailNguoiMua);
            donHang.setSdtNguoiMua(sdtNguoiMua);
            donHang.setMaKhuyenMai(null);
            donHang.setTienGiam(BigDecimal.ZERO);
            donHang.setTongTien(thanhTien);
            donHang.setPayloadJson(null);
            donHang.setPayloadHash(null);
            donHang.setTrangThaiDon("CHO_KY");
            donHang.setTrangThaiKy("CHUA_KY");
            donHang.setTrangThaiToanVen("CHUA_KIEM_TRA");

            int idDonHang = donHangDAO.insert(donHang);

            DonHangChiTiet chiTiet = new DonHangChiTiet();
            chiTiet.setIdDonHang(idDonHang);
            chiTiet.setIdVe(idVe);
            chiTiet.setSoLuong(soLuong);
            chiTiet.setDonGia(donGia);
            chiTiet.setThanhTien(thanhTien);
            chiTiet.setTenVeSnapshot("Vé máy bay demo");
            chiTiet.setThongTinVeSnapshot("Thông tin được lưu tại thời điểm tạo đơn hàng");

            donHangChiTietDAO.insert(chiTiet);

            orderPayloadService.refreshPayloadAndHash(idDonHang);

            DonHang donHangDaCoPayload = donHangDAO.findById(idDonHang)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng sau khi tạo payload"));

            String payloadJson = donHangDaCoPayload.getPayloadJson();

            response.setContentType("application/json;charset=UTF-8");
            response.setHeader(
                    "Content-Disposition",
                    "attachment; filename=\"don_hang_" + maDonHang + ".json\""
            );

            response.getWriter().write(payloadJson);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Tạo đơn hàng thất bại: " + e.getMessage());
            request.getRequestDispatcher("/page/key/create_order.jsp").forward(request, response);
        }
    }

    private int getCurrentUserId(HttpServletRequest request) {
        Object idUser = request.getSession().getAttribute("idUser");

        if (idUser instanceof Integer) {
            return (Integer) idUser;
        }

        if (idUser instanceof String) {
            try {
                return Integer.parseInt((String) idUser);
            } catch (Exception ignored) {
            }
        }

        return 8;
    }

    private int parseInt(String value, int defaultValue) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return defaultValue;
            }

            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private BigDecimal parseMoney(String value, BigDecimal defaultValue) {
        try {
            if (value == null || value.trim().isEmpty()) {
                return defaultValue;
            }

            return new BigDecimal(value.trim());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    private String getValue(String value, String defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        return value.trim();
    }
}