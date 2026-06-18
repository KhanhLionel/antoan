package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.OrderIntegrityAlert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// cảnh báo khi đơn bị sửa
public class OrderIntegrityAlertDAO {

    public int insert(OrderIntegrityAlert alert) {
        String sql = """
                INSERT INTO order_integrity_alerts
                (
                    id_don_hang,
                    actor_user_id,
                    alert_type,
                    old_hash,
                    current_hash,
                    message,
                    status
                )
                VALUES
                (
                    :idDonHang,
                    :actorUserId,
                    :alertType,
                    :oldHash,
                    :currentHash,
                    :message,
                    :status
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idDonHang", alert.getIdDonHang())
                        .bind("actorUserId", alert.getActorUserId())
                        .bind("alertType", alert.getAlertType())
                        .bind("oldHash", alert.getOldHash())
                        .bind("currentHash", alert.getCurrentHash())
                        .bind("message", alert.getMessage())
                        .bind("status", alert.getStatus())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<OrderIntegrityAlert> findAllNew() {
        String sql = """
                SELECT *
                FROM order_integrity_alerts
                WHERE status = 'NEW'
                ORDER BY created_at DESC
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .map((rs, ctx) -> mapOrderIntegrityAlert(rs))
                        .list()
        );
    }

    private OrderIntegrityAlert mapOrderIntegrityAlert(ResultSet rs) throws SQLException {
        OrderIntegrityAlert alert = new OrderIntegrityAlert();

        alert.setId(rs.getInt("id"));
        alert.setIdDonHang(rs.getInt("id_don_hang"));

        int actorUserId = rs.getInt("actor_user_id");
        alert.setActorUserId(rs.wasNull() ? null : actorUserId);

        alert.setAlertType(rs.getString("alert_type"));
        alert.setOldHash(rs.getString("old_hash"));
        alert.setCurrentHash(rs.getString("current_hash"));
        alert.setMessage(rs.getString("message"));
        alert.setStatus(rs.getString("status"));
        alert.setCreatedAt(rs.getTimestamp("created_at"));

        return alert;
    }
}