CREATE DATABASE IF NOT EXISTS anotheronetestspringdb;
USE anotheronetestspringdb;

-- Table structure for table `Activity`
DROP TABLE IF EXISTS `Activity`;

CREATE TABLE `Activity`
(
    `id`          int NOT NULL,
    `description` TEXT DEFAULT NULL,
    `duration`    int NOT NULL,
    `endTime`     datetime     DEFAULT NULL,
    `name`        TEXT DEFAULT NULL,
    `startTime`   datetime     DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    `type_id`     int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Table structure for table `ActivityRequest`
DROP TABLE IF EXISTS `ActivityRequest`;
CREATE TABLE `ActivityRequest`
(
    `id`          int NOT NULL,
    `action`      varchar(255) DEFAULT NULL,
    `status`      varchar(255) DEFAULT NULL,
    `activity_id` int          DEFAULT NULL,
    `user_id`     int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Table structure for table `Activity_User`
DROP TABLE IF EXISTS `Activity_User`;
CREATE TABLE `Activity_User`
(
    `activities_id` int NOT NULL,
    `users_id`      int NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Table structure for table `Authority`
DROP TABLE IF EXISTS `Authority`;
CREATE TABLE `Authority`
(
    `id`        int NOT NULL,
    `authority` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Dumping data for table `Authority`
LOCK
    TABLES `Authority` WRITE;
INSERT INTO `Authority`
VALUES (1, 'USER'),
       (2, 'ADMIN');
UNLOCK
    TABLES;

-- Table structure for table `Authority_User`
DROP TABLE IF EXISTS `Authority_User`;
CREATE TABLE `Authority_User`
(
    `authorities_id` int NOT NULL,
    `users_id`       int NOT NULL,
    PRIMARY KEY (`authorities_id`, `users_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

-- Table structure for table `TypeOfActivity`
DROP TABLE IF EXISTS `TypeOfActivity`;
CREATE TABLE `TypeOfActivity`
(
    `id`   int NOT NULL,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- Table structure for table `User`
DROP TABLE IF EXISTS `User`;
CREATE TABLE `User`
(
    `id`        int NOT NULL,
    `age`       int NOT NULL,
    `contact`   varchar(255) DEFAULT NULL,
    `firstName` varchar(255) DEFAULT NULL,
    `gender`    varchar(255) DEFAULT NULL,
    `lastName`  varchar(255) DEFAULT NULL,
    `password`  varchar(255) DEFAULT NULL,
    `username`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;


-- Foreign keys

ALTER TABLE `Activity` ADD CONSTRAINT `FK1iqps6vtmitn4mkfajeibb2cl` FOREIGN KEY (`type_id`) REFERENCES
    `TypeOfActivity` (`id`);

ALTER TABLE `ActivityRequest` ADD CONSTRAINT `FKic30i6l75nf4oepiqk6mpclq` FOREIGN KEY (`activity_id`) REFERENCES
    `Activity` (`id`);
ALTER TABLE `ActivityRequest` ADD CONSTRAINT `FKp27w21fo4vi5ru89b14fm9cce` FOREIGN KEY (`user_id`) REFERENCES
    `User` (`id`);


ALTER TABLE `Activity_User` ADD CONSTRAINT `FKg74vslofijh7i5ex60nv74h0b` FOREIGN KEY (`users_id`) REFERENCES
    `User` (`id`);
ALTER TABLE `Activity_User` ADD CONSTRAINT `FKohi8didccadsv429kbsnp97aa` FOREIGN KEY (`activities_id`) REFERENCES
    `Activity` (`id`);

ALTER TABLE `Authority_User` ADD CONSTRAINT `FK9ywteox02tr96ft8h96gt3cuv` FOREIGN KEY (`users_id`) REFERENCES
    `User` (`id`);
ALTER TABLE `Authority_User` ADD CONSTRAINT `FKewmkjmhtnhm3g5hal30mm75m4` FOREIGN KEY (`authorities_id`) REFERENCES
    `Authority` (`id`);

INSERT INTO `User`(id, age, contact, firstName, gender, lastName, password, username)
VALUES (1, 21, '+380985823453', 'Admin', 'Male', 'Admin', '$2a$10$H9ljbRx7an0Ks5z/l0QMiu037.jyQcYU7eQBamontJAAW/le/TAPq', 'admin');

INSERT INTO `Authority_User`(authorities_id, users_id) VALUES(1, 1);
INSERT INTO `Authority_User`(authorities_id, users_id) VALUES(2, 1);
