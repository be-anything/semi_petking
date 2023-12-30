package com.sh.petking.grade.model.entity;

public class UserGrade {
    private String id;
    private String name;
    private Long minPoint;
    private Long maxPoint;

    @Override
    public String toString() {
        return "UserGrade{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", minPoint=" + minPoint +
                ", maxPoint=" + maxPoint +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinPoint(Long minPoint) {
        this.minPoint = minPoint;
    }

    public void setMaxPoint(Long maxPoint) {
        this.maxPoint = maxPoint;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getMinPoint() {
        return minPoint;
    }

    public Long getMaxPoint() {
        return maxPoint;
    }

    public UserGrade(String id, String name, Long minPoint, Long maxPoint) {
        this.id = id;
        this.name = name;
        this.minPoint = minPoint;
        this.maxPoint = maxPoint;
    }

    public UserGrade() {
    }
}
