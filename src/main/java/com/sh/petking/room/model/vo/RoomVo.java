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

    private List<Long> delFiles = new ArrayList<>();

    public void addAttachment(RoomAttach attachment)
    {
        this.roomAttachs.add(attachment); //누적
    }
    public List<RoomAttach> getRoomAttachs() {
        return roomAttachs;
    }


    public void setRoomAttachs(List<RoomAttach> roomAttachs) {
        this.roomAttachs = roomAttachs;
    }



    public List<Long> getDelFiles() {
        return delFiles;
    }

    public void setDelFiles(List<Long> delFiles) {
        this.delFiles = delFiles;
    }

    @Override
    public String toString() {
        return "\nRoomVo{" +
                "roomAttachs=" + roomAttachs +
                '}'+ super.toString();
    }


    public void setValue(String name, String value)
    {
        switch(name)
        {
            case "id" : setId(Long.parseLong(value)); break;
            case "campId" : setCampId(Long.parseLong(value)); break;
            case "roomName": setRoomName(value); break;
            case "roomType": setRoomType(Integer.parseInt(value)); break;
            case "roomIntro": setRoomIntro(value); break;
            case "roomDefaultPerson" : setRoomDefaultPerson(Integer.parseInt(value)); break;
            case "roomMaximumPerson" : setRoomMaximumPerson(Integer.parseInt(value)); break;
            case "roomDefaultFee" : setRoomDefaultFee(Integer.parseInt(value)); break;
            case "roomOverFee" : setRoomOverFee(Integer.parseInt(value)); break;

            //1221 첨부파일 삭제 관련 기능 추가 delFile은 리스트
            case "delFile" : this.delFiles.add(Long.parseLong(value)); break;
            default:throw new RuntimeException("부적절한 name값:"+name);
        }
    }

}
