package com.sh.petking.user.model.dao;

import com.sh.petking.user.model.entity.User;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;

public class UserDao extends HttpServlet {

    public User findById(SqlSession session, String id) {
        return session.selectOne("user.findById", id);
    }
}
