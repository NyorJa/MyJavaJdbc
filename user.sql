CREATE TABLE user
(
  id INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  username VARCHAR(25),
  password VARCHAR(25),
  person_id INT(11),
  CONSTRAINT FKUPersonId FOREIGN KEY (person_id) REFERENCES person (id)
);
CREATE INDEX FKUPersonId ON user (person_id);