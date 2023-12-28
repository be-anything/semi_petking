package com.sh.petking.review.model.dao;

import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ReviewDao {
    public List<Review> findAll(SqlSession session) {
        return session.selectList("review.findAll");
    }
}
