package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.models.ThongTinNguoiDung;
import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import com.edu.hcmuaf.fit.webbanvemaybay.services.ThongTinNguoiDungService;
import com.edu.hcmuaf.fit.webbanvemaybay.services.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ThongTinNguoiDungController", value = "/ThongTinNguoiDungController")
public class ThongTinNguoiDungController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.setAttribute("messageLogin", "Bạn phải đăng nhập!");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        String id = request.getParameter("id");
        UserService userService = new UserService();
        User user = userService.getUserById(id);
        ThongTinNguoiDungService thongTinNguoiDungService = new ThongTinNguoiDungService();
        ThongTinNguoiDung thongTinNguoiDung = thongTinNguoiDungService.getThongTinNguoiDungByIdUser(id);
        UserKeyDao keyDAO = new UserKeyDao();
        boolean hasKey = keyDAO.hasActiveKey(a);
        request.setAttribute("hasActiveKey", hasKey);
        request.setAttribute("info", thongTinNguoiDung);
        request.setAttribute("user", user);
        request.getRequestDispatcher("/page/thong_tin_khach_hang/thong_tin_khach_hang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}