

create database db_sps;

create table audit_log (
	id bigserial not null primary key,
	create_time timestamp with time zone not null,
	user_id integer not null,
	role_id integer not null,
	server_ip varchar(40) not null,
	client_ip varchar(40) not null,
	action_type smallint not null,
	feature_type smallint not null,
	action_info text not null
);

create index idx_create_time on audit_log(create_time);

insert into audit_log (create_time,user_id,role_id,server_ip,client_ip,action_type,feature_type,action_info)
values ('2015-05-05 12:12:12',1,2,'172.2.2.2','10.2.2.25',2,4,'[{"element": "Strict Security","previous": "Pro-Choice:Quota","current": "Pro-Choice:Block"}]');

update audit_log set action_info='[{"element": "Strict","previous": "","current": "Pro-Choice:Block"}]' where id=1;


create table account (
  id serial not null primary key,
  user_name varchar(40) not null
);

insert into account (user_name) values ('admin1');
insert into account (user_name) values ('test1');


create table role (
  id serial not null primary key,
  role_name varchar(40) not null
);

insert into role (role_name) values ('common admin');
insert into role (role_name) values ('super admin');



select
  tba.id ,
  create_time as createTime,
  user_name as userName,
  role_name as roleName,
  server_ip as serverIp,
  client_ip as clientIp,
  action_type as actionType,
  feature_type as featureType,
  action_info as actionInfo
from
  audit_log tba,
  account tbb,
  role tbc
where
  tba.user_id = tbb.id
  and tba.role_id = tbc.id;