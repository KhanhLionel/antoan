package com.edu.hcmuaf.fit.webbanvemaybay.models;

public class SanBay {
    private int id;
    private String tenSanBay;
    private String thanhPho;
    private String quocGia;
    private int isDeleted;

    public SanBay() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getTenSanBay() { return tenSanBay; }
    public void setTenSanBay(String tenSanBay) { this.tenSanBay = tenSanBay; }
    public String getThanhPho() { return thanhPho; }
    public void setThanhPho(String thanhPho) { this.thanhPho = thanhPho; }
    public String getQuocGia() { return quocGia; }
    public void setQuocGia(String quocGia) { this.quocGia = quocGia; }
    public int getIsDeleted() { return isDeleted; }
    public void setIsDeleted(int isDeleted) { this.isDeleted = isDeleted; }
}