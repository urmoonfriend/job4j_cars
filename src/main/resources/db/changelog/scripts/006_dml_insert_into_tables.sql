insert into engine(name)
values ('1.6-liter petrol'),
       ('1.7-liter petrol'),
       ('1.8-liter petrol'),
       ('1.5-liter turbocharged petrol'),
       ('1.6-liter turbocharged petrol'),
       ('1.8-liter turbocharged petrol'),
       ('2.0-liter turbocharged petrol');


insert into car(name, engine_id)
values ('Lada Granta', (select id from engine where name = '1.6-liter petrol')),
       ('Lada Niva', (select id from engine where name = '1.7-liter petrol')),
       ('Haval Jolion', (select id from engine where name = '1.8-liter petrol')),
       ('Chery Tiggo 7 Pro', (select id from engine where name = '1.5-liter turbocharged petrol')),
       ('Haval F7x', (select id from engine where name = '1.6-liter turbocharged petrol')),
       ('Geely Atlas Pro', (select id from engine where name = '1.8-liter turbocharged petrol')),
       ('Chery Tiggo 8 Pro', (select id from engine where name = '2.0-liter turbocharged petrol'));

insert into owners(name, user_id)
values ('Ivanov', 1),
       ('Petrov', 2),
       ('Sidorov', 3);

insert into history_owner(owner_id, car_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 6),
       (3, 7);