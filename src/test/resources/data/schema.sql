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

CREATE TABLE IF NOT EXISTS counters
(
  event_name varchar(100),
  target     varchar(100),
  counter    BIGINT default 0
);
