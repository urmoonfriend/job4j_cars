alter table auto_post
    add column photo_id int references photo (id);

insert into photo(name, path)
values ('sell_release.jpg', 'photos\sell_release.jpg');

update auto_post
set photo_id = (select id from photo where name = 'sell_release.jpg');