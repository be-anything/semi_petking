package com.sh.petking.camp.model.entity;

public class _CampService {
    private Long id;
    private String name;
    private String serviceImg;

    @Override
    public String toString() {
        return "CampService{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", serviceImg='" + serviceImg + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }

    public _CampService(Long id, String name, String serviceImg) {
        this.id = id;
        this.name = name;
        this.serviceImg = serviceImg;
    }

    public _CampService() {
    }
}
