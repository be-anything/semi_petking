package com.sh.petking.room.model.service;

import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import org.apache.ibatis.session.SqlSession;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class RoomService
{
    private RoomDao roomDao = new RoomDao();

    public int insertRoom(Room room) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = roomDao.insertRoom(session, room);
            session.commit();
        }
        catch (Exception e)
        {
            session.rollback();
            throw e; //예외던지기
        }
        finally
        {
            session.close();
        }
        System.out.println(result);
        return result;
    }



}
