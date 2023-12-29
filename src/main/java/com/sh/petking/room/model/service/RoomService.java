package com.sh.petking.room.model.service;

import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.entity.Room;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

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


    public List<Room> findAll(long campId)
    {

        SqlSession session = getSqlSession();
        //session을 줄테니 db 결과값좀..ㅎ
        List<Room> rooms = roomDao.findAll(session,campId);
        //다썼으면 닫기
        session.close();
        return rooms; //결과값을 리턴
    }


    public int deleteRoom(Room room)//특정 룸 하나
    {
        int result=0;
        SqlSession session = getSqlSession();
        try
        {
            result = roomDao.deleteRoom(session,room);
            session.commit();
        }
        catch(Exception e)
        {
            session.rollback();
            throw e;
        }
        finally
        {
            //어찌됐든간에 세션종료는 필수다.
            session.close();
        }

        return result;
    }
}
