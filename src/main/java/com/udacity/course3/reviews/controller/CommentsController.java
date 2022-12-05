package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.CommentDocument;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewDocumentRepository reviewDocumentRepository;
    /**
     * Creates a comment for a review in MySQL and MongoDB.
     * If review does not exist it returns a 404
     **
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<CommentDocument> createCommentForReview(@PathVariable("reviewId") Integer reviewId,
                                                    @RequestBody @Valid Comment comment) {

        //get Review from MySQL
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        //get ReviewDocument from MongoDB
        Optional<ReviewDocument> optionalReviewDocument = reviewDocumentRepository.findById(reviewId);
        if(!optionalReview.isPresent() || !optionalReviewDocument.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        Review review = optionalReview.get();
        comment.setReview(review);

        //save comment to MySQL
        commentRepository.save(comment);

        ReviewDocument reviewDocument = optionalReviewDocument.get();

        //create new comment document
        LocalDateTime created_time = LocalDateTime.now();
        CommentDocument commentDocument = new CommentDocument(
                comment.getComment_text(),
                created_time
        );

        //add a comment to existing review document
        reviewDocument.getCommentDocuments().add(commentDocument);

        //save the comment to MongoDB
        reviewDocumentRepository.save(reviewDocument);
        return new ResponseEntity(commentDocument, HttpStatus.OK);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<CommentDocument>> listCommentsForReview(@PathVariable("reviewId") Integer reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        Optional<ReviewDocument> optionalReviewDocument = reviewDocumentRepository.findById(reviewId);

        if(!optionalReview.isPresent() || !optionalReviewDocument.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(reviewDocumentRepository.findById(reviewId).get().getCommentDocuments(), HttpStatus.OK);
    }
}