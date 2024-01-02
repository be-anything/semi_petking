package com.sh.petking.board.model.vo;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.entity.Board;
import com.sh.petking.board.model.entity.BoardComment;
import com.sh.petking.board.model.entity.BoardType;
import com.sh.petking.user.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardVo extends Board {
    private User user;
    private int attachCount; // 첨부파일 개수
    private List<Attachment> attachments = new ArrayList<>();
    private List<Long> delFiles = new ArrayList<>();
    private List<BoardComment> comments;
    private int commentCount;

    public BoardVo() {
    }

    public BoardVo(long id, String userId, BoardType boardType, String boardTitle, String boardContent, LocalDateTime regDate, int boardAttr, int viewCount, User user, int attachCount, List<Attachment> attachments, List<Long> delFiles, List<BoardComment> comments, int commentCount) {
        super(id, userId, boardType, boardTitle, boardContent, regDate, boardAttr, viewCount);
        this.user = user;
        this.attachCount = attachCount;
        this.attachments = attachments;
        this.delFiles = delFiles;
        this.comments = comments;
        this.commentCount = commentCount;
    }

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

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public List<Long> getDelFiles() {
        return delFiles;
    }

    public void setDelFiles(List<Long> delFiles) {
        this.delFiles = delFiles;
    }

    public List<BoardComment> getComments() {
        return comments;
    }

    public void setComments(List<BoardComment> comments) {
        this.comments = comments;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "BoardVo{" +
                "user=" + user +
                ", attachCount=" + attachCount +
                ", attachments=" + attachments +
                ", delFiles=" + delFiles +
                ", comments=" + comments +
                ", commentCount=" + commentCount +
                "} " + super.toString();
    }

    public void setValue(String name, String value) {
        switch (name) {
            case "id" : setId(Long.parseLong(value)); break;
            case "title" : setBoardTitle(value); break;
            case "userId" : setUserId(value); break;
            case "content" : setBoardContent(value); break;
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
