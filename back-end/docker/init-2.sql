CREATE DATABASE if not exists jevents_ticket_dev;

USE jevents_ticket_dev;

create table ticket
(
    id 			 bigint auto_increment primary key,
    event_id int null
);



