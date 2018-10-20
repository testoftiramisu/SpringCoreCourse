CREATE TABLE IF NOT EXISTS EVENTS
(
  id BIGINT,
  name varchar,
  base_price double
);

CREATE TABLE IF NOT EXISTS USERS
(
  id BIGINT,
  first_name varchar,
  last_name varchar,
  email varchar
);

CREATE TABLE IF NOT EXISTS AUDITORIUM
(
 id BIGINT,
 name varchar,
 number_of_seats BIGINT
);



