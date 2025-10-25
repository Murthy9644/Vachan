package controllers;

import backend.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChatsPageController{
    
    // Global things
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button homeBtn, chatsBtn, contactsBtn, newchatBtn;

    @FXML
    private Pane aContact;

    @FXML
    private void clicked(ActionEvent event){}

    @FXML
    private void home(ActionEvent event) throws Exception{
        root = Utils.setRoot("home");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void chats(ActionEvent event) throws Exception{
        root = Utils.setRoot("chats");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }
}
