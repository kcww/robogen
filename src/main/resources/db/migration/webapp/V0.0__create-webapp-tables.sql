CREATE DATABASE IF NOT EXISTS `webapp`;
USE `webapp`;

CREATE TABLE IF NOT EXISTS `application_user`
(
    `id`              BIGINT NOT NULL AUTO_INCREMENT,
    `version`         INT    NOT NULL,
    `hashed_password` VARCHAR(255) DEFAULT NULL COMMENT 'Hashed Password of the User',
    `name`            VARCHAR(255) DEFAULT NULL COMMENT 'Full Name of the User',
    `profile_picture` MEDIUMBLOB COMMENT 'Profile Picture of the User',
    `username`        VARCHAR(255) DEFAULT NULL COMMENT 'Username of the User',
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `user_roles`
(
    `user_id` BIGINT NOT NULL,
    `roles`   ENUM ('ADMIN','USER') DEFAULT NULL COMMENT 'Role assigned to the User',
    KEY `FK_user_roles_application_user` (`user_id`),
    CONSTRAINT `FK_user_roles_application_user` FOREIGN KEY (`user_id`) REFERENCES `application_user` (`id`) ON DELETE CASCADE
);