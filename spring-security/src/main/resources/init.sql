DROP TABLE IF EXISTS my_users;

CREATE TABLE users (
                          id INT AUTO_INCREMENT  PRIMARY KEY,
                          username VARCHAR(32) NOT NULL,
                          password VARCHAR(64) NOT NULL,
                          role VARCHAR(32) NOT NULL
);

INSERT INTO users (username, password, role) VALUES
                                                    ('user', '$2a$08$9noS/3HPxkBsp7r92EWjIOSbFZf1h6Lskj9aqjlZPfeevQgcv4jSy', 'ROLE_USER'),
                                                    ('admin', '$2a$08$xTHh1/dDb9a78PUlUW56jePjhWa0WXWlb63UPzNL3XiOW9BVtKmWa', 'ROLE_ADMIN'),
                                                    ('api', '$2a$08$xCrPHKHy2lMoHXq0VJutFeoAOuQdNGTJauPZdDG/zxo4vs9TOxJkK', 'ROLE_API');