/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package escuelasockets;

/**
 *
 * @author Absalom Herrera
 */
public class SignInData {
    private Long resultStudentId;
    private String studentPhotoClientPath;
    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Long getResultStudentId() {
        return resultStudentId;
    }

    public void setResultStudentId(Long resultStudentId) {
        this.resultStudentId = resultStudentId;
    }

    public String getStudentPhotoClientPath() {
        return studentPhotoClientPath;
    }

    public void setStudentPhotoClientPath(String studentPhotoClientPath) {
        this.studentPhotoClientPath = studentPhotoClientPath;
    }

    @Override
    public String toString() {
        return "SignInData{" + "resultStudentId=" + resultStudentId + ", studentPhotoClientPath=" + studentPhotoClientPath + '}';
    }
    
    
}
