package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.UserDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.UserKeyDao;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey;
import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@WebServlet("/LossAndImportController")
@MultipartConfig
public class LossAndImportController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String matKhauXacNhan = request.getParameter("matKhauXacNhan");
        Part filePart = request.getPart("fileKhoaMoi");

        UserDAO userDAO = new UserDAO();
        UserKeyDao keyDAO = new UserKeyDao()
                ;

        String currentPasswordInDb = userDAO.getPasswordById(user.getId());
        if (currentPasswordInDb == null || !currentPasswordInDb.equals(matKhauXacNhan)) {
            session.setAttribute("errorKey", "Mật khẩu xác nhận không chính xác!");
            response.sendRedirect(request.getContextPath() + "/ThongTinKhachHangController");
            return;
        }
        String publicKeyContent = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), "UTF-8"))) {
            publicKeyContent = reader.lines().collect(Collectors.joining("\n"));
        }

        if (publicKeyContent.trim().isEmpty()) {
            session.setAttribute("errorKey", "File khóa trống hoặc không hợp lệ!");
            response.sendRedirect(request.getContextPath() + "/ThongTinKhachHangController");
            return;
        }
        keyDAO.reportLostKey(user.getId());

        UserKey newKey = new UserKey();
        newKey.setUserId(user.getId());
        newKey.setPublicKey(publicKeyContent.trim());
        newKey.setStatus(1);
        newKey.setCreatedAt(LocalDateTime.now());

        boolean success = keyDAO.addKey(newKey);

        if (success) {
            session.setAttribute("successKey", "Báo mất thành công và đã cập nhật khóa mới vào tài khoản!");
        } else {
            session.setAttribute("errorKey", "Lỗi hệ thống, không thể cập nhật khóa mới.");
        }

        response.sendRedirect(request.getContextPath() + "/ThongTinNguoiDungController");
    }
}
