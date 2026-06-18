package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.math.BigDecimal;

public class DonHangChiTiet {
    private int id;
    private int idDonHang;
    private int idVe;

    private int soLuong;
    private BigDecimal donGia;
    private BigDecimal thanhTien;

    private String tenVeSnapshot;
    private String thongTinVeSnapshot;

    public DonHangChiTiet() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(int idDonHang) {
        this.idDonHang = idDonHang;
    }


    public int getIdVe() {
        return idVe;
    }

    public void setIdVe(int idVe) {
        this.idVe = idVe;
    }


    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }


    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }


    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }


    public String getTenVeSnapshot() {
        return tenVeSnapshot;
    }

    public void setTenVeSnapshot(String tenVeSnapshot) {
        this.tenVeSnapshot = tenVeSnapshot;
    }


    public String getThongTinVeSnapshot() {
        return thongTinVeSnapshot;
    }

    public void setThongTinVeSnapshot(String thongTinVeSnapshot) {
        this.thongTinVeSnapshot = thongTinVeSnapshot;
    }
}