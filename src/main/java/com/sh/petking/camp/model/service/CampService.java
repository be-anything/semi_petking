package com.sh.petking.camp.model.service;

import com.sh.petking.camp.model.dao.CampDao;
import com.sh.petking.camp.model.entity.Camp;
import com.sh.petking.camp.model.vo.CampVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class CampService {
    private CampDao campDao = new CampDao();
    
    // 캠핑장 전체 조회
    public List<Camp> findAll() {
        SqlSession session = getSqlSession();
        List<Camp> camps = campDao.findAll(session);
        session.close();
        return camps;
    }
    // 캠핑장 1개 조회 - id
    public Camp findById(long id) {
        SqlSession session = getSqlSession();
        Camp camp = campDao.findById(session, id);
        session.close();
        return camp;
    }

    public int insertCamp(Camp camp) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = campDao.insertCamp(session, camp);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int updateCamp(Camp camp) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = campDao.updateCamp(session, camp);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteCamp(Long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = campDao.deleteCamp(session, id);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int getTotalCount(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = campDao.getTotalCount(session, param);
        session.close();
        return totalCount;
    }

    public List<CampVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<CampVo> camps = campDao.findAll(session, param);
        session.close();
        return camps;
    }
}
