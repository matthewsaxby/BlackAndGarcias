select * from movie;

select * from movie order by user_rating desc;

select * from movie order by release_date desc;

select * from user_tbl;

select * from cinema;

select * from actor;

SELECT * FROM movie join movie_actors using (movie_id) order by movie_id;

select * from actor join movie_actors using (actor_id);

select * from actor join movie_actors using (actor_id);

select * from showing;

select * from review;

select actor_id from actor where first_name='Peter' and last_name='Smith'

select poster, movie_id from movie;

update movie set user_rating=-1, rating_count=0 where movie_id=5;

delete from USER_TBL where user_id=5;
delete from USER_TBL where user_id=6;
delete from USER_TBL where user_id=7;
delete from USER_TBL where user_id=8;
