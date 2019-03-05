/* Test 
select course.courseId, course.name, professor.professorId, professor.name, professor.lastname from course inner join professor on course.professorId=professor.professorId;
select course.name, professor.name from course inner join professor on course.professorId=professor.professorId;*/

/* All scheludes */

select idschelude,day1,day2,day3,day4,day5,NameC,name,LastName
from ((schelude natural join course)natural join professor);

/* For enrollment of single student*/

select schelude.idschelude,day1,day2,day3,day4,day5,NameC,name,LastName
from ((schelude natural join course)natural join professor) where schelude.idschelude not in (select enrollment.idschelude from enrollment where StudentId=2016630552);

/* For indivudual schelude */

select idschelude,StudentId,day1,day2,day3,day4,day5,NameC,name,LastName
from (((schelude natural join course)natural join professor)natural join enrollment)
where StudentId = '2016630552';

/* For individual grades */

select StudentId,Name,NameC,grade
from (student natural join grades)natural join course 
where StudentId = '2016630552';

/* Professor courses */

select idschelude,courseId,NameC
from (schelude natural join course)
where professorId = '11112222';

/* Students in professors class */

select idschelude,courseId,NameC,StudentId,Name,LastName
from ((schelude natural join course)natural join enrollment)natural join student
where professorId = '11112222'
group by NameC;