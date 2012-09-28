insert into cinema (name, address, seating_capacity, facilities) values ('MCR', '354 anzac parade, NSW', 50, 'popcorn');

insert into movie (title, poster, synopsis, user_rating, rating_count, release_date, director, age_rating, genres) values ('Transformers', '/images/trans.jpg', 'An ancient struggle reerupts on Earth between two extraterrestrial clans, the heroic Autobots and the evil Decepticons, with a clue to the ultimate power held by a young teenager.', 7.2, 5, '2007-06-28', 'Michael Bay', 'PG-13', 'Action, Scifi, Thriller');
insert into movie (title, poster, synopsis, user_rating, rating_count, release_date, director, age_rating, genres) values ('Moulin Rouge!', '/images/moulinrouge.jpg', 'A poet falls for a beautiful courtesan whom a jealous duke covets in this stylish musical, with music drawn from familiar 20th century sources.', 7.1, 15, '2001-05-24', 'Baz Luhrmann', 'PG-13', 'Drama, Musical, Romance');
insert into movie (title, poster, synopsis, user_rating, rating_count, release_date, director, age_rating, genres) values ('Cars', '/images/cars.jpg', 'A hot-shot race-car named Lightning McQueen gets waylaid in Radiator Springs, where he finds the true meaning of friendship and family.', 7.3, 8, '2006-06-08', 'John Lasseter', 'G', 'Animation, Adventure, Comedy' );
insert into movie (title, poster, synopsis, user_rating, rating_count, release_date, director, age_rating, genres) values ('The Hobit', '/images/hobit.jpg', 'A curious Hobbit, Bilbo Baggins, journeys to the Lonely Mountain with a vigorous group of Dwarves to reclaim a treasure stolen from them by the dragon Smaug. ', -1, 0, '2012-12-26', 'Peter Jackson', 'M', 'Action, Adventure');

insert into actor (first_name, last_name) values ('Peter', 'Smith');
insert into actor (first_name, last_name) values ('John', 'Rogers');

insert into user_tbl (username, usertype, password, emailaddress) values ('admin', 2, 'admin', 'lachlan.dally@gmail.com');

insert into movie_actors (actor_id, movie_id) values (1, 1);
insert into movie_actors (actor_id, movie_id) values (1, 2);
insert into movie_actors (actor_id, movie_id) values (1, 3);
insert into movie_actors (actor_id, movie_id) values (1, 4);

insert into movie_actors (actor_id, movie_id) values (2, 2);
insert into movie_actors (actor_id, movie_id) values (2, 4);

INSERT INTO showing (showingTime, showingDate, availableSeats, cinema_id, movie_id)
VALUES('18:00:00', '0026-06-04', 50, 1, 3);

INSERT INTO showing (showingTime, showingDate, availableSeats, cinema_id, movie_id)
VALUES('18:00:00', '0026-06-04', 430, 3, 3);

insert into review (comment, rating, user_id, movie_id) 
values('This movie was about cars. Yay Cars', 8, 1, 3);

