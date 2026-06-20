package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/VerifyOtpController")
public class VerifyOtpController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String inputOtp = request.getParameter("otp");

        String savedOtp =
                (String) session.getAttribute("otpCodeKey");

        Long expiry =
                (Long) session.getAttribute("otpExpiryKey");

        if(savedOtp == null || expiry == null){
            response.getWriter().write("otp_not_found");
            return;
        }

        if(System.currentTimeMillis() > expiry){
            response.getWriter().write("otp_expired");
            return;
        }

        if(!savedOtp.equals(inputOtp)){
            response.getWriter().write("otp_invalid");
            return;
        }

        session.setAttribute("otpVerified", true);

        response.getWriter().write("success");
    }
}
