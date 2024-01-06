package com.sh.petking.reservation.model.vo;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.reservation.model.entity.Reservation;

public class ReservationVo extends Reservation {
    // 스칼라 서브쿼리를 사용해서 전체 객체가 아닌 Camp객체와 Room객체의 일부 필드만을 필드로 가지고 있습니다.
    private String campName;
    private String campRenamedImg;
    private String roomName;

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public String getCampRenamedImg() {
        return campRenamedImg;
    }

    public void setCampRenamedImg(String campRenamedImg) {
        this.campRenamedImg = campRenamedImg;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "ReservationVo{" +
                "campName='" + campName + '\'' +
                ", campRenamedImg='" + campRenamedImg + '\'' +
                ", roomName='" + roomName + '\'' +
                '}';
    }
}
