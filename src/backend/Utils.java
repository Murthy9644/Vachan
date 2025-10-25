package backend;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Utils{

    // FXML Classpaths:
    private static final String loginfxml = "/resources/FXML/login-screen.fxml";
    private static final String signupfxml = "/resources/FXML/signup-screen.fxml";
    private static final String homefxml = "/resources/FXML/home-screen.fxml";
    private static final String chatsfxml = "/resources/FXML/chats-screen.fxml";

    // Page roots:
    private static Parent loginroot;
    private static Parent signuproot;
    private static Parent homeroot;
    private static Parent chatsroot;

    // Stylesheets:
    private static final String newmoon = "/resources/themes/newmoon.css";
    private static final String dawnlight = "/resources/themes/dawnlight.css";
    private static final String neovibe = "/resources/themes/neovibe.css";
    
    public static String setStyleSheet(String theme) throws Exception{
        String themename;
    
        switch (theme){
            case "newmoon": themename = newmoon;
            break;
    
            case "dawnlight": themename = dawnlight;
            break;
    
            case "neovibe": themename = neovibe;
            break;
    
            default: themename = neovibe;
        }

        return Utils.class.getResource(themename).toExternalForm();
    }

    public static Parent setRoot(String page) throws Exception{

        switch (page){
            case "login": return loginroot;

            case "signup": return signuproot;

            case "home": return homeroot;

            case "chats": return chatsroot;

            default: return null;
        }
    }
    
    public Utils(){
        try{
            loginroot = FXMLLoader.load(Utils.class.getResource(loginfxml));
            signuproot = FXMLLoader.load(Utils.class.getResource(signupfxml));
            homeroot = FXMLLoader.load(Utils.class.getResource(homefxml));
            chatsroot = FXMLLoader.load(Utils.class.getResource(chatsfxml));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}