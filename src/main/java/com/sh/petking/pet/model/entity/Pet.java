package com.sh.petking.pet.model.entity;

import java.time.LocalDateTime;

public class Pet {
    private String userId; // 사용자아이디
    private String petName;
    private int petAge;
    private String petGender;
    private String neutered;
    private LocalDateTime regDate;

    public Pet() {
    }

    public Pet(String userId, String petName, int petAge, String petGender, String neutered, LocalDateTime regDate) {
        this.userId = userId;
        this.petName = petName;
        this.petAge = petAge;
        this.petGender = petGender;
        this.neutered = neutered;
        this.regDate = regDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }

    public String getPetGender() {
        return petGender;
    }

    public void setPetGender(String petGender) {
        this.petGender = petGender;
    }

    public String getNeutered() {
        return neutered;
    }

    public void setNeutered(String neutered) {
        this.neutered = neutered;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "userId='" + userId + '\'' +
                ", petName='" + petName + '\'' +
                ", petAge=" + petAge +
                ", petGender='" + petGender + '\'' +
                ", neutered='" + neutered + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
