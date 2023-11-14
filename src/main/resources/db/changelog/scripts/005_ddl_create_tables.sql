create table engine
(
    id   serial primary key,
    name varchar(255) not null
);

create table car
(
    id        serial primary key,
    name      varchar(255) not null,
    engine_id int          not null unique references engine (id)
);

create table owners
(
    id      serial primary key,
    name    varchar(255) not null,
    user_id int          not null references auto_user (id)
);

create table history_owner
(
    id       serial primary key,
    owner_id int not null references owner (id),
    car_id   int not null references car (id),
    start_at TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    end_at   TIMESTAMP WITHOUT TIME ZONE DEFAULT now()
);




