package com.edu.hcmuaf.fit.webbanvemaybay.models;

public class SoHieuChuyenBay {
    private int id;
    private String maChuyenBay;
    private int idSanBayDi;
    private int idSanBayDen;
    private int idHangBay;
    private int isDeleted;

    public SoHieuChuyenBay() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getMaChuyenBay() { return maChuyenBay; }
    public void setMaChuyenBay(String maChuyenBay) { this.maChuyenBay = maChuyenBay; }
    public int getIdSanBayDi() { return idSanBayDi; }
    public void setIdSanBayDi(int idSanBayDi) { this.idSanBayDi = idSanBayDi; }
    public int getIdSanBayDen() { return idSanBayDen; }
    public void setIdSanBayDen(int idSanBayDen) { this.idSanBayDen = idSanBayDen; }

    public int getIdHangBay() {
        return idHangBay;
    }

    public void setIdHangBay(int idHangBay) {
        this.idHangBay = idHangBay;
    }
    public int getIsDeleted() { return isDeleted; }
    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
}