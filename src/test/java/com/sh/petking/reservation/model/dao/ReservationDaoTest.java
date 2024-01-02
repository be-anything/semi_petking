package com.sh.petking.reservation.model.dao;

import com.sh.petking.reservation.model.entity.Reservation;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


/**
 * 1229 hyejin
 * 예약 내역 추가, 조회 (삭제는없습니다)
 */
class ReservationDaoTest 
{
    private ReservationDao reservationDao;
    private SqlSession session;

    //BeforeEach : 메서드 실행 전에 무조건 실행된다(init)
    @BeforeEach
    void setUp() {
        this.reservationDao = new ReservationDao();
        this.session = getSqlSession();
        System.out.println(LocalDate.now());
    }

    @AfterEach
    void tearDown() {
        this.session.rollback(); //test용이라 db에 실제 반영하지 않기 위해 롤백처리
        this.session.close();//세션 사용 후 종료
    }

    @DisplayName("ReservationDao,SqlSession은 null이 아니다.")
    @Test
    public void test1()
    {
        assertThat(reservationDao).isNotNull();
        assertThat(session).isNotNull();
    }

    /**
     *     private Long id;
     *     private Long campId;
     *     private Long roomId;
     *     private String userId;
     *     private LocalDate startDate;
     *     private LocalDate endDate;
     *     private int nop;
     *     private String status;
     *     대부분 not null
     */
    //user id = heeee12 회원으로 예약 추가
    @DisplayName("예약 추가")
    @ParameterizedTest
    @CsvSource({"111,4,777,heeee12,2023-12-29,2023-12-31,5,1"})
    public void test2(Long id, Long campId, Long roomId, String userId,
                      LocalDate startDate, LocalDate endDate, int nop, String status)
    {
        //given
        Date date = java.sql.Date.valueOf(startDate);
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setCampId(campId);
        reservation.setRoomId(roomId);
        reservation.setUserId(userId);
        reservation.setStartDate(LocalDate.now());
        reservation.setEndDate(LocalDate.now());
        reservation.setNop(nop);
        reservation.setStatus(status);
        System.out.println(reservation);
        //when
        int result = reservationDao.insertReservation(session,reservation);

        //then
        assertThat(result).isGreaterThan(0);
    }

    /**
     * 위에서 생성한 예약내역 id=111를 조회할 수 있어야 한다.
     */
//    @DisplayName("예약내역 id 111를 조회한다.")
//    @Test
//    public void test3()
//    {
//        long id=111;
//        Reservation reservation = reservationDao.findById(session,id);
//        assertThat(comments)
//                .isNotNull()
//                .allSatisfy((boardComment) -> {
//                    assertThat(boardComment.getId()).isNotZero();
//                    assertThat(boardComment.getBoardId()).isEqualTo(boardId);
//                    assertThat(boardComment.getContent()).isNotNull();
//                    assertThat(boardComment.getRegDate()).isNotNull();
//                });
//    }

    /**
     * 예약 내역 모두 조회.
     */
    @DisplayName("예약내역을 모두 조회한다.")
    @Test
    public void test4()
    {
        List<Reservation> reservations = reservationDao.findAll(session);
        System.out.println("-----------------------------");
        System.out.println(reservations);
        assertThat(reservations)
                .isNotNull()
                .allSatisfy((reservation) -> {
                    assertThat(reservation.getId()).isNotZero();
                    assertThat(reservation.getUserId()).isNotNull();
                    assertThat(reservation.getStartDate()).isNotNull();
                    assertThat(reservation.getEndDate()).isNotNull();
                });
    }

}