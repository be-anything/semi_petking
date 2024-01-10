package com.sh.petking.reservation.model.entity;


import java.time.LocalDate;

//예약관련 결제 테이블 (얼마 결제했나, 포인트 사용 유무 확인)
public class ReservationPay {
    private long id; //아이디 pk
    private long reservEx; //결제금액
    private long reservUsePoint; //사용한 포인트..
    private LocalDate reservDate; //결제 한 날짜
    private long reservId; //예약 id - fk

    public ReservationPay() {
    }

    public ReservationPay(long id, long reservEx, long reservUsePoint, LocalDate reservDate, long reservId) {
        this.id = id;
        this.reservEx = reservEx;
        this.reservUsePoint = reservUsePoint;
        this.reservDate = reservDate;
        this.reservId = reservId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReservEx() {
        return reservEx;
    }

    public void setReservEx(long reservEx) {
        this.reservEx = reservEx;
    }

    public long getReservUsePoint() {
        return reservUsePoint;
    }

    public void setReservUsePoint(long reservUsePoint) {
        this.reservUsePoint = reservUsePoint;
    }

    public LocalDate getReservDate() {
        return reservDate;
    }

    public void setReservDate(LocalDate reservDate) {
        this.reservDate = reservDate;
    }

    public long getReservId() {
        return reservId;
    }

    public void setReservId(long reservId) {
        this.reservId = reservId;
    }
}
