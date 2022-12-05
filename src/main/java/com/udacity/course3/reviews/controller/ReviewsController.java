package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.ProductRepository;
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
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewDocumentRepository reviewDocumentRepository;
    /**
     * Creates a review for a product which is persisted in both - MySQL and MongoDB
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<ReviewDocument> createReviewForProduct(@PathVariable("productId") Integer productId,
                                                         @RequestBody @Valid Review review) {
        Optional<Product> optionalProduct =  productRepository.findById(productId);

        if(!optionalProduct.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //get the product
        Product product = optionalProduct.get();
        //set product
        review.setProduct(product);

        //save product - MySQL
        reviewRepository.save(review);

        //get the timestamp
        LocalDateTime created_time = LocalDateTime.now();
        //create review for MongoDB
        ReviewDocument reviewDocument = new ReviewDocument(
                review.getRating(),
                review.getReview_title(),
                review.getReview_text(),
                created_time,
                review.getProduct().getId()
        );
        //set id to match id in MySQL
        reviewDocument.setId(review.getId());
        //save to MongoDB
        reviewDocumentRepository.save(reviewDocument);

        return new ResponseEntity(reviewDocument, HttpStatus.OK);
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDocument>> listReviewsForProduct(@PathVariable("productId") Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        //retrieve reviews from MongoDB
        List<ReviewDocument> reviewList = reviewDocumentRepository.findAllReviewsByProductId(productId);

        return new ResponseEntity(reviewList, HttpStatus.OK);
    }
}