package com.sh.petking.board.model.vo;

import com.sh.petking.board.model.entity.*;
import com.sh.petking.user.model.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BoardVo extends Board {
    private User user;
    private int attachCount; // 첨부파일 개수
    private List<BoardAttach> attachments = new ArrayList<>();
    private List<Long> delFiles = new ArrayList<>();
    private List<BoardComment> comments;
    private int commentCount;

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

    public void addAttachment(BoardAttach attachment) {
        this.attachments.add(attachment);
    }

    public List<BoardAttach> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<BoardAttach> attachments) {
        this.attachments = attachments;
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
            case "boardType" : setBoardType(BoardType.valueOf(value)); break;
            case "title" : setBoardTitle(value); break;
            case "userId" : setUserId(value); break;
            case "content" : setBoardContent(value); break;
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default: throw new RuntimeException("부적절한 name값 : " + name);
        }
    }
}
