package com.sh.petking.camp.model.dao;

import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.service.CampService;
import com.sh.petking.camp.model.vo.CampVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class CampDao {
    public List<Camp> findAll(SqlSession session) {
        return session.selectList("camp.findAll");
    }

    public CampVo findById(SqlSession session, long id) {
        return session.selectOne("camp.findById", id);
    }

    public int insertCamp(SqlSession session, Camp camp) {
        return session.insert("camp.insertCamp", camp);
    }

    public int updateCamp(SqlSession session, Camp camp) {
        return session.update("camp.updateCampInfo", camp);
    }

    public int deleteCamp(SqlSession session, Long id) {
        return session.delete("camp.deleteCamp", id);
    }

    public int getTotalCount(SqlSession session, Map<String, Object> param) {
        return session.selectOne("camp.getTotalCount", param);
    }

    public List<CampVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("camp.findAll", param, rowBounds);
    }

    public List<CampService> findAllCampService(SqlSession session) {
        return session.selectList("camp.findAllCampService");
    }
}
