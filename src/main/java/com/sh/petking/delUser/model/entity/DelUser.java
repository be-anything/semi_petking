package com.sh.petking.delUser.model.entity;

import com.sh.petking.common.Role;

import java.time.LocalDate;

public class DelUser {
    private Long id;
    private String userId;
    private String name;
    private Role role;
    private LocalDate delDate;
    private String delReason;
    private LocalDate regDate;

    public DelUser() {
    }

    public DelUser(Long id, String userId, String name, Role role, LocalDate delDate, String delReason, LocalDate regDate) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.role = role;
        this.delDate = delDate;
        this.delReason = delReason;
        this.regDate = regDate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public LocalDate getDelDate() {
        return delDate;
    }

    public void setDelDate(LocalDate delDate) {
        this.delDate = delDate;
    }

    public String getDelReason() {
        return delReason;
    }

    public void setDelReason(String delReason) {
        this.delReason = delReason;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", delDate=" + delDate +
                ", delReason='" + delReason + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
