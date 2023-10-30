CREATE TABLE products (
  id BIGINT AUTO_INCREMENT NOT NULL,
   name_product VARCHAR(255) NOT NULL,
   unit_measurement VARCHAR(255) NOT NULL,
   unit_price FLOAT NOT NULL,
   category_id BIGINT NULL,
   CONSTRAINT pk_products PRIMARY KEY (id)
);

ALTER TABLE products ADD CONSTRAINT FK_PRODUCTS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES categories (id);