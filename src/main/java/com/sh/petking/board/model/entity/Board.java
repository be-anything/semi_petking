package com.sh.petking.board.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Board {
    private long id;
    private String userId;
    private BoardType boardType;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime regDate;
    private int boardAttr;
    private int viewCount;

    public Board() {
    }

    public Board(long id, String userId, BoardType boardType, String boardTitle, String boardContent, LocalDateTime regDate, int boardAttr, int viewCount) {
        this.id = id;
        this.userId = userId;
        this.boardType = boardType;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.regDate = regDate;
        this.boardAttr = boardAttr;
        this.viewCount = viewCount;
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

    public BoardType getBoardType() {
        return boardType;
    }

    public void setBoardType(BoardType boardType) {
        this.boardType = boardType;
    }

    public String getBoardTitle() {
        return boardTitle;
    }

    public void setBoardTitle(String boardTitle) {
        this.boardTitle = boardTitle;
    }

    public String getBoardContent() {
        return boardContent;
    }

    public void setBoardContent(String boardContent) {
        this.boardContent = boardContent;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public int getBoardAttr() {
        return boardAttr;
    }

    public void setBoardAttr(int boardAttr) {
        this.boardAttr = boardAttr;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", boardType=" + boardType +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContent + '\'' +
                ", regDate=" + regDate +
                ", boardAttr=" + boardAttr +
                ", viewCount=" + viewCount +
                '}';
    }
}
