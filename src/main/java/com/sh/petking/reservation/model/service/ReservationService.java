package com.sh.petking.reservation.model.service;

import com.sh.petking.reservation.model.dao.ReservationDao;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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
}
