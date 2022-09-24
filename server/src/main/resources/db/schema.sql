drop table client IF EXISTS;
drop table legal_entity IF EXISTS;

create TABLE client (
  client_id BIGINT IDENTITY PRIMARY KEY,
  client_type VARCHAR(20) not null
);

create TABLE legal_entity (
  id BIGINT IDENTITY PRIMARY KEY,
  full_name varchar(30),
  balance DECIMAL not null,
  dt_reg_end date
);



