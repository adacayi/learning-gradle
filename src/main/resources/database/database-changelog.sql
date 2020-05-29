--liquibase formatted sql
--changeset asanver:1

create table user (
    user_id uuid primary key,
    name varchar(30),
    last_name varchar(30),
    date_of_birth date
);
