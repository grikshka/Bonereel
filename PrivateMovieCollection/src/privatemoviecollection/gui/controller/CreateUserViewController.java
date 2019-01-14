/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.be.User;
import privatemoviecollection.gui.model.UserModel;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class CreateUserViewController implements Initializable {

    private UserModel model;
    
    public CreateUserViewController()
    {
        model = UserModel.createInstance();
    }
    
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtRepeatPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickCreate(ActionEvent event) {
        if(!isEmailCorrect(txtEmail.getText()))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Cannot create user");
            alert.setHeaderText(null);
            alert.setContentText("Address email is invalid");
            alert.show();
        }       
        else
        {
            List<String> faults = getPasswordFaults(txtPassword.getText());
            if(!faults.isEmpty())
            {
                String errorText = "";
                for(String s : faults)
                {
                    errorText += "- " + s + "\n";
                }
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot create user");
                alert.setHeaderText(null);
                alert.setContentText(errorText);
                alert.show();
            }
            else if(!txtPassword.getText().equals(txtRepeatPassword.getText()))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Cannot create user");
                alert.setHeaderText(null);
                alert.setContentText("Your passwords are not the same");
                alert.show();
            }
            else
            {
                User user = model.createUser(txtEmail.getText(), txtPassword.getText());
                if(user == null)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot create user");
                    alert.setHeaderText(null);
                    alert.setContentText("This adres e-mail is already taken");
                    alert.show();
                }
                else
                {
                    Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
                    stage.close();
                }
            }
        }
    }
    
    private boolean isEmailCorrect(String email)
    {
        if(email.length()<5 || !email.contains("@") || !email.contains("."))
        {
            return false;
        }
        return true;
    }
    
    private List<String> getPasswordFaults(String password)
    {
        List<String> faults = new ArrayList();
        if(password.length()<7)
        {
            faults.add("Your password need o have at least 8 characters");
        }
        if(password.equals(password.toLowerCase()) || password.equals(password.toUpperCase()))
        {
            faults.add("Your password need to contain small and big letters");
        }
        if(!password.matches(".*\\d+.*"))
        {
            faults.add("Your password need to contain numbers");
        }
        if(password.matches("[a-zA-Z0-9]*"))
        {
            faults.add("Your password need to contain special characters");
        }
        return faults;

    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
}
