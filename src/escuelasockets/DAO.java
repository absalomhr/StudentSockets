package escuelasockets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Absalom Herrera
 */
public class DAO {

    private Connection con = null;

    private static final String SQL_INSERT_STUDENT
            = "insert into student (StudentId, Name, LastName, Password, StudentPhoto) "
            + "VALUES (?, ?, ?, ?, ?)";

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

    private void getConnection() {
        String user = "root";
        String pwd = "absalom94";
        String url = "jdbc:mysql://localhost:3306/School?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
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
