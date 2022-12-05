package com.udacity.course3.reviews;

import com.udacity.course3.reviews.entity.CommentDocument;
import com.udacity.course3.reviews.entity.ReviewDocument;
import com.udacity.course3.reviews.repository.ReviewDocumentRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewMongoDBTest {

    @Autowired
    private ReviewDocumentRepository reviewMongoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void mongoTemplateTest() {
        ReviewDocument reviewDocument = new ReviewDocument(5, "review title", "review text", LocalDateTime.now(), 1);
        reviewDocument.setId(1);

        mongoTemplate.save(reviewDocument, "reviews");

        assertFalse(mongoTemplate.findAll(ReviewDocument.class, "reviews").isEmpty());
        assertEquals(1, mongoTemplate.findById(1, ReviewDocument.class).getId());
    }


    @Test
    public void givenReview_whenReviewIsSaved_thenReviewCanBeFoundByReviewId() {
        ReviewDocument reviewDocument = new ReviewDocument(5, "review title", "review text", LocalDateTime.now(), 1);
        reviewDocument.setId(1);

        reviewMongoRepository.save(reviewDocument);

        ReviewDocument expectedReview = mongoTemplate.findById(1, ReviewDocument.class);
        ReviewDocument actualReview = reviewMongoRepository.findById(1).get();

        Assert.assertEquals(expectedReview.getId(), actualReview.getId());
        Assert.assertEquals(expectedReview.getRating(), actualReview.getRating());
        Assert.assertEquals(expectedReview.getReview_text(), actualReview.getReview_text());
        Assert.assertEquals(expectedReview.getProductId(), actualReview.getProductId());
    }

    @Test
    public void givenReview_whenReviewIsSaved_thenReviewCanBeFoundByProductId() {
        ReviewDocument reviewDocument1 = new ReviewDocument(5, "review title", "review text", LocalDateTime.now(), 1);
        reviewDocument1.setId(1);
        ReviewDocument reviewDocument2 = new ReviewDocument(1, "review title", "review text", LocalDateTime.now(), 1);
        reviewDocument2.setId(2);

        reviewMongoRepository.save(reviewDocument1);
        reviewMongoRepository.save(reviewDocument2);

        List<ReviewDocument> list = mongoTemplate.findAll(ReviewDocument.class);
        List<ReviewDocument> expectedList = new ArrayList<>();
        list.forEach(reviewDocument -> {
            if(reviewDocument.getProductId() == 1) {
                expectedList.add(reviewDocument);
            }
        });

        List<ReviewDocument> actualList = reviewMongoRepository.findAllReviewsByProductId(1);

        Assert.assertEquals(expectedList.size(), actualList.size());
        Assert.assertEquals(expectedList.get(0).getProductId(), expectedList.get(0).getProductId());
        Assert.assertEquals(expectedList.get(1).getProductId(), expectedList.get(1).getProductId());
    }

    @Test
    public void givenReview_whenCommentsAreSavedUnderReview_thenCommentsCanBeFoundByReviewId() {
        ReviewDocument reviewDocument = new ReviewDocument(5, "review title", "review text", LocalDateTime.now(), 1);
        reviewDocument.setId(1);

        List<CommentDocument> commentsList = new ArrayList<CommentDocument>();
        commentsList.add(new CommentDocument("comment 1", LocalDateTime.now()));
        commentsList.add(new CommentDocument("comment 2", LocalDateTime.now()));

        reviewDocument.setCommentDocuments(commentsList);
        reviewMongoRepository.save(reviewDocument);

        ReviewDocument expectedReview = mongoTemplate.findById(1, ReviewDocument.class);
        ReviewDocument actualReview = reviewMongoRepository.findById(1).get();

        Assert.assertEquals(expectedReview.getCommentDocuments().size(), actualReview.getCommentDocuments().size());
    }

}