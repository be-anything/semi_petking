package com.sh.petking.room.model.vo;


import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.entity.RoomAttach;

import java.util.ArrayList;
import java.util.List;

/**
 * 1230 혜진
 * 객실 vo 클래스
 * 기존 entity class room에서 확장 (첨부사진 추가)
 * 기존 Room entity는 수정하지않고 RoomVo를 사용한다.
 */
public class RoomVo extends Room {

    //Room을 상속하고 기존에 없던 attach를 추가했다.
    private List<RoomAttach> roomAttachs = new ArrayList<>();

    public List<RoomAttach> getRoomAttachs() {
        return roomAttachs;
    }

    public void setRoomAttachs(List<RoomAttach> roomAttachs) {
        this.roomAttachs = roomAttachs;
    }

    @Override
    public String toString() {
        return "RoomVo{" +
                "roomAttachs=" + roomAttachs +
                '}'+ super.toString();
    }
}
