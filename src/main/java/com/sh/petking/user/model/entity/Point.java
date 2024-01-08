package com.sh.petking.user.model.entity;

import java.time.LocalDateTime;

public class Point {
    private Long id;
    private String userId;
    private Long point;
    private String pointLog;
    private LocalDateTime regDate;

    public Point() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPoint() {
        return point;
    }

    public void setPoint(Long point) {
        this.point = point;
    }

    public String getPointLog() {
        return pointLog;
    }

    public void setPointLog(String pointLog) {
        this.pointLog = pointLog;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", point=" + point +
                ", pointLog='" + pointLog + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
