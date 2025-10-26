package controllers;

import backend.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotificationsController{

    public static String prevscreen;
    
    // Global things
    // Global things
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button notifications, backBtn;

    @FXML
    private void notifications(ActionEvent event) throws Exception{
        root = Utils.setRoot("notifications");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void close(ActionEvent event) throws Exception{
        root = Utils.setRoot(prevscreen);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }
}
