package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class UserKey {
    private int id;
    private int idUser;

    private String publicKey;
    private String keyFingerprint;

    private String algorithm;
    private int keySize;

    private String status;
    private Timestamp ngayTao;
    private Timestamp ngayBaoMat;
    private Timestamp ngayThuHoi;

    public UserKey() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }


    public String getKeyFingerprint() {
        return keyFingerprint;
    }

    public void setKeyFingerprint(String keyFingerprint) {
        this.keyFingerprint = keyFingerprint;
    }


    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }


    public int getKeySize() {
        return keySize;
    }

    public void setKeySize(int keySize) {
        this.keySize = keySize;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Timestamp getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Timestamp ngayTao) {
        this.ngayTao = ngayTao;
    }


    public Timestamp getNgayBaoMat() {
        return ngayBaoMat;
    }

    public void setNgayBaoMat(Timestamp ngayBaoMat) {
        this.ngayBaoMat = ngayBaoMat;
    }


    public Timestamp getNgayThuHoi() {
        return ngayThuHoi;
    }

    public void setNgayThuHoi(Timestamp ngayThuHoi) {
        this.ngayThuHoi = ngayThuHoi;
    }

    public void setUserId(int userId) {
        this.id = userId;
    }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

}