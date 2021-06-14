drop table if exists course CASCADE;
drop table if exists enrollment CASCADE;
drop table if exists student CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table course (
    id bigint not null,
    name varchar(255),
    primary key (id)
);
create table enrollment (
    student_id bigint not null,
    course_id bigint not null
);
create table student (
    id bigint not null,
    address varchar(255),
    email varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    phone varchar(255),
    primary key (id)
);
alter table course
add constraint UK_4xqvdpkafb91tt3hsb67ga3fj unique (name);
alter table student
add constraint UK_fe0i52si7ybu0wjedj6motiim unique (email);
alter table enrollment
add constraint FKbhhcqkw1px6yljqg92m0sh2gt foreign key (course_id) references course;
alter table enrollment
add constraint FKio7fsy3vhvfgv7c0gjk15nyk4 foreign key (student_id) references student;