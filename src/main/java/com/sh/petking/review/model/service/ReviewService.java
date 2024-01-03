package com.sh.petking.review.model.service;

import com.sh.petking.review.model.dao.ReviewDao;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.vo.ReviewVo;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReviewService {
    private ReviewDao reviewDao = new ReviewDao();

    public List<ReviewVo> findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReviewVo> reviews = reviewDao.findAll(session, param);
        session.close();
        return reviews;
    }

    public int getTotalCount() {
        SqlSession session = getSqlSession();
        int totalCount = reviewDao.getTotalCount(session);
        session.close();
        return totalCount;
    }

    public int deleteReview(long id) {
        int result = 0;
        SqlSession session = getSqlSession();
        try {
            result = reviewDao.deleteReview(session, id);
            System.out.println(id);
            session.commit();
        } catch (Exception e){
            session.close();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public ReviewVo findById(Long id) {
        SqlSession session = getSqlSession();
        ReviewVo review = reviewDao.findById(session, id);
        session.close();
        return review;
    }
}
