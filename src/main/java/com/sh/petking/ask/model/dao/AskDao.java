package com.sh.petking.ask.model.dao;

import com.sh.petking.ask.model.entity.Ask;
import com.sh.petking.ask.model.vo.AskVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

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

    public int getTotalUserAsk(SqlSession session, Map<String, Object> param) {
        return session.selectOne("ask.getTotalUserAsk", param);
    }

    public List<Ask> findByUserId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("ask.findByUserId", param, rowBounds);
    }

    public int getTotalCampAsk(SqlSession session, Map<String, Object> param) {
        return session.selectOne("ask.getTotalCampAsk", param);
    }

    public List<Ask> findByCampId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("ask.findByCampId", param, rowBounds);
    }
}
