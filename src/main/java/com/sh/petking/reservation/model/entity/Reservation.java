package com.sh.petking.reservation.model.entity;

import java.time.LocalDate;

/**
 * 1228 jin 예약 누적 테이블 (취소내역까지 포함)
 * 주석 한 번 봐주세요!
 * 예약할 때 체크인,체크아웃 시간 고려하지 않아서 날짜 관련 필드는 LocalDate로 지정.
 * id : pk
 * campId : fk
 * roomId : fk
 * userId : fk
 * startDate 숙박 첫 날
 * endDate 숙박 마지막 날
 * nop (인원수)
 * status 에약 취소:0 , 예약 확정:1  기존 boolean에서 check - char(1)로 변경
 */
public class Reservation {
    private Long id;
    private Long campId;
    private Long roomId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int nop;
    private String status;

    public Reservation() {
    }

    public Reservation(Long id, Long campId, Long roomId, String userId, LocalDate startDate, LocalDate endDate, int nop, String status) {
        this.id = id;
        this.campId = campId;
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nop = nop;
        this.status = status;
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

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getNop() {
        return nop;
    }

    public void setNop(int nop) {
        this.nop = nop;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", campId=" + campId +
                ", roomId=" + roomId +
                ", userId='" + userId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", nop=" + nop +
                ", status='" + status + '\'' +
                '}';
    }
}
