CREATE DATABASE `game` CHARACTER SET utf8 COLLATE utf8_general_ci;
GRANT ALL ON `game`.* TO `player`@localhost IDENTIFIED BY '12345’;

CREATE TABLE `game` (
	`game_id` bigint(20) NOT NULL AUTO_INCREMENT,
	`game_title` varchar(255) NOT NULL,
	PRIMARY KEY (`game_id`)
) ENGINE=INNODB;

CREATE TABLE `player` (
	`player_id` bigint NOT NULL AUTO_INCREMENT,
	`first_name` varchar(255) NOT NULL,
	`last_name` varchar(255) NOT NULL,
	`address` varchar(255) NOT NULL,
	`postal_code` varchar(255) NOT NULL,
	`province` varchar(255) NOT NULL,
	`phone_number` varchar(255) NOT NULL,
	PRIMARY KEY (`player_id`)
) ENGINE=INNODB;

CREATE TABLE `playerandgame` (
	`player_game_id` bigint NOT NULL AUTO_INCREMENT,
	`game_id` bigint NOT NULL,
	`player_id` bigint NOT NULL,
	`playing_date` timestamp NOT NULL,
	`score` int NOT NULL,
	PRIMARY KEY (`player_game_id`),
	FOREIGN KEY (game_id)
        REFERENCES game(game_id)
        ON DELETE CASCADE,
	FOREIGN KEY (player_id)
        REFERENCES player(player_id)
        ON DELETE CASCADE
) ENGINE=INNODB;
