import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import backend.FileIO;
import backend.ServerCommunication;
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
        Scene scene =  new Scene(root, screenwidth, screenheight);
        scene.getStylesheets().add(Utils.setStyleSheet("neovibe"));
        primarystage.setScene(scene);
        primarystage.setTitle("Vachan");
        primarystage.getIcons().add(new Image(getClass().getResourceAsStream("resources/images/vachan-logo.png")));
        primarystage.show();

        primarystage.setOnCloseRequest(_ -> {
            ServerCommunication.stopClientConnextion();
        });
    }

    public static void main(String args[]) throws Exception{
        new Utils();
        ServerCommunication.fireClientConnection();
        FileIO files = new FileIO();
        
        if (! files.chkUserData()) root = Utils.setRoot("signup");
        else{
            root = Utils.setRoot("home");
            String userdata[] = files.readUserData();
            // Send username to server indicating ready to send requests
            ServerCommunication.sendUserName(userdata[0]);
        }
        // root = Utils.setRoot("signup");
        
        setScreenDim();
        launch(args);
    }
}