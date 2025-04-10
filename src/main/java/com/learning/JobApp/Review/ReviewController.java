package com.learning.JobApp.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getReviewsByCompanyId(@PathVariable Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId,reviewId);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review review) {
        Boolean reviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if (reviewUpdated) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @PostMapping("/reviews")
     public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        Boolean reviewCreated =  reviewService.createReview(companyId, review);
        if (!reviewCreated) {
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
     }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Boolean reviewDeleted = reviewService.deleteReviewById(companyId, reviewId);
        if (reviewDeleted) {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
