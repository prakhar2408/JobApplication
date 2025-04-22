package com.learning.reviewms.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Boolean createReview(Long companyId, Review review);

    Review getReviewById(Long reviewId);

    Boolean updateReview(Long reviewId, Review review);

    Boolean deleteReviewById(Long reviewId);
}
