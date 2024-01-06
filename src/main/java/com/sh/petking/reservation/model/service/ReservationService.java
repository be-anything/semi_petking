package com.sh.petking.reservation.model.service;

import com.sh.petking.reservation.model.dao.ReservationDao;
import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.reservation.model.vo.ReservationVo;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReservationService
{
    private ReservationDao reservationDao = new ReservationDao();
    //0105 캠핑장 아이디로 객실 들 찾기
    public List<RoomVo> findByCampId(long id) {
        SqlSession session = getSqlSession();
        List<RoomVo> room = reservationDao.findByCampId(session,id);
        session.close();
        return room;
    }


    /**
     * 사용자 아이디로 예약내역 조회하기
     * @param id
     * @return
     */
    public List <ReservationVo> findByDonReservUserId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReservationVo> reservations = reservationDao.findByDonReservUserId(session, param);
        session.close();
        return reservations;
    }

    public int getTotalDonReservCount(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reservationDao.getTotalDonReservCount(session, param);
        session.close();
        return totalCount;
    }

    public int getTotalProcessReservCount(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reservationDao.getTotalProcessReservCount(session, param);
        session.close();
        return totalCount;
    }

    public List<ReservationVo> findByProcessReservUserId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReservationVo> reservations = reservationDao.findByProcessReservUserId(session, param);
        session.close();
        return reservations;
    }

    public int getTotalCancelReservCount(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reservationDao.getTotalCancelReservCount(session, param);
        session.close();
        return totalCount;
    }

    public List<ReservationVo> findByCancelReservUserId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReservationVo> reservations = reservationDao.findByCancelReservUserId(session, param);
        session.close();
        return reservations;
    }
}
