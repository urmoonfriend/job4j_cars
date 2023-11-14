alter table auto_post
add column car_id int references car(id);

alter table auto_post
    drop column created;

alter table auto_post
    add column created TIMESTAMP WITHOUT TIME ZONE DEFAULT now();

insert into auto_post(description, auto_user_id, car_id)
select ('Продам ' || c.name || ' c двигателем ' || e.name) as description,
       (1) as auto_user_id,
       (c.id) as car_id
       from car c
        join engine e on e.id = c.engine_id
