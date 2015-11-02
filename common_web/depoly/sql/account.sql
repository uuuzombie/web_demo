
create table account (
  id serial not null primary key,
  user_name varchar(40) not null
);

insert into account (user_name) values ('admin1');
insert into account (user_name) values ('test1');

