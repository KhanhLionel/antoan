package com.edu.hcmuaf.fit.webbanvemaybay.controllers.Key;

import com.edu.hcmuaf.fit.webbanvemaybay.services.key.OrderSignatureService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "SubmitSignatureController", value = "/SubmitSignatureController")
public class SubmitSignatureController extends HttpServlet {

    private final OrderSignatureService orderSignatureService = new OrderSignatureService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/page/key/submit_signature.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idDonHangRaw = request.getParameter("idDonHang");
        String signature = request.getParameter("signature");

        if (idDonHangRaw == null || idDonHangRaw.trim().isEmpty()) {
            request.setAttribute("message", "Vui lòng nhập ID đơn hàng.");
            request.getRequestDispatcher("/page/key/submit_signature.jsp").forward(request, response);
            return;
        }

        if (signature == null || signature.trim().isEmpty()) {
            request.setAttribute("message", "Vui lòng dán chữ ký được tạo từ tool.");
            request.getRequestDispatcher("/page/key/submit_signature.jsp").forward(request, response);
            return;
        }

        try {
            int idDonHang = Integer.parseInt(idDonHangRaw.trim());

            String resultCode = orderSignatureService.submitSignature(idDonHang, signature.trim());
            String message = orderSignatureService.getVietnameseMessage(resultCode);

            request.setAttribute("idDonHang", idDonHang);
            request.setAttribute("resultCode", resultCode);
            request.setAttribute("message", message);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Xác thực chữ ký thất bại: " + e.getMessage());
        }

        request.getRequestDispatcher("/page/key/submit_signature.jsp").forward(request, response);
    }
}