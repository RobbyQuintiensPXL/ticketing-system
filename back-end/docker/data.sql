insert into events (id, description, event_date, event_name, event_type, short_description, location_id, event_time)
values  (1, 'Description', '2022-05-05 00:00:00', 'EventName', 'SPORTS', 'Short Description', 2, '20:00:00'),
        (2, 'full Description', '2022-05-05 00:00:00', 'Second Event', 'SPORTS', 'short Description', 1, '20:00:00'),
        (3, 'full Description', '2022-05-05 00:00:00', 'Second Event', 'SPORTS', 'short Description', 1, '20:00:00'),
        (4, 'full Description', '2022-05-15 00:00:00', 'Third Event', 'SPORTS', 'short Description', 1, '20:00:00');
		
insert into locations (id, address, building_name, city, country, zip_code, ticket_office_id)
values  (1, 'Adres1', 'Building1', 'Hasselt', 'Belgium', 3500, 1),
        (2, 'Adres2', 'Building2', 'Alken', 'Belgium', 3570, 1),
        (3, 'teststraat 5', 'Building3', 'Hasselt', 'Belgie', 3500, 1);
		
insert into ticket_offices (id, organisation)
values  (1, 'Test');