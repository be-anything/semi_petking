package com.sh.petking.camp.model.entity;

import java.time.LocalDate;

public class Camp {
    private Long id;
    private String businessId;
    private String businessPassword;
    private String businessNumber;
    private String businessName;
    private String campName;
    private String campIntro;
    private Long campPhone;
    private String campAddr;
    private double campLcLa;
    private double campLcLo;
    private String campImg;
    private int campState;
    private LocalDate regDate;

    public Camp() {
    }

    public Camp(Long id, String businessId, String businessPassword, String businessNumber, String businessName, String campName, String campIntro, Long campPhone, String campAddr, double campLcLa, double campLcLo, String campImg, int campState, LocalDate regDate) {
        this.id = id;
        this.businessId = businessId;
        this.businessPassword = businessPassword;
        this.businessNumber = businessNumber;
        this.businessName = businessName;
        this.campName = campName;
        this.campIntro = campIntro;
        this.campPhone = campPhone;
        this.campAddr = campAddr;
        this.campLcLa = campLcLa;
        this.campLcLo = campLcLo;
        this.campImg = campImg;
        this.campState = campState;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Camp{" +
                "id=" + id +
                ", businessId='" + businessId + '\'' +
                ", businessPassword='" + businessPassword + '\'' +
                ", businessNumber='" + businessNumber + '\'' +
                ", businessName='" + businessName + '\'' +
                ", campName='" + campName + '\'' +
                ", campIntro='" + campIntro + '\'' +
                ", campPhone=" + campPhone +
                ", campAddr='" + campAddr + '\'' +
                ", campLcLa=" + campLcLa +
                ", campLcLo=" + campLcLo +
                ", campImg='" + campImg + '\'' +
                ", campState=" + campState +
                ", regDate=" + regDate +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public void setBusinessPassword(String businessPassword) {
        this.businessPassword = businessPassword;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public void setCampIntro(String campIntro) {
        this.campIntro = campIntro;
    }

    public void setCampPhone(Long campPhone) {
        this.campPhone = campPhone;
    }

    public void setCampAddr(String campAddr) {
        this.campAddr = campAddr;
    }

    public void setCampLcLa(double campLcLa) {
        this.campLcLa = campLcLa;
    }

    public void setCampLcLo(double campLcLo) {
        this.campLcLo = campLcLo;
    }

    public void setCampImg(String campImg) {
        this.campImg = campImg;
    }

    public void setCampState(int campState) {
        this.campState = campState;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Long getId() {
        return id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public String getBusinessPassword() {
        return businessPassword;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCampName() {
        return campName;
    }

    public String getCampIntro() {
        return campIntro;
    }

    public Long getCampPhone() {
        return campPhone;
    }

    public String getCampAddr() {
        return campAddr;
    }

    public double getCampLcLa() {
        return campLcLa;
    }

    public double getCampLcLo() {
        return campLcLo;
    }

    public String getCampImg() {
        return campImg;
    }

    public int getCampState() {
        return campState;
    }

    public LocalDate getRegDate() {
        return regDate;
    }
}
