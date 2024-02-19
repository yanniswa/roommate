insert into arbeitsplatz (ausstattung) values
                                           ('Stuhl, Tisch, Lampe'),
                                           ('Monitor, Tastatur, Maus'),
                                           ('Schreibtisch, Bürostuhl');
-- Buchung am 2024-02-19
insert into buchung (local_date, anfang, ende, benutzer, arbeitsplatz, arbeitsplatz_key)
values ('2024-02-19', '08:00:00', '12:00:00', 'Elon', 1, 1);

-- Buchung am 2024-02-20
insert into buchung (local_date, anfang, ende, benutzer, arbeitsplatz, arbeitsplatz_key)
values ('2024-02-20', '08:30:00', '12:30:00', 'Elon', 2, 2);

-- Buchung am 2024-02-21
insert into buchung (local_date, anfang, ende, benutzer, arbeitsplatz, arbeitsplatz_key)
values ('2024-02-21', '09:00:00', '13:00:00', 'Elon', 3, 3);

-- Buchung am 2024-02-22
insert into buchung (local_date, anfang, ende, benutzer, arbeitsplatz, arbeitsplatz_key)
values ('2024-02-22', '08:00:00', '12:00:00', 'Elon', 1, 4);

-- Buchung am 2024-02-23
insert into buchung (local_date, anfang, ende, benutzer, arbeitsplatz, arbeitsplatz_key)
values ('2024-02-23', '09:30:00', '13:30:00', 'Elon', 1, 5);

