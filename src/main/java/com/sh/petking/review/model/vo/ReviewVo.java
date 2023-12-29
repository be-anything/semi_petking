package com.sh.petking.review.model.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.review.model.entity.Review;

public class ReviewVo extends Review {
    private Camp camp;

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public String toString() {
        return "ReviewVo{" +
                "camp=" + camp +
                "} " + super.toString();
    }
}
