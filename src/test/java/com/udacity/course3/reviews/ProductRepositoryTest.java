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
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Test
    public void givenProduct_thenProductIsFoundById() {
        Product product = new Product("product name", "desc", new BigDecimal(100.40));
        productRepository.save(product);

        Product actualProduct = productRepository.findById(product.getId()).get();

        assertNotNull(actualProduct);
        assertEquals(product.getId(), actualProduct.getId());
        assertEquals("product name", actualProduct.getName());
        assertEquals("desc", actualProduct.getDescription());
    }
}
