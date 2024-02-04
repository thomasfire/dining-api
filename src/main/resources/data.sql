/*
create table admins (
                        id bigint not null,
                        user_id bigint not null,
                        primary key (id)
);
create table restaurant (
                            id bigint not null,
                            description varchar(255),
                            title varchar(255) not null,
                            primary key (id)
);

create table reviews (
                         approved date,
                         atmosphere tinyint check (atmosphere between 0 and 4),
                         food tinyint check (food between 0 and 4),
                         posted date not null,
                         score tinyint check (score between 0 and 4),
                         id bigint not null,
                         restaurant_id bigint not null,
                         user_id bigint not null,
                         review varchar(255) not null,
                         primary key (id)
);
create table users (
                       id bigint not null,
                       bio varchar(255),
                       password varchar(255) not null,
                       username varchar(255) not null unique,
                       primary key (id)
);*/

INSERT INTO restaurant (id, description, title) VALUES ( 0, 'Best in Town', 'Aboba Rest') ;
INSERT INTO restaurant (id, description, title) VALUES ( 1, 'So-So, but ok', 'OK Lah') ;
INSERT INTO USERS (id, bio, password, username) VALUES (0, null, '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'tomihi' );
INSERT INTO ADMINS (id, user_id) VALUES ( 0, 0 );
INSERT INTO reviews (approved, atmosphere, food, posted, score, id, restaurant_id, user_id, review) VALUES ( null, 0, 0, now(), 1, 0, 1, 0, 'really so-so' );

