drop table if exists member cascade;
create table member
(
   id bigint generated  by default as identity,
   name varchar2(255),
   primary key(id)
);