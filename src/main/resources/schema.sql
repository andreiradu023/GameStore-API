CREATE DATABASE `game_store`;

USE `game_store`;

CREATE TABLE `users`
(
    `id`         BIGINT       NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(40)  NOT NULL,
    `last_name`  VARCHAR(40)  NOT NULL,
    `email`      VARCHAR(100) NOT NULL UNIQUE,
    `phone`      VARCHAR(15)  NOT NULL,
    `address`    VARCHAR(200) NOT NULL,
    `password`   VARCHAR(64)  NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `roles`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(15) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `users_roles`
(
    `user_id` BIGINT NOT NULL,
    `role_id` BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

CREATE TABLE `games`
(
    `id`             BIGINT        NOT NULL AUTO_INCREMENT,
    `title`          VARCHAR(100)  NOT NULL,
    `platform`       VARCHAR(200)  NOT NULL,
    `genre`          VARCHAR(200)  NOT NULL,
    `description`    VARCHAR(4000) NOT NULL,
    `price`          FLOAT         NOT NULL,
    `image`          VARCHAR(200),
    `release_date`   DATE,
    `stock_quantity` INT,
    PRIMARY KEY (`id`)
);

CREATE TABLE `orders`
(
    `id`          BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`     BIGINT,
    `order_date`  DATE,
    `total_price` FLOAT  NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE `order_items`
(
    `id`         BIGINT NOT NULL AUTO_INCREMENT,
    `order_id`   BIGINT,
    `game_id`    BIGINT,
    `quantity`   INT,
    `unit_price` FLOAT,
    `subtotal`   FLOAT AS (unit_price * quantity),
    PRIMARY KEY (`id`),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (game_id) REFERENCES games (id)
);

CREATE TABLE `cart_items`
(
    `id`       BIGINT NOT NULL AUTO_INCREMENT,
    `user_id`  BIGINT,
    `game_id`  BIGINT,
    `quantity` INT,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (game_id) REFERENCES games (id)
);