package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.SignedOrder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
// lưu chữ ký sau khi users ký bằng tool
public class SignedOrderDAO {

    public int insert(SignedOrder signedOrder) {
        String sql = """
                INSERT INTO signed_orders
                (
                    id_don_hang,
                    id_user_key,
                    payload_hash,
                    signature,
                    signature_algorithm,
                    verify_status,
                    is_current
                )
                VALUES
                (
                    :idDonHang,
                    :idUserKey,
                    :payloadHash,
                    :signature,
                    :signatureAlgorithm,
                    :verifyStatus,
                    :isCurrent
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idDonHang", signedOrder.getIdDonHang())
                        .bind("idUserKey", signedOrder.getIdUserKey())
                        .bind("payloadHash", signedOrder.getPayloadHash())
                        .bind("signature", signedOrder.getSignature())
                        .bind("signatureAlgorithm", signedOrder.getSignatureAlgorithm())
                        .bind("verifyStatus", signedOrder.getVerifyStatus())
                        .bind("isCurrent", signedOrder.isCurrent())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public Optional<SignedOrder> findCurrentByDonHangId(int idDonHang) {
        String sql = """
                SELECT *
                FROM signed_orders
                WHERE id_don_hang = :idDonHang
                  AND is_current = 1
                ORDER BY ngay_ky DESC
                LIMIT 1
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("idDonHang", idDonHang)
                        .map((rs, ctx) -> mapSignedOrder(rs))
                        .findOne()
        );
    }

    public int disableCurrentSignature(int idDonHang) {
        String sql = """
                UPDATE signed_orders
                SET is_current = 0
                WHERE id_don_hang = :idDonHang
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idDonHang", idDonHang)
                        .execute()
        );
    }

    private SignedOrder mapSignedOrder(ResultSet rs) throws SQLException {
        SignedOrder signedOrder = new SignedOrder();

        signedOrder.setId(rs.getInt("id"));
        signedOrder.setIdDonHang(rs.getInt("id_don_hang"));
        signedOrder.setIdUserKey(rs.getInt("id_user_key"));
        signedOrder.setPayloadHash(rs.getString("payload_hash"));
        signedOrder.setSignature(rs.getString("signature"));
        signedOrder.setSignatureAlgorithm(rs.getString("signature_algorithm"));
        signedOrder.setVerifyStatus(rs.getString("verify_status"));
        signedOrder.setCurrent(rs.getBoolean("is_current"));
        signedOrder.setNgayKy(rs.getTimestamp("ngay_ky"));

        return signedOrder;
    }
}