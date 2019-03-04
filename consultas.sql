/* Test 
select course.courseId, course.name, professor.professorId, professor.name, professor.lastname from course inner join professor on course.professorId=professor.professorId;
select course.name, professor.name from course inner join professor on course.professorId=professor.professorId;*/

/*For enrollment*/

create or replace view prof as select professorId, concat (name, ' ', lastname) as profname from professor;
create or replace view c as select schelude.idschelude, schelude.day1, schelude.day2, schelude.day3, schelude.day4, schelude.day5, schelude.professorId, course.name from schelude inner join course on schelude.courseId=course.courseId;
select c.idschelude, c.day1, c.day2, c.day3, c.day4, c.day5, c.name, prof.profname from c, prof where c.professorId=prof.professorId;

