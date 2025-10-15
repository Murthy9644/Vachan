import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args){

        try {
            Socket socket = new Socket("localhost", 5000);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner input = new Scanner(System.in);

            // out.println("Hello Server!");

            String text = "";

            while (! text.equals("exit")){
                System.err.print("> "); text = input.nextLine();
                out.println(text);
                out.println("hold");
                System.out.println();
            }

            String response = in.readLine();
            System.out.println("Server says: " + response);

            socket.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
