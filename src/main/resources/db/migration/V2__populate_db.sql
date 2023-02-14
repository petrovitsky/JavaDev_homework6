INSERT INTO worker (name, birthday, level, salary) VALUES
  ('Bibby', '1990-05-20', 'Senior', 5000),
  ('Demetria', '1998-08-31', 'Middle', 3400),
  ('Harrison', '1995-08-11', 'Junior', 2200),
  ('Natalie', '1974-07-28', 'Trainee', 350),
  ('Gal', '1998-04-14', 'Middle', 2800),
  ('Sula', '1974-06-03', 'Senior', 4750 ),
  ('Cullin', '1998-09-30', 'Trainee', 290 ),
  ('Sybilla', '1989-06-18', 'Junior', 1500),
  ('Thorny', '1985-10-23', 'Junior', 1800),
  ('Otha', '1991-01-29', 'Junior', 1600);

  INSERT INTO client (name) VALUES
  ('Reckitt Benckiser LLC'),
  ('HOMEOLAB USA INC.'),
  ('Energetix Corp'),
  ('Life Line Home Care Services'),
  ('Lupin Pharmaceuticals');

  INSERT INTO project (client_id, start_date, finish_date) VALUES
  (5, '2022-10-05', '2028-12-17'),
  (2, '2022-02-25', '2024-05-22'),
  (2, '2022-01-10', '2026-11-16'),
  (4, '2022-07-30', '2026-09-19'),
  (3, '2022-07-23', '2023-04-05'),
  (2, '2022-06-27', '2030-07-11'),
  (1, '2022-02-17', '2029-08-04'),
  (3, '2022-07-22', '2028-08-10'),
  (5, '2022-04-28', '2030-01-10'),
  (5, '2022-04-11', '2027-06-29');

  INSERT INTO project_worker (project_id, worker_id) VALUES
    (1, 7),
    (1, 8),
    (1, 6),
    (2, 9),
    (2, 10),
    (3, 9),
    (3, 8),
    (4, 7),
    (5, 9),
    (5, 8),
    (5, 6),
    (5, 7),
    (6, 9),
    (6, 7),
    (7, 7),
    (7, 8),
    (7, 6),
    (8, 7),
    (8, 9),
    (9, 6),
    (10, 6),
    (10, 5);
