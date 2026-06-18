package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;

public class SignedOrder {
    private int id;
    private int idDonHang;
    private int idUserKey;
    private String signature;
    private String thongTinGoc;
    private Timestamp ngayKy;

    public SignedOrder() {}

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdDonHang() { return idDonHang; }
    public void setIdDonHang(int idDonHang) { this.idDonHang = idDonHang; }
    public int getIdUserKey() { return idUserKey; }
    public void setIdUserKey(int idUserKey) { this.idUserKey = idUserKey; }
    public String getSignature() { return signature; }
    public void setSignature(String signature) { this.signature = signature; }
    public String getThongTinGoc() { return thongTinGoc; }
    public void setThongTinGoc(String thongTinGoc) { this.thongTinGoc = thongTinGoc; }
    public Timestamp getNgayKy() { return ngayKy; }
    public void setNgayKy(Timestamp ngayKy) { this.ngayKy = ngayKy; }
}