package com.sh.petking.review.model.vo;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.review.model.entity.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewVo extends Review {
    private Camp camp;
    private Attachment attachment;

    private String campName;
    private int commentCount;

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    private List<Attachment> attachments = new ArrayList<>();

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public String toString() {
        return "ReviewVo{" +
                "camp=" + camp +
                ", attachments=" + attachments +
                '}';
    }

    public void setValue(String name, String value) {
        switch (name){
            case "reviewTag" : this.tags.add(value); break;
            case "reviewTitle" : this.setReviewTitle(value);break;
            case "reviewContent" : this.setReviewContent(value); break;
            case "userId" : this.setUserId(value); break;
            case "campId" : this.setCampId(Long.parseLong(value));break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
