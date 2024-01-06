package com.sh.petking.club.model.vo;

import com.sh.petking.club.model.entity.*;
import com.sh.petking.user.model.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ClubVo extends Club {
    private User user;
    private int attachCount; // 첨부파일 개수
    private List<ClubAttach> attachments = new ArrayList<>();
    private List<Long> delFiles = new ArrayList<>();
    private List<ClubComment> comments;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getAttachCount() {
        return attachCount;
    }

    public void setAttachCount(int attachCount) {
        this.attachCount = attachCount;
    }

    public List<ClubAttach> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<ClubAttach> attachments) {
        this.attachments = attachments;
    }

    public List<Long> getDelFiles() {
        return delFiles;
    }

    public void setDelFiles(List<Long> delFiles) {
        this.delFiles = delFiles;
    }

    public List<ClubComment> getComments() {
        return comments;
    }

    public void setComments(List<ClubComment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "ClubVo{" +
                "user=" + user +
                ", attachCount=" + attachCount +
                ", attachments=" + attachments +
                ", delFiles=" + delFiles +
                ", comments=" + comments +
                "} " + super.toString();
    }

    public void setValue(String name, String value) {
        switch (name) {
            case "id" : setId(Long.parseLong(value)); break;
            case "clubName" : setClubName(value); break;
            case "clubIntroTitle" : setClubIntroTitle(value); break;
            case "userId" : setUserId(value); break;
            case "clubIntroContent" : setClubIntroContent(value); break;
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
