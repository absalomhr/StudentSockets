drop database School;
create database School;
use School;

create table enrollment (
	StudentId bigint not null,
	idschelude int not null);

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
	courseId int not null,
	Name varchar(50) not null);

create table grades(
	StudentId bigint not null,
	courseId int not null,
	grade int(2));

create table schelude(
	idschelude int(5) not null,
	courseId int not null,
	day1 varchar(15) not null,
	day2 varchar(15) not null,
	day3 varchar(15) not null,
	day4 varchar(15) not null,
	day5 varchar(15) not null,
	professorId bigint not null);

alter table student add constraint PKstudent Primary Key(StudentId);
alter table professor add constraint PKprofessor Primary Key(professorId);
alter table course add constraint PKcourse Primary Key(courseId);
alter table grades add constraint PKgrades Primary Key(StudentId,courseId);
alter table schelude add constraint PKschelude Primary Key(idschelude);
alter table enrollment add constraint PKenrollment Primary Key(StudentId, idschelude);

alter table grades add constraint FKgrades1 Foreign Key(StudentId) references student(StudentId) on delete cascade on update cascade;
alter table grades add constraint FKgrades2 Foreign Key(courseId) references course(courseId) on delete cascade on update cascade;
alter table schelude add constraint FKschelude2 Foreign Key(courseId) references course(courseId) on delete cascade on update cascade;

insert into professor values (12345678, 'Humbert', 'Humbert', '1111');
insert into professor values (87654321, 'Piotr', 'Ilich', '0000');
insert into professor values (11112222, 'Aleksandr', 'Pushkin', '1234');
insert into professor values (77700777, 'Fiodor', 'Dostoyevski', '0987');

insert into course values (1, 'Introduction to Music');
insert into course values (2, 'Introduction to Philosophy');
insert into course values (3, 'Introduction to Computer Networks');
insert into course values (4, 'Software Engineering');
insert into course values (5, 'Introduction to Data Science');
insert into course values (6, 'Data Mining');
insert into course values (7, 'Machine Learning');
insert into course values (8, 'Information Ethics');

insert into schelude values (1, 1, '10:30-12:00', '10:30-12:00', '-', '10:30-12:00', '-', 12345678);
insert into schelude values (2, 2, '13:30-15:00', '13:30-15:00', '-', '13:30-15:00', '-', 12345678);
insert into schelude values (3, 3, '08:30-10:00', '08:30-10:00', '-', '08:30-10:00', '-', 87654321);
insert into schelude values (4, 4, '16:30-18:00', '16:30-18:00', '-', '16:30-18:00', '-', 87654321);
insert into schelude values (5, 5, '-', '13:30-15:00', '13:30-15:00', '-', '13:30-15:00', 11112222);
insert into schelude values (6, 6, '-', '16:30-18:00', '16:30-18:00', '-', '16:30-18:00', 11112222);
insert into schelude values (7, 7, '07:00-08:30', '-', '07:00-08:30', '-', '07:00-08:30', 77700777);
insert into schelude values (8, 8, '18:00-20:00', '-', '18:00-20:00', '-', '18:00-20:00', 77700777);