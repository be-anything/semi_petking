package com.sh.petking.wish.model.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.wish.model.entity.Wish;

import java.time.LocalDate;
import java.util.List;

public class WishVo extends Wish {
    private List<Camp> camps;
    private int wishCount;

    public int getWishCount() {
        return wishCount;
    }

    public void setWishCount(int wishCount) {
        this.wishCount = wishCount;
    }

    @Override
    public String toString() {
        return "WishVo{" +
                "camps=" + camps +
                ", wishCount=" + wishCount +
                '}';
    }

    public List<Camp> getCamps() {
        return camps;
    }

    public void setCamps(List<Camp> camps) {
        this.camps = camps;
    }
}
