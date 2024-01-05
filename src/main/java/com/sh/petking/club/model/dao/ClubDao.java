package com.sh.petking.club.model.dao;

import com.sh.petking.club.model.entity.Club;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubDao {

    public List<Club> findAll(SqlSession session) {
        return session.selectList("club.findAll");
    }

    public int insertClub(SqlSession session, Club club) {
        return session.insert("club.insertClub", club);
    }

    public int deleteClub(SqlSession session, long id) {
        return session.delete("club.deleteClub", id);
    }
}
