drop table card IF EXISTS;

create TABLE card (
  id BIGINT IDENTITY PRIMARY KEY,
--  login VARCHAR(20) not null unique,
  card_number VARCHAR(20) not null unique,
  card_password VARCHAR(100) not null,
--  password VARCHAR(100) not null,
  balance DECIMAL not null,
--  card_role    VARCHAR(20) DEFAULT 'USER',
  status    VARCHAR(20) DEFAULT 'ACTIVE'
);

