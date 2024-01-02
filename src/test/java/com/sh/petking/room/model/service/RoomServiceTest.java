package com.sh.petking.room.model.service;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.dto.RoomDto;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;



/***
 * 1228 jin 객실 테이블 테스트
 * -추가부터
 */
class RoomServiceTest {
    RoomService roomService ; //fixture
    CampService campService; // 객실 추가하기 위해 테이블에 존재하는 camp 아이디 확인


    SqlSession session;

    //BeforeEach : 어노테이션을 명시한 메서드는 테스트 메서드 실행 전에 무조건 실행된다
    @BeforeEach
    public void setUp() //매번
    {
        this.session = getSqlSession();
    }

    @AfterEach
    public void tearDown()
    {
        this.session.rollback();//test라 실제 반영하지 않기 위해 롤백처리 해준다.
        this.session.close();//세션은 사용후 종료
    }

    @BeforeEach
    public void beforeEach()
    {
        this.roomService = new RoomService();
        this.campService = new CampService();
    }

    @DisplayName("RoomService/CampService 객체는 null이 아니다.")
    @Test
    public void test1()
    {
        assertThat(roomService).isNotNull();
        assertThat(campService).isNotNull();
    }

    @Disabled
    @DisplayName("존재하는 캠핑장 하나를 조회할 수 있습니다. id 4 가고파 캠핑장")
    @Test
    public void test2() {
        long id = 4;
        Camp camp = campService.findById(id);
        assertThat(camp).isNotNull();
        assertThat(camp.getId()).isNotNull();
        assertThat(camp.getBusinessId()).isNotNull();
        assertThat(camp.getBusinessPassword()).isNotNull();
        assertThat(camp.getBusinessNumber()).isNotNull();
        assertThat(camp.getBusinessName()).isNotNull();
        assertThat(camp.getCampName()).isNotNull();
      //  assertThat(camp.getCampPhone()).isNotNull();
        assertThat(camp.getCampAddr()).isNotNull();
        assertThat(camp.getCampLcLa()).isNotZero();
        assertThat(camp.getCampLcLo()).isNotZero();
        assertThat(camp.getCampState()).isNotNull();
        assertThat(camp.getRegDate()).isNotNull();
    }



   @Disabled //이미 추가한거라 disabled로 실행 막아둘게요
    @DisplayName("캠핑장id가 4번인 캠핑장에 객실 하나를 추가 할 수 있습니다.")
    @ParameterizedTest
    @CsvSource({"111,4,기본펜션형,4,무난한 객실타입. 기본형입니다. ,3,4"})
    void test3(Long id, Long campId, String roomName, int roomType, String roomIntro, int roomDefaultPerson, int roomMaximumPersona) {
        Room room = new Room();
        room.setId(id);
        room.setCampId(campId);
        room.setRoomName(roomName);
        room.setRoomType(roomType);
        room.setRoomIntro(roomIntro);
        room.setRoomDefaultPerson(roomDefaultPerson);
        room.setRoomMaximumPerson(roomMaximumPersona);
        int result = roomService.insertRoom(room);
        System.out.println(result);
        System.out.println(room);
        System.out.println("객실id:"+room.getId()+"/객실이름:"+room.getRoomName());
    }

    @Disabled
    @DisplayName("캠핑장 번호가 4인 객실(들)을 조회할 수 있습니다.")
    @Test
    void test4() {
        long id=4;
        List<RoomVo> rooms = roomService.findAll();
        //반환타입은 리스트이다.
        assertThat(rooms)
                .isNotNull()
                .isNotEmpty();
        //Consumer 타입 람다식:매개변수가 하나 있고, 리턴타입은 없음.
        rooms.forEach((room) ->
        {
            System.out.println(room);
            assertThat(room.getId()).isNotNull(); //객실번호
            assertThat(room.getRoomName()).isNotNull(); //객실명
            assertThat(room.getRoomIntro()).isNotNull(); //객실설명
            assertThat(room.getRoomDefaultPerson()).isNotZero(); //기준인원
            assertThat(room.getRoomMaximumPerson()).isNotZero(); //최대인원
            assertThat(room.getRoomType()).isNotZero(); //객실 타입
        });
    }



    /**
     * 특정 캠핑장의 특정 객실 [하나]만 조회
     */
    @DisplayName("캠핑장 아이디가 4이면서 객실 아이디가 100인 객실 1개를 조회할 수 있습니다..")
    @Test
    public void test5()
    {
        //given 캠핑장 4번 안에 객실 100번이 존재한다고 가정
        RoomDto roomDto = new RoomDto();
        long id = 100;
        long campId = 4;
        roomDto.setId(id);
        roomDto.setCampId(campId);

        //when
        Room room = roomService.findRoom(roomDto); //sql처리된 새 값을 변수에 대입

        //then
        assertThat(room).isNotNull();
        System.out.println(room);
    }


    @Disabled
    /**
     * 객실 아이디(pk)가 시퀀스로 발급받는거라 겹치는게 없겠지만 ..
     * 특정 캠핑장의 아이디도 where and 조건에 걸어야 할 것 같아서 그렇게 진행해보겠습니다..
     */
    @DisplayName("캠핑장 아이디가 4이면서 객실 아이디가 100인 객실을 삭제할 수 있습니다.")
    @Test
    public void test6()
    {
        //given 캠핑장 4번 안에 객실 100번이 존재한다고 가정
        RoomDto roomDto = new RoomDto();
        long id = 100;
        long campId = 4;
        roomDto.setId(id);
        roomDto.setCampId(campId);

        //when 삭제하면
        int result = roomService.deleteRoom(roomDto);
        assertThat(result).isGreaterThan(0);

        //then
        Room room = roomService.findRoom(roomDto);
        assertThat(room).isNull();


}

//특정 객실 하나를 수정하거나 삭제하려면 캠핑장번호and객실번호로 찾아야 한다.(only one)
//    @DisplayName("캠핑장 번호가 4이면서 객실 번호가 777인 객실의 정보를 수정 할 수 있다.")
//    @Test
//    void test5(Room room)
//    {
//        //insert와 같은 dml처리 이므로 try-catch-finally 사용하고
//        //성공시 commit, 실패시 rollback 처리
//        int result = 0;
//        SqlSession session = getSqlSession();
//        try
//        {
//            result = boardDao.updateBoard(session,board);
//            session.commit();
//        }
//        catch(Exception e)
//        {
//            session.rollback();
//            throw e;
//        }
//        finally
//        {
//            //어찌됐든간에 세션종료는 필수다.
//            session.close();
//        }
//        return result;
//    }

}