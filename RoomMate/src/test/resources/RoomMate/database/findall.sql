insert into arbeitsplatz (ausstattung) values
                                           ('Stuhl, Tisch, Lampe'),
                                           ('Monitor, Tastatur, Maus'),
                                           ('Schreibtisch, Bürostuhl');

INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Stuhl%'), 101);
INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Monitor%'), 101);
INSERT INTO room (arbeitsplatz, roomnumber) VALUES ((select id from arbeitsplatz where ausstattung like '%Schreibtisch%'), 101);