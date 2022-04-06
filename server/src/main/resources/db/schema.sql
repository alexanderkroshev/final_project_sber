drop table card IF EXISTS;
drop table user IF EXISTS;

create TABLE user (
  id BIGINT IDENTITY PRIMARY KEY,
  login VARCHAR(20) not null unique,
  password VARCHAR(100) not null,
  name VARCHAR(100) not null,
  surname VARCHAR(100) not null,
  role    VARCHAR(20) DEFAULT 'USER',
  status    VARCHAR(20) DEFAULT 'ACTIVE'


);

create TABLE card (
  id BIGINT IDENTITY PRIMARY KEY,
  card_number VARCHAR(20) not null unique,
  card_password VARCHAR(100) not null,
  balance DECIMAL not null,
  user_id BIGINT  references user (id),
  status    VARCHAR(20) DEFAULT 'ACTIVE'
);
