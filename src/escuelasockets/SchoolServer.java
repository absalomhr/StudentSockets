package escuelasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFileChooser;

/**
 *
 * @author Absalom Herrera
 */
public class SchoolServer {
    
    private ServerSocket s;
    private Socket cl;
    private JFileChooser jfc; // Route where files are going to be stored on the server side
    private static String serverRoute;
    private DataOutputStream dosToFile;
    private DataOutputStream dosToCl;
    private DataInputStream disFromCl;
    private DataInputStream disFromFile;
    private int clientRequest; // 0 = upload student
    int port;
    
    public SchoolServer() {
        // Choosing server directory (for testing test)
        File f = null;
        jfc = new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if (jfc.isMultiSelectionEnabled()) {
            jfc.setMultiSelectionEnabled(false);
        }
        int r = jfc.showOpenDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            f = jfc.getSelectedFile();
        }
        
        serverRoute = f.getAbsolutePath();
        clientRequest = 0;
        port = 9999;
        
        try {
            s = new ServerSocket(port);
            s.setReuseAddress(true);
        } catch (Exception e) {
            System.err.println("CONSTRUCTOR ERROR:\n");
            e.printStackTrace();
        }
    }
    
    public void connect() {
        try {
            for (;;) {
                System.out.println("\nSERVER ON, WAITING FOR CLIENT CONNECTION");
                cl = s.accept();
                System.out.println("CLIENT FROM: " + cl.getInetAddress() + " PORT: " + cl.getPort());
                disFromCl = new DataInputStream(cl.getInputStream());
                // 0 = upload student
                clientRequest = disFromCl.readInt();
                if (clientRequest == 0) {
                    receiveStudent();
                } else if (clientRequest == 1) {
                    dosToCl = new DataOutputStream(cl.getOutputStream());
                    login();
                }
            }
        } catch (Exception e) {
            System.err.println("CONNECT ERROR:");
            e.printStackTrace();
        }
    }
    
    public void receiveStudent() {
        long fileSize;
        String fileName;
        String fileExt;
        String studentId = "";
        String studentName = "";
        String studentLastName = "";
        String studentPass = "";
        String studentPhotoPath = "";
        try {

            // Reading image data: size, name, file extention, student id, student name, last name, pass
            fileSize = disFromCl.readLong();
            fileName = disFromCl.readUTF();
            fileExt = disFromCl.readUTF();
            studentId = disFromCl.readUTF();
            studentName = disFromCl.readUTF();
            studentLastName = disFromCl.readUTF();
            studentPass = disFromCl.readUTF();
            
            System.out.println("RECEIVING IMAGE: " + fileName);
            studentPhotoPath = serverRoute + "\\" + studentId + "." + fileExt;
            dosToFile = new DataOutputStream((new FileOutputStream(studentPhotoPath)));
            
            long r = 0;
            int n = 0, percent = 0;
            while (r < fileSize) {
                byte[] b = new byte[1500];
                n = disFromCl.read(b);
                dosToFile.write(b, 0, n);
                dosToFile.flush();
                r += n;
                percent = (int) ((r * 100) / fileSize);
                System.out.print("\rRECEIVING: " + percent + "%");
            }
            dosToFile.close();
            disFromCl.close();
            
            System.out.println("\n\nSTUDENT RECEIVED: ");
            System.out.println("ID: " + studentId);
            System.out.println("PASS: " + studentPass);
            System.out.println("NAME: " + studentName);
            System.out.println("LAST NAME: " + studentLastName);
            System.out.println("PATH: " + studentPhotoPath);
            DAO dao = new DAO();
            dao.createStudent(Long.parseLong(studentId), studentName, studentLastName, studentPass, studentPhotoPath);
            
        } catch (Exception e) {
            System.err.println("RECEIVE STUDENT ERROR:");
            e.printStackTrace();
        }
        
    }
    
    private void login() {
        try {
            String user, pass;
            user = disFromCl.readUTF();
            pass = disFromCl.readUTF();
            DAO dao = new DAO();
            Student s = dao.selectStudent(Long.parseLong(user));
            if (s != null && s.getPass().equals(pass)) {
                System.out.println("No es null");
                dosToCl.writeLong(s.getStudentId());
                System.out.println("estudiante de base: "+ s.toString());
                File f = new File(s.getStudentPhotoPath());
                disFromFile = new DataInputStream(new FileInputStream(s.getStudentPhotoPath()));
                Long fileSize = f.length();
                String name = f.getName();
                dosToCl.writeUTF(s.getName());
                dosToCl.writeLong(fileSize);
                dosToCl.writeUTF(name);
                
                long sent = 0;
                int percent = 0, n = 0;
                disFromFile = new DataInputStream(new FileInputStream(f.getAbsolutePath()));
                while (sent < fileSize) {
                    byte[] b = new byte[1500];
                    n = disFromFile.read(b);
                    dosToCl.write(b, 0, n);
                    dosToCl.flush();
                    sent += n;
                    percent = (int) ((sent * 100) / fileSize);
                    System.out.print("\rSENT: " + percent + " %");
                }
                disFromFile.close();
                
            } else {
                dosToCl.writeLong(0);
            }
        } catch (Exception e) {
            System.err.println("LOGIN SERVER ERROR");
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) {
        SchoolServer s = new SchoolServer();
        s.connect();
    }
    
}
