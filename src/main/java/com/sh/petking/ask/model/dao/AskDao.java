package com.sh.petking.ask.model.dao;

import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.ask.model.vo.AskVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class AskDao {


    public List<Ask> findAll(SqlSession session)
    {
        System.out.println("AskDao - findAll");
        return session.selectList("ask.findAll");
    }

    public int insertAsk(SqlSession session, Ask ask) {
        System.out.println("AskDao - insertAsk");
        return session.insert("ask.insertAsk",ask);
    }
    public int updateAsk(SqlSession session, AskVo ask) {
        System.out.println("AskDao - updateAsk");
        return session.update("ask.updateAsk",ask);
    }

    public AskVo findById(SqlSession session, long id) {
        return session.selectOne("ask.findById",id);
    }
}
