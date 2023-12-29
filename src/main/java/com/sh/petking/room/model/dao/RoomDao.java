package com.sh.petking.room.model.dao;

import com.sh.petking.room.model.dto.RoomDto;
import com.sh.petking.room.model.entity.Room;
import org.apache.ibatis.session.SqlSession;

import java.util.List;


//1228 jin
public class RoomDao
{

    //객실 추가
    public int insertRoom(SqlSession session, Room room) {
        System.out.println("RoomDao......");
        return session.insert("room.insertRoom", room);
    }

    //특정 캠핌장 내의 모든 객실 조회
    public static List<Room> findAll(SqlSession session,long campId)
    {
        //단일이 아닌 여러개의 값을 가져와야 하므로 list
        return session.selectList("room.findAll",campId);
    }


    public int deleteRoom(SqlSession session, RoomDto roomDto)
    {
        return session.delete("room.deleteRoom",roomDto);
    }

    public Room findRoom(SqlSession session, RoomDto roomDto)
    {
        return session.selectOne("room.findRoom",roomDto);
    }
}
