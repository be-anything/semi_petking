package com.sh.petking.reservation.model.service;

import com.sh.petking.reservation.model.dao.ReservationDao;
import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReservationService
{
    private ReservationDao reservationDao = new ReservationDao();
    //0105 캠핑장 아이디로 객실 들 찾기
    public List<RoomVo> findByCampId(Map<String, Object> params) {

        SqlSession session = getSqlSession();
        List<RoomVo> room = reservationDao.findByCampId(session,params);
        session.close();
        return room;
    }

    //첫날,마지막날,그리고 캠핑아이디 값을 담은 맵을 파라메터로 받아 대여가 가능한 객실을 출력해주는 service단 코드
    public List<Reservation> findAbleRoom(Map<String, Object> params) {

        SqlSession session = getSqlSession();
        List<Reservation> reservation = reservationDao.findAbleRoom(session,params);
        session.close();
        return reservation;


    }
}
