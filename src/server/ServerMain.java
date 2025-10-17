package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerError;
import java.util.HashMap;
import java.util.Scanner;

class ClientConnection extends Thread{
    Socket client;
    ClientData clientdata;
    HashMap<Long, Socket> clients = new HashMap<>();

    @Override
    public void run(){
        System.out.println("client connected.");

        try{
            Scanner input = new Scanner(client.getInputStream());
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);

            Long id = input.nextLong(); input.nextLine();
            System.out.println("id: " + id);
            String name = input.nextLine();
            System.out.println("username: " + name);
            String pswd = input.nextLine();
            System.out.println("password: " + pswd);
            clientdata = new ClientData(id, name, pswd);
            clients.put(id, client);
                
            System.out.println("Client connected: " + id);
            output.println("Connected to the server successfully.");

            input.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    ClientConnection(Socket client){
        this.client = client;
    }
}

public class ServerMain{

    public static void main(String args[]){

        try (ServerSocket server = new ServerSocket(5500);){
            System.out.println("Server started at port: 5500.");

            while (true){
                System.out.println("Waiting for clients to connect...");
                Socket client = server.accept();
                ClientConnection connection = new ClientConnection(client);
                connection.start();
            }
        }
        catch (ServerError e){
            //
        }
        catch (IOException e){
            //
        }
    }
}
