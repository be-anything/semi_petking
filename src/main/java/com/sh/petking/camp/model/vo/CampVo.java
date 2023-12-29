package com.sh.petking.camp.model.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.entity.CampTag;
import com.sh.petking.camp.model.entity.CampWithTag;

import java.util.ArrayList;
import java.util.List;

public class CampVo extends Camp {
    private int reviewCount;
    private int wishCount;
    private List<CampWithTagVo> campWithTags = new ArrayList<>();
    private String campTagName;

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
                ", campTagName='" + campTagName + '\'' +
                '}';
    }
}
