insert into arbeitsplatz (ausstattung) values
                                           ('Stuhl, Tisch, Lampe'),
                                           ('Monitor, Tastatur, Maus'),
                                           ('Schreibtisch, Bürostuhl');

-- Buchung am 2024-02-19
insert into buchung (local_date, anfang, ende,arbeitsplatz, arbeitsplatz_key,id)
values ('2024-02-19', '08:00:00', '12:00:00', (select min(id) from arbeitsplatz), 1,UUID());

-- Buchung am 2024-02-20
insert into buchung (local_date, anfang, ende,  arbeitsplatz, arbeitsplatz_key,id)
values ('2024-02-20', '08:30:00', '12:30:00',  (select max(id) from arbeitsplatz), 2,UUID());

-- Buchung am 2024-02-21
insert into buchung (local_date, anfang, ende,  arbeitsplatz, arbeitsplatz_key,id)
values ('2024-02-21', '09:00:00', '13:00:00',  (select min(id) from arbeitsplatz), 3,UUID());

-- Buchung am 2024-02-22
insert into buchung (local_date, anfang, ende,  arbeitsplatz, arbeitsplatz_key,id)
values ('2024-02-22', '08:00:00', '12:00:00', (select max(id) from arbeitsplatz ), 4,UUID());

-- Buchung am 2024-02-23
insert into buchung (local_date, anfang, ende,  arbeitsplatz, arbeitsplatz_key,id)
values ('2024-02-23', '09:30:00', '13:30:00',  (select min(id) from arbeitsplatz ), 5,UUID());


INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Stuhl%'), 101);
INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Monitor%'), 101);
INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Schreibtisch%'), 101);

insert into benutzer (buchung, benutzername,id) values((select id from buchung where local_date='2024-02-19'),'Elon',null);
insert into benutzer (buchung, benutzername,id) values((select id from buchung where local_date='2024-02-20'),'Elon',null);
insert into benutzer (buchung, benutzername,id) values((select id from buchung where local_date='2024-02-21'),'Elon',null);
insert into benutzer (buchung, benutzername,id) values((select id from buchung where local_date='2024-02-22'),'Elon',null);
insert into benutzer (buchung, benutzername,id) values((select id from buchung where local_date='2024-02-23'),'Elon',null);
