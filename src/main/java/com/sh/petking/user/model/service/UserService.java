package com.sh.petking.user.model.service;

import com.sh.petking.user.model.dao.UserDao;
import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class UserService {
    private UserDao userDao = new UserDao();

    public User findById(String id) {
        SqlSession session = getSqlSession();
        User user = userDao.findById(session, id);
        session.close();
        return user;
    }

    public int insertUser(User user) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = userDao.insertUser(session, user);
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
