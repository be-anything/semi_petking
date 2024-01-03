package com.sh.petking.wish.model.service;

import com.sh.petking.wish.model.dao.WishDao;
import com.sh.petking.wish.model.entity.Wish;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class WishService {
    private WishDao wishDao = new WishDao();
    public int insertWish(Wish wish) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = wishDao.insertWish(session, wish);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteWish(Wish wish) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = wishDao.deleteWish(session, wish);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<Wish> findByUserId(String userId) {
        SqlSession session = getSqlSession();
        List<Wish> wishes = wishDao.findByUserId(session, userId);
        session.close();
        return wishes;
    }

    public List<Wish> findByUserIdCampId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Wish> wishes = wishDao.findByUserIdCampId(session, param);
        session.close();
        return wishes;
    }
}
