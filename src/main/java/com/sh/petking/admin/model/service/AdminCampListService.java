package com.sh.petking.admin.model.service;

import com.sh.petking.admin.model.dao.AdminCampListDao;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.vo.CampVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class AdminCampListService {
    private AdminCampListDao adminCampListDao = new AdminCampListDao();

    public List<CampVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<CampVo> campVos = adminCampListDao.findAll(session,param);
        session.close();
        return campVos;
    }
    public int getTotalCount(){
        SqlSession session = getSqlSession();
        int totalCount = adminCampListDao.getTotalCount(session);
        session.close();
        return totalCount;
    }

    public List<CampVo> findRegistAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<CampVo> campVos = adminCampListDao.findRegistAll(session, param);
        session.close();
        return campVos;
    }

    public int getTotalRegistCount() {
        SqlSession session = getSqlSession();
        int totalCount = adminCampListDao.getTotalRegistCount(session);
        session.close();
        return totalCount;
    }

    public List<CampVo> findDeleteAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<CampVo> campVos = adminCampListDao.findDeleteAll(session, param);
        session.close();
        return campVos;
    }

    public int getTotalDeleteCount() {
        SqlSession session = getSqlSession();
        int totalCount = adminCampListDao.getTotalDeleteCount(session);
        session.close();
        return totalCount;
    }
}
