package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import com.edu.hcmuaf.fit.webbanvemaybay.services.HuyVeService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HuyVeController", value = "/HuyVeController")
public class HuyVeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idVe = request.getParameter("idVe");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        HuyVeService huyVeService = new HuyVeService();
        boolean isHuyVe = huyVeService.huyVe(idVe, user.getId());
        if (isHuyVe) {
            session.setAttribute("messageHuyVe", "Huỷ vé thành công");

        }else{
            session.setAttribute("messageHuyVe",
                    "Lỗi: Không thể huỷ vé. Vé chỉ có thể huỷ trước 1 ngày trước giờ khởi hành.");
        }
        response.sendRedirect(request.getContextPath() + "/VeDaDatController");
    }
}