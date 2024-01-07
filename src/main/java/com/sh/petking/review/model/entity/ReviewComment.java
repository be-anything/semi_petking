package com.sh.petking.review.model.entity;

import java.time.LocalDateTime;

public class ReviewComment {
    private Long id;
    private String userId;
    private Long reviewId;
    private String content;
    private LocalDateTime regDate;

    @Override
    public String toString() {
        return "ReviewComment{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", reviewId=" + reviewId +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
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

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public ReviewComment() {
    }

    public ReviewComment(Long id, String userId, Long reviewId, String content, LocalDateTime regDate) {
        this.id = id;
        this.userId = userId;
        this.reviewId = reviewId;
        this.content = content;
        this.regDate = regDate;
    }
}
