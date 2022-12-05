package com.udacity.course3.reviews.entity;

import java.time.LocalDateTime;

public class CommentDocument {

    private String comment_text;
    private LocalDateTime created_time;

    public CommentDocument() {}

    public CommentDocument(String comment_text, LocalDateTime created_time) {
        this.comment_text = comment_text;
        this.created_time = created_time;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public LocalDateTime getCreated_time() {
        return created_time;
    }

    public void setCreated_time(LocalDateTime created_time) {
        this.created_time = created_time;
    }
}