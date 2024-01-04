package com.sh.petking.ask.model.vo;


import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.camp.model.entity.Camp;

/**
 *  0104 혜진
 *  현재 ask 테이블에 campId만 있으므로 campName(camp_name) 가져오기 위한 vo 클래스 추가
 */
public class AskVo extends Ask {
    private Camp camp;

    public Camp getCamp() {
        return camp;
    }

    public void setCamp(Camp camp) {
        this.camp = camp;
    }
}
