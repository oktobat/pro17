SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS freeboard;
DROP TABLE IF EXISTS t_member;




/* Create Tables */

CREATE TABLE freeboard
(
	articleNO int NOT NULL AUTO_INCREMENT,
	parentNO int DEFAULT 0 NOT NULL,
	title varchar(100) NOT NULL,
	content varchar(4000) NOT NULL,
	imageFileName varchar(100),
	writeDate date DEFAULT (current_date) NOT NULL,
	id varchar(10) NOT NULL,
	PRIMARY KEY (articleNO),
	UNIQUE (articleNO)
);


CREATE TABLE t_member
(
	id varchar(10) NOT NULL,
	pwd varchar(10) NOT NULL,
	name varchar(10) NOT NULL,
	emai varchar(30) NOT NULL,
	joinDate date DEFAULT (current_date) NOT NULL,
	hobby varchar(30),
	PRIMARY KEY (id)
);



/* Create Foreign Keys */

ALTER TABLE freeboard
	ADD FOREIGN KEY (id)
	REFERENCES t_member (id)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



