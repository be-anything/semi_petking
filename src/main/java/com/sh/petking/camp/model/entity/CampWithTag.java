package com.sh.petking.camp.model.entity;

public class CampWithTag extends CampTag{
    private Long id;
    private Long tagId;
    private Long campId;

    public CampWithTag() {
    }

    public CampWithTag(Long id, Long tagId, Long campId) {
        this.id = id;
        this.tagId = tagId;
        this.campId = campId;
    }

    @Override
    public String toString() {
        return "CampWithTag{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", campId=" + campId +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public Long getId() {
        return id;
    }

    public Long getTagId() {
        return tagId;
    }

    public Long getCampId() {
        return campId;
    }
}
