package com.learning.reviewms.Review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByCompanyId(@RequestParam Long companyId) {
        List<Review> reviews = reviewService.getAllReviews(companyId);
        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId){
        Review review = reviewService.getReviewById(reviewId);
        if(review!=null){
            return new ResponseEntity<>(review,HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @RequestBody Review review) {
        Boolean reviewUpdated = reviewService.updateReview(reviewId, review);
        if (reviewUpdated) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

     @PostMapping
     public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        Boolean reviewCreated =  reviewService.createReview(companyId, review);
        if (!reviewCreated) {
            return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Review Added Successfully", HttpStatus.CREATED);
     }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable Long reviewId) {
        Boolean reviewDeleted = reviewService.deleteReviewById(reviewId);
        if (reviewDeleted) {
            return new ResponseEntity<>("Review deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
