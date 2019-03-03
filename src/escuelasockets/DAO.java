package escuelasockets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Absalom Herrera
 */
public class DAO {

    private Connection con = null;

    private static final String SQL_INSERT_STUDENT
            = "insert into student (StudentId, Name, LastName, Password, StudentPhoto) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_STUDENT
            = "select * from student where StudentId = ?";
    
    private static final String SQL_SELECT_PROFESSOR
            = "select * from professor where professorId = ?";

    public void createStudent(Student s) throws SQLException {
        PreparedStatement ps = null;
        getConnection();
        try {
            ps = con.prepareStatement(SQL_INSERT_STUDENT);
            ps.setLong(1, s.getStudentId());
            ps.setString(2, s.getName());
            ps.setString(3, s.getLastName());
            ps.setString(4, s.getPass());
            ps.setString(5, s.getStudentPhotoPath());
            ps.executeUpdate();
        } finally {
            close(ps);
            close(con);
        }
    }

    public Student selectStudent(Long studentId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        getConnection();
        try {
            ps = con.prepareStatement(SQL_SELECT_STUDENT);
            ps.setLong(1, studentId);
            rs = ps.executeQuery();
            List results = getResultsStudent(rs);
            if (results.size() > 0) {
                return (Student) results.get(0);
            } else {
                return null;
            }
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
    }

    public Professor selectProfessor (Long professorId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        getConnection();
        try {
            ps = con.prepareStatement(SQL_SELECT_PROFESSOR);
            ps.setLong(1, professorId);
            rs = ps.executeQuery();
            List results = getResultsProfessor(rs);
            if (results.size() > 0) {
                return (Professor) results.get(0);
            } else {
                return null;
            }
        } finally {
            close(rs);
            close(ps);
            close(con);
        }
    }
    
    private List getResultsStudent(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            Student s = new Student();
            s.setStudentId(rs.getLong("StudentId"));
            s.setName(rs.getString("Name"));
            s.setLastName(rs.getString("LastName"));
            s.setPass(rs.getString("Password"));
            s.setStudentPhotoPath(rs.getString("StudentPhoto"));

            results.add(s);
        }
        return results;
    }
    
    private List getResultsProfessor(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            Professor p = new Professor();
            p.setId(rs.getLong("professorId"));
            p.setName(rs.getString("Name"));
            p.setLastname(rs.getString("LastName"));
            p.setPass(rs.getString("Password"));
            results.add(p);
        }
        return results;
    }

    private void getConnection() {
        String user = "root";
        String pwd = "absalom94";
        String url = "jdbc:mysql://localhost:3306/School?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
        String mySqlDriver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(mySqlDriver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (Exception e) {
            System.err.println("GET CONECTION ERROR");
            e.printStackTrace();
        }
    }

    private void close(PreparedStatement ps) throws SQLException {
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
            }
        }
    }

    private void close(Connection cnn) {
        if (cnn != null) {
            try {
                cnn.close();
            } catch (SQLException e) {
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
            }
        }
    }

}
