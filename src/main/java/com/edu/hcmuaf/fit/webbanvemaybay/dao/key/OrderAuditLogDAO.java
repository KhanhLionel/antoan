package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.OrderAuditLog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
// lưu vết xem ai sửa đơn
public class OrderAuditLogDAO {

    public int insert(OrderAuditLog log) {
        String sql = """
                INSERT INTO order_audit_log
                (
                    id_don_hang,
                    actor_user_id,
                    action,
                    before_data,
                    after_data
                )
                VALUES
                (
                    :idDonHang,
                    :actorUserId,
                    :action,
                    :beforeData,
                    :afterData
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idDonHang", log.getIdDonHang())
                        .bind("actorUserId", log.getActorUserId())
                        .bind("action", log.getAction())
                        .bind("beforeData", log.getBeforeData())
                        .bind("afterData", log.getAfterData())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public List<OrderAuditLog> findByDonHangId(int idDonHang) {
        String sql = """
                SELECT *
                FROM order_audit_log
                WHERE id_don_hang = :idDonHang
                ORDER BY created_at DESC
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("idDonHang", idDonHang)
                        .map((rs, ctx) -> mapOrderAuditLog(rs))
                        .list()
        );
    }

    private OrderAuditLog mapOrderAuditLog(ResultSet rs) throws SQLException {
        OrderAuditLog log = new OrderAuditLog();

        log.setId(rs.getInt("id"));
        log.setIdDonHang(rs.getInt("id_don_hang"));

        int actorUserId = rs.getInt("actor_user_id");
        log.setActorUserId(rs.wasNull() ? null : actorUserId);

        log.setAction(rs.getString("action"));
        log.setBeforeData(rs.getString("before_data"));
        log.setAfterData(rs.getString("after_data"));
        log.setCreatedAt(rs.getTimestamp("created_at"));

        return log;
    }
}