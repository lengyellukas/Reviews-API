/*
each review is related to one specific product
*/
ALTER TABLE review ADD CONSTRAINT review_product_fk FOREIGN KEY (product_id) REFERENCES product (id);
/*
each comment is related to one specific review
*/
ALTER TABLE comment ADD CONSTRAINT comment_review_fk FOREIGN KEY (review_id) REFERENCES review (id);
/*
review rating can be only from 1 to 5
*/
ALTER TABLE review ADD CONSTRAINT review_rating_check CHECK(rating BETWEEN 1 AND 5);