package com.edu.hcmuaf.fit.webbanvemaybay.dao.key;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Key.UserKey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserKeyDAO {

    public Optional<UserKey> findActiveKeyByUserId(int idUser) {
        String sql = """
                SELECT *
                FROM user_key
                WHERE id_user = :idUser
                  AND status = 'ACTIVE'
                ORDER BY id DESC
                LIMIT 1
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("idUser", idUser)
                        .map((rs, ctx) -> mapUserKey(rs))
                        .findFirst()
        );
    }

    public Optional<UserKey> findById(int id) {
        String sql = """
                SELECT *
                FROM user_key
                WHERE id = :id
                """;

        return DBContext.get().withHandle(handle ->
                handle.createQuery(sql)
                        .bind("id", id)
                        .map((rs, ctx) -> mapUserKey(rs))
                        .findFirst()
        );
    }

    public int insert(UserKey key) {
        String sql = """
                INSERT INTO user_key (
                    id_user,
                    public_key,
                    key_fingerprint,
                    algorithm,
                    key_size,
                    status
                )
                VALUES (
                    :idUser,
                    :publicKey,
                    :keyFingerprint,
                    :algorithm,
                    :keySize,
                    :status
                )
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idUser", key.getIdUser())
                        .bind("publicKey", key.getPublicKey())
                        .bind("keyFingerprint", key.getKeyFingerprint())
                        .bind("algorithm", key.getAlgorithm())
                        .bind("keySize", key.getKeySize())
                        .bind("status", key.getStatus())
                        .executeAndReturnGeneratedKeys("id")
                        .mapTo(Integer.class)
                        .one()
        );
    }

    public int reportLostKey(int idKey) {
        String sql = """
                UPDATE user_key
                SET status = 'LOST',
                    ngay_bao_mat = NOW()
                WHERE id = :idKey
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idKey", idKey)
                        .execute()
        );
    }

    public int revokeKey(int idKey) {
        String sql = """
                UPDATE user_key
                SET status = 'REVOKED',
                    ngay_thu_hoi = NOW()
                WHERE id = :idKey
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idKey", idKey)
                        .execute()
        );
    }

    public int deactivateActiveKeysByUserId(int idUser) {
        String sql = """
                UPDATE user_key
                SET status = 'REVOKED',
                    ngay_thu_hoi = NOW()
                WHERE id_user = :idUser
                  AND status = 'ACTIVE'
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idUser", idUser)
                        .execute()
        );
    }

    public int updateCurrentKeyId(int idUser, int idKey) {
        String sql = """
                UPDATE users
                SET current_key_id = :idKey
                WHERE id = :idUser
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idKey", idKey)
                        .bind("idUser", idUser)
                        .execute()
        );
    }

    public int clearCurrentKeyId(int idUser) {
        String sql = """
                UPDATE users
                SET current_key_id = NULL
                WHERE id = :idUser
                """;

        return DBContext.get().withHandle(handle ->
                handle.createUpdate(sql)
                        .bind("idUser", idUser)
                        .execute()
        );
    }

    private UserKey mapUserKey(ResultSet rs) throws SQLException {
        UserKey key = new UserKey();

        key.setId(rs.getInt("id"));
        key.setIdUser(rs.getInt("id_user"));
        key.setPublicKey(rs.getString("public_key"));
        key.setKeyFingerprint(rs.getString("key_fingerprint"));
        key.setAlgorithm(rs.getString("algorithm"));
        key.setKeySize(rs.getInt("key_size"));
        key.setStatus(rs.getString("status"));
        key.setNgayTao(rs.getTimestamp("ngay_tao"));
        key.setNgayBaoMat(rs.getTimestamp("ngay_bao_mat"));
        key.setNgayThuHoi(rs.getTimestamp("ngay_thu_hoi"));

        return key;
    }
}