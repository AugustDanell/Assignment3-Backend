-- Franchise

INSERT INTO franchise ("name", "description") VALUES ('Kill Bill', 'An action film series directed by Quentin Tarantino, focusing on the revenge of "The Bride".'); -- 1

-- Movie
INSERT INTO movie ("title", "year", "director") VALUES ('Titanic', 1998, 'James Cameron'); -- 1
INSERT INTO movie ("title", "year", "director") VALUES ('Inception', 2010, 'Christopher Nolan'); -- 2
INSERT INTO movie ("title", "year", "director") VALUES ('The Notebook', 2004, 'Nick Cassavetes'); -- 3
INSERT INTO movie ("title", "year", "director") VALUES ('Inglorious Bastards', 2009, 'Quentin Tarantino'); -- 4
INSERT INTO movie ("franchise_id", "title", "year", "director") VALUES (1, 'Kill Bill Vol 1', 2003, 'Quentin Tarantino'); -- 5
INSERT INTO movie ("franchise_id", "title", "year", "director") VALUES (1, 'Kill Bill Vol 2', 2004, 'Quentin Tarantino'); -- 6
INSERT INTO movie ("title", "year", "director") VALUES ('Shutter Island', 2010, 'Martin Scorsese'); -- 7


-- Character
INSERT INTO character ("name", "gender") VALUES ('Jack Dawson', 'male'); -- 1
INSERT INTO character ("name", "gender") VALUES ('Aldo Raine', 'male'); -- 2
INSERT INTO character ("name", "gender") VALUES ('Noah Calhoun', 'male'); -- 3
INSERT INTO character ("name", "gender") VALUES ('Beatrix Kiddo', 'female'); -- 4
INSERT INTO character ("name", "gender") VALUES ('Rose DeWitt Bukater','female'); -- 5
INSERT INTO character ("name", "gender") VALUES ('Hans Landa', 'male'); -- 6
INSERT INTO character ("name", "gender") VALUES ('Dolores Chanal', 'female'); -- 7


-- Character_movie
INSERT INTO character_movie (character_id, movie_id) VALUES(1, 1);
INSERT INTO character_movie (character_id, movie_id) VALUES(5, 1);
INSERT INTO character_movie (character_id, movie_id) VALUES(2, 2);
INSERT INTO character_movie (character_id, movie_id) VALUES(3, 3);
INSERT INTO character_movie (character_id, movie_id) VALUES(6, 4);
INSERT INTO character_movie (character_id, movie_id) VALUES(4, 5);
INSERT INTO character_movie (character_id, movie_id) VALUES(4, 6);
INSERT INTO character_movie (character_id, movie_id) VALUES(7, 7);