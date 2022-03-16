CREATE TABLE POST
(
ID INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
CONTENT VARCHAR(255),
POST_STATUS ENUM ('ACTIVE', 'DELETE'),
WRITER_ID INT,
FOREIGN KEY (WRITER_ID) REFERENCES WRITER (ID) ON DELETE CASCADE
);