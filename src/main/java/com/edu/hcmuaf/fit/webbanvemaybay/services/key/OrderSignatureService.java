package com.edu.hcmuaf.fit.webbanvemaybay.services.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.SignedOrderDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.UserKeyDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHang;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.SignedOrder;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.RSAHelper;

public class OrderSignatureService {

    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final UserKeyDAO userKeyDAO = new UserKeyDAO();
    private final SignedOrderDAO signedOrderDAO = new SignedOrderDAO();

    public String submitSignature(int idDonHang, String signatureBase64) {
        DonHang donHang = donHangDAO.findById(idDonHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng id = " + idDonHang));

        if (donHang.getIdUserKey() == null) {
            donHangDAO.updateSignatureStatus(idDonHang, "CHO_KY", "CHUA_CO_KHOA");
            return "NO_PUBLIC_KEY";
        }

        UserKey userKey = userKeyDAO.findById(donHang.getIdUserKey())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy public key của đơn hàng"));

        SignedOrder signedOrder = new SignedOrder();
        signedOrder.setIdDonHang(idDonHang);
        signedOrder.setIdUserKey(userKey.getId());
        signedOrder.setPayloadHash(donHang.getPayloadHash());
        signedOrder.setSignature(signatureBase64);
        signedOrder.setSignatureAlgorithm("SHA256withRSA");
        signedOrder.setCurrent(true);

        if (donHang.getPayloadJson() == null || donHang.getPayloadJson().trim().isEmpty()) {
            signedOrder.setVerifyStatus("INVALID");

            signedOrderDAO.disableCurrentSignature(idDonHang);
            signedOrderDAO.insert(signedOrder);

            donHangDAO.updateSignatureStatus(idDonHang, "CHO_KY", "THIEU_PAYLOAD_JSON");
            return "MISSING_PAYLOAD_JSON";
        }

        if (userKey.getNgayBaoMat() != null
                && donHang.getNgayTao() != null
                && donHang.getNgayTao().after(userKey.getNgayBaoMat())) {

            signedOrder.setVerifyStatus("KEY_LOST");

            signedOrderDAO.disableCurrentSignature(idDonHang);
            signedOrderDAO.insert(signedOrder);

            donHangDAO.updateSignatureStatus(idDonHang, "CHO_KY", "KHOA_BI_BAO_MAT");
            return "KEY_LOST";
        }

        boolean valid = RSAHelper.verifySignature(
                donHang.getPayloadJson(),
                signatureBase64,
                userKey.getPublicKey()
        );

        signedOrder.setVerifyStatus(valid ? "VALID" : "INVALID");

        signedOrderDAO.disableCurrentSignature(idDonHang);
        signedOrderDAO.insert(signedOrder);

        if (valid) {
            donHangDAO.updateSignatureStatus(idDonHang, "CHO_XAC_THUC", "DA_KY");
            return "VALID";
        } else {
            donHangDAO.updateSignatureStatus(idDonHang, "CHO_KY", "KY_KHONG_HOP_LE");
            return "INVALID";
        }
    }

    public String getVietnameseMessage(String resultCode) {
        if ("VALID".equals(resultCode)) {
            return "Chữ ký hợp lệ. Đơn hàng đã được ký thành công.";
        }

        if ("INVALID".equals(resultCode)) {
            return "Chữ ký không hợp lệ. Vui lòng kiểm tra lại.";
        }

        if ("NO_PUBLIC_KEY".equals(resultCode)) {
            return "Người dùng chưa có public key để xác thực chữ ký.";
        }

        if ("MISSING_PAYLOAD_JSON".equals(resultCode)) {
            return "Đơn hàng chưa có dữ liệu JSON gốc để xác thực chữ ký.";
        }

        if ("KEY_LOST".equals(resultCode)) {
            return "Khóa dùng để ký đã bị báo mất trước thời điểm tạo đơn.";
        }

        return "Không xác định được kết quả xác thực chữ ký.";
    }
}