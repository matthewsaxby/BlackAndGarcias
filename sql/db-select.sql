select * from movie;

select * from movie order by user_rating desc;

select * from movie order by release_date desc;


select * from user_tbl;

select * from cinema;

select * from actor;

SELECT * FROM movie join movie_actors using (movie_id) order by movie_id;

select * from actor join movie_actors using (actor_id);

select * from actor join movie_actors using (actor_id) where movie_id = 2;