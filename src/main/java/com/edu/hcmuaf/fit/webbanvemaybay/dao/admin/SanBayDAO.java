package com.edu.hcmuaf.fit.webbanvemaybay.dao.admin;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.SanBay;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class SanBayDAO extends DBContext {
    public List<SanBay> getAllSanBay() {
        try {
            Jdbi jdbi = get();
            List<SanBay> listSanBay = jdbi.withHandle(h -> {
                String q = "select * from san_bay where is_deleted = 0";
                return h.createQuery(q).mapToBean(SanBay.class).list();
            });
            return listSanBay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean addSanBay(SanBay sanBay) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "insert into san_bay(id, ten_san_bay, thanh_pho, quoc_gia) values (:id, :tenSanBay, :thanhPho, :quocGia)";
                return h.createUpdate(q).bindBean(sanBay).execute();
            });
            return soLuong > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public SanBay getSanBayById(String id) {
        try {
            Jdbi jdbi = get();
            SanBay sanBay = jdbi.withHandle(h -> {
                String q = "select * from san_bay where id = :id and is_deleted = 0";
                return h.createQuery(q).bind("id", id).mapToBean(SanBay.class).findFirst().orElse(null);
            });
            return sanBay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateSanBayById(SanBay sanBay) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update san_bay set ten_san_bay = :tenSanBay, thanh_pho = :thanhPho, quoc_gia = :quocGia where id = :id";
                return h.createUpdate(q).bindBean(sanBay).execute();
            });
            return soLuong > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeSanBayById(String id) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update san_bay set is_deleted = 1 where id = :id";
                return h.createUpdate(q).bind("id", id).execute();
            });
            return soLuong > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<SanBay> getSanBayByInput(String input) {
        try {
            Jdbi jdbi = get();
            String textInput = "%" +  input + "%";
            return jdbi.withHandle(h -> {
                String q = "select * from san_bay where (id=:input or ten_san_bay like :textInput or thanh_pho like :textInput or quoc_gia like :textInput) and is_deleted = 0";
                return h.createQuery(q).bind("input", input).bind("textInput", textInput).mapToBean(SanBay.class).list();
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
