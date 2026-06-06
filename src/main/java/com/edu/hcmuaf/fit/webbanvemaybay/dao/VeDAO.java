package com.edu.hcmuaf.fit.webbanvemaybay.dao;

import com.edu.hcmuaf.fit.webbanvemaybay.models.DatVe;
import com.edu.hcmuaf.fit.webbanvemaybay.models.Ve;
import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.VeDto;
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

    public List<VeDto> getListVeNoiDia() {
        try {
            Jdbi jdbi = get();
            List<VeDto> rawList = jdbi.withHandle(h -> {
                String q = "SELECT " +
                           "v.id AS idVe, " +
                           "sb_di.quoc_gia AS khoiHanh, " +
                           "sb_den.quoc_gia AS haCanh, " +
                           "lv.ten_loai_ve AS hangGhe, " +
                           "sb_di.thanh_pho AS diemDi, " +
                           "sb_den.thanh_pho AS diemDen, " +
                           "sb_di.ten_san_bay AS sanBayDi, " +
                           "sb_den.ten_san_bay AS sanBayDen, " +
                           "v.gia AS gia, " +
                           "cb.thoi_gian_khoi_hanh AS thoiGianKhoiHanh, " +
                           "cb.thoi_gian_ha_canh AS thoiGianHaCanh, " +
                           "hb.ten_hang_bay AS hangBay, " +
                           "shcb.ma_chuyen_bay AS soHieuChuyenBay " +
                           "FROM ve v " +
                           "JOIN chuyen_bay cb ON v.id_chuyen_bay = cb.id " +
                           "JOIN so_hieu_chuyen_bay shcb ON cb.id_so_hieu_chuyen_bay = shcb.id " +
                           "JOIN san_bay sb_di ON shcb.id_san_bay_di = sb_di.id " +
                           "JOIN san_bay sb_den ON shcb.id_san_bay_den = sb_den.id " +
                           "JOIN loai_ve lv ON v.id_loai_ve = lv.id " +
                           "JOIN hang_bay hb ON shcb.id_hang_bay = hb.id " +
                           "WHERE v.so_luong_ton > 0 AND cb.is_deleted = 0 " +
                           "AND sb_di.quoc_gia = 'Việt Nam' AND sb_den.quoc_gia = 'Việt Nam' " +
                           "ORDER BY v.id DESC " +
                           "LIMIT 150";
                return h.createQuery(q)
                        .map((rs, ctx) -> {
                            VeDto dto = new VeDto();
                            dto.setIdVe(rs.getInt("idVe"));
                            dto.setKhoiHanh(rs.getString("khoiHanh"));
                            dto.setHaCanh(rs.getString("haCanh"));
                            dto.setHangGhe(rs.getString("hangGhe"));
                            dto.setDiemDi(rs.getString("diemDi"));
                            dto.setDiemDen(rs.getString("diemDen"));
                            dto.setSanBayDi(rs.getString("sanBayDi"));
                            dto.setSanBayDen(rs.getString("sanBayDen"));
                            dto.setGia(com.edu.hcmuaf.fit.webbanvemaybay.services.core.FormatVND.formatVND(rs.getDouble("gia")));
                            dto.setThoiGianKhoiHanh(rs.getString("thoiGianKhoiHanh"));
                            dto.setThoiGianHaCanh(rs.getString("thoiGianHaCanh"));
                            dto.setHangBay(rs.getString("hangBay"));
                            dto.setSoHieuChuyenBay(rs.getString("soHieuChuyenBay"));
                            return dto;
                        })
                        .list();
            });
            if (rawList == null) return null;
            return interleaveTickets(rawList, 12);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<VeDto> getListVeQuocTe() {
        try {
            Jdbi jdbi = get();
            List<VeDto> rawList = jdbi.withHandle(h -> {
                String q = "SELECT " +
                           "v.id AS idVe, " +
                           "sb_di.quoc_gia AS khoiHanh, " +
                           "sb_den.quoc_gia AS haCanh, " +
                           "lv.ten_loai_ve AS hangGhe, " +
                           "sb_di.thanh_pho AS diemDi, " +
                           "sb_den.thanh_pho AS diemDen, " +
                           "sb_di.ten_san_bay AS sanBayDi, " +
                           "sb_den.ten_san_bay AS sanBayDen, " +
                           "v.gia AS gia, " +
                           "cb.thoi_gian_khoi_hanh AS thoiGianKhoiHanh, " +
                           "cb.thoi_gian_ha_canh AS thoiGianHaCanh, " +
                           "hb.ten_hang_bay AS hangBay, " +
                           "shcb.ma_chuyen_bay AS soHieuChuyenBay " +
                           "FROM ve v " +
                           "JOIN chuyen_bay cb ON v.id_chuyen_bay = cb.id " +
                           "JOIN so_hieu_chuyen_bay shcb ON cb.id_so_hieu_chuyen_bay = shcb.id " +
                           "JOIN san_bay sb_di ON shcb.id_san_bay_di = sb_di.id " +
                           "JOIN san_bay sb_den ON shcb.id_san_bay_den = sb_den.id " +
                           "JOIN loai_ve lv ON v.id_loai_ve = lv.id " +
                           "JOIN hang_bay hb ON shcb.id_hang_bay = hb.id " +
                           "WHERE v.so_luong_ton > 0 AND cb.is_deleted = 0 " +
                           "AND (sb_di.quoc_gia <> 'Việt Nam' OR sb_den.quoc_gia <> 'Việt Nam') " +
                           "ORDER BY v.id DESC " +
                           "LIMIT 150";
                return h.createQuery(q)
                        .map((rs, ctx) -> {
                            VeDto dto = new VeDto();
                            dto.setIdVe(rs.getInt("idVe"));
                            dto.setKhoiHanh(rs.getString("khoiHanh"));
                            dto.setHaCanh(rs.getString("haCanh"));
                            dto.setHangGhe(rs.getString("hangGhe"));
                            dto.setDiemDi(rs.getString("diemDi"));
                            dto.setDiemDen(rs.getString("diemDen"));
                            dto.setSanBayDi(rs.getString("sanBayDi"));
                            dto.setSanBayDen(rs.getString("sanBayDen"));
                            dto.setGia(com.edu.hcmuaf.fit.webbanvemaybay.services.core.FormatVND.formatVND(rs.getDouble("gia")));
                            dto.setThoiGianKhoiHanh(rs.getString("thoiGianKhoiHanh"));
                            dto.setThoiGianHaCanh(rs.getString("thoiGianHaCanh"));
                            dto.setHangBay(rs.getString("hangBay"));
                            dto.setSoHieuChuyenBay(rs.getString("soHieuChuyenBay"));
                            return dto;
                        })
                        .list();
            });
            if (rawList == null) return null;
            return interleaveTickets(rawList, 12);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<VeDto> interleaveTickets(List<VeDto> rawList, int limit) {
        java.util.Map<String, List<VeDto>> grouped = new java.util.LinkedHashMap<>();
        for (VeDto dto : rawList) {
            String routeKey = dto.getDiemDi() + "-" + dto.getDiemDen();
            grouped.computeIfAbsent(routeKey, k -> new java.util.ArrayList<>()).add(dto);
        }
        List<VeDto> result = new java.util.ArrayList<>();
        boolean hasMore = true;
        int index = 0;
        while (hasMore && result.size() < limit) {
            hasMore = false;
            for (List<VeDto> list : grouped.values()) {
                if (index < list.size()) {
                    result.add(list.get(index));
                    hasMore = true;
                    if (result.size() >= limit) {
                        break;
                    }
                }
            }
            index++;
        }
        return result;
    }
}
