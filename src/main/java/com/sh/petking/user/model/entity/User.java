package com.sh.petking.user.model.entity;

import com.sh.petking.common.Role;

import java.util.Date;

public class User {
    private String id;
    private String gradeId;
    private int clubId;
    private String nickname;
    private String name;
    private String password;
    private String originProfileName;
    private String renamedProfileName;
    private String email;
    private String phone;
    private int resultPoint;
    private Role role;
    private Date regDate;

    public User() {
    }

    public User(String id, String gradeId, int clubId, String nickname, String name, String password, String originProfileName, String renamedProfileName, String email, String phone, int resultPoint, Role role, Date regDate) {
        this.id = id;
        this.gradeId = gradeId;
        this.clubId = clubId;
        this.nickname = nickname;
        this.name = name;
        this.password = password;
        this.originProfileName = originProfileName;
        this.renamedProfileName = renamedProfileName;
        this.email = email;
        this.phone = phone;
        this.resultPoint = resultPoint;
        this.role = role;
        this.regDate = regDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOriginProfileName() {
        return originProfileName;
    }

    public void setOriginProfileName(String originProfileName) {
        this.originProfileName = originProfileName;
    }

    public String getRenamedProfileName() {
        return renamedProfileName;
    }

    public void setRenamedProfileName(String renamedProfileName) {
        this.renamedProfileName = renamedProfileName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getResultPoint() {
        return resultPoint;
    }

    public void setResultPoint(int resultPoint) {
        this.resultPoint = resultPoint;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", gradeId='" + gradeId + '\'' +
                ", clubId=" + clubId +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", originProfileName='" + originProfileName + '\'' +
                ", renamedProfileName='" + renamedProfileName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", resultPoint=" + resultPoint +
                ", role=" + role +
                ", regDate=" + regDate +
                '}';
    }
}