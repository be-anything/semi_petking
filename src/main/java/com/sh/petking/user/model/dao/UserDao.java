package com.sh.petking.user.model.dao;

import com.sh.petking.user.model.entity.User;
import com.sh.petking.user.model.vo.UserVo;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServlet;
import java.util.List;
import java.util.Map;

public class UserDao extends HttpServlet {

    public User findById(SqlSession session, String id) {
        return session.selectOne("user.findById", id);
    }
    public List<User> findAll(SqlSession session) {
        return session.selectList("user.findAll");
    }

    public List<UserVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("user.findAll", param, rowBounds);
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("user.getTotalCount");
    }


    public int updateUserRole(SqlSession session, User user) {
        System.out.println(user);
        return session.update("admin.updateUserRole", user);
    }
}
