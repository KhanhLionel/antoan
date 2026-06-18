package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;

public class SignedOrder {
    private int id;
    private int idDonHang;
    private int idUserKey;

    private String payloadHash;
    private String signature;
    private String signatureAlgorithm;
    private String verifyStatus;
    private boolean current;

    private Timestamp ngayKy;

    public SignedOrder() {}

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


    public int getIdUserKey() {
        return idUserKey;
    }

    public void setIdUserKey(int idUserKey) {
        this.idUserKey = idUserKey;
    }


    public String getPayloadHash() {
        return payloadHash;
    }

    public void setPayloadHash(String payloadHash) {
        this.payloadHash = payloadHash;
    }


    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }


    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }


    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }


    public boolean isCurrent() {
        return current;
    }

    public void setCurrent(boolean current) {
        this.current = current;
    }


    public Timestamp getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(Timestamp ngayKy) {
        this.ngayKy = ngayKy;
    }
}