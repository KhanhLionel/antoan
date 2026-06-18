package com.edu.hcmuaf.fit.webbanvemaybay.services.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.OrderIntegrityAlertDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHang;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.OrderIntegrityAlert;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.RSAHelper;

public class OrderIntegrityService {

    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final OrderIntegrityAlertDAO alertDAO = new OrderIntegrityAlertDAO();
    private final OrderPayloadService orderPayloadService = new OrderPayloadService();

    public boolean checkIntegrity(int idDonHang, Integer actorUserId) {
        DonHang donHang = donHangDAO.findById(idDonHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng id = " + idDonHang));

        String oldHash = donHang.getPayloadHash();

        if (oldHash == null || oldHash.trim().isEmpty()) {
            donHangDAO.updateIntegrityStatus(idDonHang, "CHUA_KIEM_TRA");
            return false;
        }

        String currentPayloadJson = orderPayloadService.buildCurrentPayloadJson(idDonHang);
        String currentHash = RSAHelper.sha256(currentPayloadJson);

        if (oldHash.equals(currentHash)) {
            donHangDAO.updateIntegrityStatus(idDonHang, "HOP_LE");
            return true;
        }

        donHangDAO.updateIntegrityStatus(idDonHang, "BI_CHINH_SUA");

        OrderIntegrityAlert alert = new OrderIntegrityAlert();
        alert.setIdDonHang(idDonHang);
        alert.setActorUserId(actorUserId);
        alert.setAlertType("DON_HANG_BI_CHINH_SUA");
        alert.setOldHash(oldHash);
        alert.setCurrentHash(currentHash);
        alert.setMessage("Đơn hàng đã bị thay đổi dữ liệu không được phép sửa. Hash hiện tại khác với hash ban đầu, cần admin kiểm tra.");
        alert.setStatus("MOI");

        alertDAO.insert(alert);

        return false;
    }
}