package com.edu.hcmuaf.fit.webbanvemaybay.services;

import com.edu.hcmuaf.fit.webbanvemaybay.dao.*;
import com.edu.hcmuaf.fit.webbanvemaybay.dao.HangBayDAO;
import com.edu.hcmuaf.fit.webbanvemaybay.models.*;
import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.ThongTinTimVeDto;
import com.edu.hcmuaf.fit.webbanvemaybay.models.DTO.VeDto;
import com.edu.hcmuaf.fit.webbanvemaybay.services.core.FormatVND;

import java.util.ArrayList;
import java.util.List;

public class TimVeService {
    public List<VeDto> getListVeByFilter(String khoiHanh, String haCanh, String hangGhe, String diemDi,
                                         String diemDen, boolean xepVe, String ngayDi) {
//        private String khoiHanh;
//    private String haCanh;
//    private String hangGhe;
//    private String diemDi;
//    private String diemDen;
//    private String gia;//
//    private String thoiGianKhoiHanh;//
//    private String thoiGianHaCanh;//
//    private String HangBay;//
//    private String soHieuChuyenBay;//

        LoaiVeDAO loaiVeDAO = new LoaiVeDAO();
        String idLoaiVe = loaiVeDAO.getIdLoaiVeByName(hangGhe);

        VeDAO veDAO = new VeDAO();
        List<Ve> listVeByHangGhe = veDAO.getListVeByHangGhe(idLoaiVe);

        ChuyenBayDAO chuyenBayDAO = new ChuyenBayDAO();
        List<ChuyenBay> listChuyenBayByNgayDi = chuyenBayDAO.getListChuyenBayByNgayDi(ngayDi);

        SanBayDAO sanBayDAO = new SanBayDAO();
        List<SanBay> sanBayDi = sanBayDAO.getSanBayByTPAndQG(khoiHanh, diemDi);
        List<SanBay> sanBayDen = sanBayDAO.getSanBayByTPAndQG(haCanh, diemDen);


        SoHieuChuyenBayDAO soHieuChuyenBayDAO = new SoHieuChuyenBayDAO();
        List<SoHieuChuyenBay> listSoHieuChuyenbayBySanBayDiAndSanBayDen = soHieuChuyenBayDAO.getListSoHieuChuyenBayBySanBayDiAndSanBayDen(
                sanBayDi, sanBayDen
        );

        List<VeDto> listVeDto = new ArrayList<>();
        vong1: for (int i = 0; i < listVeByHangGhe.size(); i++) {
            VeDto veDto = new VeDto();
            boolean isInfor1 = false;
            boolean isInfor2 = false;
            vong2: for (int j = 0; j < listChuyenBayByNgayDi.size(); j++) {
                if (listVeByHangGhe.get(i).getIdChuyenBay() == listChuyenBayByNgayDi.get(j).getId()) {
                    isInfor1 = true;
                    veDto.setGia(FormatVND.formatVND(listVeByHangGhe.get(i).getGia()));
                    veDto.setThoiGianKhoiHanh(listChuyenBayByNgayDi.get(j).getThoiGianKhoiHanh());
                    veDto.setThoiGianHaCanh(listChuyenBayByNgayDi.get(j).getThoiGianHaCanh());

                    vong3: for (int k = 0; k < listSoHieuChuyenbayBySanBayDiAndSanBayDen.size(); k++) {
                        if (listChuyenBayByNgayDi.get(j).getIdSoHieuChuyenBay() == listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getId()) {
                            veDto.setSoHieuChuyenBay(listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getMaChuyenBay());
                            int idSanBayDi = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdSanBayDi();
                            String tenSanBayDi = sanBayDi.stream().filter(sb -> sb.getId() == idSanBayDi).map(SanBay::getTenSanBay).findFirst().orElse("");
                            veDto.setSanBayDi(tenSanBayDi);
                            int idSanBayDen = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdSanBayDen();
                            String tenSanBayDen = sanBayDen.stream().filter(sb -> sb.getId() == idSanBayDen).map(SanBay::getTenSanBay).findFirst().orElse("");
                            veDto.setSanBayDen(tenSanBayDen);

                            int idHangBay = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdHangBay();
                            HangBayDAO hangBayDAO = new HangBayDAO();
                            HangBay hangBay = hangBayDAO.getHangBayById(idHangBay);
                            veDto.setHangBay(hangBay.getTenHangBay());

                            int idVe = listVeByHangGhe.get(i).getId();
                            veDto.setIdVe(idVe);
                            isInfor2 = true;
                            break vong3;
                        }
                    }
                }
            }
            if (isInfor1 &&  isInfor2) {
                veDto.setKhoiHanh(khoiHanh);
                veDto.setHaCanh(haCanh);
                veDto.setHangGhe(hangGhe);
                veDto.setDiemDi(diemDi);
                veDto.setDiemDen(diemDen);
                listVeDto.add(veDto);
            }
        }
        listVeDto.sort((v1, v2) -> {
            double gia1 = Double.parseDouble(v1.getGia().replace(".", ""));
            double gia2 = Double.parseDouble(v2.getGia().replace(".", ""));
            return Double.compare(gia1, gia2);
        });
        return listVeDto;
    }

    public List<VeDto> getListVeByFilterRange(String khoiHanh, String haCanh, String hangGhe, String diemDi,
                                         String diemDen, boolean xepVe, String tuNgay, String denNgay) {
        LoaiVeDAO loaiVeDAO = new LoaiVeDAO();
        String idLoaiVe = loaiVeDAO.getIdLoaiVeByName(hangGhe);

        VeDAO veDAO = new VeDAO();
        List<Ve> listVeByHangGhe = veDAO.getListVeByHangGhe(idLoaiVe);

        ChuyenBayDAO chuyenBayDAO = new ChuyenBayDAO();
        List<ChuyenBay> listChuyenBayByNgayDi = chuyenBayDAO.getListChuyenBayByNgayDiRange(tuNgay, denNgay);

        SanBayDAO sanBayDAO = new SanBayDAO();
        List<SanBay> sanBayDi = sanBayDAO.getSanBayByTPAndQG(khoiHanh, diemDi);
        List<SanBay> sanBayDen = sanBayDAO.getSanBayByTPAndQG(haCanh, diemDen);

        SoHieuChuyenBayDAO soHieuChuyenBayDAO = new SoHieuChuyenBayDAO();
        List<SoHieuChuyenBay> listSoHieuChuyenbayBySanBayDiAndSanBayDen = soHieuChuyenBayDAO.getListSoHieuChuyenBayBySanBayDiAndSanBayDen(
                sanBayDi, sanBayDen
        );

        List<VeDto> listVeDto = new ArrayList<>();
        vong1: for (int i = 0; i < listVeByHangGhe.size(); i++) {
            VeDto veDto = new VeDto();
            boolean isInfor1 = false;
            boolean isInfor2 = false;
            vong2: for (int j = 0; j < listChuyenBayByNgayDi.size(); j++) {
                if (listVeByHangGhe.get(i).getIdChuyenBay() == listChuyenBayByNgayDi.get(j).getId()) {
                    isInfor1 = true;
                    veDto.setGia(FormatVND.formatVND(listVeByHangGhe.get(i).getGia()));
                    veDto.setThoiGianKhoiHanh(listChuyenBayByNgayDi.get(j).getThoiGianKhoiHanh());
                    veDto.setThoiGianHaCanh(listChuyenBayByNgayDi.get(j).getThoiGianHaCanh());

                    vong3: for (int k = 0; k < listSoHieuChuyenbayBySanBayDiAndSanBayDen.size(); k++) {
                        if (listChuyenBayByNgayDi.get(j).getIdSoHieuChuyenBay() == listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getId()) {
                            veDto.setSoHieuChuyenBay(listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getMaChuyenBay());
                            int idSanBayDi = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdSanBayDi();
                            String tenSanBayDi = sanBayDi.stream().filter(sb -> sb.getId() == idSanBayDi).map(SanBay::getTenSanBay).findFirst().orElse("");
                            veDto.setSanBayDi(tenSanBayDi);
                            int idSanBayDen = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdSanBayDen();
                            String tenSanBayDen = sanBayDen.stream().filter(sb -> sb.getId() == idSanBayDen).map(SanBay::getTenSanBay).findFirst().orElse("");
                            veDto.setSanBayDen(tenSanBayDen);

                            int idHangBay = listSoHieuChuyenbayBySanBayDiAndSanBayDen.get(k).getIdHangBay();
                            HangBayDAO hangBayDAO = new HangBayDAO();
                            HangBay hangBay = hangBayDAO.getHangBayById(idHangBay);
                            veDto.setHangBay(hangBay.getTenHangBay());

                            int idVe = listVeByHangGhe.get(i).getId();
                            veDto.setIdVe(idVe);
                            isInfor2 = true;
                            break vong3;
                        }
                    }
                }
            }
            if (isInfor1 &&  isInfor2) {
                veDto.setKhoiHanh(khoiHanh);
                veDto.setHaCanh(haCanh);
                veDto.setHangGhe(hangGhe);
                veDto.setDiemDi(diemDi);
                veDto.setDiemDen(diemDen);
                listVeDto.add(veDto);
            }
        }
        listVeDto.sort((v1, v2) -> {
            double gia1 = Double.parseDouble(v1.getGia().replace(".", ""));
            double gia2 = Double.parseDouble(v2.getGia().replace(".", ""));
            return Double.compare(gia1, gia2);
        });
        return listVeDto;
    }

    // hàm lấy thông tin tìm vé
    public ThongTinTimVeDto getThongTinTimVeDto(String khoiHanh, String haCanh) {
        ThongTinTimVeDto thongTinTimVeDto = new ThongTinTimVeDto();

        SanBayDAO sanBayDAO = new SanBayDAO();
        List<String> listSanBayName = sanBayDAO.getListSanBayName();
        thongTinTimVeDto.setListKhoiHanh(listSanBayName);
        thongTinTimVeDto.setListHaCanh(listSanBayName);

        LoaiVeDAO loaiVeDAO = new LoaiVeDAO();
        List<String> listLoaiVe = loaiVeDAO.getListLoaiVe();
        thongTinTimVeDto.setListHangGhe(listLoaiVe);

        List<String> listTPKhoiHanh = sanBayDAO.getListTPByQuocGia(khoiHanh);
        List<String> listTPHaCanh = sanBayDAO.getListTPByQuocGia(haCanh);
        thongTinTimVeDto.setListDiemDi(listTPKhoiHanh);
        thongTinTimVeDto.setListDiemDen(listTPHaCanh);

        return thongTinTimVeDto;
    }

    public VeDto getVeByIdVe(int id) {
        VeDAO veDAO = new VeDAO();
        Ve ve = veDAO.getVeById(id);

        if (ve == null) {
            return null;
        }

        int idLoaiVe = ve.getIdLoaiVe();
        LoaiVeDAO loaiVeDAO = new LoaiVeDAO();
        LoaiVe loaiVe = loaiVeDAO.getLoaiVeById(idLoaiVe);

        int idChuyenBay = ve.getIdChuyenBay();
        ChuyenBayDAO chuyenBayDAO = new ChuyenBayDAO();
        ChuyenBay chuyenBay = chuyenBayDAO.getChuyenBayById(idChuyenBay);

        int idSoHieuChuyenBay = chuyenBay.getIdSoHieuChuyenBay();
        SoHieuChuyenBayDAO soHieuChuyenBayDAO = new SoHieuChuyenBayDAO();
        SoHieuChuyenBay soHieuChuyenBay = soHieuChuyenBayDAO.getSoHieuChuyenBayById(idSoHieuChuyenBay);

        int idSanBayDi = soHieuChuyenBay.getIdSanBayDi();
        int idSanBayDen = soHieuChuyenBay.getIdSanBayDen();
        SanBayDAO sanBayDAO = new SanBayDAO();
        SanBay sanBayDi = sanBayDAO.getSanBayById(idSanBayDi);
        SanBay sanBayDen = sanBayDAO.getSanBayById(idSanBayDen);

        int idHangBay = soHieuChuyenBay.getIdHangBay();
        HangBayDAO hangBayDAO = new HangBayDAO();
        HangBay hangBay = hangBayDAO.getHangBayById(idHangBay);
        // id
//        private String khoiHanh;
//        private String haCanh;
//        private String hangGhe;
//        private String diemDi;
//        private String diemDen;
//        private String sanBayDi;
//        private String sanBayDen;
//        private String gia;
//        private String thoiGianKhoiHanh;
//        private String thoiGianHaCanh;
//        private String HangBay;
//        private String soHieuChuyenBay;
        VeDto veDto = new VeDto();
        veDto.setIdVe(id);
        veDto.setKhoiHanh(sanBayDi.getQuocGia());
        veDto.setHaCanh(sanBayDen.getQuocGia());
        veDto.setHangGhe(loaiVe.getTenLoaiVe());
        veDto.setDiemDi(sanBayDi.getThanhPho());
        veDto.setDiemDen(sanBayDen.getThanhPho());
        veDto.setSanBayDi(sanBayDi.getTenSanBay());
        veDto.setSanBayDen(sanBayDen.getTenSanBay());
        veDto.setGia(FormatVND.formatVND(ve.getGia()));
        veDto.setThoiGianKhoiHanh(chuyenBay.getThoiGianKhoiHanh());
        veDto.setThoiGianHaCanh(chuyenBay.getThoiGianHaCanh());
        veDto.setHangBay(hangBay.getTenHangBay());
        veDto.setSoHieuChuyenBay(soHieuChuyenBay.getMaChuyenBay());
        return veDto;
    }

    public static void main(String[] args) {
        TimVeService timVeService = new TimVeService();
        System.out.println(timVeService.getListVeByFilter("Việt Nam", "Việt Nam", "Thương gia", "Thủ đô Hà Nội", "Thành phố Hồ Chí Minh", true, "2026-2-3"));
    }
}
