package com.sh.petking.reservation.model.dao;


import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ReservationDao
{
    //예약 내역 하나 추가
    public int insertReservation(SqlSession session, Reservation reservation)
    {
        return session.insert("reservation.insertReservation",reservation);
    }

    //모든 예약 내역 조회
    public List<Reservation> findAll(SqlSession session)
    {
        return session.selectList("reservation.findAll");
    }


    //특정 예약내역 하나만 조회
    public Reservation findById(SqlSession session, long id) {
        return session.selectOne("reservation.findById",id);
    }

    public List<RoomVo> findByCampId(SqlSession session, long campId)
    {
        System.out.println("ReservationDao , 4번 캠핑장 객실리스트 출력");
        return session.selectList("reservation.findByCampId",campId);
    }


}
