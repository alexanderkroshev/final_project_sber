drop table Card IF EXISTS;

create TABLE Card (
  id BIGINT IDENTITY PRIMARY KEY,
  card_number BIGINT not null,
  card_password INTEGER not null,
  balance DECIMAL
);

