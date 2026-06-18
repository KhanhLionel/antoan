package com.edu.hcmuaf.fit.webbanvemaybay.models.Key;

import java.sql.Timestamp;

public class OrderAuditLog {
    private int id;
    private int idDonHang;
    private Integer actorUserId;

    private String action;
    private String beforeData;
    private String afterData;

    private Timestamp createdAt;

    public OrderAuditLog() {
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


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getBeforeData() {
        return beforeData;
    }

    public void setBeforeData(String beforeData) {
        this.beforeData = beforeData;
    }


    public String getAfterData() {
        return afterData;
    }

    public void setAfterData(String afterData) {
        this.afterData = afterData;
    }


    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}