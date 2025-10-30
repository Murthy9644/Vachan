package backend;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerCommunication{
    private static Socket me;
    private static Scanner input;
    private static PrintWriter output;

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

    public static String sendFrndReq(String frndusername){
        output.println(frndusername);
        output.println("SENDFRIENDREQ");
        String resp = input.nextLine();

        return resp;
    }

    public static void fireClientConnection() throws Exception{
        me = new Socket(
            "172.18.202.106", 
            5500
        );
        input = new Scanner(me.getInputStream());
        output = new PrintWriter(me.getOutputStream(), true);
    }

    public static void stopClientConnextion(){

        try{
            output.println("EXIT");
            
            input.close();
            output.close();
            me.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
