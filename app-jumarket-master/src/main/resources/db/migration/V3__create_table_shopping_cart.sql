CREATE TABLE shopping_cart (
id BIGINT AUTO_INCREMENT NOT NULL,
 quantity_items INT NOT NULL,
 sale_price FLOAT NOT NULL,
 payment_type VARCHAR(255) NULL,
 CONSTRAINT pk_shopping_cart PRIMARY KEY (id)
);