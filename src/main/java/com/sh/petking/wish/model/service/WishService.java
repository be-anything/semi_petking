package com.sh.petking.wish.model.service;

import com.sh.petking.wish.model.dao.WishDao;
import com.sh.petking.wish.model.entity.Wish;
import org.apache.ibatis.session.SqlSession;

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
}
