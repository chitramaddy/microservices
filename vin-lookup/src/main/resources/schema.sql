create schema if not exists vin_lookup;
use vin_lookup;

create table if not exists vehicle (
    vin String not null primary key,
    type varchar(20) not null,
    make varchar(20) not null,
    model varchar(20) not null,
    year varchar(4) not null,
    color varchar(20) not null
);