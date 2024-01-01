package com.sh.petking.camp.model.entity;

public class CampAttach {
    private Long id;
    private Long campId;
    private String campAttachOriginalName;
    private String campAttachRenamedName;

    public CampAttach() {
    }

    @Override
    public String toString() {
        return "CampAttach{" +
                "id=" + id +
                ", campId=" + campId +
                ", campAttachOriginalName='" + campAttachOriginalName + '\'' +
                ", campAttachRenamedName='" + campAttachRenamedName + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public String getCampAttachOriginalName() {
        return campAttachOriginalName;
    }

    public void setCampAttachOriginalName(String campAttachOriginalName) {
        this.campAttachOriginalName = campAttachOriginalName;
    }

    public String getCampAttachRenamedName() {
        return campAttachRenamedName;
    }

    public void setCampAttachRenamedName(String campAttachRenamedName) {
        this.campAttachRenamedName = campAttachRenamedName;
    }

    public CampAttach(Long id, Long campId, String campAttachOriginalName, String campAttachRenamedName) {
        this.id = id;
        this.campId = campId;
        this.campAttachOriginalName = campAttachOriginalName;
        this.campAttachRenamedName = campAttachRenamedName;
    }
}
