package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Comment;
import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
import com.udacity.course3.reviews.repository.CommentRepository;
import com.udacity.course3.reviews.repository.ProductRepository;
import com.udacity.course3.reviews.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    public void givenReview_whenReviewHasComment_thenCommentIsFoundByReviewId() {
        Product product = new Product("product name 3", "desc", new BigDecimal(100.40));
        productRepository.save(product);

        Review review = new Review( 5,"review title",
                "review text", product);
        reviewRepository.save(review);

        Comment comment = new Comment("comment", review);
        commentRepository.save(comment);

        List<Comment> commentsList = commentRepository.findAllCommentsByReview(review);

        assertEquals(1, commentsList.size());
        assertEquals("comment", commentsList.get(0).getComment_text());
        assertEquals(review.getId(), commentsList.get(0).getReview().getId());
    }
}
