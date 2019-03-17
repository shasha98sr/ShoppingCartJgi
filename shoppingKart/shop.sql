




DROP DATABASE shoplogin;



CREATE DATABASE shoplogin;


CREATE TABLE login (uname varchar(30) , pass varchar(30) , name varchar(30) ); 

ALTER TABLE `login`ADD PRIMARY KEY (`uname`);

INSERT INTO login VALUES('abc','nayan','123'); 

CREATE TABLE cart (pname varchar(30),qty varchar(2),price varchar(10));


