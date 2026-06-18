package com.edu.hcmuaf.fit.webbanvemaybay.services.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangChiTietDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.key.DonHangDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHang;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHangChiTiet;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.RSAHelper;

import java.math.BigDecimal;
import java.util.List;

public class OrderPayloadService {

    private final DonHangDAO donHangDAO = new DonHangDAO();
    private final DonHangChiTietDAO donHangChiTietDAO = new DonHangChiTietDAO();

    public String refreshPayloadAndHash(int idDonHang) {
        DonHang donHang = donHangDAO.findById(idDonHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng id = " + idDonHang));

        List<DonHangChiTiet> details = donHangChiTietDAO.findByDonHangId(idDonHang);

        String payloadJson = buildPayloadJson(donHang, details);
        String payloadHash = RSAHelper.sha256(payloadJson);

        donHangDAO.updatePayload(idDonHang, payloadJson, payloadHash);

        return payloadJson;
    }

    public String buildCurrentPayloadJson(int idDonHang) {
        DonHang donHang = donHangDAO.findById(idDonHang)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng id = " + idDonHang));

        List<DonHangChiTiet> details = donHangChiTietDAO.findByDonHangId(idDonHang);

        return buildPayloadJson(donHang, details);
    }

    public String buildPayloadJson(DonHang donHang, List<DonHangChiTiet> details) {
        StringBuilder json = new StringBuilder();

        json.append("{");
        json.append("\"ma_don_hang\":\"").append(escape(donHang.getMaDonHang())).append("\",");
        json.append("\"id_user\":").append(donHang.getIdUser()).append(",");
        json.append("\"id_user_key\":").append(donHang.getIdUserKey() == null ? "null" : donHang.getIdUserKey()).append(",");
        json.append("\"ten_nguoi_mua\":\"").append(escape(donHang.getTenNguoiMua())).append("\",");
        json.append("\"email_nguoi_mua\":\"").append(escape(donHang.getEmailNguoiMua())).append("\",");
        json.append("\"sdt_nguoi_mua\":\"").append(escape(donHang.getSdtNguoiMua())).append("\",");
        json.append("\"ma_khuyen_mai\":\"").append(escape(donHang.getMaKhuyenMai())).append("\",");
        json.append("\"tien_giam\":").append(toMoney(donHang.getTienGiam())).append(",");
        json.append("\"tong_tien\":").append(toMoney(donHang.getTongTien())).append(",");
        json.append("\"ngay_tao\":\"").append(donHang.getNgayTao() == null ? "" : donHang.getNgayTao()).append("\",");

        json.append("\"items\":[");
        for (int i = 0; i < details.size(); i++) {
            DonHangChiTiet item = details.get(i);

            json.append("{");
            json.append("\"id_ve\":").append(item.getIdVe()).append(",");
            json.append("\"so_luong\":").append(item.getSoLuong()).append(",");
            json.append("\"don_gia\":").append(toMoney(item.getDonGia())).append(",");
            json.append("\"thanh_tien\":").append(toMoney(item.getThanhTien())).append(",");
            json.append("\"ten_ve\":\"").append(escape(item.getTenVeSnapshot())).append("\",");
            json.append("\"thong_tin_ve\":\"").append(escape(item.getThongTinVeSnapshot())).append("\"");
            json.append("}");

            if (i < details.size() - 1) {
                json.append(",");
            }
        }
        json.append("]");

        json.append("}");

        return json.toString();
    }

    private String toMoney(BigDecimal value) {
        if (value == null) {
            return "0";
        }

        return value.stripTrailingZeros().toPlainString();
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }

        return value
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "")
                .replace("\n", "\\n")
                .trim();
    }
}