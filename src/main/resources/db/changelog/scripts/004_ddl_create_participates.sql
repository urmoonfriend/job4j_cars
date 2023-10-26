CREATE TABLE participates (
  id serial primary key,
    user_id int references auto_user(id),
    post_id int references auto_post(id)
);
