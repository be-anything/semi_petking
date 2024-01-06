package com.sh.petking.index.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.index.model.entity.CampPromotion;

import java.time.LocalDate;

public class CampPromotionVo extends CampPromotion {
    private Camp camp;

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    @Override
    public String toString() {
        return "CampPromotionVo{" +
                "camp=" + camp +
                "} " + super.toString();
    }
}
