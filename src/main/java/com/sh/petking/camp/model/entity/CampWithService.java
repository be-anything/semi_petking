package com.sh.petking.camp.model.entity;

public class CampWithService {
    private Long id;
    private Long serviceId;
    private Long campId;

    @Override
    public String toString() {
        return "CampWithService{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", campId=" + campId +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public CampWithService(Long id, Long serviceId, Long campId) {
        this.id = id;
        this.serviceId = serviceId;
        this.campId = campId;
    }

    public CampWithService() {
    }
}
