USE jevent_event_dev;

create table ticket_offices
(
    id 			 bigint auto_increment primary key,
    organisation varchar(255) null
);

create table locations
(
    id				 bigint auto_increment primary key,
    address          varchar(255) null,
    building_name    varchar(255) null,
    city             varchar(255) null,
    country          varchar(255) null,
    zip_code         int          null,
    ticket_office_id bigint       null,
    constraint FKmt398hucyq7bfhqekm8co77iq
        foreign key (ticket_office_id) references ticket_offices (id)
);

create table events
(
    id                bigint auto_increment primary key,
    description       varchar(255) null,
    event_date        datetime(6)  null,
    event_name        varchar(255) null,
    event_type        varchar(255) null,
    short_description varchar(255) null,
    location_id       bigint       null,
    event_time        time         null,
    constraint FK7a9tiyl3gaugxrtjc2m97awui
        foreign key (location_id) references locations (id)
);

insert into ticket_offices (id, organisation)
values  (1, 'Test');

insert into locations (id, address, building_name, city, country, zip_code, ticket_office_id)
values  (1, 'Adres1', 'Building1', 'Hasselt', 'Belgium', 3500, 1),
        (2, 'Adres2', 'Building2', 'Alken', 'Belgium', 3570, 1),
        (3, 'teststraat 5', 'Building3', 'Hasselt', 'Belgie', 3500, 1);

insert into events (id, description, event_date, event_name, event_type, short_description, location_id, event_time)
values  (1, 'Description', '2022-05-05 00:00:00', 'EventName', 'SPORTS', 'Short Description', 2, '20:00:00'),
        (2, 'full Description', '2022-05-05 00:00:00', 'Second Event', 'SPORTS', 'short Description', 1, '20:00:00'),
        (3, 'full Description', '2022-05-05 00:00:00', 'Second Event', 'SPORTS', 'short Description', 1, '20:00:00'),
        (4, 'full Description', '2022-05-15 00:00:00', 'Third Event', 'SPORTS', 'short Description', 1, '20:00:00');
		
		



