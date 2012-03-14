USE c_cs108_gxj;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS friendship;
DROP TABLE IF EXISTS friend_request;
DROP TABLE IF EXISTS challenge;
DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS achievement;
DROP TABLE IF EXISTS achievement_record;
DROP TABLE IF EXISTS quiz_taken_record;
DROP TABLE IF EXISTS quiz_created_record;
DROP TABLE IF EXISTS quiz;
DROP TABLE IF EXISTS announcement;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS question_response_question;
DROP TABLE IF EXISTS fill_in_blank_question;
DROP TABLE IF EXISTS multiple_choice_question;
DROP TABLE IF EXISTS picture_response_question;
DROP TABLE IF EXISTS multiple_answer_question;
DROP TABLE IF EXISTS multiple_choice_answer_question;
DROP TABLE IF EXISTS matching_question;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
	userid INT AUTO_INCREMENT NOT NULL,
    username CHAR(64),
    password CHAR(64),
    url CHAR(64),
	permission INT,
	isblocked BOOLEAN,
	isdead BOOLEAN, 
	practicenumber INT,
	highscorenumber INT,
	PRIMARY KEY (userid)
);

CREATE TABLE friendship (
	uid1 INT,
	uid2 INT
);

CREATE TABLE friend_request (
	mid INT AUTO_INCREMENT NOT NULL,
	uid1 INT,
	uid2 INT,
	time TIMESTAMP,
	isConfirmed BOOLEAN,
	isRejected BOOLEAN,
	PRIMARY KEY (mid)
);

CREATE TABLE challenge (
	mid INT AUTO_INCREMENT NOT NULL,
	uid1 INT,
	uid2 INT,
	time TIMESTAMP,
	qid INT,
	bestScore INT,
	isRead BOOLEAN,
	PRIMARY KEY (mid)
);

CREATE TABLE note (
	mid INT AUTO_INCREMENT NOT NULL,
	uid1 INT,
	uid2 INT,
	time TIMESTAMP,
	note TEXT,
	isRead BOOLEAN,
	PRIMARY KEY (mid)
);

CREATE TABLE achievement (
	aid INT AUTO_INCREMENT NOT NULL,
	type INT,
	name CHAR(64),
	url TEXT,
	description TEXT,
	threshold INT,
	PRIMARY KEY (aid)
);

CREATE TABLE achievement_record (
	id INT AUTO_INCREMENT NOT NULL,
	userid INT,
	aid INT,
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);

CREATE TABLE quiz_taken_record (
	id INT AUTO_INCREMENT NOT NULL,
	qid INT,
	userid INT,
	timespan BIGINT,
	score DECIMAL(5,2),
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	isfeedback BOOLEAN,
	ispractice BOOLEAN,	
	PRIMARY KEY (id)
);
	
CREATE TABLE quiz_created_record (
	id INT AUTO_INCREMENT NOT NULL,
	userid INT,
	qid INT,
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (id)
);	

CREATE TABLE quiz (
	qid INT AUTO_INCREMENT NOT NULL,
	name CHAR(64),
	url TEXT,
	description TEXT,
	category CHAR(64),
	userid INT,	
	israndom BOOLEAN, 
	isonepage BOOLEAN,
	opfeedback BOOLEAN,
	oppractice BOOLEAN,
	raternumber INT,
	rating DECIMAL(5,2),
	PRIMARY KEY (qid)
);

CREATE TABLE announcement (
	aid INT AUTO_INCREMENT NOT NULL,
	title TEXT,
	content TEXT,
	time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY (aid)
);

CREATE TABLE tags (
	qid INT,
	tag CHAR(64) 
);


CREATE TABLE question_response_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	PRIMARY KEY (questionid)
);

CREATE TABLE fill_in_blank_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	PRIMARY KEY (questionid)
);

CREATE TABLE multiple_choice_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	PRIMARY KEY (questionid)
);

CREATE TABLE picture_response_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	url TEXT,
	PRIMARY KEY (questionid)
);

CREATE TABLE multiple_answer_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	answernumber INT,
	isordered BOOLEAN,
	PRIMARY KEY (questionid)
);

CREATE TABLE multiple_choice_answer_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	PRIMARY KEY (questionid)
);

CREATE TABLE matching_question (
	questionid INT AUTO_INCREMENT NOT NULL,
	quizid INT,
	position INT,
	question TEXT,
	answer TEXT,
	score DECIMAL(5,2),
	PRIMARY KEY (questionid)
);
