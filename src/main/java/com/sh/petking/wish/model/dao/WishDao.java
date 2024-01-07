package com.sh.petking.wish.model.dao;

import com.sh.petking.wish.model.entity.Wish;
import com.sh.petking.wish.model.vo.WishVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class WishDao {
    public List<Wish> findAll(SqlSession session) {
        return session.selectList("wish.findAll");
    }

    public List<Wish> _findByUserId(SqlSession session, String userId) {
        return session.selectList("wish._findByUserId", userId);
    }
    public List<WishVo> findByUserId(SqlSession session, String userId) {
        return session.selectList("wish.findByUserId", userId);
    }

    public List<Wish> findByCampId(SqlSession session, Long campId) {
        return session.selectList("wish.findByCampId", campId);
    }

    public int insertWish(SqlSession session, Wish wish) {
        return session.insert("wish.insertWish", wish);
    }

    public Wish findById(SqlSession session, Long id) {
        return session.selectOne("wish.findById", id);
    }

    public int deleteWish(SqlSession session, Long id) {
        return session.delete("wish.deleteWish", id);
    }

    public int deleteWishByUserId(SqlSession session, String userId) {
        return session.delete("wish.deleteWishByUserId", userId);
    }

    public int deleteWish(SqlSession session, Wish wish) {
        return session.delete("wish.deleteWishByWish", wish);
    }

    public List<Wish> findByUserIdCampId(SqlSession session, Map<String, Object> param) {
        return session.selectList("wish.findByUserIdCampId", param);
    }
}
