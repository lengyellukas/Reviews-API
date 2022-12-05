create table product (
    id int auto_increment,
    name varchar(150) NOT NULL unique,
    description varchar(255),
    price decimal(6,2),
    PRIMARY KEY (id));

create table comment (
    id int auto_increment,
    comment_text varchar(500) NOT NULL,
    created_time timestamp default CURRENT_TIMESTAMP,
    review_id int not null,
    PRIMARY KEY (id));

create table review (
    id int auto_increment,
    rating tinyint NOT NULL,
    review_title varchar(150) NOT NULL,
    review_text varchar(1500) NOT NULL,
    created_time timestamp default CURRENT_TIMESTAMP,
    product_id int not null,
    PRIMARY KEY (id));