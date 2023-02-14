CREATE TABLE IF NOT EXISTS worker(
  id IDENTITY PRIMARY KEY,
  name VARCHAR (1000) NOT NULL,
  birthday date,
  level VARCHAR(10) NOT NULL,
  salary INT);

ALTER TABLE worker
ADD CONSTRAINT level_values
CHECK (level IN ('Trainee', 'Junior', 'Middle', 'Senior'));

ALTER TABLE worker
ADD CONSTRAINT birthday_min_value
CHECK (birthday > '1900-12-31');

ALTER TABLE worker
ADD CONSTRAINT salary_values
CHECK (salary > 100 AND salary < 100000);

ALTER TABLE worker
ADD CONSTRAINT check_min_name_length
CHECK (LENGTH(name) > 2);

--INSERT INTO worker (name, BIRTHDAY , leVEL )
--VALUES ('Eugene', '1984-01-24', 'Junior')


CREATE TABLE IF NOT EXISTS client (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(1000) NOT NULL
);

ALTER TABLE client
ADD CONSTRAINT client_min_name_length
CHECK (LENGTH(name) > 2);


CREATE TABLE IF NOT EXISTS project(
  id IDENTITY PRIMARY KEY,
  client_id BIGINT NOT NULL,
  start_date date,
  finish_date date,
  FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE IF NOT EXISTS project_worker(
  project_id BIGINT NOT NULL,
  worker_id BIGINT NOT NULL,
  PRIMARY KEY (project_id, worker_id),
  FOREIGN KEY (project_id) REFERENCES project(id),
  FOREIGN KEY (worker_id) REFERENCES worker(id)
);
