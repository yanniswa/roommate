create table arbeitsplatz(
    id serial primary key,
    roomnumber integer
);
create table room(
    arbeitsplatz integer primary key references arbeitsplatz(id)
);

create table buchung(
    id serial primary key,
    local_date date,
    anfang time,
    ende time,
    benutzer varchar(50),
    arbeitsplatz integer references arbeitsplatz(id),
    arbeitsplatz_key integer

);