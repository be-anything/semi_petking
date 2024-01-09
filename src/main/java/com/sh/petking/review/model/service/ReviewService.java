package com.sh.petking.review.model.service;

import com.sh.petking.board.model.entity.Attachment;
import com.sh.petking.board.model.entity.BoardAttach;
import com.sh.petking.review.model.dao.ReviewDao;
import com.sh.petking.review.model.vo.ReviewVo;
import com.sh.petking.review.model.vo._ReviewVo;
import com.sh.petking.user.model.entity.Point;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
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
    public List<_ReviewVo> _findAll(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<_ReviewVo> reviews = reviewDao._findAll(session, param);
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

    public ReviewVo findById(Long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        ReviewVo review = new ReviewVo();
        int result = 0;
        try {
            if(!hasRead)
            result = reviewDao.updateReviewReadCount(session, id);

            review = reviewDao.findById(session, id);
            List<ReviewVo> reviewVos = reviewDao.findVoByReviewId(session, id);
            review.setReviewContent(review.getReviewContent());

            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return review;
    }

    public int insertReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int result = 0;
        ReviewVo review = (ReviewVo) param.get("review");
        List<Attachment> attachments = (List<Attachment>) param.get("attachments");

        try {
            // review 테이블에 저장
            StringBuilder csvTag = new StringBuilder();
            for(String tag : review.getTags()){
                csvTag.append(tag).append(",");
            }
            review.setReviewTag(String.valueOf(csvTag));
            System.out.println(review.getReviewTag());


            result = reviewDao.insertReview(session, review);
            // 방금 작성된 리뷰의 id 가져오기
            System.out.println(review.getId());

            // attach 테이블에 저장
            // board-attach 테이블에 저장하기 위해 값 가져와서 셋팅
            List<BoardAttach> boardAttaches = new ArrayList<>();
            if(!attachments.isEmpty()){
                for (Attachment attachment: attachments){
                    result = reviewDao.insertAttachment(session, attachment);
                    System.out.println(attachment);
                    BoardAttach boardAttach = new BoardAttach(0L, attachment.getId(), review.getId(), 2);
                    boardAttaches.add(boardAttach);
                    // board-attach 테이블에 저장하기
                    result = reviewDao.insertBoardAttach(session, boardAttach);
                }
            }

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        System.out.println(review.getReviewTag());
        return result;
    }

    public List<ReviewVo> findByUserId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReviewVo> reviews = reviewDao.findByUserId(session, param);
        session.close();
        return reviews;
    }

    public int getTotalUserReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reviewDao.getTotalUserReview(session, param);
        session.close();
        return totalCount;
    }

    public ReviewVo findByIdWithAttach(Long id, boolean hasRead) {
        SqlSession session = getSqlSession();
        ReviewVo review = new ReviewVo();
        int result = 0;
        try {
            if(!hasRead){
                result = reviewDao.updateReviewReadCount(session, id);
            }
            review = reviewDao.findByIdWithAttach(session, id);
            List<ReviewVo> reviewVos = reviewDao.findVoByReviewId(session, id);
            review.setReviewContent(review.getReviewContent());
            session.commit();
        } catch (Exception e){
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return review;
    }

    public int getTotalReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reviewDao.getTotalReview(session, param);
        session.close();
        return totalCount;
    }

    public int deleteReviewAttach(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = reviewDao.deleteReviewAttach(session, param);
            result = reviewDao.deleteReviewAttachBridge(session, param);
            System.out.println(result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public int updateReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int result = 0;
        ReviewVo review = (ReviewVo) param.get("review");
        List<Attachment> attachments = (List<Attachment>) param.get("attachments");

        try {
            // review 테이블에 저장
            StringBuilder csvTag = new StringBuilder();
            for(String tag : review.getTags()){
                csvTag.append(tag).append(",");
            }
            review.setReviewTag(String.valueOf(csvTag));
            System.out.println(review.getReviewTag());

            result = reviewDao.updateReview(session, review);

            // attach 테이블에 저장
            // board-attach 테이블에 저장하기 위해 값 가져와서 셋팅
            List<BoardAttach> boardAttaches = new ArrayList<>();
            if(!attachments.isEmpty()){
                for (Attachment attachment: attachments){
                    result = reviewDao.insertAttachment(session, attachment);
                    System.out.println(attachment);
                    BoardAttach boardAttach = new BoardAttach(0L, attachment.getId(), review.getId(), 2);
                    boardAttaches.add(boardAttach);
                    // board-attach 테이블에 저장하기
                    result = reviewDao.insertBoardAttach(session, boardAttach);
                }
            }

            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        System.out.println(review.getReviewTag());
        return result;
    }

    public List<_ReviewVo> _findPhotoReview() {
        SqlSession session = getSqlSession();
        List<_ReviewVo> reviews = reviewDao._findPhotoReview(session);
        session.close();
        return reviews;
    }

    public int insertPoint(Point point) {
        SqlSession session = getSqlSession();
        int result = 0;
        try {
            result = reviewDao.insertPoint(session, point);
            System.out.println(result);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    public List<ReviewVo> findPhotoReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReviewVo> reviews = reviewDao.findPhotoReview(session, param);
        session.close();
        return reviews;
    }

    public int getTotalCampReview(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        int totalCount = reviewDao.getTotalCampReview(session, param);
        session.close();
        return totalCount;
    }

    public List<ReviewVo> findByCampId(Map<String, Object> param) {
        SqlSession session = getSqlSession();
        List<ReviewVo> reviews = reviewDao.findByCampId(session, param);
        session.close();
        return reviews;
    }

}
