package com.edu.hcmuaf.fit.webbanvemaybay.dao.admin;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.ChuyenBay;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class ChuyenBayDAO extends DBContext {
    public List<ChuyenBay> getChuyenBayByPage(int offset, int pageSize) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select * from chuyen_bay where is_deleted = 0 LIMIT :limit OFFSET :offset";
                return h.createQuery(q)
                        .bind("limit", pageSize)
                        .bind("offset", offset)
                        .mapToBean(ChuyenBay.class)
                        .list();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalChuyenBayCount() {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h ->
                    h.createQuery("select count(*) from chuyen_bay where is_deleted = 0").mapTo(Integer.class).one()
            );
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean addChuyenBay(ChuyenBay chuyenBay) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "insert into chuyen_bay(id, thoi_gian_khoi_hanh, thoi_gian_ha_canh, id_so_hieu_chuyen_bay) " +
                        "values (:id, :thoiGianKhoiHanh, :thoiGianHaCanh, :idSoHieuChuyenBay)";
                return h.createUpdate(q).bindBean(chuyenBay).execute();
            });
            return soLuong > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChuyenBay getChuyenBayById(String id) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select * from chuyen_bay where id = :id and is_deleted = 0";
                return h.createQuery(q).bind("id", id).mapToBean(ChuyenBay.class).findFirst().orElse(null);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateChuyenBayById(ChuyenBay chuyenBay) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "update chuyen_bay set thoi_gian_khoi_hanh = :thoiGianKhoiHanh, thoi_gian_ha_canh = :thoiGianHaCanh, " +
                        "id_so_hieu_chuyen_bay = :idSoHieuChuyenBay where id = :id";
                return h.createUpdate(q)
                        .bind("thoiGianKhoiHanh", chuyenBay.getThoiGianKhoiHanh())
                        .bind("thoiGianHaCanh", chuyenBay.getThoiGianHaCanh())
                        .bind("idSoHieuChuyenBay", chuyenBay.getIdSoHieuChuyenBay())
                        .bind("id", chuyenBay.getId()).execute();
            }) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeChuyenBayById(String id) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "update chuyen_bay set is_deleted = 1 where id = :id";
                return h.createUpdate(q).bind("id", id).execute();
            }) > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ChuyenBay> getChuyenBayByPageAndInput(int offset, int pageSize, String input) {
        try {
            Jdbi jdbi = get();
            String textInput = "%" + input + "%";
            return jdbi.withHandle(h -> {
                String q = "select * from chuyen_bay where (id = :input or thoi_gian_khoi_hanh = :textInput or thoi_gian_ha_canh = :textInput or " +
                        "id_so_hieu_chuyen_bay = :input) and is_deleted = 0 limit :limit offset :offset";
                return h.createQuery(q)
                        .bind("input", input)
                        .bind("textInput", textInput)
                        .bind("limit", pageSize)
                        .bind("offset", offset)
                        .mapToBean(ChuyenBay.class)
                        .list();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getTotalChuyenBayCountByInput(String input) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "select count(*) from chuyen_bay where (id = :input or thoi_gian_khoi_hanh = :textInput or " +
                        "thoi_gian_ha_canh = :textInput or id_so_hieu_chuyen_bay = :input) and is_deleted = 0";
                return h.createQuery(q)
                        .bind("input", input)
                        .mapTo(Integer.class).one();
            });
        } catch (Exception e) {
            return 0;
        }
    }
}
