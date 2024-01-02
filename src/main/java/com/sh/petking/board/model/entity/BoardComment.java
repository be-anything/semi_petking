package com.sh.petking.board.model.entity;

import java.time.LocalDateTime;

public class BoardComment {
    private Long id;
    private Long boardId;
    private String userId;
    private String content;
    private LocalDateTime regDate;

    public BoardComment() {
    }

    public BoardComment(Long id, Long boardId, String userId, String content, LocalDateTime regDate) {
        this.id = id;
        this.boardId = boardId;
        this.userId = userId;
        this.content = content;
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoardId() {
        return boardId;
    }

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    @Override
    public String toString() {
        return "BoardComment{" +
                "id=" + id +
                ", boardId=" + boardId +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
