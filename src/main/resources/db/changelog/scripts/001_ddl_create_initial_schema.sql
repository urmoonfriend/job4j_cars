create table auto_user
(
    id   serial primary key,
    login varchar not null,
    password varchar not null
);

create table auto_post
(
    id   serial primary key,
    description varchar,
    created timestamp not null,
    auto_user_id references auto_user (id) not null
);