DELETE FROM book;
ALTER TABLE book AUTO_INCREMENT = 1001;

DELETE FROM category;
ALTER TABLE category AUTO_INCREMENT = 1001;

INSERT INTO `category` (`name`) VALUES ('Comedy'),('Technology'),('Sports'),('Romance'),('Lifestyle');

-- Comedy
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Comedy Bang Bang!', 'Scott Aukerman', '', 1299, 4.5, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Start Without Me', 'Gary Janetti', '', 1799, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Happy Go Lucky', 'David Sedaris', '', 3599, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('That''s Not Funny', 'Matt Sienkiewicz', '', 1399, 0, TRUE, FALSE, 1001);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Gray Lady Winked', 'Ashley Rindsberg', '', 499, 0, TRUE, FALSE, 1001);

-- Technology category
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Innovators:', 'Walter Isaacson', '', 1299, 4.4, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Sapiens', 'Yuval Noah Harari', '', 1399, 4.6, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Code Book', 'Simon Singh', '', 1199, 4.3, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Phoenix Project', 'Gene Kim,', '', 1099, 4.5, TRUE, FALSE, 1002);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Lean Startup', 'Eric Ries', '', 1499, 4.2, TRUE, FALSE, 1002);

-- Sports
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Moneyball', 'Michael Lewis', '', 1099, 4.4, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Boys in the Boat', 'Daniel James Brown', '', 1299, 4.7, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Open', 'Andre Agassi', '', 1199, 4.6, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Born to Run', 'Christopher McDougall', '', 1399, 4.5, TRUE, FALSE, 1003);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Blind Side', 'Michael Lewis', '', 1099, 4.3, TRUE, FALSE, 1003);


-- Romance category
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Pride and Prejudice', 'Jane Austen', '', 899, 4.8, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Outlander', 'Diana Gabaldon', '', 1299, 4.7, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Fault in Our Stars', 'John Green', '', 1099, 4.5, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Me Before You', 'Jojo Moyes', '', 999, 4.6, TRUE, FALSE, 1004);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Notebook', 'Nicholas Sparks', '', 899, 4.3, TRUE, FALSE, 1004);

-- Lifestyle category
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Life-Changing Magic of Tidying Up', 'Marie Kondo', '', 999, 4.2, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Atomic Habits', 'James Clear', '', 1299, 4.6, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The 5 Love Languages', 'Gary Chapman', '', 1099, 4.5, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('Eat Pray Love', 'Elizabeth Gilbert', '', 1199, 4.4, TRUE, FALSE, 1005);
INSERT INTO `book` (title, author, description, price, rating, is_public, is_featured, category_id)
VALUES ('The Power of Now', 'Eckhart Tolle', '', 999, 4.3, TRUE, FALSE, 1005);
