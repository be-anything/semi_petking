package com.sh.petking.review.model.dao;

import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;

import static com.sh.petking.common.SqlSessionTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

public class ReviewDaoTest {
    ReviewDao reviewDao;
    private SqlSession session;
    @BeforeEach
    public void setUp(){
        this.reviewDao = new ReviewDao();
        this.session = getSqlSession();
    }
    @AfterEach
    public void tearDown(){
        this.session.rollback();
        this.session.close();
    }

    @DisplayName("ReviewDao객체는 null이 아니다.")
    @Test
    public void test1(){
        assertThat(reviewDao).isNotNull();
        assertThat(session).isNotNull();
    }

    @DisplayName("리뷰 전체 조회")
    @Test
    public void test2(){

        List<Review> reviews = reviewDao.findAll(session);
        assertThat(reviews)
                .isNotNull();
        System.out.println(reviews);
        reviews.forEach((review) -> {
            assertThat(review.getId()).isNotNull();
            assertThat(review.getUserId()).isNotNull();
            assertThat(review.getCampId()).isNotNull();
            assertThat(review.getBoardAttr()).isNotNull();
            assertThat(review.getReviewTag()).isNotNull();
            assertThat(review.getReviewTitle()).isNotNull();
            assertThat(review.getReviewContent()).isNotNull();
            assertThat(review.getViewCount()).isGreaterThanOrEqualTo(0);
            assertThat(review.getLikeCount()).isGreaterThanOrEqualTo(0);
            assertThat(review.getRegDate()).isNotNull();
        });
    }
    @DisplayName("존재하는 리뷰 한건 조회를 할 수 있습니다.")
    @Test
    public void test3(){
        long id = 34;
        Review review = reviewDao.findById(session, id);
        System.out.println(review);
        assertThat(review).isNotNull();
        assertThat(review.getId()).isNotNull();
        assertThat(review.getUserId()).isNotNull();
        assertThat(review.getCampId()).isNotNull();
        assertThat(review.getBoardAttr()).isNotNull();
        assertThat(review.getReviewTag()).isNotNull();
        assertThat(review.getReviewTitle()).isNotNull();
        assertThat(review.getReviewContent()).isNotNull();
        assertThat(review.getViewCount()).isGreaterThanOrEqualTo(0);
        assertThat(review.getLikeCount()).isGreaterThanOrEqualTo(0);
        assertThat(review.getRegDate()).isNotNull();
    }

    @DisplayName("리뷰 등록")
    @Test
    public void test4(){
        String userId = "goyoung12";
        long campId = 6;
        long boardAttr = 2;
        String reviewTag = "깨끗함,매너타임,친절함";
        String reviewTitle = "테스트입니당";
        String reviewContent = "무조건 테스트입니당";
        Review review = new Review();
        review.setUserId(userId);
        review.setCampId(campId);
        review.setBoardAttr(boardAttr);
        review.setReviewTag(reviewTag);
        review.setReviewTitle(reviewTitle);
        review.setReviewContent(reviewContent);
        System.out.println(review);
        int result = reviewDao.insertReview(session, review);
        assertThat(result).isGreaterThan(0);
        assertThat(review.getId()).isNotNull().isNotZero();
    }
    @DisplayName("리뷰 수정")
    @Test
    public void test5(){
        // 일단 리뷰아디를 찾아와야함 34번 수정할꺼야
        long id = 34;
        Review review = reviewDao.findById(session,id);

        // 수정할내용쓰기
        String newReviewTag = "아름다움,멋있음,이쁨";
        String newReviewTitle = "화이또";
        String newReviewContent = "화이또에 대한 내용입니다.";
        review.setReviewTag(newReviewTag);
        review.setReviewTitle(newReviewTitle);
        review.setReviewContent(newReviewContent);
        System.out.println(review);

        int result = reviewDao.updateReview(session, review);
        assertThat(result).isGreaterThan(0);
        assertThat(review.getId()).isNotNull().isNotZero();
    }
    @DisplayName("리뷰 삭제")
    @Test
    public void test6(){
        // 삭제할 34번 리뷰 가져와야지
        long id = 34;
        Review review = reviewDao.findById(session, id);

        // when
        int result = reviewDao.deleteReview(session, id);
        assertThat(result).isNotZero();
        System.out.println(review);
        // then
        Review review1 = reviewDao.findById(session, id);
        assertThat(review1).isNull();

    }
}
