package com.udacity.course3.reviews.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String comment_text;

    @ManyToOne
    private Review review;

    public Comment() {}

    public Comment(Integer id) {
        this.id = id;
    }

    public Comment(Integer id, String comment_text, Review review) {
        this.id = id;
        this.comment_text = comment_text;
        this.review = review;
    }

    public Comment(String comment_text, Review review) {
        this.comment_text = comment_text;
        this.review = review;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }
}