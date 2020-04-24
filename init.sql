CREATE DATABASE IF NOT EXISTS travel_agency
    COLLATE utf8_general_ci;

USE travel_agency;

DROP TABLE IF EXISTS client_order;
DROP TABLE IF EXISTS status_order;
DROP TABLE IF EXISTS tour_package;
DROP TABLE IF EXISTS transport;
DROP TABLE IF EXISTS food_system;
DROP TABLE IF EXISTS tour_type;
DROP TABLE IF EXISTS authorization;
DROP TABLE IF EXISTS client;

CREATE TABLE transport
(
    name VARCHAR(50) NOT NULL UNIQUE,
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into transport(name)
values ('AIRPLANE')
     ,('TRAIN')
     ,('SHIP')
     ,('BUS')
     ,('RELAXATION');

CREATE TABLE food_system
(
    name VARCHAR(50) NOT NULL UNIQUE,
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into food_system(name)
values   ('RO'),
         ('BB'),
         ('HB'),
         ('FB'),
         ('AI'),
         ('UAI');

CREATE TABLE tour_type
(
    name VARCHAR(50) NOT NULL UNIQUE,
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into tour_type(name)
values ('SHOPPING')
     ,('MEDICAL')
     ,('CRUISE')
     ,('EXCURSION')
     ,('RELAXATION');

CREATE TABLE tour_package
(
    name VARCHAR(50) NOT NULL,
    type        BIGINT(20) NULL,
    food_system BIGINT(50) NULL,
    transport   BIGINT(15) NULL,
    days        INT(10) NULL,
    price       INT(20) NULL,
    status      BOOL NULL,
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    FOREIGN KEY (type) REFERENCES tour_type (id)  ON DELETE CASCADE,
    FOREIGN KEY (transport) REFERENCES transport (id) ON DELETE CASCADE,
    FOREIGN KEY (food_system) REFERENCES food_system (id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into tour_package(name, type, food_system, transport, days, price, status)
values ('The best shopping tour', 1, 1, 1, 3, 300, false )
     , ('Very very cooling cruise', 2, 2, 2, 15, 1000, true)
     , ('The best shopping tour', 3, 3, 3, 5, 300, true )
     , ('Very very cooling cruise', 4, 4, 4, 5, 1000, false)
     , ('The best shopping tour', 5, 5, 5, 10, 300, false )
     , ('Very very cooling cruise', 5, 1, 1, 15, 1000, false)
     ,('The best shopping tour', 4, 6, 2, 20, 300, false )
     , ('Very very cooling cruise', 3, 6, 3, 15, 1000, false)
     ,('The best shopping tour', 2, 1, 4, 2, 300, false )
     , ('Very very cooling cruise', 1, 2, 5, 30, 1000, false);

CREATE TABLE client
(
    id                  BIGINT(20)         NOT NULL AUTO_INCREMENT,
    discount            INT            NOT NULL,
    first_name          varchar(30)   not null,
    second_name         varchar(30)     not null,
    phone_number        varchar(20)     not null,
    email               varchar(100)     not null,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into client (discount, first_name, second_name, phone_number, email)
values (10, 'Ivan', 'Ivanov', '+3752222222', 'ivan@gmail.com');

CREATE TABLE status_order
(
    id      BIGINT(20)      NOT NULL AUTO_INCREMENT,
    name    VARCHAR(50)     NOT NULL,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into status_order (name)
values ('NEW'),
       ('PAYED'),
       ('CANCEL'),
       ('FINISHED');

CREATE TABLE client_order
(
    id                  BIGINT(20)         NOT NULL AUTO_INCREMENT,
    client_id           BIGINT(20)         NOT NULL,
    tour_package_id     BIGINT(20)         NOT NULL,
    number_card         INT(4)             NOT NULL,
    total_cost          BIGINT(20)         NOT NULL,
    create_date          DATETIME           NOT NULL,
    status              BIGINT             NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE,
    FOREIGN KEY (tour_package_id) REFERENCES tour_package (id) ON DELETE CASCADE,
    FOREIGN KEY (status) REFERENCES status_order(id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into client_order (client_id, tour_package_id, number_card, total_cost, create_date, status)
values (1, 1, 1234, 1000, '20-03-01 10:05:10', 2)
     ,(1, 2, 1234, 500, '19-03-01 10:05:10', 4);

CREATE TABLE authorization
(
    login        VARCHAR(30)        NOT NULL,
    password     VARCHAR(60)        NOT NULL,
    role         VARCHAR(10)        NOT NULL,
    active       BOOL               NOT NULL,
    client_id    BIGINT(20),
    PRIMARY KEY (login),
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into authorization (login, password, role, client_id, active)
values ('admin', '$2a$10$qzncMPtSI7of.AbIytli0OfvTfgiHFHDUt3Bsq1wPEx5YOZ5wC216', 'ROLE_ADMIN',null, true)
     , ('user', '$2a$10$qzncMPtSI7of.AbIytli0OfvTfgiHFHDUt3Bsq1wPEx5YOZ5wC216', 'ROLE_USER', 1, true);
