package com.sh.petking.ask.model.entity;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

/**
 * 0103 혜진
 * 문의 테이블과 1대1로 매칭되는 entity class
 */
public class Ask {
    private long id; //문의 고유 id :pk
    private String userId; //문의글 작성자 :fk
    private long campId; //문의하려는 캠핑장 번호 :fk
    private String askTitle; //문의글 제목
    private String askContent; //문의글 내용
    private LocalDate askRegDate; //문의글 작성일
    private String askComment; //문의글 답변
    private LocalDate askCommentRegDate; //문의글 답변 작성일

    //기본생성자
    public Ask() {
    }
    
    //파라메터 생성자
    public Ask(long id, String userId, long campId, String askTitle, String askContent, LocalDate askRegDate, String askComment, LocalDate askCommentRegDate) {
        this.id = id;
        this.userId = userId;
        this.campId = campId;
        this.askTitle = askTitle;
        this.askContent = askContent;
        this.askRegDate = askRegDate;
        this.askComment = askComment;
        this.askCommentRegDate = askCommentRegDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getCampId() {
        return campId;
    }

    public void setCampId(long campId) {
        this.campId = campId;
    }

    public String getAskTitle() {
        return askTitle;
    }

    public void setAskTitle(String askTitle) {
        this.askTitle = askTitle;
    }

    public String getAskContent() {
        return askContent;
    }

    public void setAskContent(String askContent) {
        this.askContent = askContent;
    }

    public LocalDate getAskRegDate() {
        return askRegDate;
    }

    public void setAskRegDate(LocalDate askRegDate) {
        this.askRegDate = askRegDate;
    }

    public String getAskComment() {
        return askComment;
    }

    public void setAskComment(String askComment) {
        this.askComment = askComment;
    }

    public LocalDate getAskCommentRegDate() {
        return askCommentRegDate;
    }

    public void setAskCommentRegDate(LocalDate askCommentRegDate) {
        this.askCommentRegDate = askCommentRegDate;
    }

    @Override
    public String toString() {
        return "Ask{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", campId=" + campId +
                ", askTitle='" + askTitle + '\'' +
                ", askContent='" + askContent + '\'' +
                ", askRegDate=" + askRegDate +
                ", askComment='" + askComment + '\'' +
                ", askCommentRegDate=" + askCommentRegDate +
                '}';
    }
}
