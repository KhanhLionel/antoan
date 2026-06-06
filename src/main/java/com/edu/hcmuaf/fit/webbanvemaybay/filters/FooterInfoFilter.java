package com.edu.hcmuaf.fit.webbanvemaybay.filters;

import com.edu.hcmuaf.fit.webbanvemaybay.models.DaiLy;
import com.edu.hcmuaf.fit.webbanvemaybay.services.DaiLyService;
import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.VeDto;
import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.DeXuatVeDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(urlPatterns = "/*", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class FooterInfoFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            ServletException, IOException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        DaiLyService daiLyService = new DaiLyService();
        DaiLy daiLy = daiLyService.getDaiLy();
        req.setAttribute("daiLy", daiLy);

        String uri = req.getRequestURI();
        if (uri.endsWith("/") || uri.endsWith("/index.jsp") || uri.equals(req.getContextPath()) || uri.endsWith("/ROOT/")) {
            com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO veDAO = new com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO();
            List<Integer> deXuatVeIds = veDAO.getDeXuatVeIds();
            List<DeXuatVeDto> listDeXuatVe = new ArrayList<>();
            com.edu.hcmuaf.fit.webbanvemaybay.services.TimVeService timVeService = new com.edu.hcmuaf.fit.webbanvemaybay.services.TimVeService();
            if (deXuatVeIds != null) {
                for (int id : deXuatVeIds) {
                    com.edu.hcmuaf.fit.webbanvemaybay.models.Ve ve = veDAO.getVeById(id);
                    VeDto veDto = timVeService.getVeByIdVe(id);
                    if (ve != null && veDto != null) {
                        DeXuatVeDto deXuat = new DeXuatVeDto();
                        deXuat.setVeDto(veDto);
                        deXuat.setSoLuongTon(ve.getSoLuongTon());
                        
                        int pt = 15;
                        String code = "SALE15";
                        if (ve.getSoLuongTon() >= 50) {
                            pt = 30;
                            code = "SALE30";
                        } else if (ve.getSoLuongTon() >= 30) {
                            pt = 20;
                            code = "SALE20";
                        }
                        deXuat.setPhanTramGiam(pt);
                        deXuat.setVoucherCode(code);
                        
                        double giaGiam = ve.getGia() * (1 - pt / 100.0);
                        deXuat.setGiaDaGiam(com.edu.hcmuaf.fit.webbanvemaybay.services.core.FormatVND.formatVND(giaGiam));
                        listDeXuatVe.add(deXuat);
                    }
                }
            }
            req.setAttribute("listDeXuatVe", listDeXuatVe);
        }

        if (uri.endsWith("ve_noi_dia.jsp")) {
            com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO veDAO = new com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO();
            List<VeDto> listVe = veDAO.getListVeNoiDia();
            req.setAttribute("listVeNoiDia", listVe);
        } else if (uri.endsWith("ve_quoc_te.jsp")) {
            com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO veDAO = new com.edu.hcmuaf.fit.webbanvemaybay.dao.VeDAO();
            List<VeDto> listVe = veDAO.getListVeQuocTe();
            req.setAttribute("listVeQuocTe", listVe);
        }


        chain.doFilter(request, response);
    }
}