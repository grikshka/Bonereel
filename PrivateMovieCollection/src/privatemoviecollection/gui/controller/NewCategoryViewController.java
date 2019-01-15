/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatemoviecollection.gui.controller;

import java.net.URL;
import java.util.EventObject;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import privatemoviecollection.be.Movie;
import privatemoviecollection.gui.exceptions.ModelException;
import privatemoviecollection.gui.model.CategoriesModel;
import privatemoviecollection.gui.util.WarningDisplayer;

/**
 * FXML Controller class
 *
 * @author Acer
 */
public class NewCategoryViewController implements Initializable {

    private CategoriesModel model;
    private WarningDisplayer warningDisplayer;
    
    @FXML
    private TextField txtName;
    
    public NewCategoryViewController()
    {
        warningDisplayer = new WarningDisplayer();
        try
        {
            model = CategoriesModel.createInstance();
        }
        catch(ModelException e)
        {
            Stage currentStage = (Stage) txtName.getScene().getWindow();
            warningDisplayer.displayError(currentStage, "Error", e.getMessage());
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void clickSave(ActionEvent event) 
    {
        try
        {
            model.createCategory(txtName.getText());
        }
        catch(ModelException e)
        {
            Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
            warningDisplayer.displayError(stage, "Error", e.getMessage());
        }
    }

    @FXML
    private void clickCancel(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickClose(ActionEvent event) {
        Stage stage = (Stage)((Node)((EventObject) event).getSource()).getScene().getWindow();
        stage.close();
    }
    
}
