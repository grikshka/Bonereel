/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import privatemoviecollection.be.User;
import privatemoviecollection.gui.model.MainModel;
import privatemoviecollection.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class LoginViewController implements Initializable {
    
    private UserModel userModel;

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    public LoginViewController()
    {
        userModel = UserModel.createInstance();
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createTextFieldListeners();
    }    
    
    private void createTextFieldListeners()
    {
        txtEmail.setOnKeyPressed(new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent key) 
                    {
                        if(key.getCode() == KeyCode.ENTER)
                        {
                            txtPassword.requestFocus();
                        }
                    }

                }
            );
        txtPassword.setOnKeyPressed(new EventHandler<KeyEvent>()
                {
                    @Override
                    public void handle(KeyEvent key) 
                    {
                        if(key.getCode() == KeyCode.ENTER)
                        {
                            btnLogin.fire();
                        }
                    }

                }
            );
    }

    @FXML
    private void clickCreateAccount(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/CreateUserView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Sign In");
        stage.setScene(new Scene(root));  
        stage.show();
    }

    @FXML
    private void clickLogin(ActionEvent event) throws IOException {
        User user = userModel.getUser(txtEmail.getText(), txtPassword.getText());
        if(user != null)
        {
            Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
            stage.hide();
            userModel.setUser(user);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/MainView.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            stage.setScene(new Scene(root));  
            stage.centerOnScreen();
            stage.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot log in");
            alert.setHeaderText(null);
            alert.setContentText("Invalid email or password");
            alert.show();
        }
    }
    
}
