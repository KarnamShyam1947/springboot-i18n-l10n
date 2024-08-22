ALTER TABLE review_translations 
  CONVERT TO CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;


ALTER TABLE review_translations 
  MODIFY title VARCHAR(255) 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

ALTER TABLE review_translations 
  MODIFY username VARCHAR(255) 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;

ALTER TABLE review_translations 
  MODIFY description VARCHAR(255) 
  CHARACTER SET utf8mb4 
  COLLATE utf8mb4_unicode_ci;
