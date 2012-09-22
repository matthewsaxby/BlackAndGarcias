drop table fav_genres;
drop table fav_actors;
drop table fav_movies;
drop table review;
drop table attending;
drop table showing;
drop table booking;
drop table movie_actors;
drop table movie;
drop table cinema;
drop table actor;
drop table user_tbl;


create table user_tbl (
	user_id int not null generated always as identity constraint user_pk primary key,
	username varchar(50),
	userType int,
	nickName varchar(50),
	firstName varchar(50),
	lastName varchar(50),
	yearOfBirth int,
	password varchar(50),
	emailAddress varchar(30)
);

create table actor (
	actor_id int not null generated always as identity constraint actor_pk primary key,
	first_name varchar(50),
	last_name varchar(50)
);

create table cinema (
	cinema_id int not null generated always as identity constraint cinema_pk primary key,
	name varchar(50),
	address varchar(50),
	seating_capacity int,
	facilities varchar(100)
);

create table movie (
	movie_id int not null generated always as identity constraint movie_pk primary key,
	title varchar(50),
	poster varchar(50),
	synopsis long varchar,
	user_rating double,
	rating_count integer,
	release_date date,
	director varchar(50),
	age_rating varchar(50),
	genres long varchar,
	cinema_id int constraint cinema_fk0 references cinema
);

create table movie_actors (
	actor_id int constraint actor_fk0 references actor,
	movie_id int constraint movie_fk0 references actor,
	movie_actors_id int not null generated always as identity constraint ma_pk primary key
);

create table booking (
	booking_id int not null generated always as identity constraint booking_pk primary key,
	numAdultTickets int,
	numConcessionTickets int,
	numChildTickets int,
	sessionTime date,
	cost int,
	uniqueID int,
	user_id int constraint user_fk2 references user_tbl,
	movie_id int constraint movie_fk1 references movie,
	cinema_id int constraint cinema_fk1 references cinema
);

create table showing (
	showing_id int not null generated always as identity constraint showing_pk primary key,
	showingTime date,
	availableSeats int,
	cinema_id int constraint cinema_fk2 references cinema,
	movie_id int constraint movie_fk2 references movie
);

create table attending (
	user_id int constraint user_fk3 references user_tbl,
	showing_id int constraint showing_fk0 references showing,
	attending_id int not null generated always as identity constraint attending_pk primary key
);

create table review (
	review_id int not null generated always as identity constraint review_pk primary key,
	comment long varchar,
	rating int,
	user_id int constraint user_fk4 references user_tbl, 
	movie_id int constraint movie_fk3 references movie
);

create table fav_movies (
	fav_movs_id int not null generated always as identity constraint fm_pk primary key,
	user_id int constraint user_fk5 references user_tbl,
	movie_id int constraint movie_fk4 references movie
);

create table fav_actors (
	fav_acts_id int not null generated always as identity constraint fa_pk primary key,
	user_id int constraint user_fk6 references user_tbl,
	actor_id int constraint actor_fk1 references actor
);

create table fav_genres (
	fav_gens_id int not null generated always as identity constraint fg_pk primary key,
	user_id int constraint user_fk7 references user_tbl,
	genres long varchar
);
