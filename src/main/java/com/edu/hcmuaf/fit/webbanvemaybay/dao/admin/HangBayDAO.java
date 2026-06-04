package com.edu.hcmuaf.fit.webbanvemaybay.dao.admin;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.HangBay;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class HangBayDAO extends DBContext {
    public List<HangBay> getAllHangBay() {
        try {
            Jdbi jdbi = get();
            List<HangBay> listHangBay = jdbi.withHandle(h -> {
                String q = "select * from hang_bay where is_deleted = 0";
                return h.createQuery(q).mapToBean(HangBay.class).list();
            });
            return listHangBay;
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public HangBay getHangBayById(int id) {
        try {
            Jdbi jdbi = get();
            HangBay hangBay = jdbi.withHandle(h -> {
                String q = "select * from hang_bay where id=:id and is_deleted = 0";
                return h.createQuery(q).bind("id", id).mapToBean(HangBay.class).one();
            });
            return hangBay;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateHangBay(HangBay hangBay) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update hang_bay set ten_hang_bay=:tenHangBay, quoc_gia=:quocGia where id=:id";
                return h.createUpdate(q).bind("tenHangBay", hangBay.getTenHangBay())
                        .bind("quocGia", hangBay.getQuocGia())
                        .bind("id", hangBay.getId()).execute();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean removeHangBayById(String id) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "update hang_bay set is_deleted = 1 where id=:id";
                return h.createUpdate(q).bind("id", id).execute();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        }  catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean addHangBay(HangBay hangBay) {
        try {
            Jdbi jdbi = get();
            int soLuong = jdbi.withHandle(h -> {
                String q = "insert into hang_bay (id, ten_hang_bay, quoc_gia) values (:id, :tenHangBay, :quocGia)";
                return h.createUpdate(q).bindBean(hangBay).execute();
            });
            if (soLuong > 0) {
                return true;
            }
            return false;
        }  catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<HangBay> getHangBayByInput(String input) {
        try {
            Jdbi jdbi = get();
            String textInput = "%" +  input + "%";
            return jdbi.withHandle(h -> {
                String q = "select * from hang_bay where (id=:input or ten_hang_bay like :textInput or quoc_gia like :textInput) and is_deleted = 0";
                return h.createQuery(q).bind("input", input).bind("textInput", textInput).mapToBean(HangBay.class).list();
            });
        }  catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
