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
    private static final String contactsfxml = "/resources/FXML/contacts-screen.fxml";
    private static final String newcontactfxml = "/resources/FXML/newcontact-screen.fxml";
    private static final String notificationsfxml = "/resources/FXML/notifications.fxml";

    // Page roots:
    private static Parent loginroot;
    private static Parent signuproot;
    private static Parent homeroot;
    private static Parent chatsroot;
    private static Parent contactsroot;
    private static Parent newcontactroot;
    private static Parent notificationsroot;

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

            case "contacts": return contactsroot;

            case "newcontact": return newcontactroot;

            case "notifications": return notificationsroot;

            default: return null;
        }
    }
    
    public Utils(){
        try{
            loginroot = FXMLLoader.load(Utils.class.getResource(loginfxml));
            signuproot = FXMLLoader.load(Utils.class.getResource(signupfxml));
            homeroot = FXMLLoader.load(Utils.class.getResource(homefxml));
            chatsroot = FXMLLoader.load(Utils.class.getResource(chatsfxml));
            contactsroot = FXMLLoader.load(Utils.class.getResource(contactsfxml));
            newcontactroot = FXMLLoader.load(Utils.class.getResource(newcontactfxml));
            notificationsroot = FXMLLoader.load(Utils.class.getResource(notificationsfxml));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}