package escuelasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Absalom Herrera
 */
public class SchoolClient {

    private Socket s;
    private String host;
    private int port;
    private DataOutputStream dosToServer;
    private DataInputStream disFromFile;
    private DataInputStream disFromServer;

    public SchoolClient() {
        host = "127.0.0.1";
        port = 9999;
    }

    public void sendStudent(File f, String studentId, String studentName, String studentLastName, String studentPass) {
        long fileSize = f.length();
        String fileName = f.getName();
        String fileExt = FilenameUtils.getExtension(fileName);
        
        try {
            s = new Socket(host, port);
            dosToServer = new DataOutputStream(s.getOutputStream());
            // Sending image data (0): size, name, file extention, student id, student name, last name, pass
            dosToServer.writeInt(0);
            dosToServer.flush();
            dosToServer.writeLong(fileSize);
            dosToServer.flush();
            dosToServer.writeUTF(fileName);
            dosToServer.flush();
            dosToServer.writeUTF(fileExt);
            dosToServer.flush();
            dosToServer.writeUTF(studentId);
            dosToServer.flush();            
            dosToServer.writeUTF(studentName);
            dosToServer.flush();
            dosToServer.writeUTF(studentLastName);
            dosToServer.flush();
            dosToServer.writeUTF(studentPass);
            dosToServer.flush();
            // Sending the image to server
            System.out.println("SENDING IMAGE FILE : " + fileName);
            long sent = 0;
            int percent = 0, n = 0;
            disFromFile = new DataInputStream(new FileInputStream(f.getAbsolutePath()));
            while (sent < fileSize) {
                byte[] b = new byte[1500];
                n = disFromFile.read(b);
                dosToServer.write(b, 0, n);
                dosToServer.flush();
                sent += n;
                percent = (int) ((sent * 100) / fileSize);
                System.out.print("\rSENT: " + percent + " %");
            }
            disFromFile.close();
            s.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public String Login (String studentId, String pass){
        try {
            String res;
            s = new Socket(host, port);
            dosToServer = new DataOutputStream(s.getOutputStream());
            dosToServer.writeInt(1);
            dosToServer.writeUTF(studentId);
            dosToServer.writeUTF(pass);
            disFromServer = new DataInputStream(s.getInputStream());
            return disFromServer.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
