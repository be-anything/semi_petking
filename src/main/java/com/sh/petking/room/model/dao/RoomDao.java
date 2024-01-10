package com.sh.petking.room.model.dao;

import com.sh.petking.reservation.model.entity.Reservation;
import com.sh.petking.room.model.dto.RoomDto;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.entity.RoomAttach;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;


//1228 jin
public class RoomDao
{

    //객실 추가
    public int insertRoom(SqlSession session, RoomVo room) {
        System.out.println("RoomDao......"+room);
        return session.insert("room.insertRoom", room);
    }

    //test - 모든 객실 조회
    public static List<RoomVo> findAll(SqlSession session, Map<String, Object> param)
    {
        System.out.println("---------페이징 dao - find all---------------");
        //단일이 아닌 여러개의 값을 가져와야 하므로 list
        int page = (int)param.get("page");
        int limit = (int)param.get("limit");
        int offset = (page-1) * limit;
        //RowBounds rowBounds = new RowBounds(offset,limit);
        return session.selectList("room.findAll",param);
    }
    
    //getTotalCount 쿼리문 수정
    public int getToTalCount(SqlSession session,long campId)
    {
        return session.selectOne("room.getTotalCount",campId);
    }

    public int deleteRoom(SqlSession session, RoomVo room)
    {
        System.out.println("deleteRoom DAO - room: "+room);
        return session.delete("room.deleteRoom",room);
    }

    public Room findRoom(SqlSession session, RoomDto roomDto)
    {
        return session.selectOne("room.findRoom",roomDto);
    }

    public RoomVo findById(SqlSession session, long id)
    {
            return session.selectOne("room.findById",id);
    }

    //객실 첨부사진 추가
    public int insertAttachment(SqlSession session, RoomAttach attach) {
        System.out.println("dao 첨부파일......"+attach);
        return session.insert("room.insertRoomAttach",attach);
    }
    //객실 첨부사진 삭제
    public int deleteAttachment(SqlSession session, Long id) {
        return session.delete("room.deleteRoomAttach",id);
    }

    //0103 객실 수정
    public int updateRoom(SqlSession session, RoomVo room) {
        return session.update("room.updateRoom", room);
    }

    //0105 캠핑장 아이디로 객실 리스트 조회
    public RoomVo findByCampId(SqlSession session, long id)
    {
        System.out.println("daofindByCampId......캠핑장 아이디 : "+id);
        return session.selectOne("room.findByCampId",id);
    }

    
    //객실 리스트 -> 헤더에서 사업자관리로 넘기면서 임시로 처리한 것 페이지바 없음
    public List<RoomVo> findRoomListByCampId(SqlSession session, long id) {
        System.out.println("객실 리스트 -> 헤더에서 사업자관리로 넘기면서 임시로 처리한 것 페이지바 없음");
        return session.selectList("room.findRoomListByCampId", id);
    }

    //내가 삭제하려고 하는 객실이 오늘날짜 이후로 숙박기간이 종료되는것이라면 삭제 해선 안된다.
    //그런 예약 내역이 있는지 미리 조회하는 select 쿼리를 작성.
    public List<Reservation> deleteRoomBeforeCheck(SqlSession session, long id) {
        return session.selectList("room.deleteRoomBeforeCheck", id);
    }
}
