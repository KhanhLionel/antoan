package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHang;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class DonHangDAO {

    public int insert(DonHang donHang) {
        String sql = """
                INSERT INTO don_hang (
                    ma_don_hang,
                    id_user,
                    id_user_key,
                    ten_nguoi_mua,
                    email_nguoi_mua,
                    sdt_nguoi_mua,
                    ma_khuyen_mai,
                    tien_giam,
                    tong_tien,
                    payload_json,
                    payload_hash,
                    trang_thai_don,
                    trang_thai_ky,
                    trang_thai_toan_ven
                )
                VALUES (
                    :maDonHang,
                    :idUser,
                    :idUserKey,
                    :tenNguoiMua,
                    :emailNguoiMua,
                    :sdtNguoiMua,
                    :maKhuyenMai,
                    :tienGiam,
                    :tongTien,
                    :payloadJson,
                    :payloadHash,
                    :trangThaiDon,
                    :trangThaiKy,
                    :trangThaiToanVen
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("maDonHang", donHang.getMaDonHang())
                        .bind("idUser", donHang.getIdUser())
                        .bind("idUserKey", donHang.getIdUserKey())
                        .bind("tenNguoiMua", donHang.getTenNguoiMua())
                        .bind("emailNguoiMua", donHang.getEmailNguoiMua())
                        .bind("sdtNguoiMua", donHang.getSdtNguoiMua())
                        .bind("maKhuyenMai", donHang.getMaKhuyenMai())
                        .bind("tienGiam", donHang.getTienGiam())
                        .bind("tongTien", donHang.getTongTien())
                        .bind("payloadJson", donHang.getPayloadJson())
                        .bind("payloadHash", donHang.getPayloadHash())
                        .bind("trangThaiDon", donHang.getTrangThaiDon())
                        .bind("trangThaiKy", donHang.getTrangThaiKy())
                        .bind("trangThaiToanVen", donHang.getTrangThaiToanVen())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public Optional<DonHang> findById(int id) {
        String sql = """
                SELECT *
                FROM don_hang
                WHERE id = :id
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .map((rs, ctx) -> mapDonHang(rs))
                        .findFirst()
        );
    }

    public int updatePayload(int idDonHang, String payloadJson, String payloadHash) {
        String sql = """
                UPDATE don_hang
                SET payload_json = :payloadJson,
                    payload_hash = :payloadHash,
                    ngay_cap_nhat = NOW()
                WHERE id = :idDonHang
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("payloadJson", payloadJson)
                        .bind("payloadHash", payloadHash)
                        .bind("idDonHang", idDonHang)
                        .execute()
        );
    }

    public int updateSignatureStatus(int idDonHang, String trangThaiDon, String trangThaiKy) {
        String sql = """
                UPDATE don_hang
                SET trang_thai_don = :trangThaiDon,
                    trang_thai_ky = :trangThaiKy,
                    ngay_cap_nhat = NOW()
                WHERE id = :idDonHang
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("trangThaiDon", trangThaiDon)
                        .bind("trangThaiKy", trangThaiKy)
                        .bind("idDonHang", idDonHang)
                        .execute()
        );
    }

    public int updateIntegrityStatus(int idDonHang, String trangThaiToanVen) {
        String sql = """
                UPDATE don_hang
                SET trang_thai_toan_ven = :trangThaiToanVen,
                    ngay_cap_nhat = NOW()
                WHERE id = :idDonHang
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("trangThaiToanVen", trangThaiToanVen)
                        .bind("idDonHang", idDonHang)
                        .execute()
        );
    }

    private DonHang mapDonHang(ResultSet rs) throws SQLException {
        DonHang donHang = new DonHang();

        donHang.setId(rs.getInt("id"));
        donHang.setMaDonHang(rs.getString("ma_don_hang"));
        donHang.setIdUser(rs.getInt("id_user"));

        Object idUserKey = rs.getObject("id_user_key");
        if (idUserKey != null) {
            donHang.setIdUserKey(((Number) idUserKey).intValue());
        } else {
            donHang.setIdUserKey(null);
        }

        donHang.setTenNguoiMua(rs.getString("ten_nguoi_mua"));
        donHang.setEmailNguoiMua(rs.getString("email_nguoi_mua"));
        donHang.setSdtNguoiMua(rs.getString("sdt_nguoi_mua"));
        donHang.setMaKhuyenMai(rs.getString("ma_khuyen_mai"));
        donHang.setTienGiam(rs.getBigDecimal("tien_giam"));
        donHang.setTongTien(rs.getBigDecimal("tong_tien"));
        donHang.setPayloadJson(rs.getString("payload_json"));
        donHang.setPayloadHash(rs.getString("payload_hash"));
        donHang.setTrangThaiDon(rs.getString("trang_thai_don"));
        donHang.setTrangThaiKy(rs.getString("trang_thai_ky"));
        donHang.setTrangThaiToanVen(rs.getString("trang_thai_toan_ven"));
        donHang.setNgayTao(rs.getTimestamp("ngay_tao"));
        donHang.setNgayCapNhat(rs.getTimestamp("ngay_cap_nhat"));

        return donHang;
    }
}