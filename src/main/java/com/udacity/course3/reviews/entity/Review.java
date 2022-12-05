package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Min(1)
    @Max(5)
    private Integer rating;

    @NotBlank
    private String review_title;

    @NotBlank
    private String review_text;

    @ManyToOne
    private Product product;

    public Review() {}

    public Review(Integer id, Integer rating) {
        this.id = id;
        this.rating = rating;
    }

    public Review(Integer id, Integer rating, String review_title, String review_text, Product product) {
        this.id = id;
        this.rating = rating;
        this.review_title = review_title;
        this.review_text = review_text;
        this.product = product;
    }

    public Review(Integer rating, String review_title, String review_text, Product product) {
        this.rating = rating;
        this.review_title = review_title;
        this.review_text = review_text;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}