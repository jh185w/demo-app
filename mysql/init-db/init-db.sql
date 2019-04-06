DROP DATABASE IF EXISTS demo;
CREATE DATABASE demo;

CREATE TABLE `demo`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `price` DOUBLE(10,2) NOT NULL,
  PRIMARY KEY (`id`));

INSERT INTO `demo`.`product` (`name`,`description`,`price`)
VALUES ('Shirt','Short sleeve shirt made of cotton',5.00);

INSERT INTO `demo`.`product` (`name`,`description`,`price`)
VALUES ('Sunglasses','Black sunglasses with UV protection',12.00);