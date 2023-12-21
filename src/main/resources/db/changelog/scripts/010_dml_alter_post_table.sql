alter table auto_post
    add column title varchar(255);

update auto_post
set title = (select 'Продам ' || name from car c where c.id = car_id)
where car_id is not null;

alter table auto_post
    add column is_sold boolean default false;

update auto_post
set is_sold = false;

