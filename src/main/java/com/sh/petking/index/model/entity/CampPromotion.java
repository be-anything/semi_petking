package com.sh.petking.index.model.entity;

import java.time.LocalDate;

public class CampPromotion {
    private Long promoId;
    private Long campId;
    private String imgOriginalName;
    private String imgRenamedName;
    private Long promoState;
    private LocalDate regDate;

    public CampPromotion() {
    }

    public CampPromotion(Long promoId, Long campId, String imgOriginalName, String imgRenamedName, Long promoState, LocalDate regDate) {
        this.promoId = promoId;
        this.campId = campId;
        this.imgOriginalName = imgOriginalName;
        this.imgRenamedName = imgRenamedName;
        this.promoState = promoState;
        this.regDate = regDate;
    }

    public Long getPromoId() {
        return promoId;
    }

    public void setPromoId(Long promoId) {
        this.promoId = promoId;
    }

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public String getImgOriginalName() {
        return imgOriginalName;
    }

    public void setImgOriginalName(String imgOriginalName) {
        this.imgOriginalName = imgOriginalName;
    }

    public String getImgRenamedName() {
        return imgRenamedName;
    }

    public void setImgRenamedName(String imgRenamedName) {
        this.imgRenamedName = imgRenamedName;
    }

    public Long getPromoState() {
        return promoState;
    }

    public void setPromoState(Long promoState) {
        this.promoState = promoState;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "promoId=" + promoId +
                ", campId=" + campId +
                ", imgOriginalName='" + imgOriginalName + '\'' +
                ", imgRenamedName='" + imgRenamedName + '\'' +
                ", promoState=" + promoState +
                ", regDate=" + regDate +
                '}';
    }
}
