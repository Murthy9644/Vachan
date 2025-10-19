// import java.io.IOException;
// import java.io.PrintWriter;
// import java.net.Socket;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.Scanner;

// import javafx.application.Application;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;

// public class Main extends Application{
//     static Parent root;

//     @Override
//     public void start(Stage primarystage) throws Exception{
//         root = FXMLLoader.load(getClass().getResource("/resources/FXML/login-screen.fxml"));
//         primarystage.setScene(new Scene(root));
//         primarystage.show();
//     }

//     static Long idGenerator(){
//         LocalDateTime now = LocalDateTime.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmssSSSSSSSSS");
//         Long id = Long.parseLong(now.format(formatter));

//         return id;
//     }

//     public static void main(String args[]){
//         Scanner consoleinput = new Scanner(System.in);
//         launch(args);
        
//         try (Socket me = new Socket("localhost", 5500);){
//             Scanner input = new Scanner(me.getInputStream());
//             PrintWriter output = new PrintWriter(me.getOutputStream(), true);

//             output.println(idGenerator());
//             System.out.println("Enter your user name:");
//             output.println(consoleinput.nextLine());
//             System.out.println("Create a strong password:");
//             output.println(consoleinput.nextLine());

//             String connectionstatus = input.nextLine();
//             System.out.println(connectionstatus);

//             input.close();
//             output.close();
//         }
//         catch (IOException e){
//             e.printStackTrace();
//         }

//         consoleinput.close();
//     }
// }

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import backend.Utils;

public class Main extends Application{
    
    // Global things of this class:
    static Parent root;
    private static double screenwidth, screenheight;

    private static void setScreenDim(){
        Rectangle2D screenbounds = Screen.getPrimary().getVisualBounds();
        screenwidth = screenbounds.getWidth() * .7;
        screenheight = screenbounds.getHeight() * .8;
    }

    @Override
    public void start(Stage primarystage) throws Exception{
        root = Utils.setRoot("signup");
        Scene scene =  new Scene(root, screenwidth, screenheight);
        scene.getStylesheets().add(Utils.setStyleSheet("newmoon"));
        primarystage.setScene(scene);
        primarystage.show();
    }

    public static void main(String args[]){
        new Utils();
        setScreenDim();
        launch(args);
    }
}