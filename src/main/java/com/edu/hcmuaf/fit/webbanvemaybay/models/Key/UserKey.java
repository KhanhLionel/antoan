package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;

public class UserKey {
    private int id;
    private int idUser;
    private String publicKey;
    private int status; // 1: Hoạt động, 0: Thu hồi
    private Timestamp ngayTao;
    private Timestamp ngayThuHoi;

    public UserKey() {}

    // Getter và Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUser() { return idUser; }
    public void setIdUser(int idUser) { this.idUser = idUser; }
    public String getPublicKey() { return publicKey; }
    public void setPublicKey(String publicKey) { this.publicKey = publicKey; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public Timestamp getNgayTao() { return ngayTao; }
    public void setNgayTao(Timestamp ngayTao) { this.ngayTao = ngayTao; }
    public Timestamp getNgayThuHoi() { return ngayThuHoi; }
    public void setNgayThuHoi(Timestamp ngayThuHoi) { this.ngayThuHoi = ngayThuHoi; }
}
