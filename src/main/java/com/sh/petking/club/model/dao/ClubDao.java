package com.sh.petking.club.model.dao;

import com.sh.petking.board.model.vo.BoardVo;
import com.sh.petking.club.model.entity.Club;
import com.sh.petking.club.model.entity.ClubUsers;
import com.sh.petking.club.model.vo.ClubVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ClubDao {

    public List<Club> findAll(SqlSession session) {
        return session.selectList("club.findAll");
    }
    public ClubVo findById(SqlSession session, long id) {
        return session.selectOne("club.findById", id);
    }
    public int insertClub(SqlSession session, Club club) {
        return session.insert("club.insertClub", club);
    }
    public int deleteClub(SqlSession session, long id) {
        return session.delete("club.deleteClub", id);
    }

    public int updateClub(SqlSession session, Club club) {
        return session.update("club.updateClub", club);
    }

    public int insertClubUsers(SqlSession session, ClubUsers clubUsers) {
        return session.insert("club.insertClubUsers", clubUsers);
    }

    public int updateClubViewCount(SqlSession session, long id) {
        return session.update("club.updateClubViewCount", id);
    }

    public ClubUsers findByUserId(SqlSession session, String userId) {
        return session.selectOne("club.findByUserId", userId);
    }

    public List<ClubUsers> findAllClubUsers(SqlSession session) {
        return session.selectList("club.findAllClubUsers");
    }
}
