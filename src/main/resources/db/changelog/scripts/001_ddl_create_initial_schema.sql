create table auto_user
(
    id   serial primary key,
    name varchar not null,
    login varchar not null,
    password varchar not null
);

create table auto_post
(
    id   serial primary key,
    description varchar,
    created timestamp not null,
    auto_user_id int references auto_user (id) not null
);