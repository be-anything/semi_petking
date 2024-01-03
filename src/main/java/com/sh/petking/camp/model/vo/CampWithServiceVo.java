package com.sh.petking.camp.model.vo;

public class CampWithServiceVo {
    private Long id;
    private Long serviceId;
    private Long campId;
    private String serviceName;
    private String serviceImg;

    public CampWithServiceVo() {
    }

    public CampWithServiceVo(Long id, Long serviceId, Long campId, String serviceName, String serviceImg) {
        this.id = id;
        this.serviceId = serviceId;
        this.campId = campId;
        this.serviceName = serviceName;
        this.serviceImg = serviceImg;
    }

    @Override
    public String toString() {
        return "CampWithServiceVo{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", campId=" + campId +
                ", serviceName='" + serviceName + '\'' +
                ", serviceImg='" + serviceImg + '\'' +
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceImg() {
        return serviceImg;
    }

    public void setServiceImg(String serviceImg) {
        this.serviceImg = serviceImg;
    }
}
