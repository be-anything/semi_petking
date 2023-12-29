package com.sh.petking.camp.model.vo;

import com.sh.petking.camp.model.entity.CampTag;

public class CampWithTagVo {
    private Long id;
    private Long tagId;
    private Long campId;
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public CampWithTagVo() {
    }

    public CampWithTagVo(Long id, Long tagId, Long campId) {
        this.id = id;
        this.tagId = tagId;
        this.campId = campId;
    }

    @Override
    public String toString() {
        return "CampWithTagVo{" +
                "id=" + id +
                ", tagId=" + tagId +
                ", campId=" + campId +
                ", tagName='" + tagName + '\'' +
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
