package com.sh.petking.club.model.dao;

import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.vo.ClubVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ClubDao {

    public List<Club> findAll(SqlSession session) {
        return session.selectList("club.findAll");
    }

    public ClubVo findById(SqlSession session, Long id) {
        return session.selectOne("club.findById", id);
    }
    public int insertClub(SqlSession session, Club club) {
        return session.insert("club.insertClub", club);
    }

    public int deleteClub(SqlSession session, Long id) {
        return session.delete("club.deleteClub", id);
    }

    public int updateClub(SqlSession session, Club club) {
        return session.update("club.updateClub", club);
    }

    public int insertClubUsers(SqlSession session, ClubUsers clubUsers) {
        return session.insert("club.insertClubUsers", clubUsers);
    }

    public int updateClubViewCount(SqlSession session, Long id) {
        return session.update("board.updateClubViewCount", id);
    }
}
