package com.udacity.course3.reviews.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("reviews")
public class ReviewDocument {

    @Id
    private Integer id;
    private Integer rating;
    private String review_title;
    private String review_text;
    private List<CommentDocument> commentDocuments = new ArrayList<>();
    private Integer productId;
    private LocalDateTime created_time;

    public ReviewDocument() {}

    public ReviewDocument(Integer rating, String review_title, String review_text, LocalDateTime created_time, Integer productId) {
        this.rating = rating;
        this.review_title = review_title;
        this.review_text = review_text;
        this.created_time = created_time;
        this.productId = productId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getReview_title() {
        return review_title;
    }

    public void setReview_title(String review_title) {
        this.review_title = review_title;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public List<CommentDocument> getCommentDocuments() {
        return commentDocuments;
    }

    public void setCommentDocuments(List<CommentDocument> commentDocuments) {
        this.commentDocuments = commentDocuments;
    }

    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public LocalDateTime getCreated_time() {
        return created_time;
    }

}
