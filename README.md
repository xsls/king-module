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

show columns from user;


### 三、模块及依赖说明

1、king-common模块存放的是基础功能，应该做到和项目完全无关，而且尽量做到和spring框架也无关。
如果做不到这一点，那么相应的功能就不要放在此模块下

2、king-data模块是项目的数据层，所以对jpa和数据库驱动的依赖，以及model和数据层代码放在此模块下。

3、king-business模块是项目的业务层。request和response

4、king-web模块是项目对外的接口。所以controller及项目启动的Application.java放在此处

5、king-auth模块



2、依赖项应该尽量最小化