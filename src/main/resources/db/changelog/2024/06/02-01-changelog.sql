-- liquibase formatted sql

-- changeset Torchez:1717343516884-1
CREATE SEQUENCE IF NOT EXISTS account_id_seq START WITH 101 INCREMENT BY 20;

-- changeset Torchez:1717343516884-2
CREATE TABLE account
(
    id       BIGINT       NOT NULL,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(50)  NOT NULL,
    active   BOOLEAN      NOT NULL,
    CONSTRAINT pk_account PRIMARY KEY (id)
);

-- changeset Torchez:1717343516884-3
CREATE TABLE role
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name          VARCHAR(20)                             NOT NULL,
    account_id_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_role PRIMARY KEY (id)
);

-- changeset Torchez:1717343516884-4
ALTER TABLE role
    ADD CONSTRAINT FK_ROLE_ON_ACCOUNTID FOREIGN KEY (account_id_id) REFERENCES account (id);

-- changeset Torchez:1717343516884-5
ALTER TABLE account
    ADD CONSTRAINT uc_account_email UNIQUE (email);

-- changeset Torchez:1717343516884-6
INSERT INTO account (id, email, password, active)
VALUES (100, 'qXkU9@example.com', 'password', true),
       (101, '3KsZj@example.com', 'password', true),
       (102, 'pZ7p4@example.com', 'password', true),
       (103, '9ZJpU@example.com', 'password', true),
       (104, 'YXQpY@example.com', 'password', true);

-- changeset Torchez:1717343516884-7
INSERT INTO role (id, name, account_id_id)
VALUES (100, 'USER', 100),
       (101, 'USER', 101),
       (102, 'USER', 102),
       (103, 'USER', 103),
       (104, 'USER', 104)