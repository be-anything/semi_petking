package com.sh.petking.camp.model.entity;

public class Approve {
    private Long id;
    private Long campId;
    private String campMsg;

    public Approve() {
    }

    public Approve(Long id, Long campId, String campMsg) {
        this.id = id;
        this.campId = campId;
        this.campMsg = campMsg;
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

    public String getCampMsg() {
        return campMsg;
    }

    public void setCampMsg(String campMsg) {
        this.campMsg = campMsg;
    }

    @Override
    public String toString() {
        return "Approve{" +
                "id=" + id +
                ", campId=" + campId +
                ", campMsg='" + campMsg + '\'' +
                '}';
    }
}
