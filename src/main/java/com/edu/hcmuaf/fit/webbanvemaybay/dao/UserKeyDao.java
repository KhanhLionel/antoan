package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import org.jdbi.v3.core.Jdbi;

import java.time.LocalDateTime;

public class UserKeyDao extends DBContext {
        public boolean hasActiveKey(int u) {
            try {
                Jdbi jdbi = get();
                int soLuong = jdbi.withHandle(h -> {
                    String q = "SELECT COUNT(*) FROM user_keys WHERE user_id = :idUser AND status = 1";
                    return h.createQuery(q)
                            .bind("idUser", u)
                            .mapTo(Integer.class)
                            .one();
                });
                return soLuong > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public boolean addKey(UserKey userKey) {
            try {
                Jdbi jdbi = get();
                int soDongThayDoi = jdbi.withHandle(h -> {
                    String q = "INSERT INTO user_keys (user_id, public_key, status, created_at) " +
                            "VALUES (:userId, :publicKey, :status, :createdAt)";
                    return h.createUpdate(q)
                            .bindBean(userKey)
                            .execute();
                });
                return soDongThayDoi > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public boolean reportLostKey(int idUser) {
            try {
                Jdbi jdbi = get();
                int soDongThayDoi = jdbi.withHandle(h -> {
                    String q = "UPDATE user_keys SET status = 0 WHERE user_id = :idUser AND status = 1";
                    return h.createUpdate(q)
                            .bind("idUser", idUser)
                            .execute();
                });
                return soDongThayDoi > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        public UserKey getActiveKeyByUserId(int userId) {
            try {
                Jdbi jdbi = get();
                return jdbi.withHandle(h -> {
                    String q = "SELECT * FROM user_keys WHERE user_id = :userId AND status = 1 LIMIT 1";

                    return h.createQuery(q)
                            .bind("userId", userId)
                            .mapToBean(UserKey.class)
                            .findFirst()
                            .orElse(null);
                });
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public boolean insertNewKey(int id, String pubKeyContent) {
            try {
                Jdbi jdbi = get();
                return jdbi.inTransaction(h -> {

                    String sqlRevokeOld = "UPDATE user_keys SET status = 0, revoked_at = :now WHERE user_id = :userId AND status = 1";
                    h.createUpdate(sqlRevokeOld)
                            .bind("now", LocalDateTime.now())
                            .bind("userId", id)
                            .execute();

                    // BƯỚC 2: Chèn khóa mới lên với trạng thái mặc định kích hoạt (status = 1)
                    // Các trường 'status' và 'created_at' được gán trực tiếp tại đây!
                    String sqlInsert = "INSERT INTO user_keys (user_id, public_key, status, created_at) " +
                            "VALUES (:userId, :publicKey, 1, :now)";

                    int rowsAffected = h.createUpdate(sqlInsert)
                            .bind("userId", id)
                            .bind("publicKey", pubKeyContent)
                            .bind("now", LocalDateTime.now()) // gán thời gian hiện tại cho created_at
                            .execute();

                    return rowsAffected > 0; // Trả về true nếu INSERT thành công vào DB
                });
            } catch (Exception e) {
                System.err.println("Lỗi DB khi lưu khóa: " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        public boolean revokeActiveKey(int userId) {
            try {
                Jdbi jdbi = get();
                int rows = jdbi.withHandle(h -> {
                    String sql = "UPDATE user_keys SET status = 0, revoked_at = :now WHERE user_id = :userId AND status = 1";
                    return h.createUpdate(sql)
                            .bind("userId", userId)
                            .bind("now", LocalDateTime.now())
                            .execute();
                });
                return rows > 0;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        // UserKeyDao.java — thêm hàm mới
        public UserKey getKeyById(long keyId) {
            try {
                Jdbi jdbi = get();
                return jdbi.withHandle(h -> {
                    String q = "SELECT * FROM user_keys WHERE id = :keyId LIMIT 1";
                    return h.createQuery(q)
                            .bind("keyId", keyId)
                            .map((rs, ctx) -> {
                                UserKey key = new UserKey();
                                key.setId(rs.getInt("id"));
                                key.setUserId(rs.getInt("user_id"));
                                key.setPublicKey(rs.getString("public_key"));
                                key.setStatus(rs.getInt("status"));
                                key.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
                                return key;
                            })
                            .findFirst()
                            .orElse(null);
                });
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public static void main(String[] args) {
            com.edu.hcmuaf.fit.webbanvemaybay.dao.UserKeyDao userKeyDao = new com.edu.hcmuaf.fit.webbanvemaybay.dao.UserKeyDao();
            UserKey a = new com.edu.hcmuaf.fit.webbanvemaybay.dao.UserKeyDao().getActiveKeyByUserId(9);
            System.out.println(a);
            System.out.println(userKeyDao.getActiveKeyByUserId(9));
        }

}
