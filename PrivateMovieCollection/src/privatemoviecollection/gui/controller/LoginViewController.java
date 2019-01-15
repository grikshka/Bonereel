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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import privatemoviecollection.be.User;
import privatemoviecollection.gui.model.MainModel;
import privatemoviecollection.gui.model.UserModel;
import privatemoviecollection.gui.util.WarningDisplayer;
import privatemoviecollection.gui.util.WindowDecorator;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class LoginViewController implements Initializable {
    
    private UserModel userModel;
    private double xOffset;
    private double yOffset;
    private WarningDisplayer warningDisplayer;

    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;

    public LoginViewController()
    {
        userModel = UserModel.createInstance();
        warningDisplayer = new WarningDisplayer();
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
        Stage currentStage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        WindowDecorator.fadeOutStage(currentStage);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/privatemoviecollection/gui/view/CreateUserView.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("Sign In");
        stage.setScene(new Scene(root));  
        stage.showAndWait();
        WindowDecorator.fadeInStage(currentStage);
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
            WindowDecorator.showStage(stage);
        }
        else
        {
            Stage currentStage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
            warningDisplayer.displayError(currentStage, "Cannot log in", "Invalid email or password");
        }
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void clickMinimalize(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    private void clickMouseDragged(MouseEvent event) 
    {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() + xOffset);
        stage.setY(event.getScreenY() + yOffset);
    }

    @FXML
    private void clickMousePressed(MouseEvent event) 
    {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        xOffset = stage.getX() - event.getScreenX();
        yOffset = stage.getY() - event.getScreenY();
    }
    
}
