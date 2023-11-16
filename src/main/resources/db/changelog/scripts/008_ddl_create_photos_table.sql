create table photo(
    id serial primary key,
    name varchar(255),
    path text not null unique
);