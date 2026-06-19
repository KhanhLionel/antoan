package com.edu.hcmuaf.fit.webbanvemaybay.controllers;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.UserDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.GoogleUser;
import com.edu.hcmuaf.fit.webbanvemaybay.models.User;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.GoogleConstants;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.HashPassword;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebServlet(name = "GoogleLoginController", value = "/GoogleLoginController")
public class GoogleLoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            request.setAttribute("message", "Đăng nhập Google thất bại!");
            request.getRequestDispatcher("page/dang_nhap/dang_nhap.jsp").forward(request, response);
            return;
        }

        try {
            String accessToken = getToken(code);
            GoogleUser googleUser = getUserInfo(accessToken);

            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserByEmail(googleUser.getEmail());

            if (user == null) {
                user = new User();

                String usernameBase = googleUser.getName();

                user.setUsername(usernameBase);
                user.setPassword(HashPassword.hashPassword("PassGoogle@12345"));
                user.setEmail(googleUser.getEmail());
                user.setSoDienThoai("0000000000");

                user.setRole("user");
                user.setStatus(1);

                userDAO.addUser(user);

                user = userDAO.getUserByEmail(googleUser.getEmail());
            }

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/index.jsp");

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Lỗi hệ thống khi gọi API Google.");
            request.getRequestDispatcher("page/dang_nhap/dang_nhap.jsp").forward(request, response);
        }
    }

    private String getToken(String code) throws Exception {
        String params = "client_id=" + GoogleConstants.GOOGLE_CLIENT_ID
                + "&client_secret=" + GoogleConstants.GOOGLE_CLIENT_SECRET
                + "&redirect_uri=" + GoogleConstants.GOOGLE_REDIRECT_URI
                + "&code=" + code
                + "&grant_type=" + GoogleConstants.GOOGLE_GRANT_TYPE;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(GoogleConstants.GOOGLE_LINK_GET_TOKEN))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(params))
                .build();

        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        JsonObject jsonObject = new Gson().fromJson(res.body(), JsonObject.class);
        return jsonObject.get("access_token").getAsString();
    }

    private GoogleUser getUserInfo(String accessToken) throws Exception {
        String link = GoogleConstants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();
        HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
        return new Gson().fromJson(res.body(), GoogleUser.class);
    }
}
