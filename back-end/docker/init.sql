create table ticket_offices
(
    id           bigint auto_increment
        primary key,
    organisation varchar(255) null
);

create table locations
(
    id               bigint auto_increment
        primary key,
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
    id                bigint auto_increment
        primary key,
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

