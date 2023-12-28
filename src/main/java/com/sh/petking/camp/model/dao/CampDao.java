package com.sh.petking.camp.model.dao;

import com.sh.petking.camp.model.entity.Camp;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class CampDao {
    public List<Camp> findAll(SqlSession session) {
        return session.selectList("camp.findAll");
    }

    public Camp findById(SqlSession session, long id) {
        return session.selectOne("camp.findById", id);
    }

    public int insertCamp(SqlSession session, Camp camp) {
        return session.insert("camp.insertCamp", camp);
    }

    public int updateCamp(SqlSession session, Camp camp) {
        return session.update("camp.updateCamp", camp);
    }

    public int deleteCamp(SqlSession session, Long id) {
        return session.delete("camp.deleteCamp", id);
    }
}
