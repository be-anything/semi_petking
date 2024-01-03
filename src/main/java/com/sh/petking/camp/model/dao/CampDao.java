package com.sh.petking.camp.model.dao;

import com.sh.petking.camp.model.entity.*;
import com.sh.petking.camp.model.vo.CampVo;
import org.apache.commons.fileupload.FileItem;
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

    public List<_CampService> findAllCampService(SqlSession session) {
        return session.selectList("camp.findAllCampService");
    }

    public List<CampTag> findAllCampTag(SqlSession session) {
        return session.selectList("camp.findAllCampTag");
    }

    public int deleteCampTag(SqlSession session, Long campId) {
        return session.delete("camp.deleteCampTag", campId);
    }

    public int insertCampWithTag(SqlSession session, CampWithTag campWithTag) {
        return session.insert("camp.insertCampWithTag", campWithTag);
    }

    public int deleteCampService(SqlSession session, Long campId) {
        return session.delete("camp.deleteCampService", campId);
    }

    public int insertCampWithService(SqlSession session, CampWithService campWithService) {
        return session.insert("camp.insertCampWithService", campWithService);
    }

    public int deleteCampAttach(SqlSession session, Long campId) {
        return session.delete("camp.deleteCampAttach", campId);
    }

    public int insertCampAttach(SqlSession session, CampAttach campAttach) {
        System.out.println("================ dao attach ============");
        System.out.println(campAttach.getCampId());
        System.out.println(campAttach.getCampAttachOriginalName());
        System.out.println(campAttach.getCampAttachRenamedName());

        return session.insert("camp.insertCampAttach", campAttach);
    }
}
