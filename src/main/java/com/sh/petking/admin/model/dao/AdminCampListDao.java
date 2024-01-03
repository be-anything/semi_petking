package com.sh.petking.admin.model.dao;

import com.sh.petking.camp.model.vo.CampVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class AdminCampListDao {

    public List<CampVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("admin.findAll", param, rowBounds);
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("admin.getTotalCount");
    }

    public List<CampVo> findRegistAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("camp.findRegistAll", param, rowBounds);
    }
}
