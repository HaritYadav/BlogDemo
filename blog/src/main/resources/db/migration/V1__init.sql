CREATE TABLE post (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  title varchar(100) NOT NULL
--  	CONSTRAINT title_not_empty CHECK(
--            LENGTH(title) > 0 
--        )
    ,
  content LONGTEXT NOT NULL
--  	CONSTRAINT content_not_empty CHECK(
--            LENGTH(content) > 0 
--        )
	,
  created_on TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_on TIMESTAMP, -- DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  username varchar(50) NOT NULL
--  	CONSTRAINT username_not_empty CHECK(
--            LENGTH(username) > 0 
--        )
	,
  PRIMARY KEY (id),
  UNIQUE KEY UK_posttitle (title)
);

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(50) NOT NULL
--  	CONSTRAINT username_not_empty CHECK(
--            LENGTH(username) > 0 
--        )
    ,
  password TINYTEXT NOT NULL
--  	CONSTRAINT password_not_empty CHECK(
--            LENGTH(password) > 0 
--        )
    ,
  email varchar(50) NOT NULL
--  	CONSTRAINT email_not_empty CHECK(
--            LENGTH(email) > 0 
--        )
    ,
  PRIMARY KEY (id),
  UNIQUE KEY UK_username (username)
);