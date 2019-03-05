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
public class Grade implements Serializable{
    private String courseName;
    private int grade;

    public Grade() {
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" + "courseName=" + courseName + ", grade=" + grade + '}';
    }
    
    
}
