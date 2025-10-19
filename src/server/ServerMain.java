package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

class ClientConnection extends Thread{
    Scanner input;
    PrintWriter output;
    Long id;
    Socket client;
    ClientData clientdata;

    @Override
    public void run(){
        System.out.println("Client connected: " + id);

        String username = input.nextLine();
        String pswd = input.nextLine();
        clientdata = new ClientData(id, username, pswd);
                
        output.println("Connected to the server successfully.");

        input.close();
        output.close();
    }

    ClientConnection(Scanner input, PrintWriter output, Long id, Socket client){
        this.input = input;
        this.output = output;
        this.id = id;
        this.client = client;
    }
}

public class ServerMain{
    static HashMap<Long, Socket> clients = new HashMap<>();

    public static void main(String args[]){

        try (ServerSocket server = new ServerSocket(5500);){
            System.out.println("Server started at port: 5500.");

            while (true){
                System.out.println("Waiting for client to connect...");
                Socket client = server.accept();

                Scanner input = new Scanner(client.getInputStream());
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);

                Long id = input.nextLong(); input.nextLine();
                clients.put(id, client);
                
                ClientConnection connection = new ClientConnection(input, output, id, client);
                connection.start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
