drop database School;
create database School;
use School;



create table student(
	StudentId bigint not null,
	Name varchar(20) not null,
	LastName varchar(20) not null,
	Password varchar (20) not null,
	StudentPhoto varchar(200) not null);

create table professor(
	professorId bigint not null,
	Name varchar(20) not null,
	LastName varchar(20) not null,
	Password varchar (20));

create table course(
	courseId bigint not null,
	Name varchar(30) not null,
	GroupName varchar(15) not null, 
	professorId bigint not null);

create table grades(
	StudentId bigint not null,
	courseId bigint not null,
	grade int(2));

create table schelude(
	idschelude int(5) not null,
	courseId bigint not null,
	day varchar(10) not null, 
	beginHrs varchar(5) not null,
	endHrs varchar(5) not null);



alter table student add constraint PKstudent Primary Key(StudentId);
alter table professor add constraint PKprofessor Primary Key(professorId);
alter table course add constraint PKcourse Primary Key(courseId,GroupName);
alter table grades add constraint PKgrades Primary Key(StudentId,courseId);
alter table schelude add constraint PKschelude Primary Key(idschelude);



alter table course add constraint FKcourse Foreign Key(professorId) references professor(professorId) on delete cascade on update cascade;
alter table grades add constraint FKgrades1 Foreign Key(StudentId) references student(StudentId) on delete cascade on update cascade;
alter table grades add constraint FKgrades2 Foreign Key(courseId) references course(courseId) on delete cascade on update cascade;
alter table schelude add constraint FKschelude2 Foreign Key(courseId) references course(courseId) on delete cascade on update cascade;

insert into professor values (12345678, 'Humbert', 'Humbert', '1111');