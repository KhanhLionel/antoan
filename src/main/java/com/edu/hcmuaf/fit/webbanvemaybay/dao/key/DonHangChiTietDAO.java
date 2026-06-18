package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.DonHangChiTiet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DonHangChiTietDAO {
// lưu và lấy danh sách vé trong đơn haàng
    public int insert(DonHangChiTiet detail) {
        String sql = """
                INSERT INTO don_hang_chi_tiet
                (
                    id_don_hang,
                    id_ve,
                    so_luong,
                    don_gia,
                    thanh_tien,
                    ten_ve_snapshot,
                    thong_tin_ve_snapshot
                )
                VALUES
                (
                    :idDonHang,
                    :idVe,
                    :soLuong,
                    :donGia,
                    :thanhTien,
                    :tenVeSnapshot,
                    :thongTinVeSnapshot
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idDonHang", detail.getIdDonHang())
                        .bind("idVe", detail.getIdVe())
                        .bind("soLuong", detail.getSoLuong())
                        .bind("donGia", detail.getDonGia())
                        .bind("thanhTien", detail.getThanhTien())
                        .bind("tenVeSnapshot", detail.getTenVeSnapshot())
                        .bind("thongTinVeSnapshot", detail.getThongTinVeSnapshot())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<DonHangChiTiet> findByDonHangId(int idDonHang) {
        String sql = """
                SELECT *
                FROM don_hang_chi_tiet
                WHERE id_don_hang = :idDonHang
                ORDER BY id ASC
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("idDonHang", idDonHang)
                        .map((rs, ctx) -> mapDonHangChiTiet(rs))
                        .list()
        );
    }

    private DonHangChiTiet mapDonHangChiTiet(ResultSet rs) throws SQLException {
        DonHangChiTiet detail = new DonHangChiTiet();

        detail.setId(rs.getInt("id"));
        detail.setIdDonHang(rs.getInt("id_don_hang"));
        detail.setIdVe(rs.getInt("id_ve"));
        detail.setSoLuong(rs.getInt("so_luong"));
        detail.setDonGia(rs.getBigDecimal("don_gia"));
        detail.setThanhTien(rs.getBigDecimal("thanh_tien"));
        detail.setTenVeSnapshot(rs.getString("ten_ve_snapshot"));
        detail.setThongTinVeSnapshot(rs.getString("thong_tin_ve_snapshot"));

        return detail;
    }
}