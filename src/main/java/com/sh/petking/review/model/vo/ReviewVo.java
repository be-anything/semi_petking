package com.sh.petking.review.model.vo;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.vo.AttachmentVo;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.entity.ReviewComment;

import java.util.ArrayList;
import java.util.List;

public class ReviewVo extends Review {
    private Camp camp;
    private String campName;
    private int commentCount;

    List<AttachmentVo> attachments = new ArrayList<>();
    List<ReviewComment> reviewComments = new ArrayList<>();

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

    private List<String> tags = new ArrayList<>();

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<AttachmentVo> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentVo> attachments) {
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
                ", campName='" + campName + '\'' +
                ", commentCount=" + commentCount +
                ", attachments=" + attachments +
                ", reviewComments=" + reviewComments +
                ", tags=" + tags +
                '}';
    }

    public void setValue(String name, String value) {
        switch (name){
            case "reviewId" :this.setId(Long.parseLong(value)); break;
            case "reviewTag" : this.tags.add(value); break;
            case "reviewTitle" : this.setReviewTitle(value);break;
            case "reviewContent" : this.setReviewContent(value); break;
            case "userId" : this.setUserId(value); break;
            case "campId" : this.setCampId(Long.parseLong(value));break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
