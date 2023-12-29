package com.sh.petking.review.model.service;

import com.sh.petking.review.model.dao.ReviewDao;
import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    public List<Review> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<Review> reviews = reviewDao.findAll(session, param);
        session.close();
        return reviews;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = reviewDao.getTotalCount(session);
        session.close();
        return totalCount;
    }
}
