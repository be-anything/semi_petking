package com.sh.petking.reservation.model.dao;


import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.reservation.model.vo.ReservationVo;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

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


    public List<ReservationVo> findByDonReservUserId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("reservation.findByDonReservUserId", param, rowBounds);
    }

    public int getTotalDonReservCount(SqlSession session, Map<String, Object> param) {
        return session.selectOne("reservation.getTotalDonReservCount", param);
    }

    public int getTotalProcessReservCount(SqlSession session, Map<String, Object> param) {
        return session.selectOne("reservation.getTotalProcessReservCount", param);
    }

    public List<ReservationVo> findByProcessReservUserId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("reservation.findByProcessReservUserId", param, rowBounds);
    }

    public int getTotalCancelReservCount(SqlSession session, Map<String, Object> param) {
        return session.selectOne("reservation.getTotalCancelReservCount", param);
    }

    public List<ReservationVo> findByCancelReservUserId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("reservation.findByCancelReservUserId", param, rowBounds);
    }
}
