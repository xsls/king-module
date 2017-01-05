-- 建表sql

create table user
(
	id             varchar(50)  primary key,
	username       varchar(50),
	password       varchar(50),
	created_date   datetime
);