CREATE TABLE users
(
  id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR
);
INSERT INTO users(name)
VALUES
  ('ユーザーA'),
  ('ユーザーB'),
  ('ユーザーC'),
  ('ユーザーD'),
  ('ユーザーE'),
  ('ユーザーF'),
  ('ユーザーG'),
  ('ユーザーH'),
  ('ユーザーI'),
  ('ユーザーJ'),
  ('ユーザーAA'),
  ('ユーザーAAA'),
  ('ユーザーAB'),
  ('ユーザーBB'),
  ('ユーザーBBB');