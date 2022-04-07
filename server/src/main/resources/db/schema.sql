drop table card if exists;
drop table user if exists;

create table user (
  id serial,
  login varchar(20) not null unique,
  password varchar(100) not null,
  name varchar(100) not null,
  surname varchar(100) not null,
  role    varchar(20) default 'USER',
  status    varchar(20) default 'ACTIVE'
);

create table card (
  id serial,
  card_number varchar(20) not null unique,
  card_password varchar(100) not null,
  balance decimal not null,
  user_id bigint  references user (id),
  status    varchar(20) default 'ACTIVE'
);
