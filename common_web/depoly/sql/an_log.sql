
create table an_log (
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

create index idx_create_time on an_log(create_time);

insert into an_log (create_time,user_id,role_id,server_ip,client_ip,action_type,feature_type,action_info)
values ('2015-05-05 12:12:12',1,2,'172.2.2.2','10.2.2.25',2,4,'[{"element": "Strict Security","previous": "Pro-Choice:Quota","current": "Pro-Choice:Block"}]');
