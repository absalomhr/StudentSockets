package escuelasockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author Absalom Herrera
 */
public class SchoolClient {

    private Socket so;
    private String host;
    private int port;
    private ObjectOutputStream oosToServer;
    private DataOutputStream dosToServer;
    private DataOutputStream dosToFile;
    private ObjectInputStream oisFromServer;
    private DataInputStream disFromServer;
    private DataInputStream disFromFile;
    private String clientRoute;
    

    public SchoolClient() {
        host = "127.0.0.1";
        port = 9999;
        clientRoute = "C:\\Users\\elpat\\Documents\\lul";
    }

    public void sendStudent (Student s){
        try{
            so = new Socket(host, port);
            oosToServer = new ObjectOutputStream(so.getOutputStream());
            Option op = new Option(0);
            oosToServer.writeObject(op);
            oosToServer.writeObject(s);
            Socket so1 = new Socket (host, port+1);
            dosToServer = new DataOutputStream(so1.getOutputStream());
            File f = new File (s.getStudentPhotoPath());
            dosToServer.writeLong(f.length());
            dosToServer.writeUTF(f.getName());
            System.out.println("SENDING IMAGE FILE : " + f.getName());
            long sent = 0;
            int percent = 0, n = 0;
            disFromFile = new DataInputStream(new FileInputStream(f.getAbsolutePath()));
            while (sent < f.length()) {
                byte[] b = new byte[1500];
                n = disFromFile.read(b);
                dosToServer.write(b, 0, n);
                dosToServer.flush();
                sent += n;
                percent = (int) ((sent * 100) / f.length());
                System.out.print("\rSENT: " + percent + " %");
            }
            disFromFile.close();
            dosToServer.close();
            so1.close();
            oosToServer.close();
            so.close();
        }catch (Exception e){
            System.err.println("SENDING STUDENT ERROR\n");
            e.printStackTrace();
        }
    }
    
    public Object Login (Student s){
        try {
            so = new Socket(host, port);
            oosToServer = new ObjectOutputStream(so.getOutputStream());
            Option op = new Option(1);
            oosToServer.writeObject(op);
            oosToServer.writeObject(s);
            oisFromServer = new ObjectInputStream(so.getInputStream());
            Option res = (Option) oisFromServer.readObject();
            
            if (res.getOption() == 0){ // Student found
                s = (Student) oisFromServer.readObject();
                Socket so1 = new Socket(host, port + 1);
                disFromServer = new DataInputStream(so1.getInputStream());
                Long size = disFromServer.readLong();
                String name = disFromServer.readUTF();
                dosToFile = new DataOutputStream(new FileOutputStream(clientRoute + "\\" + name));
                s.setStudentPhotoPath(clientRoute + "\\" + name);
                long r = 0;
                int n = 0, percent = 0;
                while (r < size) {
                    byte[] b = new byte[1500];
                    n = disFromServer.read(b);
                    dosToFile.write(b, 0, n);
                    dosToFile.flush();
                    r += n;
                    percent = (int) ((r * 100) / size);
                    System.out.print("\rRECEIVING: " + percent + "%");
                }
                dosToFile.close();
                disFromServer.close();
                so1.close();
                oosToServer.close();
                oisFromServer.close();
                so.close();
                return s;
            }else if (res.getOption() == 1){ // Professor found
                Professor p = (Professor) oisFromServer.readObject();
                return p;
            }else {
                return null;
            }
        }catch (Exception e){
            System.err.println("LOGIN CL ERROR");
            e.printStackTrace();
        }
        return null;
    }
    
    public List getAllScheludes (){
        try {
            so = new Socket(host, port);
            oosToServer = new ObjectOutputStream(so.getOutputStream());
            Option op = new Option(2);
            oosToServer.writeObject(op);
            oisFromServer = new ObjectInputStream(so.getInputStream());
            List l = null;
            l = (List) oisFromServer.readObject();
            if (l != null){
                /*for (int i = 0; i < l.size(); i++) {
                    Schelude sche = (Schelude) l.get(i);
                    System.out.println(sche.getIdSchelude());
                }*/
                return l;
            } else {
                return null;
            }
        }catch (Exception e){
            System.err.println("CLIENT GET SCHELUDES ERROR");
            e.printStackTrace();
        }
        return null;
    }
    
    public int enrollStudent (Student st, Schelude sch){
        try{
            so = new Socket(host, port);
            oosToServer = new ObjectOutputStream(so.getOutputStream());
            Option op = new Option(3);
            oosToServer.writeObject(op);
            oosToServer.writeObject(st);
            oosToServer.writeObject(sch);
            oisFromServer = new ObjectInputStream(so.getInputStream());
            op = (Option) oisFromServer.readObject();
            oosToServer.close();
            so.close();
            return op.getOption();
        }catch(Exception e){
            System.err.println("ENROLL CLIENT ERROR");
            e.printStackTrace();
        }
        return -1;
    }
}
