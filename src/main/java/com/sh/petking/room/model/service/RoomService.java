package com.sh.petking.room.model.service;

import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.dto.RoomDto;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.vo.RoomVo;
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


    public List<RoomVo> findAll()
    {
        SqlSession session = getSqlSession();
        List<RoomVo> rooms = roomDao.findAll(session);
        session.close();
        return rooms; //결과값을 리턴
    }


    public int deleteRoom(RoomDto roomDto)//특정 룸 하나
    {
        int result=0;
        SqlSession session = getSqlSession();
        try
        {
            result = roomDao.deleteRoom(session,roomDto);
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

    public Room findRoom(RoomDto roomDto)
    {
        SqlSession session = getSqlSession();
        Room room = roomDao.findRoom(session,roomDto);
        session.close();

        return room;

    }
}
