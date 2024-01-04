package com.sh.petking.room.model.entity;


/***
 * 1228 jin 객실 table 1:1 매칭되는 entity class
 * 코드단에서는 카멜케이싱
 *
 * 0102 jin 객실 테이블 변경사항
 * -roomType 외래키 삭제
 * -room_default_fee / room_over_fee 컬럼 추가 (요금 컬럼이 빠져있었다..)
 */
public class Room {
    private Long id; //객실 아이디
    private Long campId; //캠핑장 아이디
    private String roomName; //객실명
    private int roomType; //객실 타입
    private String roomIntro; //객실설명
    private int roomDefaultPerson; //적정(기본)인원
    private int roomMaximumPerson; //최대인원

    private int roomDefaultFee; //기본요금
    private int roomOverFee; //초과요금

    public Room() {
    }

    public Room(Long id, Long campId, String roomName, int roomType, String roomIntro,
                int roomDefaultPerson, int roomMaximumPerson,int roomDefaultFee, int roomOverFee) {
        System.out.println("room 매개변수 있는 생성자.");
        this.id = id;
        this.campId = campId;
        this.roomName = roomName;
        this.roomType = roomType;
        this.roomIntro = roomIntro;
        this.roomDefaultPerson = roomDefaultPerson;
        this.roomMaximumPerson = roomMaximumPerson;
        this.roomDefaultFee = roomDefaultFee;
        this.roomOverFee = roomOverFee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampId() {
        return campId;
    }

    public void setCampId(Long campId) {
        this.campId = campId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomType() {
        return roomType;
    }

    public void setRoomType(int roomType) {
        this.roomType = roomType;
    }

    public String getRoomIntro() {
        return roomIntro;
    }

    public void setRoomIntro(String roomIntro) {
        this.roomIntro = roomIntro;
    }

    public int getRoomDefaultPerson() {
        return roomDefaultPerson;
    }

    public void setRoomDefaultPerson(int roomDefaultPerson) {
        this.roomDefaultPerson = roomDefaultPerson;
    }

    public int getRoomMaximumPerson() {
        return roomMaximumPerson;
    }

    public void setRoomMaximumPerson(int roomMaximumPerson) {
        this.roomMaximumPerson = roomMaximumPerson;
    }

    public int getRoomDefaultFee() {
        return roomDefaultFee;
    }

    public void setRoomDefaultFee(int roomDefaultFee) {
        this.roomDefaultFee = roomDefaultFee;
    }

    public int getRoomOverFee() {
        return roomOverFee;
    }

    public void setRoomOverFee(int roomOverFee) {
        this.roomOverFee = roomOverFee;
    }

    @Override
    public String toString() {
        return "ToString test - Room{" +
                "id=" + id +
                ", campId=" + campId +
                ", roomName='" + roomName + '\'' +
                ", roomType=" + roomType +
                ", roomIntro='" + roomIntro + '\'' +
                ", roomDefaultPerson=" + roomDefaultPerson +
                ", roomMaximumPerson=" + roomMaximumPerson +
                ", roomDefaultFee=" + roomDefaultFee +
                ", roomOverFee=" + roomOverFee +
                '}';
    }
}
