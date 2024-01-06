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
    private String campPhone;
    private String campAddr;
    private double campLcLa;
    private double campLcLo;
    private String campOriginalImg;
    private String campRenamedImg;
    private int campState;
    private LocalDate regDate;


    public Camp() {
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
                ", campPhone='" + campPhone + '\'' +
                ", campAddr='" + campAddr + '\'' +
                ", campLcLa=" + campLcLa +
                ", campLcLo=" + campLcLo +
                ", campOriginalImg='" + campOriginalImg + '\'' +
                ", campRenamedImg='" + campRenamedImg + '\'' +
                ", campState=" + campState +
                ", regDate=" + regDate +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessPassword() {
        return businessPassword;
    }

    public void setBusinessPassword(String businessPassword) {
        this.businessPassword = businessPassword;
    }

    public String getBusinessNumber() {
        return businessNumber;
    }

    public void setBusinessNumber(String businessNumber) {
        this.businessNumber = businessNumber;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampIntro() {
        return campIntro;
    }

    public void setCampIntro(String campIntro) {
        this.campIntro = campIntro;
    }

    public String getCampPhone() {
        return campPhone;
    }

    public void setCampPhone(String campPhone) {
        this.campPhone = campPhone;
    }

    public String getCampAddr() {
        return campAddr;
    }

    public void setCampAddr(String campAddr) {
        this.campAddr = campAddr;
    }

    public double getCampLcLa() {
        return campLcLa;
    }

    public void setCampLcLa(double campLcLa) {
        this.campLcLa = campLcLa;
    }

    public double getCampLcLo() {
        return campLcLo;
    }

    public void setCampLcLo(double campLcLo) {
        this.campLcLo = campLcLo;
    }

    public String getCampOriginalImg() {
        return campOriginalImg;
    }

    public void setCampOriginalImg(String campOriginalImg) {
        this.campOriginalImg = campOriginalImg;
    }

    public String getCampRenamedImg() {
        return campRenamedImg;
    }

    public void setCampRenamedImg(String campRenamedImg) {
        this.campRenamedImg = campRenamedImg;
    }

    public int getCampState() {
        return campState;
    }

    public void setCampState(int campState) {
        this.campState = campState;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDate regDate) {
        this.regDate = regDate;
    }

    public Camp(Long id, String businessId, String businessPassword, String businessNumber, String businessName, String campName, String campIntro, String campPhone, String campAddr, double campLcLa, double campLcLo, String campOriginalImg, String campRenamedImg, int campState, LocalDate regDate) {
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
        this.campOriginalImg = campOriginalImg;
        this.campRenamedImg = campRenamedImg;
        this.campState = campState;
        this.regDate = regDate;
    }
}
