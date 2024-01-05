package com.sh.petking.room.model.service;

import com.sh.petking.room.model.dao.RoomDao;
import com.sh.petking.room.model.dto.RoomDto;
import com.sh.petking.room.model.entity.Room;
import com.sh.petking.room.model.entity.RoomAttach;
import com.sh.petking.room.model.vo.RoomVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class RoomService
{
    private RoomDao roomDao = new RoomDao();


    public int insertRoom(RoomVo room)
    {
        int result = 0;
        SqlSession session = getSqlSession();

        try
        {
            //board + attach 둘 다 insert하고 commit or rollback 처리 해야함
            result = roomDao.insertRoom(session,room);

            System.out.println("room get id())" + room.getId());
            //attach 테이블에 등록
            List<RoomAttach> attachments = room.getRoomAttachs();
            if(!attachments.isEmpty())
            {
                for(RoomAttach attach : attachments)
                {
                    attach.setRoomId(room.getId()); //fk로 사용할 객실 id
                    result = roomDao.insertAttachment(session,attach);

                    //attachment 테이블삭제
                    List<Long> delFiles = room.getDelFiles();
                    if(!delFiles.isEmpty())
                    {
                        System.out.println("지울 첨부파일이 있다........");
                        for(Long id : delFiles)
                            result = roomDao.deleteAttachment(session,id);
                    }
                }
            }


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


    //페이징 추가한 findAll
    public List<RoomVo> findAll(Map<String, Object> param)
    {
        SqlSession session = getSqlSession();
        List<RoomVo> rooms = roomDao.findAll(session,param);
        session.close();
        return rooms; //결과값을 리턴
    }


    public int deleteRoom(RoomVo room)//특정 룸 하나
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

    public Room findRoom(RoomDto roomDto)
    {
        SqlSession session = getSqlSession();
        Room room = roomDao.findRoom(session,roomDto);
        session.close();

        return room;
    }
    public int getTotalCount()
    {
        SqlSession session = getSqlSession();
        int totalCount = roomDao.getToTalCount(session);
        session.close();
        return totalCount;
    }
    
    public RoomVo findById (long id) {
        SqlSession session = getSqlSession();
        RoomVo room = roomDao.findById(session,id);
        session.close();
        return room;
    }

    //0105 캠핑장 아이디로 객실 들 찾기
    public List<RoomVo> findByCampId(long id) {
        SqlSession session = getSqlSession();
        List<RoomVo> room = roomDao.findByCampId(session,id);
        session.close();
        return room;
    }

    //0103 객실 수정 service 단 코드
    public int updateRoom(RoomVo room) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            // board테이블 수정
            result = roomDao.updateRoom(session, room);

            // attachment테이블 삭제
            List<Long> delFiles = room.getDelFiles();
            if (!delFiles.isEmpty()) {
                for (Long id : delFiles) {
                    result = roomDao.deleteAttachment(session, id);
                }
            }

            // attachment테이블 등록
            List<RoomAttach> attachments = room.getRoomAttachs();
            if (!attachments.isEmpty()) {
                for (RoomAttach attach : attachments) {
                    attach.setRoomId(room.getId()); // fk 등록
                    result = roomDao.insertAttachment(session, attach);
                }
            }
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
