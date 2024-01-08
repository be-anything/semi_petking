package com.sh.petking.club.model.entity;

public class ClubAttach {
    private long id;
    private long attachId;
    private long clubId;

    public ClubAttach() {
    }

    public ClubAttach(long id, long attachId, long clubId) {
        this.id = id;
        this.attachId = attachId;
        this.clubId = clubId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAttachId() {
        return attachId;
    }

    public void setAttachId(long attachId) {
        this.attachId = attachId;
    }

    public long getClubId() {
        return clubId;
    }

    public void setClubId(long clubId) {
        this.clubId = clubId;
    }

    @Override
    public String toString() {
        return "ClubAttach{" +
                "id=" + id +
                ", attachId=" + attachId +
                ", clubId=" + clubId +
                '}';
    }
}
