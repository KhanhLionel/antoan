package com.edu.hcmuaf.fit.webbanvemaybay.controllers;


import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Random;

@WebServlet("/SendOtpController")
public class SendOtpController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("SESSION USER = " + user);

        if(user != null){
            System.out.println("EMAIL = " + user.getEmail());
        }
        if (user == null || user.getEmail() == null) {
            response.getWriter().write("error_auth");
            return;
        }
        String otpCode = String.format("%06d", new Random().nextInt(999999));
        long expiryTime = System.currentTimeMillis() + (5 * 60 * 1000);
        session.setAttribute("otpCodeKey", otpCode);
        session.setAttribute("otpExpiryKey", expiryTime);


        try {
            String subject = "Mã xác nhận báo mất khóa bảo mật";
            String content = "Chào bạn, mã OTP xác nhận báo mất và đổi khóa mới của bạn là: "
                    + otpCode + ". Mã này có hiệu lực trong 5 phút.";

            MailService.sendMail(user.getEmail(), subject, content);
            System.out.println("Gửi OTP thành công tới " + user.getEmail() + ": " + otpCode);

            response.getWriter().write("success");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error_send");
        }
    }
}