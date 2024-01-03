package com.sh.petking.camp.model.service;

import com.sh.petking.camp.model.dao.CampDao;
import com.sh.petking.camp.model.entity.*;
import com.sh.petking.camp.model.vo.CampVo;
import com.sh.petking.camp.model.vo.CampWithTagVo;
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
    public CampVo findById(long id) {
        SqlSession session = getSqlSession();
        CampVo camp = campDao.findById(session, id);
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

    public List<_CampService> findAllCampService() {
        SqlSession session = getSqlSession();
        List<_CampService> campServices = campDao.findAllCampService(session);
        session.close();
        return campServices;
    }

    public List<CampTag> findAllCampTag() {
        SqlSession session = getSqlSession();
        List<CampTag> campTags = campDao.findAllCampTag(session);
        return campTags;
    }

    public int updateCampDetail(Map<String, List<Object>> param) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            // param -> campWithTags와 campWithSerivce를 꺼내서 모두 지우고 다시 인서트 처리
            Long campId = (Camp) param.get("campId").get(0).getId();
            System.out.println(campId);

            List<Object> campWithTags = param.get("campWithTags");
            List<Object> campWithServices = param.get("campWithServices");

            // tag 전체 삭제하기
            result = campDao.deleteCampTag(session, campId);
            System.out.println("삭제된 태그 수 " + result);
            // tag 다시 인서트하기
            for(Object campWithTag : campWithTags){
                result = campDao.insertCampWithTag(session, (CampWithTag) campWithTag);
            }

            // service 전체 삭제하기
            result = campDao.deleteCampService(session, campId);
            System.out.println("삭제된 태그 수 " + result);
            // tag 다시 인서트하기
            for(Object campWithService : campWithServices){
                result = campDao.insertCampWithService(session, (CampWithService) campWithService);
            }




            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
