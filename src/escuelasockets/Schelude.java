/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelasockets;

import java.io.Serializable;

/**
 *
 * @author Absalom Herrera
 */
public class Schelude implements Serializable {

    private int idSchelude;
    private String day1, day2, day3, day4, day5, courseName, professorName, professorLastName;

    public Schelude() {
    }

    public int getIdSchelude() {
        return idSchelude;
    }

    public void setIdSchelude(int idSchelude) {
        this.idSchelude = idSchelude;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getDay3() {
        return day3;
    }

    public void setDay3(String day3) {
        this.day3 = day3;
    }

    public String getDay4() {
        return day4;
    }

    public void setDay4(String day4) {
        this.day4 = day4;
    }

    public String getDay5() {
        return day5;
    }

    public void setDay5(String day5) {
        this.day5 = day5;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getProfessorLastName() {
        return professorLastName;
    }

    public void setProfessorLastName(String professorLastName) {
        this.professorLastName = professorLastName;
    }

    @Override
    public String toString() {
        return "Schelude{" + "idSchelude=" + idSchelude + ", day1=" + day1 + ", day2=" + day2 + ", day3=" + day3 + ", day4=" + day4 + ", day5=" + day5 + ", courseName=" + courseName + ", professorName=" + professorName + ", professorLastName=" + professorLastName + '}';
    }

    
}
