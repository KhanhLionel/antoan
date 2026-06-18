package com.edu.hcmuaf.fit.webbanvemaybay.controllers.Key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.UserKeyDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Optional;

@WebServlet(name = "KeyManagementController", value = "/KeyManagementController")
public class KeyManagementController extends HttpServlet {

    private final UserKeyDAO userKeyDAO = new UserKeyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idUser = getCurrentUserId(request);

        Optional<UserKey> activeKey = userKeyDAO.findActiveKeyByUserId(idUser);

        request.setAttribute("idUser", idUser);
        request.setAttribute("activeKey", activeKey.orElse(null));

        request.getRequestDispatcher("/page/key/key.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idUser = getCurrentUserId(request);
        String action = request.getParameter("action");

        if ("addPublicKey".equals(action)) {
            addPublicKey(request, idUser);
        } else if ("reportLost".equals(action)) {
            reportLostKey(request, idUser);
        }

        response.sendRedirect(request.getContextPath() + "/KeyManagementController");
    }

    private void addPublicKey(HttpServletRequest request, int idUser) {
        String publicKey = request.getParameter("publicKey");

        if (publicKey == null || publicKey.trim().isEmpty()) {
            request.getSession().setAttribute("message", "Vui lòng nhập public key.");
            return;
        }

        publicKey = publicKey.trim();

        try {
            UserKey key = new UserKey();
            key.setIdUser(idUser);
            key.setPublicKey(publicKey);
            key.setKeyFingerprint(createFingerprint(publicKey));
            key.setAlgorithm("RSA");
            key.setKeySize(2048);
            key.setStatus("ACTIVE");

            userKeyDAO.deactivateActiveKeysByUserId(idUser);

            int newKeyId = userKeyDAO.insert(key);
            userKeyDAO.updateCurrentKeyId(idUser, newKeyId);

            request.getSession().setAttribute("message", "Thêm public key thành công.");
        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Thêm public key thất bại.");
        }
    }

    private void reportLostKey(HttpServletRequest request, int idUser) {
        Optional<UserKey> activeKey = userKeyDAO.findActiveKeyByUserId(idUser);

        if (activeKey.isEmpty()) {
            request.getSession().setAttribute("message", "Không có khóa đang hoạt động.");
            return;
        }

        userKeyDAO.reportLostKey(activeKey.get().getId());
        userKeyDAO.clearCurrentKeyId(idUser);

        request.getSession().setAttribute("message", "Đã báo mất khóa hiện tại.");
    }

    private String createFingerprint(String publicKey) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(publicKey.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(hash);
    }

    private int getCurrentUserId(HttpServletRequest request) {
        Object idUser = request.getSession().getAttribute("idUser");

        if (idUser instanceof Integer) {
            return (Integer) idUser;
        }

        if (idUser instanceof String) {
            try {
                return Integer.parseInt((String) idUser);
            } catch (Exception ignored) {
            }
        }

        return 8;
    }
}