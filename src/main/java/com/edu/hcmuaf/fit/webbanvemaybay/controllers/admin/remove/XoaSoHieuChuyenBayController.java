package com.edu.hcmuaf.fit.webbanvemaybay.controllers.admin.remove;

import com.edu.hcmuaf.fit.webbanvemaybay.services.admin.SoHieuChuyenBayService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "XoaSoHieuChuyenBayController", value = "/admin/XoaSoHieuChuyenBayController")
public class XoaSoHieuChuyenBayController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id =  request.getParameter("id");
        SoHieuChuyenBayService soHieuChuyenBayService = new SoHieuChuyenBayService();
        String message = soHieuChuyenBayService.deleteSoHieuChuyenBayById(id);
        HttpSession session = request.getSession();
        session.setAttribute("messageRemove", message);
        response.sendRedirect(request.getContextPath() + "/admin/SoHieuChuyenBayController");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}