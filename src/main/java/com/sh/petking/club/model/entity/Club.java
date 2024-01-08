package com.sh.petking.club.model.entity;

import java.time.LocalDateTime;

public class Club {
    private long id;
    private String clubName;
    private String clubIntroTitle;
    private String clubIntroContent;
    private LocalDateTime regDate;
    private String userId;

    public Club() {
    }

    public Club(long id, String clubName, String clubIntroTitle, String clubIntroContent, LocalDateTime regDate, String userId) {
        this.id = id;
        this.clubName = clubName;
        this.clubIntroTitle = clubIntroTitle;
        this.clubIntroContent = clubIntroContent;
        this.regDate = regDate;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getClubIntroTitle() {
        return clubIntroTitle;
    }

    public void setClubIntroTitle(String clubIntroTitle) {
        this.clubIntroTitle = clubIntroTitle;
    }

    public String getClubIntroContent() {
        return clubIntroContent;
    }

    public void setClubIntroContent(String clubIntroContent) {
        this.clubIntroContent = clubIntroContent;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", clubName='" + clubName + '\'' +
                ", clubIntroTitle='" + clubIntroTitle + '\'' +
                ", clubIntroContent='" + clubIntroContent + '\'' +
                ", regDate=" + regDate +
                ", userId='" + userId + '\'' +
                '}';
    }

//    public void setValue(String name, String value) {
//        switch (name) {
//            case "id" : setId(Long.parseLong(value)); break;
//            case "clubName" : setClubName(value); break;
//            case "clubIntroTitle" : setClubIntroTitle(value); break;
//            case "userId" : setUserId(value); break;
//            case "clubIntroContent" : setClubIntroContent(value); break;
//            default: throw new RuntimeException("부적절한 name값 : " + name);
//        }
//    }
}
