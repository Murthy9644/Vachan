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
        output.println((Long) data[0]);
        output.println((String) data[1]);
        output.println((String) data[2]);

        return true;
    }

    public static String getLoginInfo(long id){
        output.println("GETLOGININFO");
        output.println((long) id);

        return input.nextLine();
    }

    public static void fireClientConnection() throws Exception{
        me = new Socket("localhost", 5500);
        input = new Scanner(me.getInputStream());
        output = new PrintWriter(me.getOutputStream(), true);
    }

    public static void stopClientConnextion(){

        try{
            output.println("exit");
            
            input.close();
            output.close();
            me.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
