insert into engine(name)
values ('1.6-liter petrol'),
       ('1.7-liter petrol'),
       ('1.8-liter petrol'),
       ('1.5-liter turbocharged petrol'),
       ('1.6-liter turbocharged petrol'),
       ('1.8-liter turbocharged petrol'),
       ('2.0-liter turbocharged petrol');

insert into car(name, engine_id)
values ('Lada Granta', 1),
       ('Lada Niva', 2),
       ('Haval Jolion', 4),
       ('Chery Tiggo 7 Pro', 4),
       ('Geely Coolray', 4),
       ('Chery Tiggo Pro 4', 4),
       ('Haval F7x', 7),
       ('Geely Atlas Pro', 6),
       ('Chery Tiggo 8 Pro', 5);

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
       (3, 7),
       (3, 8),
       (3, 9);




