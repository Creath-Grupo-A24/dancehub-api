CREATE TABLE CREATH_ROLES
(
    id   serial primary key,
    type varchar(30) not null
);

INSERT INTO CREATH_ROLES (type) VALUES ('ADMIN');
INSERT INTO CREATH_ROLES (type) VALUES ('MANAGER');
INSERT INTO CREATH_ROLES (type) VALUES ('TEACHER');
INSERT INTO CREATH_ROLES (type) VALUES ('DANCER');

CREATE TABLE CREATH_USERS
(
    id            uuid primary key,
    username      varchar not null unique,
    email         varchar not null unique,
    phone         varchar,
    token         varchar unique,
    name          varchar not null,
    password      varchar not null,
    cpf           varchar not null unique,
    birth_date    date    not null,
    password_date date
);

CREATE TABLE CREATH_USERS_ROLES
(
    user_id uuid    not null references CREATH_USERS (id),
    role_id integer not null references CREATH_ROLES (id),
    unique (user_id, role_id)
);

comment on table CREATH_ROLES is 'Tabela de perfis';
comment on table CREATH_USERS is 'Tabela de usuários';
comment on table CREATH_USERS_ROLES is 'Tabela de perfis dos usuários';

TRUNCATE TABLE creath_users, creath_users_roles, creath_attractions, creath_users_attractions;