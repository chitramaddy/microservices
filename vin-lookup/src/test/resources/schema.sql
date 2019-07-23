create schema if not exists vin_lookup_test;
use vin_lookup_test;

create table if not exists vehicle (
    vin String not null primary key,
    type varchar(20) not null,
    make varchar(20) not null,
    model varchar(20) not null,
    year varchar(4) not null,
    color varchar(20) not null
);