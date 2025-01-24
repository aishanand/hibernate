
INSERT INTO author (name, genre, age)
VALUES ('Mark Janel', 'Anthology', 23)
ON CONFLICT (id) DO NOTHING;

INSERT INTO author (name, genre, age)
VALUES ('Joana Nimar', 'History', 34)
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id )
VALUES ('001-JN', 'A History of Ancient Prague', (SELECT id FROM author WHERE name = 'Joana Nimar'))
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id)
VALUES ('002-JN', 'A People''s History',  (SELECT id FROM author WHERE name = 'Joana Nimar') )
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id)
VALUES ('001-MJ', 'The Beatles Anthology',  (SELECT id FROM author WHERE name = 'Mark Janel') )
ON CONFLICT (id) DO NOTHING;

INSERT INTO book (isbn, title, author_id)
VALUES ('007-JN', 'Carrie',  (SELECT id FROM author WHERE name = 'Joana Nimar') )
ON CONFLICT (id) DO NOTHING;