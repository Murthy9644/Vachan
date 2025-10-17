import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main{

    static Long idGenerator(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssSSSSSSSSS");
        Long id = Long.parseLong(now.format(formatter));

        return id;
    }

    public static void main(String args[]){
        Scanner consoleinput = new Scanner(System.in);
        
        try (Socket me = new Socket("localhost", 5500);){
            Scanner input = new Scanner(me.getInputStream());
            PrintWriter output = new PrintWriter(me.getOutputStream(), true);

            output.println(idGenerator());
            System.out.println("Enter your user name:");
            output.println(consoleinput.nextLine());
            System.out.println("Create a strong password:");
            output.println(consoleinput.nextLine());

            String connectionstatus = input.nextLine();
            System.out.println(connectionstatus);

            input.close();
            output.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }

        consoleinput.close();
    }
}