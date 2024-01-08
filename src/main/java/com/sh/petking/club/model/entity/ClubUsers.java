package com.sh.petking.club.model.entity;

import com.sh.petking.common.Role;

import java.time.LocalDateTime;

public class ClubUsers {

    private long clubId;
    private String userId;
    private int joinState;
    private LocalDateTime regDate;
    private Role role;

    public ClubUsers() {
    }

    public ClubUsers(long clubId, String userId, int joinState, LocalDateTime regDate, Role role) {
        this.clubId = clubId;
        this.userId = userId;
        this.joinState = joinState;
        this.regDate = regDate;
        this.role = role;
    }

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getJoinState() {
        return joinState;
    }

    public void setJoinState(int joinState) {
        this.joinState = joinState;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "ClubUsers{" +
                "clubId=" + clubId +
                ", userId='" + userId + '\'' +
                ", joinState=" + joinState +
                ", regDate=" + regDate +
                ", role=" + role +
                '}';
    }
}
