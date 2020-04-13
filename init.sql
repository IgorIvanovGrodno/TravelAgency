CREATE DATABASE IF NOT EXISTS travel_agency
    COLLATE utf8_general_ci;

USE travel_agency;

DROP TABLE IF EXISTS tour_package;

CREATE TABLE tour_package
(
    name VARCHAR(50) NOT NULL,
    type        VARCHAR(20) NULL,
    food_system VARCHAR(50) NULL,
    transport   VARCHAR(15) NULL,
    days        INT(10) NULL,
    price       INT(20) NULL,
    status      BOOL NULL,
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into tour_package(name, type, food_system, transport, days, price, status)
values ('The best shopping tour', 'SHOPPING', 'AI', 'BUS', 3, 300, false )
     , ('Very very cooling cruise', 'MEDICAL', 'HB', 'AIRPLANE', 15, 1000, true)
     , ('The best shopping tour', 'MEDICAL', 'BB', 'TRAIN', 5, 300, true )
     , ('Very very cooling cruise', 'CRUISE', 'HB', 'SHIP', 5, 1000, false)
     , ('The best shopping tour', 'EXCURSION', 'FB', 'BUS', 10, 300, false )
     , ('Very very cooling cruise', 'RELAXATION', 'UAI', 'TRAIN', 15, 1000, false)
     ,('The best shopping tour', 'SHOPPING', 'AI', 'BUS', 20, 300, false )
     , ('Very very cooling cruise', 'CRUISE', 'HB', 'SHIP', 15, 1000, false)
     ,('The best shopping tour', 'EXCURSION', 'BB', 'AIRPLANE', 2, 300, false )
     , ('Very very cooling cruise', 'RELAXATION', 'AI', 'SHIP', 30, 1000, false);


DROP TABLE IF EXISTS client;

CREATE TABLE client
(
    id                  BIGINT(20)         NOT NULL AUTO_INCREMENT,
    discount            INT            NOT NULL,
    first_name          varchar(20)   not null,
    second_name         varchar(20)     not null,
    phone_number        varchar(20)     not null,
    email               varchar(20)     not null,
    PRIMARY KEY (id)

)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into client (discount, first_name, second_name, phone_number, email)
values (10, 'Ivan', 'Ivanov', '+3752222222', 'ivan@gmail.com');

DROP TABLE IF EXISTS authorization;
CREATE TABLE authorization
(
    authorization        VARCHAR(30)        NOT NULL,
    password     VARCHAR(60)        NOT NULL,
    role         VARCHAR(10)        NOT NULL,
    client_id    BIGINT(20),
    PRIMARY KEY (authorization),
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into authorization (authorization, password, role, client_id)
values ('admin', '$2a$10$qzncMPtSI7of.AbIytli0OfvTfgiHFHDUt3Bsq1wPEx5YOZ5wC216', 'ROLE_ADMIN',null)
     , ('user', '$2a$10$qzncMPtSI7of.AbIytli0OfvTfgiHFHDUt3Bsq1wPEx5YOZ5wC216', 'ROLE_USER', 1);

DROP TABLE IF EXISTS client_tour;

CREATE TABLE client_tour
(
    id                  BIGINT(20)         NOT NULL AUTO_INCREMENT,
    client_id           BIGINT(20)            NOT NULL,
    tour_package_id     BIGINT(20)            NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES client (id) ON DELETE CASCADE,
    FOREIGN KEY (tour_package_id) REFERENCES tour_package (id) ON DELETE CASCADE


)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

insert into client_tour (client_id, tour_package_id)
values (1, 1)
     ,(1, 2);CREATE DATABASE IF NOT EXISTS travel_agency
    COLLATE utf8_general_ci;
