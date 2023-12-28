package com.sh.petking.room.model.dao;

import com.sh.petking.room.model.entity.Room;
import org.apache.ibatis.session.SqlSession;


//1228객실 추가
public class RoomDao
{

    public int insertRoom(SqlSession session, Room room) {
        System.out.println("RoomDao......");
        return session.insert("room.insertRoom", room);
    }
}
