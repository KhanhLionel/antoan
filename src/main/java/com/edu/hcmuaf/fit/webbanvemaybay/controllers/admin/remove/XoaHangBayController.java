package com.edu.hcmuaf.fit.webbanvemaybay.controllers.admin.remove;

import com.edu.hcmuaf.fit.webbanvemaybay.services.admin.HangBayService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "XoaHangBayController", value = "/admin/XoaHangBayController")
public class XoaHangBayController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");

        HangBayService hangBayService = new HangBayService();
        boolean isRemove = hangBayService.removeHangBayById(id);
        HttpSession session = request.getSession();
        if (isRemove) {
            session.setAttribute("messageRemove", "xoá thành công");
        } else {
            session.setAttribute("messageRemove", "xoá thất bại");
        }
        response.sendRedirect(request.getContextPath() + "/admin/HangBayController");
    }
}