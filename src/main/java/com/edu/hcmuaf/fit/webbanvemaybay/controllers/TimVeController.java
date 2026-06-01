package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.VeDto;
import com.edu.hcmuaf.fit.webbanvemaybay.services.TimVeService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "TimVeController", value = "/TimVeController")
public class TimVeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String khoiHanh = request.getParameter("khoi_hanh");
        String haCanh = request.getParameter("ha_canh");
        String hang_ghe = request.getParameter("hang_ghe");
        String diemDi = request.getParameter("diem_di");
        String diemDen =  request.getParameter("diem_den");
        String isXepVe = request.getParameter("is_xep_ve");
        String ngayDi = request.getParameter("ngay_di");
        boolean xepVe = "on".equals(isXepVe) ? true : false;

        String loaiHinh = request.getParameter("loai_hinh");
        String ngayVe = request.getParameter("ngay_ve");
        String ngayVeLinhHoat = request.getParameter("ngay_ve_linh_hoat");

        boolean isRoundTrip = "round_trip".equals(loaiHinh);
        boolean isReturnFlexible = "on".equals(ngayVeLinhHoat);

        TimVeService timVeService = new TimVeService();
        List<VeDto> listVeRes = timVeService.getListVeByFilter(
                khoiHanh, haCanh, hang_ghe, diemDi, diemDen, xepVe, ngayDi
        );
        request.setAttribute("listVeRes", listVeRes);

        if (isRoundTrip && ngayVe != null && !ngayVe.isEmpty()) {
            List<VeDto> listVeReturn = null;
            if (isReturnFlexible) {
                String tuNgay = ngayVe;
                String denNgay = ngayVe;
                try {
                    java.time.LocalDate date = java.time.LocalDate.parse(ngayVe);
                    tuNgay = date.minusDays(3).toString();
                    denNgay = date.plusDays(3).toString();
                } catch (Exception e) {
                }
                listVeReturn = timVeService.getListVeByFilterRange(
                        haCanh, khoiHanh, hang_ghe, diemDen, diemDi, xepVe, tuNgay, denNgay
                );
            } else {
                listVeReturn = timVeService.getListVeByFilter(
                        haCanh, khoiHanh, hang_ghe, diemDen, diemDi, xepVe, ngayVe
                );
            }
            request.setAttribute("listVeReturn", listVeReturn);
            request.setAttribute("isRoundTrip", true);
            request.setAttribute("ngayVe", ngayVe);
            request.setAttribute("isReturnFlexible", isReturnFlexible);
        }

        request.getRequestDispatcher("page/list_ve/list_ve.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}