package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class DonHang {
    private int id;
    private String maDonHang;

    private int idUser;
    private Integer idUserKey;

    private String tenNguoiMua;
    private String emailNguoiMua;
    private String sdtNguoiMua;

    private String maKhuyenMai;
    private BigDecimal tienGiam;
    private BigDecimal tongTien;

    private String payloadJson;
    private String payloadHash;

    private String trangThaiDon;
    private String trangThaiKy;
    private String trangThaiToanVen;

    private Timestamp ngayTao;
    private Timestamp ngayCapNhat;

    public DonHang() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public Integer getIdUserKey() {
        return idUserKey;
    }

    public void setIdUserKey(Integer idUserKey) {
        this.idUserKey = idUserKey;
    }


    public String getTenNguoiMua() {
        return tenNguoiMua;
    }

    public void setTenNguoiMua(String tenNguoiMua) {
        this.tenNguoiMua = tenNguoiMua;
    }


    public String getEmailNguoiMua() {
        return emailNguoiMua;
    }

    public void setEmailNguoiMua(String emailNguoiMua) {
        this.emailNguoiMua = emailNguoiMua;
    }


    public String getSdtNguoiMua() {
        return sdtNguoiMua;
    }

    public void setSdtNguoiMua(String sdtNguoiMua) {
        this.sdtNguoiMua = sdtNguoiMua;
    }


    public String getMaKhuyenMai() {
        return maKhuyenMai;
    }

    public void setMaKhuyenMai(String maKhuyenMai) {
        this.maKhuyenMai = maKhuyenMai;
    }


    public BigDecimal getTienGiam() {
        return tienGiam;
    }

    public void setTienGiam(BigDecimal tienGiam) {
        this.tienGiam = tienGiam;
    }


    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }


    public String getPayloadJson() {
        return payloadJson;
    }

    public void setPayloadJson(String payloadJson) {
        this.payloadJson = payloadJson;
    }


    public String getPayloadHash() {
        return payloadHash;
    }

    public void setPayloadHash(String payloadHash) {
        this.payloadHash = payloadHash;
    }


    public String getTrangThaiDon() {
        return trangThaiDon;
    }

    public void setTrangThaiDon(String trangThaiDon) {
        this.trangThaiDon = trangThaiDon;
    }


    public String getTrangThaiKy() {
        return trangThaiKy;
    }

    public void setTrangThaiKy(String trangThaiKy) {
        this.trangThaiKy = trangThaiKy;
    }


    public String getTrangThaiToanVen() {
        return trangThaiToanVen;
    }

    public void setTrangThaiToanVen(String trangThaiToanVen) {
        this.trangThaiToanVen = trangThaiToanVen;
    }


    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }


    public Timestamp getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Timestamp ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }
}