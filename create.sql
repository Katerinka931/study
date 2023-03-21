create table public.groups (id bigserial not null, name text not null, primary key (id));
create table public.students (id bigserial not null, birthdate timestamp(6), name varchar(255), number integer not null, group_id bigint, primary key (id));
alter table if exists public.groups add constraint UK_8mf0is8024pqmwjxgldfe54l7 unique (name);
create table public.groups (id bigserial not null, name text not null, primary key (id));
create table public.students (id bigserial not null, birthdate timestamp(6), name varchar(255), number integer not null, group_id bigint, primary key (id));
alter table if exists public.groups add constraint UK_8mf0is8024pqmwjxgldfe54l7 unique (name);
create table public.groups (id bigserial not null, name text not null, primary key (id));
create table public.students (id bigserial not null, birthdate timestamp(6), name varchar(255), number integer not null, group_id bigint, primary key (id));
alter table if exists public.groups add constraint UK_8mf0is8024pqmwjxgldfe54l7 unique (name);
