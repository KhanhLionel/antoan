package com.edu.hcmuaf.fit.webbanvemaybay.dao.admin;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.DBContext;
import com.edu.hcmuaf.fit.webbanvemaybay.models.SoHieuChuyenBay;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jdbi.v3.core.Jdbi;

import java.io.IOException;
import java.util.List;

public class SoHieuChuyenBayDAO extends DBContext {
//    private int id;
//    private String maChuyenBay;
//    private int idSanBayDi;
//    private int idSanBayDen;
//    private int idHangBay;

    // lấy tất cả các chuyến bay
    public List<SoHieuChuyenBay> getListSoHieuChuyenBay() {
        Jdbi jdbi = get();
        List<SoHieuChuyenBay> listSoHieuChuyenBay = jdbi.withHandle(h -> {
            String q = "select id as id, ma_chuyen_bay as maChuyenBay, id_san_bay_di as idSanBayDi, id_san_bay_den as idSanBayDen, id_hang_bay as idHangBay from " +
                    "so_hieu_chuyen_bay where is_deleted = 0";
            return h.createQuery(q).mapToBean(SoHieuChuyenBay.class).list();
        });
        return listSoHieuChuyenBay;
    }

    // thêm mố số hiệu chuyến bay mới
    public String addSoHieuChuyenBay(SoHieuChuyenBay soHieuChuyenBay) {
        try {
            Jdbi jdbi = get();

            // lấy id sân bay đi trong bản san_bay
            int isIdSanBayDi = jdbi.withHandle(h -> {
                String q = "select count(*) from san_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdSanBayDi()).mapTo(Integer.class).one();
            });
            int isIdSanBayDen = jdbi.withHandle(h -> {
                String q = "select count(*) from san_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdSanBayDen()).mapTo(Integer.class).one();
            });
            int isHangBay = jdbi.withHandle(h -> {
                String q = "select count(*) from hang_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdHangBay()).mapTo(Integer.class).one();
            });
            if (isIdSanBayDi <= 0) {
                return "id sân bay đi không tồn tại";
            }
            if (isIdSanBayDen <= 0) {
                return "id sân bay đến không tồn tại";
            }
            if (isHangBay <= 0) {
                return "id hãng bay không tồn tại";
            }
            jdbi.withHandle(h -> {
                String q = "insert into so_hieu_chuyen_bay (ma_chuyen_bay, id_san_bay_di, id_san_bay_den, id_hang_bay) values " +
                        "(:maChuyenBay, :idSanBayDi, :idSanBayDen, :idHangBay)";
                return h.createUpdate(q).bindBean(soHieuChuyenBay).execute();
            });
            return "Thêm SHCB: " + soHieuChuyenBay.getMaChuyenBay() + " thành công";
        } catch (Exception e) {
            return "thêm thất bại do lỗi hệ thống";
        }
    }

    public SoHieuChuyenBay getSoHieuChuyenBayById(int id) {
        try {
            Jdbi jdbi = get();
            List<SoHieuChuyenBay> listSoHieuChuyenBay = jdbi.withHandle(h -> {
                String q = "select * from so_hieu_chuyen_bay where id = :id and is_deleted = 0";
                return h.createQuery(q).bind("id", id).mapToBean(SoHieuChuyenBay.class).list();
            });
            return listSoHieuChuyenBay.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String updateSoHieuChuyenBayById(SoHieuChuyenBay soHieuChuyenBay) {
        try {
            Jdbi jdbi = get();
            // lấy id sân bay đi trong bản san_bay
            int isIdSanBayDi = jdbi.withHandle(h -> {
                String q = "select count(*) from san_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdSanBayDi()).mapTo(Integer.class).one();
            });
            int isIdSanBayDen = jdbi.withHandle(h -> {
                String q = "select count(*) from san_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdSanBayDen()).mapTo(Integer.class).one();
            });
            int isHangBay = jdbi.withHandle(h -> {
                String q = "select count(*) from hang_bay where id = :id";
                return h.createQuery(q).bind("id", soHieuChuyenBay.getIdHangBay()).mapTo(Integer.class).one();
            });
            if (isIdSanBayDi <= 0) {
                return "id sân bay đi không tồn tại";
            }
            if (isIdSanBayDen <= 0) {
                return "id sân bay đến không tồn tại";
            }
            if (isHangBay <= 0) {
                return "id hãng bay không tồn tại";
            }
            int soDong = jdbi.withHandle(h -> {
                String q = "update so_hieu_chuyen_bay " +
                        "set ma_chuyen_bay = :maChuyenBay," +
                        "id_san_bay_di = :idSanBayDi," +
                        "id_san_bay_den = :idSanBayDen," +
                        "id_hang_bay = :idHangBay " +
                        "where id = :id";
                return h.createUpdate(q).bindBean(soHieuChuyenBay).execute();
            });
            if (soDong == 1) {
                return "Sửa thành công";
            } else {
                return "sửa thất bại";
            }

        } catch (Exception e) {
            return "sửa thất bại do lỗi hệ thống";
        }
    }

    public String deleteSoHieuChuyenBayById(String id) {
        try {
            Jdbi jdbi = get();
            int soDong = jdbi.withHandle(h -> {
                String q = "update so_hieu_chuyen_bay set is_deleted = 1 where id = :id";
                return h.createUpdate(q).bind("id", id).execute();
            });
            if (soDong == 1) {
                return "xoá thành công";
            } else {
                return "xoá thất bại";
            }
        } catch (Exception e) {
            return "xoá thất bại do lỗi hệ thống";
        }
    }

    public List<SoHieuChuyenBay> getSoHieuChuyenBayByInput(String input) {
        try {
            Jdbi jdbi = get();
            String maChuyenBay = "%" + input.strip() + "%";
            return jdbi.withHandle(h -> {
                String q = "select * from so_hieu_chuyen_bay where (id = :input or ma_chuyen_bay like :maChuyenBay or " +
                        "id_san_bay_di = :input or id_san_bay_den = :input or id_hang_bay = :input) and is_deleted = 0";
                return h.createQuery(q).bind("input", input).bind("maChuyenBay", maChuyenBay).mapToBean(SoHieuChuyenBay.class).list();
            });
        }  catch (Exception e) {
            return null;
        }
    }
}
