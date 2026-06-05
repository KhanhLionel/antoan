package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import com.edu.hcmuaf.fit.webbanvemaybay.models.DatVe;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Ve;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class VeDAO extends DBContext {
    public List<Ve> getListVeByHangGhe(String idLoaiVe) {
        try {
            Jdbi jdbi = get();
            List<Ve> listVe = jdbi.withHandle(h -> {
                String q = "select * from ve where id_loai_ve=:idLoaiVe and so_luong_ton > 0";
                return h.createQuery(q).bind("idLoaiVe", idLoaiVe).mapToBean(Ve.class).list();
            });
            return listVe;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Ve getVeById(int id) {
        try {
            Jdbi jdbi = get();
            Ve ve = jdbi.withHandle(h -> {
                String q = "select * from ve where id = :id";
                return h.createQuery(q).bind("id", id).mapToBean(Ve.class).findOne().orElse(null);
            });
            return ve;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean setSoLuongVe(List<DatVe> listDatVe) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "update ve set so_luong_ton=so_luong_ton - :soLuong where id=:idVe";
                for (DatVe datVe : listDatVe) {
                    h.createUpdate(q).bind("soLuong", datVe.getSoLuong()).bind("idVe", datVe.getIdVe()).execute();
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addSoLuongVe(List<DatVe> listDatVe) {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "update ve set so_luong_ton=so_luong_ton + :soLuong where id=:idVe";
                for (DatVe datVe : listDatVe) {
                    h.createUpdate(q).bind("soLuong", datVe.getSoLuong()).bind("idVe", datVe.getIdVe()).execute();
                }
                return true;
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Integer> getDeXuatVeIds() {
        try {
            Jdbi jdbi = get();
            return jdbi.withHandle(h -> {
                String q = "SELECT v.id FROM ve v " +
                           "JOIN chuyen_bay cb ON v.id_chuyen_bay = cb.id " +
                           "LEFT JOIN ve_da_dat vdd ON v.id = vdd.id_ve " +
                           "WHERE v.so_luong_ton > 0 AND cb.is_deleted = 0 " +
                           "GROUP BY v.id, v.so_luong_ton " +
                           "ORDER BY COALESCE(SUM(vdd.so_luong), 0) ASC, v.so_luong_ton DESC " +
                           "LIMIT 5";
                return h.createQuery(q).mapTo(Integer.class).list();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
