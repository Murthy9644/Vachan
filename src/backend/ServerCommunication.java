package backend;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunication{
    private static Socket me;
    private static Scanner input;
    private static ObjectInputStream objectinput;
    private static PrintWriter output;
    private static ObjectOutputStream objectoutput;

    public static Boolean sendLoginInfo(Object data[]){
        output.println((String) data[0]);   // username
        output.println("STOREREGISTERINFO");    // Request
        String ack = input.nextLine();

        if (ack.equals("~ACC-EXISTS")) return false;

        output.println((String) data[1]);   // Password

        return true;
    }

    public static String getLoginInfo(String username){
        output.println(username);
        output.println("GETLOGININFO");
        String pswd = input.nextLine();

        if (pswd.equals("~NO-ACC-FOUND")) return null;

        return pswd;
    }

    public static String[] getUsernames(String searchstr){
        output.println("GETUSERNAMES");
        output.println(searchstr);

        try{
            String names[] = (String[]) objectinput.readObject();
            return names;
        }
        catch (Exception e){e.printStackTrace();
            System.out.println("Got ntng");
            return null;
        }
    }

    public static String sendFrndReq(String frndusername){
        output.println("SENDFRIENDREQ");
        output.println(frndusername);
        String resp = input.nextLine();

        return resp;
    }

    public static void fireClientConnection() throws Exception{
        me = new Socket(
            "localhost", 
            5500
        );

        input = new Scanner(me.getInputStream());
        output = new PrintWriter(me.getOutputStream(), true);
        objectoutput = new ObjectOutputStream(me.getOutputStream());
        objectinput = new ObjectInputStream(me.getInputStream());
    }

    public static void sendUserName(String username){
        output.println(username);
    }

    public static void stopClientConnextion(){

        try{
            output.println("EXIT");
            
            input.close();
            output.close();
            me.close();
            objectinput.close();
            objectoutput.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
