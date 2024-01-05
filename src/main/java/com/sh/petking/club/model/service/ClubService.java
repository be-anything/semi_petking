package com.sh.petking.club.model.service;

import com.sh.petking.club.model.dao.ClubDao;
import com.sh.petking.club.model.entity.Club;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ClubService {

    private ClubDao clubDao = new ClubDao();

    public List<Club> findAll() {
        SqlSession session = getSqlSession();
        List<Club> clubs = clubDao.findAll(session);
        session.close();
        return clubs; //결과값을 리턴
    }

    public int insertClub(Club club) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.insertClub(session, club);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteClub(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = clubDao.deleteClub(session, id);
            session.commit();
        }
        catch(Exception e){
            session.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }
}
