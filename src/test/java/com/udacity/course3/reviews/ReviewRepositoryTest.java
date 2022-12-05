package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.Product;
import com.udacity.course3.reviews.entity.Review;
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
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    public void givenProduct_whenProductHasReview_thenReviewIsFoundByProductId() {
        Product product = new Product("product name 2", "desc", new BigDecimal(100.40));
        productRepository.save(product);

        Review review = new Review( 5,"review title",
                "review text", product);
        reviewRepository.save(review);

        List<Review> reviews = reviewRepository.findAllReviewsByProductId(product.getId());

        assertEquals(1, reviews.size());
        assertTrue(5==reviews.get(0).getRating());
        assertEquals("review title", reviews.get(0).getReview_title());
        assertEquals("review text", reviews.get(0).getReview_text());
        assertEquals(product.getId(), reviews.get(0).getProduct().getId());
    }

}
