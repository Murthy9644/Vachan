package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import backend.Processes;
import backend.Utils;

public class LoginPageController{

    // Global things
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private TextField username;

    @FXML
    private PasswordField pswd;

    @FXML
    private Label validationlbl;

    @FXML
    private Button donthaveaccbtn, loginbtn;

    @FXML
    private void switchToSignup(ActionEvent event) throws Exception{
        root = Utils.setRoot("signup");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void login(ActionEvent event) throws Exception{
        
        if (username.getText().equals("")){
            username.setStyle(
                "-fx-border-color: transparent transparent rgb(255, 82, 82) transparent;"
            );
            validationlbl.setText("Username can't be empty");

            return;
        } else{
            username.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            validationlbl.setText("");
        }

        if (pswd.getText().equals("")){
            pswd.setStyle(
                "-fx-border-color: transparent transparent rgb(255, 82, 82) transparent;"
            );
            validationlbl.setText("Password can't be empty");

            return;
        } else{
            pswd.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            validationlbl.setText("");
        }

        Boolean ack = Processes.authenticateUser(username.getText(), pswd.getText());
        
        if (ack == null){
            username.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            pswd.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            validationlbl.setText("Account doesn't exist");
        }
                
        else if (ack == true){
            root = Utils.setRoot("home");
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = ((Node) (event.getSource())).getScene();
            scene.setRoot(root);
            stage.setScene(scene);
            stage.show();
        }

        else{
            username.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            pswd.setStyle(
                "-fx-border-color: transparent transparent rgb(66, 133, 244) transparent;"
            );
            validationlbl.setText("ID or password is incorrect");
        }
    }
}
