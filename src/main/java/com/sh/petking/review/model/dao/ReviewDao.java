package com.sh.petking.review.model.dao;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.board.model.vo.AttachmentVo;
import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.review.model.vo._ReviewVo;
import com.sh.petking.user.model.entity.Point;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

public class ReviewDao {
    public List<ReviewVo> findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("review.findAll", param, rowBounds);
    }
    public List<Review> findAll(SqlSession session){
        return session.selectList("review.findAll");
    }

    public int insertReview(SqlSession session, Review review) {
        return session.insert("review.insertReview", review);
    }

    public int updateReview(SqlSession session, Review review) {
        return session.update("review.updateReview", review);
    }

    public int deleteReview(SqlSession session, String userId) {
        System.out.println(userId);
        return session.delete("review.deleteReview", userId);
    }
    public int deleteReview(SqlSession session, long id) {
        return session.delete("review.deleteReview", id);
    }
    public int getTotalCount(SqlSession session) {
        return session.selectOne("review.getTotalCount");
    }

    public ReviewVo findByIdWithAttach(SqlSession session, Long id) {
        return session.selectOne("review.findByIdWithAttach", id);
    }
    public ReviewVo findById(SqlSession session, Long id) {
        return session.selectOne("review.findById", id);
    }

    public int updateReviewReadCount(SqlSession session, Long id) {
        return session.update("review.updateReviewReadCount", id);
    }

    public List<ReviewVo> findVoByReviewId(SqlSession session, Long id) {
        return session.selectList("review.findVoByReviewId", id);
    }

    public int insertAttachment(SqlSession session, Attachment attachment) {
        return session.insert("review.insertAttachment", attachment);
    }

    public int insertBoardAttach(SqlSession session, BoardAttach boardAttach) {
        return session.insert("review.insertBoardAttach", boardAttach);
    }

    public List<ReviewVo> findByUserId(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("review.findByUserId", param, rowBounds);
    }

    public int getTotalUserReview(SqlSession session, Map<String, Object> param) {
        return session.selectOne("review.getTotalUserReview", param);
    }

    public int getTotalReview(SqlSession session, Map<String, Object> param) {
        return session.selectOne("review.getTotalReview", param);
    }

    public int deleteReviewAttach(SqlSession session, Map<String, Object> param) {
        return session.delete("review.deleteReviewAttach", param);
    }

    public int deleteReviewAttachBridge(SqlSession session, Map<String, Object> param) {
        return session.delete("review.deleteReviewAttachBridge", param);
    }

    public List<ReviewVo> findPhotoReview(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("review.findPhotoReview", param, rowBounds);
    }

    public List<_ReviewVo> _findAll(SqlSession session, Map<String, Object> param) {
        int page = (int) param.get("page");
        int limit = (int) param.get("limit");
        int offset = (page - 1) * limit;
        RowBounds rowBounds = new RowBounds(offset, limit);
        return session.selectList("review._findAll", param, rowBounds);
    }

    public List<_ReviewVo> _findPhotoReview(SqlSession session) {
        return session.selectList("review.findPhotoReview");
    }

    public int insertPoint(SqlSession session, Point point) {
        return session.insert("review.insertPoint", point);
    }
}
