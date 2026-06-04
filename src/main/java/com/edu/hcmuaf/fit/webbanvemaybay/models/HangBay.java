package com.edu.hcmuaf.fit.webbanvemaybay.models;

public class HangBay {
    private int id;
    private String tenHangBay;
    private String quocGia;
    private int isDeleted;

    public HangBay() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTenHangBay() { return tenHangBay; }
    public void setTenHangBay(String tenHangBay) { this.tenHangBay = tenHangBay; }
    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) { this.quocGia = quocGia; }
    public int getIsDeleted() { return isDeleted; }
    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
}