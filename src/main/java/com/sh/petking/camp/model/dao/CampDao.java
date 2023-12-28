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
        System.out.println(camp);
        return session.insert("camp.insertCamp", camp);
    }
}
