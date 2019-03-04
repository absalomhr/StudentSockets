package escuelasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
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
    private ObjectOutputStream oosToCl;
    private ObjectInputStream oisFromCl;
    private DataInputStream disFromFile;
    private DataInputStream disFromCl;
    
    private int clientRequest; // 0 = upload student
    int port;
    
    public SchoolServer() {
        serverRoute = "C:\\Users\\elpat\\Documents\\lolaz";
        clientRequest = -1;
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
                oisFromCl = new ObjectInputStream (cl.getInputStream());
                // 0 = upload student, 1 = login, 2 = get scheludes
                Option op = (Option) oisFromCl.readObject();
                clientRequest = op.getOption();
                if (clientRequest == 0) {
                    receiveStudent();
                } else if (clientRequest == 1) {
                    login();
                } else if (clientRequest == 2){
                    getAllScheludes();
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
        try {
            Student st = (Student) oisFromCl.readObject();
            ServerSocket s1 = new ServerSocket(port+1);
            Socket cl2 = s1.accept();
            disFromCl = new DataInputStream(cl2.getInputStream());
            // Reading image data: size, name, file extention, student id, student name, last name, pass
            fileSize = disFromCl.readLong();
            fileName = disFromCl.readUTF();
            
            System.out.println("RECEIVING IMAGE: " + fileName);
            String studentPhotoPath = serverRoute + "\\" + fileName;
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
            cl2.close();
            s1.close();;
            st.setStudentPhotoPath(studentPhotoPath);
            System.out.println("\n\nSTUDENT RECEIVED: ");
            System.out.println("ID: " + st.getStudentId());
            System.out.println("PASS: " + st.getPass());
            System.out.println("NAME: " + st.getName());
            System.out.println("LAST NAME: " + st.getLastName());
            System.out.println("PATH: " + st.getStudentPhotoPath()); 
            DAO dao = new DAO();
            dao.createStudent(st);
        } catch (Exception e) {
            System.err.println("RECEIVE STUDENT ERROR\n");
            e.printStackTrace();
        }
        
    }
    
    private void login() {
        try {
            Student st = (Student) oisFromCl.readObject();
            Long user;
            String pass;
            user = st.getStudentId();
            pass = st.getPass();
            DAO dao = new DAO();
            Student st2 = dao.selectStudent(user);
            oosToCl = new ObjectOutputStream(cl.getOutputStream());
            if (st2 != null && st2.getPass().equals(pass)) {
                oosToCl.writeObject(new Option(0)); // 0 Student, 1 Professor, 2 NotFound
                oosToCl.writeObject(st2);
                
                File f = new File(st2.getStudentPhotoPath());
                disFromFile = new DataInputStream(new FileInputStream(st2.getStudentPhotoPath()));
                Long fileSize = f.length();
                String name = f.getName();
                ServerSocket s2 = new ServerSocket(port+1);
                Socket cl2 = s2.accept();
                dosToCl = new DataOutputStream(cl2.getOutputStream());
                
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
                dosToCl.close();
                cl2.close();
                s2.close();
            } else if (st2 == null) {
                Professor p = dao.selectProfessor(user);
                if (p != null && p.getPass().equals(pass)){
                    oosToCl.writeObject(new Option(1)); // 0 Student, 1 Professor, 2 NotFound
                    oosToCl.writeObject(p);
                    oosToCl.close();
                } else {
                    oosToCl.writeObject(new Option(2)); // 0 Student, 1 Professor, 2 NotFound
                }
            }
        } catch (Exception e) {
            System.err.println("LOGIN SERVER ERROR");
            e.printStackTrace();
        }
    }
    
    private void getAllScheludes(){
        List l = null;
        DAO dao = new DAO();
        try{
            oosToCl = new ObjectOutputStream(cl.getOutputStream());
            l = dao.selectScheludeAll();
            if (l != null){
                oosToCl.writeObject(l);
                /*for (int i = 0; i < l.size(); i++) {
                    Schelude sche = (Schelude) l.get(i);
                    System.out.println(sche.getIdSchelude());
                }*/
            }
            else {
                oosToCl.writeObject(null);
            }
            oosToCl.close();
        }catch(Exception e){
            System.err.println("SCHELUDE SELECTION ERROR");
            e.printStackTrace();
        }
        
    }
    
    public static void main(String args[]) {
        SchoolServer s = new SchoolServer();
        s.connect();
    }
    
}
