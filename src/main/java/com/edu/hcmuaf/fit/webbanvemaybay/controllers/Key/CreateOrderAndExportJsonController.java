package com.edu.hcmuaf.fit.webbanvemaybay.controllers.Key;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CreateOrderAndExportJsonController", value = "/CreateOrderAndExportJsonController")
public class CreateOrderAndExportJsonController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        // Nhận thông tin đơn hàng từ giao diện gửi lên
        String idVe = request.getParameter("idVe");
        String soLuong = request.getParameter("soLuong");
        String tongTien = request.getParameter("tongTien");

        // Data mặc định để test nếu chưa truyền từ form qua
        if (idVe == null) idVe = "9002";
        if (soLuong == null) soLuong = "1";
        if (tongTien == null) tongTien = "1850000";

        // Sinh mã ngẫu nhiên cho đơn hàng và gán ID User mẫu
        int idDonHangMoi = (int) (System.currentTimeMillis() % 100000);
        int idUserLogged = 8;
        String ngayTaoDon = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // chuỗi Json
        String jsonOutput = "{\n" +
                "  \"ma_don_hang\": " + idDonHangMoi + ",\n" +
                "  \"ma_nguoi_dung\": " + idUserLogged + ",\n" +
                "  \"ma_ve\": " + idVe + ",\n" +
                "  \"hang_bay\": \"Vietnam Airlines\",\n" +
                "  \"so_hieu_chuyen_bay\": \"DEMO2026\",\n" +
                "  \"san_bay_di\": \"Tân Sơn Nhất (TP.HCM)\",\n" +
                "  \"san_bay_den\": \"Nội Bài (Hà Nội)\",\n" +
                "  \"thoi_gian_khoi_hanh\": \"2026-06-25 13:30:00\",\n" +
                "  \"so_luong\": " + soLuong + ",\n" +
                "  \"tong_tien\": " + tongTien + ",\n" +
                "  \"ngay_tao_don\": \"" + ngayTaoDon + "\"\n" +
                "}";

        // web tải file Json về máy người dùng
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=don_hang_" + idDonHangMoi + ".json");

        // Ghi thẳng chuỗi vừa ghép ra file để người dùng tải về
        response.getWriter().write(jsonOutput);
    }
}