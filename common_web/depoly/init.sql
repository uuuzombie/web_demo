

create user dbuser with password 'dbuser';

create database test_db owner dbuser;

grant all privileges on database test_db to dbuser;