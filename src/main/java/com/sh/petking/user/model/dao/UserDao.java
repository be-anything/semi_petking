package com.sh.petking.user.model.dao;

import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import java.util.List;

public class UserDao extends HttpServlet {

    public User findById(SqlSession session, String id) {
        return session.selectOne("user.findById", id);
    }

    public List<User> findAll(SqlSession session) {
        return session.selectList("user.findAll");
    }

    public int insertUser(SqlSession session, User user) {
        return session.insert("user.insertUser", user);
    }
}
