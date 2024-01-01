package com.sh.petking.wish.model.entity;

import java.time.LocalDate;

public class Wish {
    private long id;
    private String userId;
    private long campId;
    private LocalDate regDate;

    @Override
    public String toString() {
        return "Wish{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", campId=" + campId +
                ", regDate=" + regDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Wish(long id, String userId, long campId, LocalDate regDate) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
        this.regDate = regDate;
    }

    public Wish() {
    }
}
