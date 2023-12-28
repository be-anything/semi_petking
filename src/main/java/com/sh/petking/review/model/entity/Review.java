package com.sh.petking.review.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Review {
    private Long id;
    private String userId;
    private Long campId;
    private Long boardAttr;
    private String reviewTag;
    private String reviewTitle;
    private String reviewContent;
    private Long viewCount;
    private Long likeCount;
    private LocalDateTime regDate;

    public Review() {
    }

    public Review(Long id, String userId, Long campId, Long boardAttr, String reviewTag, String reviewTitle, String reviewContent, Long viewCount, Long likeCount, LocalDateTime regDate) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
        this.boardAttr = boardAttr;
        this.reviewTag = reviewTag;
        this.reviewTitle = reviewTitle;
        this.reviewContent = reviewContent;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
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

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public Long getBoardAttr() {
        return boardAttr;
    }

    public void setBoardAttr(Long boardAttr) {
        this.boardAttr = boardAttr;
    }

    public String getReviewTag() {
        return reviewTag;
    }

    public void setReviewTag(String reviewTag) {
        this.reviewTag = reviewTag;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Long getViewCount() {
        return viewCount;
    }

    public void setViewCount(Long viewCount) {
        this.viewCount = viewCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", campId=" + campId +
                ", boardAttr=" + boardAttr +
                ", reviewTag='" + reviewTag + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", regDate=" + regDate +
                '}';
    }
}
