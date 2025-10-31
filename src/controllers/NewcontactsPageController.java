package controllers;

import backend.ServerCommunication;
import backend.Utils;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class NewcontactsPageController implements Initializable{
    
    // Global things
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button homeBtn, chatsBtn, contactsBtn, newchatBtn;

    @FXML
    private Button notifications;

    @FXML
    private TextField contactsearchbar;

    @FXML
    private VBox searchres_cont, main_cont;

    @FXML
    private ScrollPane searchres_cont_wrapper, pymk_cont;

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

    @FXML
    private void contacts(ActionEvent event) throws Exception{
        root = Utils.setRoot("contacts");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void newContacts(ActionEvent event) throws Exception{
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void notifications(ActionEvent event) throws Exception{
        NotificationsController.prevscreen = "newcontact";
        root = Utils.setRoot("notifications");
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = ((Node) (event.getSource())).getScene();
        scene.setRoot(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(java.net.URL location, java.util.ResourceBundle resources){
        ObservableList<Node> main_cont_children = main_cont.getChildren();
        int searchres_index = main_cont_children.indexOf(searchres_cont_wrapper);
        main_cont.getChildren().remove(searchres_cont_wrapper);

        this.contactsearchbar.textProperty().addListener((_, _, newText) -> {
            searchres_cont.getChildren().clear();
            String[] searchres;

            if (! newText.equals("")){
                
                try{
                    main_cont.getChildren().add(searchres_index, searchres_cont_wrapper);
                    main_cont.getChildren().remove(pymk_cont);
                }
                catch (Exception e){e.printStackTrace();}
                searchres = ServerCommunication.getUsernames(newText);
            }
            else{
                try{
                    main_cont.getChildren().add(pymk_cont);
                    main_cont.getChildren().remove(searchres_cont_wrapper);
                }
                catch (Exception e){e.printStackTrace();}
                return;
            }

            for (String res: searchres){

                try{
                    FXMLLoader loader = new FXMLLoader(
                        NewcontactsPageController.class.getResource("/resources/FXML/utils.fxml")
                    );
                    VBox cont = loader.load();
                    HBox searchrescontact = (HBox) cont.getChildren().get(0);
                    Label searchres_name = (Label) searchrescontact.getChildren().get(1);
                    searchres_name.setText(res);
                    Button searchres_addfrnd = (Button) searchrescontact.getChildren().get(2);
                    searchres_addfrnd.getStyleClass().add("contactsearchres_addfrnd");
                    searchres_addfrnd.setId(res);
                    searchres_cont.getChildren().add(searchrescontact);
                }
                catch (Exception e){e.printStackTrace();}
            }
        });
    }
}
