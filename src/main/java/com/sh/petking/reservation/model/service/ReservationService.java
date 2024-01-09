package com.sh.petking.reservation.model.service;

import com.sh.petking.reservation.model.dao.ReservationDao;
import com.sh.petking.reservation.model.entity.Reservation;

import com.sh.petking.reservation.model.entity.ReservationPay;
import com.sh.petking.reservation.model.vo.ReservationVo;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.entity.RoomAttach;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReservationService {
    private ReservationDao reservationDao = new ReservationDao();

    //0105 캠핑장 아이디로 객실들 찾기
    public List<RoomVo> findByCampId(Map<String, Object> params) {

        SqlSession session = getSqlSession();
        List<RoomVo> room = reservationDao.findByCampId(session, params);
        session.close();
        return room;
    }

    //첫날,마지막날,그리고 캠핑아이디 값을 담은 맵을 파라메터로 받아 대여가 가능한 객실을 출력해주는 service단 코드
    public List<Reservation> findAbleRoom(Map<String, Object> params) {

        SqlSession session = getSqlSession();
        List<Reservation> reservation = reservationDao.findAbleRoom(session, params);
        session.close();
        return reservation;

    }

        /**
         * 사용자 아이디로 예약내역 조회하기
         * @param
         * @return
         */
        public List<ReservationVo> findByDonReservUserId (Map < String, Object > param){
            SqlSession session = getSqlSession();
            List<ReservationVo> reservations = reservationDao.findByDonReservUserId(session, param);
            session.close();
            return reservations;
        }

        public int getTotalDonReservCount (Map < String, Object > param){
            SqlSession session = getSqlSession();
            int totalCount = reservationDao.getTotalDonReservCount(session, param);
            session.close();
            return totalCount;
        }

        public int getTotalProcessReservCount (Map < String, Object > param){
            SqlSession session = getSqlSession();
            int totalCount = reservationDao.getTotalProcessReservCount(session, param);
            session.close();
            return totalCount;
        }

        public List<ReservationVo> findByProcessReservUserId (Map < String, Object > param){
            SqlSession session = getSqlSession();
            List<ReservationVo> reservations = reservationDao.findByProcessReservUserId(session, param);
            session.close();
            return reservations;
        }

        public int getTotalCancelReservCount (Map < String, Object > param){
            SqlSession session = getSqlSession();
            int totalCount = reservationDao.getTotalCancelReservCount(session, param);
            session.close();
            return totalCount;
        }

        public List<ReservationVo> findByCancelReservUserId (Map < String, Object > param){
            SqlSession session = getSqlSession();
            List<ReservationVo> reservations = reservationDao.findByCancelReservUserId(session, param);
            session.close();
            return reservations;
        }

        //예약 누적 테이블 - reservation에 새 내역을 insert
        //동시에  reservation_pay 테이블에도 insert해줘야함.
        public int insertReservaion(Reservation reservation, ReservationPay reservationPay)
        {
            int result = 0;
            SqlSession session = getSqlSession();

            try
            {
                //board + attach 둘 다 insert하고 commit or rollback 처리 해야함
                result = reservationDao.insertReservation(session,reservation);
                System.out.println("reservation get id())" + reservation.getId());
                //위에서 받아온 예약내역 id를 fk로 사용한다.
                reservationPay.setReservId(reservation.getId());

                result= reservationDao.insertReservationPay(session,reservationPay);

                //attach 테이블에 등록
//                List<RoomAttach> attachments = room.getRoomAttachs();
//                if(!attachments.isEmpty())
//                {
//                    for(RoomAttach attach : attachments)
//                    {
//                        attach.setRoomId(room.getId()); //fk로 사용할 객실 id
//                        result = roomDao.insertAttachment(session,attach);
//
//                        //attachment 테이블삭제
//                        List<Long> delFiles = room.getDelFiles();
//                        if(!delFiles.isEmpty())
//                        {
//                            System.out.println("지울 첨부파일이 있다........");
//                            for(Long id : delFiles)
//                                result = roomDao.deleteAttachment(session,id);
//                        }
//                    }
//                }

                session.commit();
            }
            catch(Exception e)
            {
                //에러가 발생할 경우 롤백하기
                session.rollback();
                throw  e;
            }
            finally
            {
                session.close();
            }
            return result;
        }

    public List<Room> findByRoomCampId(long campId) {
        SqlSession session = getSqlSession();
        List<Room> room = reservationDao.findByRoomCampId(session, campId);
        session.close();
        return room;
    }
}