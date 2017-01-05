# king-module

### 一、项目概述

1、这是一个多模块示例项目


### 二、数据库和表

数据库类型：mysql

create database king_module;

use king_module;

create table user
(
	id             varchar(50)  primary key,
	username       varchar(50),
	password       varchar(50),
	created_date   datetime
);