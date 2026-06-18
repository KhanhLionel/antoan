package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;

public class OrderIntegrityAlert {
    private int id;
    private int idDonHang;
    private Integer actorUserId;

    private String alertType;
    private String oldHash;
    private String currentHash;
    private String message;
    private String status;

    private Timestamp createdAt;

    public OrderIntegrityAlert() {
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


    public Integer getActorUserId() {
        return actorUserId;
    }

    public void setActorUserId(Integer actorUserId) {
        this.actorUserId = actorUserId;
    }


    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }


    public String getOldHash() {
        return oldHash;
    }

    public void setOldHash(String oldHash) {
        this.oldHash = oldHash;
    }


    public String getCurrentHash() {
        return currentHash;
    }

    public void setCurrentHash(String currentHash) {
        this.currentHash = currentHash;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}