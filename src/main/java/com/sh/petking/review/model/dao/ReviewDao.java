package com.sh.petking.review.model.dao;

import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ReviewDao {
    public List<Review> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("review.findAll", param, rowBounds);
    }
    public List<Review> findAll(SqlSession session){
        return session.selectList("review.findAll");
    }
    public Review findById(SqlSession session, Long id) {
        return session.selectOne("review.findById", id);
    }

    public int insertReview(SqlSession session, Review review) {
        return session.insert("review.insertReview", review);
    }

    public int updateReview(SqlSession session, Review review) {
        return session.update("review.updateReview", review);
    }

    public int deleteReview(SqlSession session, long id) {
        return session.delete("review.deleteReview", id);
    }

    public int getTotalCount(SqlSession session) {
        return session.selectOne("review.getTotalCount");
    }
}
