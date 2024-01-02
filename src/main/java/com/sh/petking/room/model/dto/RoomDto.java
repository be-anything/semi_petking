package com.sh.petking.room.model.dto;


/***
 * 1229 room entity를 대신해 데이터 교환 담당 , getter/setter만 존재
 * 예비용으로 만든거라 신경안쓰셔도 됩니다!
 */
public class RoomDto {
    private Long id; //객실 아이디
    private Long campId; //캠핑장 아이디

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
}
