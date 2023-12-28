package com.sh.petking.review;

import com.sh.petking.review.model.entity.Review;
import com.sh.petking.review.model.service.ReviewService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewServiceTest {
    ReviewService reviewService;

    @BeforeEach
    public void beforeEach(){
        this.reviewService = new ReviewService();
    }

    @DisplayName("ReviewService객체는 null이 아니다.")
    @Test
    public void test1(){
        assertThat(reviewService).isNotNull();
    }

    @DisplayName("리뷰 전체 조회")
    @Test
    public void test2(){
        List<Review> reviews = reviewService.findAll();
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
