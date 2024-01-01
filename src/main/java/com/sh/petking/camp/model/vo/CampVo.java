package com.sh.petking.camp.model.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.entity.CampTag;
import com.sh.petking.camp.model.entity.CampWithTag;
import com.sh.petking.room.model.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class CampVo extends Camp {
    private int reviewCount;
    private int wishCount;
    private List<CampWithTagVo> campWithTags = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    private String campTagName;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setCampTagName(String campTagName) {
        this.campTagName = campTagName;
    }

    public String getCampTagName() {
        return campTagName;
    }

    public List<CampWithTagVo> getCampWithTags() {
        return campWithTags;
    }

    public void setCampWithTags(List<CampWithTagVo> campWithTags) {
        this.campWithTags = campWithTags;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }
    public int getReviewCount() {
        return reviewCount;
    }

    public int getWishCount() {
        return wishCount;
    }

    @Override
    public String toString() {
        return "CampVo{" +
                "reviewCount=" + reviewCount +
                ", wishCount=" + wishCount +
                ", campWithTags=" + campWithTags +
                ", rooms=" + rooms +
                ", campTagName='" + campTagName + '\'' +
                '}';
    }

    public void setValue(String name, String value) {
        switch (name) {
            // form안의 일반 field - id(findById), campIntro | campPhone | campName | campAddr
            case "id" : this.setId(Long.parseLong(value)); break;
            case "campIntro" : this.setCampIntro(value); break;
            case "campPhone" : this.setCampPhone(value); break;
            case "campName" : this.setCampName(value); break;
            case "campAddr" : this.setCampAddr(value); break;
            default:throw new RuntimeException("부적절한 name 값" + name);
        }
    }
}
