package com.sh.petking.room.model.entity;


/**
 * 1230
 * 객실 사진 테이블과 1:1매칭되는 클래스
 * 테이블명 room_attach
 *  id number not null,
 *  room_id number not null,
 *  room_attach_original_name varchar2(255) not null,
 *  room_attach_renamed_name varchar2(255) not null, -- uuid
 *  constraints pk_room_attach_id primary key(id),
 *  constraints fk_room_attach_room_id foreign key(room_id) references room(id) on delete cascade
 *
 */

public class RoomAttach
{
    private long id; //첨부파일 고유 id
    private long roomId; //해당하는 객실 id
    private String roomAttachOriginalName; //원본파일명
    private String roomAttachRenamedName; //새파일명

    public RoomAttach() {
    }

    public RoomAttach(long id, long roomId, String roomAttachOriginalName, String roomAttachRenamedName) {
        this.id = id;
        this.roomId = roomId;
        this.roomAttachOriginalName = roomAttachOriginalName;
        this.roomAttachRenamedName = roomAttachRenamedName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getRoomAttachOriginalName() {
        return roomAttachOriginalName;
    }

    public void setRoomAttachOriginalName(String roomAttachOriginalName) {
        this.roomAttachOriginalName = roomAttachOriginalName;
    }

    public String getRoomAttachRenamedName() {
        return roomAttachRenamedName;
    }

    public void setRoomAttachRenamedName(String roomAttachRenamedName) {
        this.roomAttachRenamedName = roomAttachRenamedName;
    }

    @Override
    public String toString() {
        return "RoomAttach{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", roomAttachOriginalName='" + roomAttachOriginalName + '\'' +
                ", roomAttachRenamedName='" + roomAttachRenamedName + '\'' +
                '}';
    }
}
