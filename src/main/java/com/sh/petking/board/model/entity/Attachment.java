package com.sh.petking.board.model.entity;

import com.sh.petking.board.model.vo.BoardVo;

import java.util.ArrayList;
import java.util.List;

public class Attachment{
    private long id;
    private String originalName;
    private String renamedName;

    List<Attachment> attachments = new ArrayList<>();

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public Attachment() {
    }

    public Attachment(long id, String originalName, String renamedName) {
        this.id = id;
        this.originalName = originalName;
        this.renamedName = renamedName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getRenamedName() {
        return renamedName;
    }

    public void setRenamedName(String renamedName) {
        this.renamedName = renamedName;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", originalName='" + originalName + '\'' +
                ", renamedName='" + renamedName + '\'' +
                '}';
    }

    public void addAttachment(Attachment attach) {
        this.attachments.add(attach);
    }
}
