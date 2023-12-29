package com.sh.petking.review.model.dao;

import com.sh.petking.review.model.entity.Review;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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


}
