package com.sh.petking.room.model.service;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;



/***
 * 1228 jin 객실 테이블 테스트
 * -추가부터
 */
class RoomServiceTest {
    RoomService roomService ; //fixture
    CampService campService; // 객실 추가하기 위해 테이블에 존재하는 camp 아이디 확인

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
        assertThat(camp.getCampPhone()).isNotNull();
        assertThat(camp.getCampAddr()).isNotNull();
        assertThat(camp.getCampLcLa()).isNotZero();
        assertThat(camp.getCampLcLo()).isNotZero();
        assertThat(camp.getCampState()).isNotNull();
        assertThat(camp.getRegDate()).isNotNull();
    }


    //#{id}, #{campId}, #{roomName}, #{roomType}, #{roomIntro}, #{roomDefaultPerson}, #{roomMaximumPerson}
    @DisplayName("캠핑장id가 4번인 캠핑장에 객실 하나를 추가 할 수 있습니다.")
    @ParameterizedTest
    @CsvSource({"777,4,럭셔리레이크뷰,4,눈 앞에 ㅇㅇ강이 펼쳐진 유니크한 객실,3,5"})
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
    
    

}