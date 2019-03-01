package escuelasockets;

/**
 *
 * @author Absalom Herrera
 */
public class Student {
    private String name;
    private String lastName;
    private String studentPhotoPath;

    public String getStudentPhotoPath() {
        return studentPhotoPath;
    }

    public void setStudentPhotoPath(String studentPhotoPath) {
        this.studentPhotoPath = studentPhotoPath;
    }
    private Long studentId;
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Student{" + "name=" + name + ", lastName=" + lastName + ", studentPhotoPath=" + studentPhotoPath + ", studentId=" + studentId + ", pass=" + pass + '}';
    }
    
    
}
