package com.edu.hcmuaf.fit.webbanvemaybay.models;
import  java.util.Date;
public class ChuyenBay {
    private int id;
    private String thoiGianKhoiHanh;
    private String thoiGianHaCanh;
    private int idSoHieuChuyenBay;
    private int isDeleted;

    public ChuyenBay() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThoiGianKhoiHanh() { return thoiGianKhoiHanh; }
    public void setThoiGianKhoiHanh(String thoiGianKhoiHanh) { this.thoiGianKhoiHanh = thoiGianKhoiHanh; }
    public String getThoiGianHaCanh() { return thoiGianHaCanh; }
    public void setThoiGianHaCanh(String thoiGianHaCanh) { this.thoiGianHaCanh = thoiGianHaCanh; }

    public int getIdSoHieuChuyenBay() {
        return idSoHieuChuyenBay;
    }

    public void setIdSoHieuChuyenBay(int idSoHieuChuyenBay) {
        this.idSoHieuChuyenBay = idSoHieuChuyenBay;
    }
    public int getIsDeleted() { return isDeleted; }
    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
}