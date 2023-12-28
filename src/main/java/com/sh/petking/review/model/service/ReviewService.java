package com.sh.petking.review.model.service;

import com.sh.petking.review.model.dao.ReviewDao;
import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    public List<Review> findAll() {
        SqlSession session = getSqlSession();
        List<Review> reviews = reviewDao.findAll(session);
        session.close();
        return reviews;
    }
}
