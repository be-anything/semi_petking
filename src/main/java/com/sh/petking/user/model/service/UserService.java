package com.sh.petking.user.model.service;

import com.sh.petking.user.model.dao.UserDao;
import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.vo.UserVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class UserService {
    private UserDao userDao = new UserDao();

    public User findById(String id) {
        SqlSession session = getSqlSession();
        User user = userDao.findById(session, id);
        session.close();
        return user;
    }

    public List<UserVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<UserVo> users = userDao.findAll(session, param);
        session.close();
        return users;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = userDao.getTotalCount(session);
        session.close();
        return getTotalCount();
    }
}
