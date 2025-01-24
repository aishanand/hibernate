INSERT INTO author (age, name, genre, id)
VALUES (23, 'Mark Janel', 'Anthology', 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO author (age, name, genre, id)
VALUES (34, 'Joana Nimar', 'History', 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id, id)
VALUES ('001-JN', 'A History of Ancient Prague', 2, 1)
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id, id)
VALUES ('002-JN', 'A People''s History', 2, 2)
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id, id)
VALUES ('001-MJ', 'The Beatles Anthology', 1, 3)
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id, id)
VALUES ('007-JN', 'Carrie', 2, 4)
ON CONFLICT (id) DO NOTHING;