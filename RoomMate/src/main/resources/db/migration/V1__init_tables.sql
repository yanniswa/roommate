create table arbeitsplatz (
                              id serial primary key,
                              ausstattung varchar(100) array
);

create table room(
    arbeitsplatz integer,
    roomnumber integer,
    foreign key (arbeitsplatz) references arbeitsplatz(id)
);

create table buchung(
                        id UUID primary key,
                        local_date date,
                        anfang time,
                        ende time,
                        arbeitsplatz integer references arbeitsplatz(id),
                        arbeitsplatz_key integer


);

create table benutzer(
    buchung UUID,
    benutzername varchar(50),
    foreign key (buchung) references buchung(id)
);
