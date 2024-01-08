package com.sh.petking.user.model.dao;

import com.sh.petking.pet.model.entity.Pet;
import com.sh.petking.user.model.entity.Point;
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

    public int insertUser(SqlSession session, User user) {
        return session.insert("user.insertUser", user);
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

    public List<User> findByName(SqlSession session, String keyword) {
        return session.selectList("user.findByName", keyword);
    }

    public int updateUser(SqlSession session, User user) {
        System.out.println("user dao - "+user);
        return session.update("user.updateUser", user);
    }
    public int updateUserRole(SqlSession session, User user) {
        System.out.println(user);
        return session.update("admin.updateUserRole", user);

    }

    /**
     * 민준씨
     *
     *
     */
    public UserVo findGradeId(SqlSession session, String id) {
        return session.selectOne("user.findGradeId", id);
    }
    public List<Point> findPointAll (SqlSession session, String id){
        return session.selectList("user.findPointAll", id);
    }







    /**
     * 정효씨
     *
     *
     */
    public int userPasswordUpdate(SqlSession session, User user) {
        return session.update("user.userPasswordUpdate", user);
    }

    public int deleteUser(SqlSession session, String id) {
        return session.delete("user.deleteUser", id);
    }

    public User findByEmail(SqlSession session, String email) {
        return session.selectOne("user.findByEmail", email);
    }

    public int insertPet(SqlSession session, Pet pet) {
        return session.insert("pet.insertPet", pet);
    }
}
