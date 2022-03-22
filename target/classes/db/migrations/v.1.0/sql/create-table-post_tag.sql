CREATE TABLE POST_TAG
(
POST_ID INT,
TAG_ID INT,
FOREIGN KEY (POST_ID) REFERENCES POST (ID) ON DELETE CASCADE,
FOREIGN KEY (TAG_ID) REFERENCES TAG (ID) ON DELETE CASCADE
);