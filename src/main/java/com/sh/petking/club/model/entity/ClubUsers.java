package com.sh.petking.club.model.entity;

import java.time.LocalDateTime;

public class ClubUsers {

    private long clubId;
    private String userId;
    private int joinState;
    private LocalDateTime regDate;

    public ClubUsers() {
    }

    public ClubUsers(long clubId, String userId, int joinState, LocalDateTime regDate) {
        this.clubId = clubId;
        this.userId = userId;
        this.joinState = joinState;
        this.regDate = regDate;
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

    @Override
    public String toString() {
        return "ClubUsers{" +
                "clubId=" + clubId +
                ", userId='" + userId + '\'' +
                ", joinState=" + joinState +
                ", regDate=" + regDate +
                '}';
    }
}
