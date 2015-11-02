
create table role (
  id serial not null primary key,
  role_name varchar(40) not null
);

insert into role (role_name) values ('common admin');
insert into role (role_name) values ('super admin');


