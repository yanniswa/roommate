create table keymaster_raum(
    id serial primary key,
    uuid varchar(50),
    room varchar(30)
);

create table keymaster_schluessel(
    id serial primary key,
    uuid varchar(50),
    owner varchar(30)
);