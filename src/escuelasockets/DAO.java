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

    public void createStudent(Long StudentId, String Name, String LastName, String pass, String StudentPhotoPath) throws SQLException {
        PreparedStatement ps = null;
        getConnection();
        try {
            ps = con.prepareStatement(SQL_INSERT_STUDENT);
            ps.setLong(1, StudentId);
            ps.setString(2, Name);
            ps.setString(3, LastName);
            ps.setString(4, pass);
            ps.setString(5, StudentPhotoPath);
            ps.executeUpdate();
        } finally {
            close(ps);
            close(con);
        }
    }

    public Student selectStudent(Long StudentId) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        getConnection();
        try {
            ps = con.prepareStatement(SQL_SELECT_STUDENT);
            ps.setLong(1, StudentId);
            rs = ps.executeQuery();
            List results = getResults(rs);
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

    private List getResults(ResultSet rs) throws SQLException {
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
