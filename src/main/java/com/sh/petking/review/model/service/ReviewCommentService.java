package com.sh.petking.review.model.service;

import com.sh.petking.review.model.dao.ReviewCommentDao;
import com.sh.petking.review.model.entity.ReviewComment;
import org.apache.ibatis.session.SqlSession;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;

public class ReviewCommentService {
    private ReviewCommentDao reviewCommentDao = new ReviewCommentDao();
    public int insertComment(ReviewComment reviewComment) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = reviewCommentDao.insertComment(session, reviewComment);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int deleteComment(Long id) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = reviewCommentDao.deleteComment(session, id);
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }
}
