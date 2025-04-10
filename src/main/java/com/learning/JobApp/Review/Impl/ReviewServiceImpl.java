package com.learning.JobApp.Review.Impl;

import com.learning.JobApp.Company.Company;
import com.learning.JobApp.Company.CompanyService;
import com.learning.JobApp.Review.Review;
import com.learning.JobApp.Review.ReviewRepository;
import com.learning.JobApp.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Boolean updateReview(Long companyId, Long reviewId, Review updatedReview) {
        if (companyService.getCompanyById(companyId) != null) {
            updatedReview.setCompany(companyService.getCompanyById(companyId));
            updatedReview.setId(reviewId);
            reviewRepository.save(updatedReview);
            return true;
        }
        return false;
    }

    @Override
    public Boolean deleteReviewById(Long companyId, Long reviewId) {

        if (companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)) {
            List<Review> reviews = reviewRepository.findByCompanyId(companyId);
            Review reviewToDelete = reviewRepository.findById(reviewId).orElse(null);
            Company company = reviewToDelete.getCompany();
            reviewToDelete.setCompany(null);
            company.getReviews().remove(reviewToDelete);
            companyService.updateCompany(company.getId(), company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
