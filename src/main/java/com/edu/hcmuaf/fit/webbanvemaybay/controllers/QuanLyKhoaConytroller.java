package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.UserKeyDao; // Sửa lại chữ o viết thường/hoa theo đúng file của bạn (UserKeyDao hoặc UserKeyDAO)
import com.edu.hcmuaf.fit.webbanvemaybay.models.UserKey;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "QuanLyKhoaController", value = "/QuanLyKhoaController")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class QuanLyKhoaController extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        hienThiTrangKhoa(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        String action = request.getParameter("action");
        UserKeyDao keyDAO = new UserKeyDao();

        if ("uploadKey".equals(action)) {
            try {
                Part filePart = request.getPart("publicKeyFile");
                if (filePart == null || filePart.getSize() == 0) {
                    request.setAttribute("keyStatus", "error");
                    request.setAttribute("keyMessage", "Vui lòng chọn một file khóa hợp lệ!");
                    hienThiTrangKhoa(request, response);
                    return;
                }
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream(), StandardCharsets.UTF_8))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String trimmedLine = line.trim();
                        if (trimmedLine.startsWith("-----BEGIN") || trimmedLine.startsWith("-----END")) {
                            continue;
                        }
                        sb.append(trimmedLine);
                    }
                }
                String pubKeyContent = sb.toString().replaceAll("\\s+", "");

                if (pubKeyContent.isEmpty()) {
                    request.setAttribute("keyStatus", "error");
                    request.setAttribute("keyMessage", "Nội dung file khóa rỗng hoặc không đúng định dạng chuỗi Base64!");
                } else {
                    boolean isSaved = keyDAO.insertNewKey(user.getId(), pubKeyContent);

                    if (isSaved) {
                        request.setAttribute("keyStatus", "success");
                        request.setAttribute("keyMessage", " Dã tải khóa lên thành công");
                    } else {
                        request.setAttribute("keyStatus", "error");
                        request.setAttribute("keyMessage", "Lưu khóa không thành công");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("keyStatus", "error");
                request.setAttribute("keyMessage", "" + e.getMessage());
            }
        }
        else if ("reportLost".equals(action)) {
            Boolean otpVerified =
                    (Boolean) session.getAttribute("otpVerified");

            if (otpVerified == null || !otpVerified) {

                request.setAttribute("keyStatus", "error");
                request.setAttribute("keyMessage",
                        "Bạn phải xác thực OTP trước khi báo mất khóa.");

                hienThiTrangKhoa(request, response);
                return;
            }

            session.removeAttribute("otpVerified");
            session.removeAttribute("otpCodeKey");
            session.removeAttribute("otpExpiryKey");
            try {
                boolean isRevoked = keyDAO.reportLostKey(user.getId());

                if (isRevoked) {
                    request.setAttribute("keyStatus", "success");
                    request.setAttribute("keyMessage", "Hủy khóa thành công");
                } else {
                    request.setAttribute("keyStatus", "error");
                    request.setAttribute("keyMessage", "Hủy khóa không thành công");
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("keyStatus", "error");
                request.setAttribute("keyMessage", " " + e.getMessage());
            }
        }
        hienThiTrangKhoa(request, response);
    }
    private void hienThiTrangKhoa(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
            return;
        }

        UserKeyDao keyDAO = new UserKeyDao();
        UserKey activeKey = keyDAO.getActiveKeyByUserId(user.getId());

        if (activeKey != null) {
            request.setAttribute("hasActiveKey", true);
            request.setAttribute("activePublicKey", activeKey.getPublicKey());

            if (activeKey.getCreatedAt() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                request.setAttribute("keyCreatedAt", activeKey.getCreatedAt().format(formatter));
            }
        } else {
            request.setAttribute("hasActiveKey", false);
        }

        request.getRequestDispatcher("page/thong_tin_khach_hang/thong_tin_khach_hang.jsp").forward(request, response);
    }
}